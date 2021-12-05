package de.cas.dcs.sync.adventofcode2021.day5;

public class LineValidator {
	public boolean isLineHorizontal(Line line) {
		return line.start.x == line.end.x;
	}

	public boolean isLineVertical(Line line) {
		return line.start.y == line.end.y;
	}
}
