package de.cas.dcs.sync.adventofcode2021.day14;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day14 {
  private static final String PUZZLE_RESOURCE_NAME = "day14.file";

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(Day14.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    List<String> lines = Files.lines(path).collect(Collectors.toList());
    partOne(lines);
    partTwo(lines);
  }

  public static void partOne(List<String> lines) {
    System.out.println("partOne: " + new Day14().execute(lines, false));
  }

  public static void partTwo(List<String> lines) {
    System.out.println("partTwo: " + new Day14().execute(lines, true));
  }

  public long execute(List<String> entries, boolean partTwo) {
    List<Template> templates = toTemplates(entries);

    int steps = partTwo ? 40 : 10;

    Map<Character, Long> counts = new HashMap<>();

    Template template = templates.get(0);
    List<Character> startCharacters = new ArrayList<>();
    for (Character c : template.start().toCharArray()) {
      startCharacters.add(c);
      Long count = counts.getOrDefault(c, 0L);
      counts.put(c, count + 1);
    }

    Map<Rule, Long> rulesToMatchingPairs = new HashMap<>();
    template.rules().forEach(r -> rulesToMatchingPairs.put(r, 0L));

    // init
    for (int j = 0; j < startCharacters.size(); j++) {
      if (j + 1 < startCharacters.size()) {
        char startChar = startCharacters.get(j);
        char endChar = startCharacters.get(j + 1);
        Optional<Rule> transformRuleOpt =
            getTransformRule(startChar, endChar, rulesToMatchingPairs.keySet());
        if (transformRuleOpt.isPresent()) {
          Rule rule = transformRuleOpt.get();
          rulesToMatchingPairs.put(rule, rulesToMatchingPairs.get(rule) + 1);
        }
      }
    }

    for (int i = 0; i < steps; i++) {
      Map<Rule, Long> newMatches = new HashMap<>();

      for (Rule rule : rulesToMatchingPairs.keySet()) {

        long currentRuleMatches = rulesToMatchingPairs.get(rule);

        Optional<Rule> newRuleMatchOne =
            getTransformRule(rule.start(), rule.add(), rulesToMatchingPairs.keySet());
        Optional<Rule> newRuleMatchTwo =
            getTransformRule(rule.add(), rule.end(), rulesToMatchingPairs.keySet());

        Long count = counts.getOrDefault(rule.add(), 0L);
        counts.put(rule.add(), count + currentRuleMatches);

        if (newRuleMatchOne.isPresent()) {
          Rule match = newRuleMatchOne.get();
          newMatches.put(match, newMatches.getOrDefault(match, 0L) + currentRuleMatches);
        }

        if (newRuleMatchTwo.isPresent()) {
          Rule match = newRuleMatchTwo.get();
          newMatches.put(match, newMatches.getOrDefault(match, 0L) + currentRuleMatches);
        }

        // remove matches for current rules as the pair has been ripped apart
        newMatches.put(rule, newMatches.getOrDefault(rule, 0L) - currentRuleMatches);
      }

      for (Rule rule : rulesToMatchingPairs.keySet()) {
        rulesToMatchingPairs.put(rule, rulesToMatchingPairs.get(rule) + newMatches.get(rule));
      }
    }

    long lowestCount = Long.MAX_VALUE;
    long highestCount = Long.MIN_VALUE;

    for (long count : counts.values()) {
      if (count > highestCount) {
        highestCount = count;
      }
      if (count < lowestCount) {
        lowestCount = count;
      }
    }

    return highestCount - lowestCount;
  }

  private Optional<Rule> getTransformRule(char startChar, char endChar, Set<Rule> rules) {
    return rules.stream().filter(r -> r.start() == startChar && r.end() == endChar).findFirst();
  }

  private List<Template> toTemplates(List<String> entries) {
    List<Template> templates = new ArrayList<>();
    Template currentTemplate = null;

    for (String line : entries) {
      if (isNewEntryStarting(line)) {
        currentTemplate = new Template(line, new ArrayList<>());
        templates.add(currentTemplate);
      } else if (!line.isBlank()) {
        currentTemplate.rules().add(toRule(line));
      }
    }

    return templates;
  }

  private Rule toRule(String line) {
    String[] split = line.split(" -> ");
    return new Rule(split[0].charAt(0), split[0].charAt(1), split[1].charAt(0));
  }

  private boolean isNewEntryStarting(String line) {
    return !line.isBlank() && !line.contains("->");
  }
}
