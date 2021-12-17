package de.cas.dcs.sync.adventofcode2021.day6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LineParser {
  public List<Fish> lineToFishs(String line, int daysLeft) {
    return Arrays.stream(line.split(","))
        .map(Integer::parseInt)
        .map(i -> new Fish(i, daysLeft))
        .collect(Collectors.toList());
  }
}
