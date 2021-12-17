package de.cas.dcs.sync.adventofcode2021.day17;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day17 {
  private static final String PUZZLE_RESOURCE_NAME = "day17.file";

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(Day17.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    List<String> lines = Files.lines(path).collect(Collectors.toList());
    partOne(lines);
    partTwo(lines);
  }

  public static void partOne(List<String> lines) {
    TargetArea targetArea = new TargetArea(25, 67, -260, -200);
    System.out.println("partOne: " + new Day17().execute(targetArea, false));
  }

  public static void partTwo(List<String> lines) {
    TargetArea targetArea = new TargetArea(25, 67, -260, -200);
    System.out.println("partTwo: " + new Day17().execute(targetArea, true));
  }

  public String execute(TargetArea targetArea, boolean partTwo) {
    Set<Probe> validProbes = new HashSet<>();
    Probe bestProbe = new Probe(0L, 0L);
    bestProbe.y_position_max = Long.MIN_VALUE;

    long x_min_start_velocity = 0;
    long x_max_start_velocity = targetArea.x_max();
    long y_min_start_velocity = targetArea.y_min();
    long y_max_start_velocity = 10000; // brute force I know :D

    for (long x_start_velocity = x_min_start_velocity;
        x_start_velocity <= x_max_start_velocity;
        x_start_velocity++) {
      for (long y_start_velocity = y_min_start_velocity;
          y_start_velocity <= y_max_start_velocity;
          y_start_velocity++) {
        Probe currentProbe = new Probe(x_start_velocity, y_start_velocity);

        while (isTargetAreaReachable(currentProbe, targetArea)) {
          applyStepToProbe(currentProbe);
          if (currentProbe.y_position > currentProbe.y_position_max) {
            currentProbe.y_position_max = currentProbe.y_position;
          }
          if (isProbeInTargetArea(currentProbe, targetArea)) {
            if (currentProbe.y_position_max > bestProbe.y_position_max) {
              bestProbe = currentProbe;
            }
            validProbes.add(currentProbe);
            break;
          }
        }
      }
    }

    return partTwo
        ? "" + validProbes.size()
        : bestProbe.x_start_velocity + "|" + bestProbe.y_start_velocity;
  }

  private boolean isTargetAreaReachable(Probe currentProbe, TargetArea targetArea) {
    boolean reachable = true;
    if (currentProbe.x_velocity == 0
        && (currentProbe.x_position > targetArea.x_max()
            || currentProbe.x_position < targetArea.x_min())) {
      reachable = false;
    }
    if (currentProbe.y_position < targetArea.y_min()) {
      reachable = false;
    }
    return reachable;
  }

  private boolean isProbeInTargetArea(Probe probe, TargetArea targetArea) {
    return probe.x_position <= targetArea.x_max()
        && probe.x_position >= targetArea.x_min()
        && probe.y_position >= targetArea.y_min()
        && probe.y_position <= targetArea.y_max();
  }

  public void applyStepToProbe(Probe probe) {
    probe.x_position += probe.x_velocity;
    probe.y_position += probe.y_velocity;

    if (probe.x_velocity != 0) {
      probe.x_velocity += probe.x_velocity < 0 ? 1 : -1;
    }

    probe.y_velocity = probe.y_velocity - 1;
  }
}
