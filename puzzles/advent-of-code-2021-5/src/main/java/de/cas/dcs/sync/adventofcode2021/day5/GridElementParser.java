package de.cas.dcs.sync.adventofcode2021.day5;

public class GridElementParser {
  public Line stringToLine(String stringLine) {
    String[] split = stringLine.split(" -> ");
    Point start = stringToPoint(split[0]);
    Point end = stringToPoint(split[1]);
    Line line = new Line(start, end);
    return line;
  }

  public Point stringToPoint(String stringPoint) {
    String[] split = stringPoint.split(",");
    int x = Integer.parseInt(split[0]);
    int y = Integer.parseInt(split[1]);
    Point point = new Point(x, y);
    return point;
  }
}
