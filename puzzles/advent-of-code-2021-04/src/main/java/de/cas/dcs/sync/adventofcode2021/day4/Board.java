package de.cas.dcs.sync.adventofcode2021.day4;

import java.util.Arrays;

public class Board {
  int[][] grid = new int[5][5];
  boolean[][] matched = new boolean[5][5];

  @Override
  public String toString() {
    return "Board{"
        + "grid="
        + Arrays.toString(grid)
        + ", matched="
        + Arrays.toString(matched)
        + '}';
  }
}
