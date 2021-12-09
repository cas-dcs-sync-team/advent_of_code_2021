package de.cas.dcs.sync.adventofcode2021.day9;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PartTwo {
  private static final String PUZZLE_RESOURCE_NAME = "day9.file";

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(PartTwo.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    Stream<String> lines = Files.lines(path);
    System.out.println(execute(lines));
  }

  public static int execute(Stream<String> lines) {

    List<String> collect = lines.collect(Collectors.toList());

    Integer[][] grid = new Integer[collect.size()][collect.get(0).length()];

    for (int i = 0; i < collect.size(); i++) {
      char[] positions = collect.get(i).toCharArray();
      for (int j = 0; j < positions.length; j++) {
        int currentPosition = Integer.parseInt("" + positions[j]);
        grid[i][j] = currentPosition;
      }
    }

    List<Integer> basinSizes = new ArrayList<>();

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        int currentPosition = grid[i][j];

        int up = getAdjacent(i - 1, j, grid);
        int down = getAdjacent(i + 1, j, grid);
        int left = getAdjacent(i, j - 1, grid);
        int right = getAdjacent(i, j + 1, grid);

        if (currentPosition < up
            && currentPosition < down
            && currentPosition < left
            && currentPosition < right) {
          basinSizes.add(calculateBasinSize(i, j, grid));
        }
      }
    }

    Collections.sort(basinSizes);

    return basinSizes.get(basinSizes.size() - 1)
        * basinSizes.get(basinSizes.size() - 2)
        * basinSizes.get(basinSizes.size() - 3);
  }

  private static Integer calculateBasinSize(int i, int j, Integer[][] grid) {
    return getHigherAdjacent(i, j, grid).size() + 1;
  }

  private static Set<String> getHigherAdjacent(int i, int j, Integer[][] grid) {
    Set<String> positions = new HashSet<>();
    int currentPosition = grid[i][j];

    int up = getAdjacent(i - 1, j, grid);
    int down = getAdjacent(i + 1, j, grid);
    int left = getAdjacent(i, j - 1, grid);
    int right = getAdjacent(i, j + 1, grid);

    if (up < 9 && currentPosition < up) {
      positions.add((i - 1) + "," + j);
      positions.addAll(getHigherAdjacent(i - 1, j, grid));
    }
    if (down < 9 && currentPosition < down) {
      positions.add((i + 1) + "," + j);
      positions.addAll(getHigherAdjacent(i + 1, j, grid));
    }
    if (left < 9 && currentPosition < left) {
      positions.add((i) + "," + (j - 1));
      positions.addAll(getHigherAdjacent(i, j - 1, grid));
    }
    if (right < 9 && currentPosition < right) {
      positions.add((i) + "," + (j + 1));
      positions.addAll(getHigherAdjacent(i, j + 1, grid));
    }

    return positions;
  }

  private static int getAdjacent(int i, int j, Integer[][] grid) {
    if (i < 0 || i >= grid.length) {
      return Integer.MAX_VALUE;
    }

    if (j < 0 || j >= grid[i].length) {
      return Integer.MAX_VALUE;
    }
    return grid[i][j];
  }
}
