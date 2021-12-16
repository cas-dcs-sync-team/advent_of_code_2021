package de.cas.dcs.sync.adventofcode2021.day16;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day16 {
  private static final String PUZZLE_RESOURCE_NAME = "day16.file";

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(Day16.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    List<String> lines = Files.lines(path).collect(Collectors.toList());
    partOne(lines);
    partTwo(lines);
  }

  public static void partOne(List<String> lines) {
    System.out.println("partOne: " + new Day16().execute(lines, false));
  }

  public static void partTwo(List<String> lines) {
    System.out.println("partTwo: " + new Day16().execute(lines, true));
  }

  public long execute(List<String> entries, boolean partTwo) {
    return 0L;
  }
}
