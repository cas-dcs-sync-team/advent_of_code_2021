package de.cas.dcs.sync.adventofcode2021.day5;

public class GridElementParser {
	public Line stringToLine(String stringLine) {
		String[] split = stringLine.split(" -> ");
		Line line = new Line();
		Point start = stringToPoint(split[0]);
		Point end = stringToPoint(split[1]);
		line.start = start;
		line.end = end;
		return line;
	}

	public Point stringToPoint(String stringPoint) {
		String[] split = stringPoint.split(",");
		Point point = new Point();
		point.x = Integer.parseInt(split[0]);
		point.y = Integer.parseInt(split[1]);
		return point;
	}
}
