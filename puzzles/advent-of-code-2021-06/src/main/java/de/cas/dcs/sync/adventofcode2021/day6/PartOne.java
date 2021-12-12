package de.cas.dcs.sync.adventofcode2021.day6;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class PartOne {
  private static final Path PUZZLE_RESOURCE = Paths.get("puzzle_resources/day6/day6.file");

  public static void main(String[] args)
      throws IOException, ExecutionException, InterruptedException {
    List<String> values = Files.lines(PUZZLE_RESOURCE).collect(Collectors.toList());

    System.out.println(execute(values));
  }

  public static String execute(List<String> values)
      throws ExecutionException, InterruptedException {
    LineParser lineParser = new LineParser();

    final List<Fish> fishs = lineParser.lineToFishs(values.get(0), 80);
    Simulator simulator = new Simulator();
    BigInteger simulatedFishs = simulator.simulate(fishs);

    return "" + simulatedFishs;
  }
}
