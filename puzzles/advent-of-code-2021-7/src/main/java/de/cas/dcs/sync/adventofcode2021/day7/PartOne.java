package de.cas.dcs.sync.adventofcode2021.day7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PartOne {
  private static final String PUZZLE_RESOURCE_NAME = "day7.file";

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(PartOne.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    Stream<String> lines = Files.lines(path);
    System.out.println(execute(lines));
  }

  public static int execute(Stream<String> lines) {
    List<Integer> values =
        lines
            .flatMap(s -> Arrays.stream(s.split(",")))
            .map(Integer::parseInt)
            .sorted()
            .collect(Collectors.toList());

    int minPosition = values.get(0);
    int maxPosition = values.get(values.size() - 1);

    int minFuelCost = Integer.MAX_VALUE;

    for (int meetingPosition = minPosition; meetingPosition <= maxPosition; meetingPosition++) {
      int fuelCost = 0;
      for (Integer crabPosition : values) {
        fuelCost += Math.abs(meetingPosition - crabPosition);
      }
      if (fuelCost < minFuelCost) {
        minFuelCost = fuelCost;
      }
    }
    return minFuelCost;
  }
}
