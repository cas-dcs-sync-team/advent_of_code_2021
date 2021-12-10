package de.cas.dcs.sync.adventofcode2021.day9;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PartOne {
  private static final String PUZZLE_RESOURCE_NAME = "day10.file";

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(PartOne.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    Stream<String> lines = Files.lines(path);
    System.out.println(execute(lines));
  }

  public static int execute(Stream<String> lines) {

    Map<String, String> m = new HashMap<>();
    m.put("[", "]");
    m.put("<", ">");
    m.put("{", "}");
    m.put("(", ")");

    List<String> collect = lines.collect(Collectors.toList());

    List<String> expectedClosingChars = new ArrayList<>();

    List<String> incorrectClosignSigns = new ArrayList<>();

    for (String line : collect) {
      for (int i = 0; i < line.length(); i++) {
        String currentSymbol = "" + line.charAt(i);
        if (m.containsKey(currentSymbol)) {
          expectedClosingChars.add(0, m.get(currentSymbol));
        } else if (m.containsValue(currentSymbol)) {
          if (expectedClosingChars.size() == 0
              || !expectedClosingChars.get(0).equals(currentSymbol)) {
            incorrectClosignSigns.add(currentSymbol);
            break;
          } else {
            expectedClosingChars.remove(0);
          }
        } else {
          throw new IllegalStateException("unexpected symbol: " + currentSymbol);
        }
      }
    }

    return incorrectClosignSigns.stream().map(s -> mapToPoints(s)).reduce(0, Integer::sum);
  }

  private static int mapToPoints(String s) {
    switch (s) {
      case "}":
        return 1197;
      case "]":
        return 57;
      case ">":
        return 25137;
      case ")":
        return 3;
    }
    throw new IllegalStateException("Unknown symbol: " + s);
  }
}
