package de.cas.dcs.sync.adventofcode2021.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartTwo {
	private static final Path PUZZLE_RESOURCE = Paths.get("puzzle_resources/day3/day3.file");

	public static void main(String[] args) throws IOException {
		List<String> values = Files.lines(PUZZLE_RESOURCE)
				.collect(Collectors.toList());

		List<String> oxygen = new ArrayList<>(values);
		List<String> co2 = new ArrayList<>(values);

		int od = Integer.parseInt(reduce(oxygen, '1'), 2);
		int co2d = Integer.parseInt(reduce(co2, '0'), 2);


		System.out.println(od * co2d);
	}

	private static String reduce(List<String> values, char t) {
		for(int i=0; i<12; i++) {
			int ones = 0;
			int zeros = 0;
			for(String value : values) {
				char c = value.charAt(i);
				if('1' == c) {
					ones++;
				} else {
					zeros++;
				}
			}

			final int currentIndex = i;

			if(ones >= zeros) {
				values.removeIf(s -> values.size() > 1 && s.charAt(currentIndex) != t);
			} else {
				values.removeIf(s -> values.size() > 1 && s.charAt(currentIndex) == t);
			}
		}
		return values.get(0);
	}
}
