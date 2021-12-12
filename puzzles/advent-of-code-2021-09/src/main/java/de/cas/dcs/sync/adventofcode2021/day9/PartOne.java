package de.cas.dcs.sync.adventofcode2021.day9;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PartOne {
  private static final String PUZZLE_RESOURCE_NAME = "day9.file";

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(PartOne.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    Stream<String> lines = Files.lines(path);
    System.out.println(execute(lines));
  }

  public static int execute(Stream<String> lines) {

    List<String> collect = lines.collect(Collectors.toList());

    Integer[][] grid = new Integer[collect.size()][collect.get(0).length()];

    List<Integer> lowNumbers = new ArrayList<>();

    for (int i = 0; i < collect.size(); i++) {
      char[] positions = collect.get(i).toCharArray();
      for (int j = 0; j < positions.length; j++) {
        int currentPosition = Integer.parseInt("" + positions[j]);
        grid[i][j] = currentPosition;
      }
    }

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
          lowNumbers.add(currentPosition);
        }
      }
    }

    return lowNumbers.stream().map(i -> i + 1).reduce(0, Integer::sum);
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
