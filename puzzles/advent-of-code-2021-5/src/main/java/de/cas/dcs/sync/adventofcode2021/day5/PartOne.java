package de.cas.dcs.sync.adventofcode2021.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PartOne {
	private static final Path PUZZLE_RESOURCE = Paths.get("puzzle_resources/day5/day5.file");


	public static void main(String[] args) throws IOException {
		List<String> values = Files.lines(PUZZLE_RESOURCE)
				.collect(Collectors.toList());

		System.out.println(execute(values));
	}

	public static String execute(List<String> values) {
		GridElementParser parser = new GridElementParser();
		LineValidator validator = new LineValidator();
		int[][] grid = new int[1000][1000];

		List<Line> lines = values.stream().map(line -> parser.stringToLine(line))
				.filter(line -> validator.isLineHorizontal(line) || validator.isLineVertical(line))
				.collect(Collectors.toList());

		lines.stream().forEach(line -> addLineToGrid(line, grid));

		int count = 0;
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid.length; j++) {
				if(grid[i][j] > 1) {
					count++;
				}
			}
		}

		return "" + count;
	}

	private static void addLineToGrid(Line line, int[][] grid) {
		Point start = line.start;
		Point end = line.end;

		Point currentPosition = new Point();
		currentPosition.x = start.x;
		currentPosition.y = start.y;

		while(currentPosition.x != end.x || currentPosition.y != end.y) {
			grid[currentPosition.x][currentPosition.y]++;

			if(currentPosition.x > end.x) {
				currentPosition.x--;
			}
			if(currentPosition.x < end.x) {
				currentPosition.x++;
			}
			if(currentPosition.y > end.y) {
				currentPosition.y--;
			}
			if(currentPosition.y < end.y) {
				currentPosition.y++;
			}
		}

		grid[currentPosition.x][currentPosition.y]++;

	}
}
