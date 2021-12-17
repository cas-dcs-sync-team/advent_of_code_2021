package de.cas.dcs.sync.adventofcode2021.day13;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day13 {
  private static final String PUZZLE_RESOURCE_NAME = "day13.file";

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(Day13.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    List<String> lines = Files.lines(path).collect(Collectors.toList());
    partOne(lines);
    partTwo(lines);
  }

  public static void partOne(List<String> lines) {
    System.out.println("partOne: " + new Day13().execute(lines, false));
  }

  public static void partTwo(List<String> lines) {
    System.out.println("partTwo: " + new Day13().execute(lines, true));
  }

  public long execute(List<String> entries, boolean partTwo) {
    List<Dot> dots = getDots(entries);
    List<Instruction> instructions = getInstructions(entries);

    for (Instruction instruction : instructions) {
      int x = instruction.x();
      int y = instruction.y();

      for (int i = 0; i < dots.size(); i++) {
        Dot dot = dots.get(i);
        if (instruction.x() != 0) {
          if (dot.x() > instruction.x()) {
            dot = getMirroredPosition(dot, x, y);
            dots.set(i, dot);
          } else if (dot.x() == instruction.x()) {
            dots.remove(i);
            i--;
          }
        }

        if (instruction.y() != 0) {
          if (dot.y() > instruction.y()) {
            dot = getMirroredPosition(dot, x, y);
            dots.set(i, dot);
          } else if (dot.y() == instruction.y()) {
            dots.remove(i);
            i--;
          }
        }
      }

      if (!partTwo) {
        break;
      }
    }

    Set<Dot> noDuplicates = dots.stream().collect(Collectors.toSet());

    printGrid(noDuplicates);

    return noDuplicates.size();
  }

  private void printGrid(Set<Dot> dots) {

    int xMax = dots.stream().map(Dot::x).max(Comparator.naturalOrder()).get();
    int yMax = dots.stream().map(Dot::y).max(Comparator.naturalOrder()).get();

    String[][] grid = new String[yMax + 1][xMax + 1];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        grid[i][j] = ".";
      }
    }
    for (Dot dot : dots) {
      // use y as row and x as column
      grid[dot.y()][dot.x()] = "#";
    }

    for (int i = 0; i < grid.length; i++) {
      System.out.println();
      for (int j = 0; j < grid[i].length; j++) {
        System.out.print(grid[i][j]);
      }
    }
    System.out.println();
  }

  private Dot getMirroredPosition(Dot dot, int x, int y) {
    int newX = x != 0 ? 2 * x - dot.x() : dot.x();
    int newY = y != 0 ? 2 * y - dot.y() : dot.y();
    return new Dot(newX, newY);
  }

  private List<Dot> getDots(List<String> entries) {
    return entries.stream()
        .filter(s -> !s.isBlank() && !s.startsWith("fold"))
        .map(this::toDot)
        .collect(Collectors.toList());
  }

  private List<Instruction> getInstructions(List<String> entries) {
    return entries.stream()
        .filter(s -> s.startsWith("fold"))
        .map(this::toInstruction)
        .collect(Collectors.toList());
  }

  private Dot toDot(String line) {
    String[] split = line.split(",");
    return new Dot(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
  }

  private Instruction toInstruction(String line) {
    String[] split = line.split("=");
    if (line.contains("x=")) {
      return new Instruction(Integer.parseInt(split[1]), 0);
    } else {
      return new Instruction(0, Integer.parseInt(split[1]));
    }
  }
}
