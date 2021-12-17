package de.cas.dcs.sync.adventofcode2021.day15;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day15 {
  private static final String PUZZLE_RESOURCE_NAME = "day15.file";

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(Day15.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    List<String> lines = Files.lines(path).collect(Collectors.toList());
    partOne(lines);
    partTwo(lines);
  }

  public static void partOne(List<String> lines) {
    System.out.println("partOne: " + new Day15().execute(lines, false));
  }

  public static void partTwo(List<String> lines) {
    System.out.println("partTwo: " + new Day15().execute(lines, true));
  }

  public long execute(List<String> entries, boolean partTwo) {
    int[][] grid = initGrid(entries, partTwo ? 5 : 1);

    Set<Point> resolvedPoints = new HashSet<>();
    Set<Point> unresolvedPoints = new HashSet<>();
    Map<String, Point> allPoints = new HashMap<>();

    Point startPoint = new Point(0, 0, 0);
    startPoint.setTotalRisk(0);
    allPoints.put("0_0", startPoint);
    unresolvedPoints.add(startPoint);

    Point endPoint = createPoint(grid[0].length - 1, grid.length - 1, grid, allPoints);

    // thanks for the little refresher: https://www.baeldung.com/java-dijkstra
    while (unresolvedPoints.size() != 0) {
      Point currentPoint = getPointWithLowestTotalRisk(unresolvedPoints);
      unresolvedPoints.remove(currentPoint);
      for (Point nextPoint : getPossibleNextPoints(currentPoint, grid, allPoints)) {
        if (!resolvedPoints.contains(nextPoint)) {
          addRisk(nextPoint, currentPoint);
          unresolvedPoints.add(nextPoint);
        }
      }
      resolvedPoints.add(currentPoint);
    }

    return endPoint.getTotalRisk();
  }

  private void addRisk(Point nextPoint, Point currentPoint) {
    int currenTotalRisk = currentPoint.getTotalRisk();
    int riskOfNextPoint = nextPoint.getRisk();
    if ((currenTotalRisk + riskOfNextPoint) < nextPoint.getTotalRisk()) {
      nextPoint.setTotalRisk(currenTotalRisk + riskOfNextPoint);
    }
  }

  private Point getPointWithLowestTotalRisk(Set<Point> points) {
    Point pointWithLowestTotalRisk = null;
    for (Point point : points) {
      if (pointWithLowestTotalRisk == null
          || point.getTotalRisk() < pointWithLowestTotalRisk.getTotalRisk()) {
        pointWithLowestTotalRisk = point;
      }
    }
    return pointWithLowestTotalRisk;
  }

  private List<Point> getPossibleNextPoints(
      Point currentPoint, int[][] grid, Map<String, Point> allPoints) {
    List<Point> nextPoints = new ArrayList<>(4);

    if (currentPoint.getY() + 1 < grid.length) {
      nextPoints.add(createPoint(currentPoint.getX(), currentPoint.getY() + 1, grid, allPoints));
    }
    if (currentPoint.getX() + 1 < grid[0].length) {
      nextPoints.add(createPoint(currentPoint.getX() + 1, currentPoint.getY(), grid, allPoints));
    }

    if (currentPoint.getX() != 0) {
      nextPoints.add(createPoint(currentPoint.getX() - 1, currentPoint.getY(), grid, allPoints));
    }
    if (currentPoint.getY() != 0) {
      nextPoints.add(createPoint(currentPoint.getX(), currentPoint.getY() - 1, grid, allPoints));
    }

    return nextPoints;
  }

  private Point createPoint(int x, int y, int[][] grid, Map<String, Point> allPoints) {
    allPoints.putIfAbsent(x + "_" + y, new Point(x, y, grid[y][x]));
    return allPoints.get(x + "_" + y);
  }

  private int[][] initGrid(List<String> entries, int multiplyGrid) {
    int lineLength = entries.get(0).length();
    int[][] grid = new int[entries.size() * multiplyGrid][lineLength * multiplyGrid];

    for (int i = 0; i < entries.size(); i++) {
      String line = entries.get(i);
      for (int j = 0; j < lineLength; j++) {
        for (int mY = 0; mY < multiplyGrid; mY++) {
          int value = Integer.parseInt(String.valueOf(line.charAt(j)));
          for (int mX = 0; mX < multiplyGrid; mX++) {
            int modifiedValue =
                mY == 0
                    ? mX == 0
                        ? value
                        : grid[i + (mY * entries.size())][j + ((mX - 1) * lineLength)] + 1
                    : grid[i + ((mY - 1) * entries.size())][j + (mX * lineLength)] + 1;
            if (modifiedValue > 9) {
              modifiedValue = 1;
            }
            grid[i + (mY * entries.size())][j + (mX * lineLength)] = modifiedValue;
          }
        }
      }
    }

    return grid;
  }
}
