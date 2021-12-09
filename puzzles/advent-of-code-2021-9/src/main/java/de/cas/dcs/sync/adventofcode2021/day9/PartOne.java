package de.cas.dcs.sync.adventofcode2021.day9;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PartOne {
  private static final String PUZZLE_RESOURCE_NAME = "day9.file";

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(PartOne.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    Stream<String> lines = Files.lines(path);
    System.out.println(execute(lines));
  }

  public static int execute(Stream<String> lines) {
    return 0;
  }
}
