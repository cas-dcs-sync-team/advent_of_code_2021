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

public class PartOne {
  private static final String PUZZLE_RESOURCE_NAME = "day12.file";
  List<String> startPoints = null;
  List<String> endPoints = null;
  List<String> routePoints = null;

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(PartOne.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    Stream<String> lines = Files.lines(path);
    System.out.println(new PartOne().execute(lines));
  }

  public long execute(Stream<String> lines) {

    List<String> entries = lines.collect(Collectors.toList());

    startPoints = getStartPoints(entries);
    endPoints = getEndPoints(entries);
    routePoints = getRoutePoints(entries);

    List<List<String>> routes = new ArrayList<>();

    for (String startPoint : startPoints) {
      List<String> currentRoute = new ArrayList<>();
      currentRoute.add(startPoint);
      routes.addAll(resolveRoutingLvl(extractCaveFromStartPoint(startPoint), currentRoute));
    }

    return routes.size();
  }

  private List<List<String>> resolveRoutingLvl(String currentCave, List<String> currentRoute) {
    List<List<String>> routes = new ArrayList<>();
    List<String> nextSteps = getPossibleNextSteps(currentCave, currentRoute);
    for (String nextStep : nextSteps) {
      List<String> routePermutation = new ArrayList<>(currentRoute);
      routePermutation.add(nextStep);
      final String nextCave = extractNewCave(currentCave, nextStep);
      if (isEndStep(nextStep)) {
        routes.add(routePermutation);
      } else {
        routes.addAll(resolveRoutingLvl(nextCave, routePermutation));
      }
    }
    return routes;
  }

  private boolean isEndStep(String nextStep) {
    return endPoints.contains(nextStep);
  }

  private List<String> getPossibleNextSteps(String currentCave, List<String> currentRoute) {
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
                  return !isSmallCave(cave) || !routeContainsCave(cave, currentRoute);
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
        ? routing.replaceAll(currentCave + "-", "").replaceAll("-", "")
        : routing.replaceAll("-" + currentCave, "").replaceAll("-", "");
  }

  private String extractCaveFromStartPoint(String lastRouting) {
    return lastRouting.replaceAll("start", "").replaceAll("-", "");
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
