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
  private Map<Rule, Long> rulesToMatchingPairs = Collections.emptyMap();
  private Map<Character, Long> counts = new HashMap<>();

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

    Template template = templates.get(0);
    List<Character> startCharacters = new ArrayList<>();

    for (Character character : template.start().toCharArray()) {
      startCharacters.add(character);
      // count also the start characters
      counts.compute(character, (key, value) -> increaseBy(value, 1));
    }

    rulesToMatchingPairs = initWithStartString(startCharacters, template.rules());

    for (int i = 0; i < steps; i++) {
      applyRulesToPairs();
    }

    long lowestCount = counts.values().stream().mapToLong(Long::valueOf).min().orElseThrow();
    long highestCount = counts.values().stream().mapToLong(Long::valueOf).max().orElseThrow();

    return highestCount - lowestCount;
  }

  private void addRuleMatchesForNewPairs(
      char start, char end, long currentRuleMatches, Map<Rule, Long> newMatches) {
    getTransformRule(start, end, rulesToMatchingPairs.keySet())
        .ifPresent(
            rule ->
                newMatches.compute(rule, (key, value) -> increaseBy(value, currentRuleMatches)));
  }

  private void applyRulesToPairs() {
    Map<Rule, Long> newMatches = new HashMap<>();

    for (Rule rule : rulesToMatchingPairs.keySet()) {

      long currentRuleMatches = rulesToMatchingPairs.get(rule);

      // increase count for added characters
      counts.compute(rule.newCharacter(), (key, value) -> increaseBy(value, currentRuleMatches));

      addRuleMatchesForNewPairs(rule.start(), rule.newCharacter(), currentRuleMatches, newMatches);
      addRuleMatchesForNewPairs(rule.newCharacter(), rule.end(), currentRuleMatches, newMatches);

      // remove matches for current rules as the pair has been ripped apart
      newMatches.compute(rule, (key, value) -> increaseBy(value, -currentRuleMatches));
    }

    // merge pair matches from current step into global state
    for (Rule rule : rulesToMatchingPairs.keySet()) {
      rulesToMatchingPairs.put(rule, rulesToMatchingPairs.get(rule) + newMatches.get(rule));
    }
  }

  private Map<Rule, Long> initWithStartString(List<Character> startCharacters, List<Rule> rules) {
    Map<Rule, Long> rulesToMatchingPairs = new HashMap<>();
    rules.forEach(r -> rulesToMatchingPairs.put(r, 0L));

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
    return rulesToMatchingPairs;
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

  private Long increaseBy(Long currentValue, long toAdd) {
    return currentValue == null ? toAdd : currentValue + toAdd;
  }
}
