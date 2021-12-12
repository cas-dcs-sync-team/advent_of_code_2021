package de.cas.dcs.sync.adventofcode2021.day12;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {
  private static final String PUZZLE_RESOURCE_NAME = "day12.file";
  List<String> startPoints = null;
  List<String> endPoints = null;
  List<String> routePoints = null;

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(Day12.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    Stream<String> lines = Files.lines(path);
    partTwo(lines);
  }

  public static void partTwo(Stream<String> lines) {
    System.out.println("partTwo: " + new Day12().execute(lines, true));
  }

  public long execute(Stream<String> lines, boolean visistOneSmallCaveTwice) {

    List<String> entries = lines.collect(Collectors.toList());

    startPoints = getStartPoints(entries);
    endPoints = getEndPoints(entries);
    routePoints = getRoutePoints(entries);

    List<List<String>> routes = new ArrayList<>();

    for (String startPoint : startPoints) {
      List<String> currentRoute = new ArrayList<>();
      currentRoute.add(startPoint);
      routes.addAll(
          resolveRoutingLvl(
              extractCaveFromStartPoint(startPoint), currentRoute, !visistOneSmallCaveTwice));
    }

    return routes.size();
  }

  private List<List<String>> resolveRoutingLvl(
      String currentCave, List<String> currentRoute, boolean twoSmallCaves) {
    List<List<String>> routes = new ArrayList<>();
    List<String> nextSteps = getPossibleNextSteps(currentCave, currentRoute, twoSmallCaves);
    for (String nextStep : nextSteps) {
      boolean newTwoSmallCaves = twoSmallCaves;
      List<String> routePermutation = new ArrayList<>(currentRoute);
      routePermutation.add(nextStep);
      final String nextCave = extractNewCave(currentCave, nextStep);
      if (isSmallCave(nextCave) && alreadyVistedCave(nextCave, currentRoute)) {
        newTwoSmallCaves = true;
      }

      if (isEndStep(nextStep)) {
        routes.add(routePermutation);
      } else {
        routes.addAll(resolveRoutingLvl(nextCave, routePermutation, newTwoSmallCaves));
      }
    }
    return routes;
  }

  private boolean alreadyVistedCave(String cave, List<String> currentRoute) {
    return currentRoute.stream()
            .filter(s -> s.startsWith(cave + "-") || s.endsWith("-" + cave))
            .count()
        >= 2;
  }

  private boolean isEndStep(String nextStep) {
    return endPoints.contains(nextStep);
  }

  private List<String> getPossibleNextSteps(
      String currentCave, List<String> currentRoute, boolean twoSmallCaves) {
    List<String> nextPossibleSteps = new ArrayList<>();

    nextPossibleSteps.addAll(
        endPoints.stream()
            .filter(s -> s.startsWith(currentCave + "-") || s.endsWith("-" + currentCave))
            .toList());

    nextPossibleSteps.addAll(
        routePoints.stream()
            .filter(s -> s.contains(currentCave))
            .filter(
                s -> {
                  String cave = extractNewCave(currentCave, s);
                  return !isSmallCave(cave)
                      || !twoSmallCaves
                      || !routeContainsCave(cave, currentRoute);
                })
            .toList());

    return nextPossibleSteps;
  }

  private boolean routeContainsCave(String cave, List<String> currentRoute) {
    return currentRoute.stream().anyMatch(s -> s.contains(cave));
  }

  private boolean isSmallCave(String cave) {
    return cave.toLowerCase(Locale.ROOT).equals(cave);
  }

  private String extractNewCave(String currentCave, String routing) {
    return routing.startsWith(currentCave + "-")
        ? routing.replaceAll(currentCave + "-", "")
        : routing.replaceAll("-" + currentCave, "");
  }

  private String extractCaveFromStartPoint(String lastRouting) {
    return lastRouting.replaceAll("start-", "").replaceAll("-start", "");
  }

  private List<String> getStartPoints(List<String> entries) {
    return entries.stream().filter(s -> s.contains("start")).collect(Collectors.toList());
  }

  private List<String> getEndPoints(List<String> entries) {
    return entries.stream().filter(s -> s.contains("end")).collect(Collectors.toList());
  }

  private List<String> getRoutePoints(List<String> entries) {
    return entries.stream()
        .filter(s -> !s.contains("start") && !s.contains("end"))
        .collect(Collectors.toList());
  }
}
