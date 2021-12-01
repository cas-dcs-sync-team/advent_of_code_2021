package de.cas.dcs.sync.adventofcode2021.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PartOne {

	private static final Path PUZZLE_RESOURCE = Paths.get("puzzle_resources/day1/day1.file");

	public static void main(String[] args) throws IOException {
		List<Integer> values = Files.lines(PUZZLE_RESOURCE)
				.map(s -> Integer.parseInt(s))
				.collect(Collectors.toList());
		int increases = 0;
		int lastValue = 0;

		// worked only as I saw there are no 0 values in the data set
		for(int currentValue : values) {
			if(lastValue != 0 && currentValue > lastValue) {
				increases ++;
			}
			lastValue = currentValue;

		}
		System.out.println(increases);
	}
}
