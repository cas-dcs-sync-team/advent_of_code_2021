package de.cas.dcs.sync.adventofcode2021.day10;

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

public class PartTwo {
  private static final String PUZZLE_RESOURCE_NAME = "day10.file";

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(PartOne.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    Stream<String> lines = Files.lines(path);
    System.out.println(execute(lines));
  }

  public static long execute(Stream<String> lines) {

    Map<String, String> m = new HashMap<>();
    m.put("[", "]");
    m.put("<", ">");
    m.put("{", "}");
    m.put("(", ")");

    List<String> collect = lines.collect(Collectors.toList());

    List<String> missingClosingLines = new ArrayList<>();

    for (String line : collect) {
      List<String> expectedClosingChars = new ArrayList<>();
      boolean notCorrupted = true;
      for (int i = 0; i < line.length(); i++) {
        String currentSymbol = "" + line.charAt(i);
        if (m.containsKey(currentSymbol)) {
          expectedClosingChars.add(0, m.get(currentSymbol));
        } else if (m.containsValue(currentSymbol)) {
          if (expectedClosingChars.size() == 0
              || !expectedClosingChars.get(0).equals(currentSymbol)) {
            notCorrupted = false;
            break;
          } else {
            expectedClosingChars.remove(0);
          }
        } else {
          throw new IllegalStateException("unexpected symbol: " + currentSymbol);
        }
      }
      if (notCorrupted) {
        missingClosingLines.add(expectedClosingChars.stream().reduce("", (a, b) -> a + b));
      }
    }

    return missingClosingLines.stream()
        .map(s -> mapMissingSymbolsToPoints(s))
        .sorted()
        .collect(Collectors.toList())
        .get((missingClosingLines.size() / 2));
  }

  private static long mapMissingSymbolsToPoints(String s) {
    long points = 0;
    for (int i = 0; i < s.length(); i++) {
      String currentSymbol = "" + s.charAt(i);
      points = points * 5 + mapToPoints(currentSymbol);
    }
    return points;
  }

  private static long mapToPoints(String s) {

    switch (s) {
      case "}":
        return 3;
      case "]":
        return 2;
      case ">":
        return 4;
      case ")":
        return 1;
    }
    throw new IllegalStateException("Unknown symbol: " + s);
  }
}
