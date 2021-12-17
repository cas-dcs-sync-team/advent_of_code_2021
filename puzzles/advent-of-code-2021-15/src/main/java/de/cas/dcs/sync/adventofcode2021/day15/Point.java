package de.cas.dcs.sync.adventofcode2021.day15;

public class Point {

  private final int x;
  private final int y;
  private final int risk;

  private int totalRisk = Integer.MAX_VALUE;

  public Point(int x, int y, int risk) {
    this.x = x;
    this.y = y;
    this.risk = risk;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getRisk() {
    return risk;
  }

  public int getTotalRisk() {
    return totalRisk;
  }

  public void setTotalRisk(int totalRisk) {
    this.totalRisk = totalRisk;
  }
}
