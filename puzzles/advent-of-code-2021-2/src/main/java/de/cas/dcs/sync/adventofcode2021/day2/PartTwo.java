package de.cas.dcs.sync.adventofcode2021.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PartTwo {

	private static final Path PUZZLE_RESOURCE = Paths.get("puzzle_resources/day2/day2.file");

	public static void main(String[] args) throws IOException {
		List<NavigationCommand> values = Files.lines(PUZZLE_RESOURCE)
				.map(s -> convert(s))
				.collect(Collectors.toList());
		PositionTwo position = new PositionTwo();
		values.forEach(v -> position.applyNavigationCommand(v));

		System.out.println(position.getX() * position.getY());
	}

	public static NavigationCommand convert(String raw) {
		String[] s = raw.split(" ");
		CommandType type = CommandType.valueOf(s[0].toUpperCase(Locale.ROOT));
		int value = Integer.parseInt(s[1]);
		return new NavigationCommand(type, value);
	}
}
