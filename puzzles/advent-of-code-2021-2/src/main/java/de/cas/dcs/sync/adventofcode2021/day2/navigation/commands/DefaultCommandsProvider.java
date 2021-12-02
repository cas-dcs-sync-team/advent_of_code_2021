package de.cas.dcs.sync.adventofcode2021.day2.navigation.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DefaultCommandsProvider implements CommandsProvider {

	private static final Path PUZZLE_RESOURCE = Paths.get("puzzle_resources/day2/day2.file");

	@Override
	public List<NavigationCommand> getNavigationCommands() throws IOException {
		return Files.lines(PUZZLE_RESOURCE)
				.map(s -> convert(s))
				.collect(Collectors.toList());
	}

	public NavigationCommand convert(String raw) {
		String[] s = raw.split(" ");
		CommandType type = CommandType.valueOf(s[0].toUpperCase(Locale.ROOT));
		int value = Integer.parseInt(s[1]);
		return new NavigationCommand(type, value);
	}
}
