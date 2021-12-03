package de.cas.dcs.sync.adventofcode2021.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class PartOne {
	private static final Path PUZZLE_RESOURCE = Paths.get("puzzle_resources/day3/day3.file");

	public static void main(String[] args) throws IOException {
		List<String> values = Files.lines(PUZZLE_RESOURCE)
				.collect(Collectors.toList());

		String gamma_rate = "";
		String epsilon_rate = "";

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

			if(ones > zeros) {
				gamma_rate += "1";
				epsilon_rate += "0";
			} else {
				gamma_rate += "0";
				epsilon_rate += "1";
			}

		}

		int gd = Integer.parseInt(gamma_rate, 2);
		int ed = Integer.parseInt(epsilon_rate, 2);

		System.out.println(gd * ed);
	}
}
