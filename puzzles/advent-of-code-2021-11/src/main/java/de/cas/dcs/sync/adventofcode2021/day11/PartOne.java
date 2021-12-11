package de.cas.dcs.sync.adventofcode2021.day11;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PartOne {
  private static final String PUZZLE_RESOURCE_NAME = "day11.file";

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(PartOne.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    Stream<String> lines = Files.lines(path);
    System.out.println(new PartOne().execute(lines));
  }

  public long execute(Stream<String> lines) {
    int[][] grid = new int[10][10];

    initGrid(grid, lines);
    long flashes = 0;

    for (int step = 0; step < 100; step++) {
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid.length; j++) {
          int energyLvl = grid[i][j];
          energyLvl++;
          grid[i][j] = energyLvl;
        }
      }

      Set<String> alreadyFlashed = new HashSet<>();

      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid.length; j++) {
          if (shouldFlash(i, j, alreadyFlashed, grid)) {
            long flash = flash(i, j, alreadyFlashed, grid);
            flashes += flash;
          }
        }
      }

      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid.length; j++) {
          if (grid[i][j] > 9) {
            grid[i][j] = 0;
          }
        }
      }
    }

    return flashes;
  }

  private long flash(int i, int j, Set<String> alreadyFlashed, int[][] grid) {
    long flashes = 1;

    alreadyFlashed.add(i + "_" + j);

    flashes += flashAdjacent(i - 1, j - 1, alreadyFlashed, grid);
    flashes += flashAdjacent(i - 1, j, alreadyFlashed, grid);
    flashes += flashAdjacent(i - 1, j + 1, alreadyFlashed, grid);
    flashes += flashAdjacent(i, j - 1, alreadyFlashed, grid);
    flashes += flashAdjacent(i, j + 1, alreadyFlashed, grid);
    flashes += flashAdjacent(i + 1, j - 1, alreadyFlashed, grid);
    flashes += flashAdjacent(i + 1, j, alreadyFlashed, grid);
    flashes += flashAdjacent(i + 1, j + 1, alreadyFlashed, grid);

    return flashes;
  }

  private long flashAdjacent(int i, int j, Set<String> alreadyFlashed, int[][] grid) {
    if (i >= 0 && j >= 0 && i < grid.length && j < grid[i].length) {
      grid[i][j] = grid[i][j] + 1;
      return shouldFlash(i, j, alreadyFlashed, grid) ? flash(i, j, alreadyFlashed, grid) : 0;
    } else {
      return 0;
    }
  }

  private boolean shouldFlash(int i, int j, Set<String> alreadyFlashed, int[][] grid) {
    boolean shouldFlash = grid[i][j] > 9 && !alreadyFlashed.contains(i + "_" + j);
    return shouldFlash;
  }

  private void initGrid(int[][] grid, Stream<String> lines) {
    List<String> collect = lines.collect(Collectors.toList());
    for (int i = 0; i < grid.length; i++) {
      String currentLine = collect.get(i);
      for (int j = 0; j < grid.length; j++) {
        grid[i][j] = Integer.parseInt("" + currentLine.charAt(j));
      }
    }
  }
}
