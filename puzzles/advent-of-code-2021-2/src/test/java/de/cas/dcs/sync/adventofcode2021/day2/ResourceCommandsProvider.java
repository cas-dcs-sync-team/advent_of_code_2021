package de.cas.dcs.sync.adventofcode2021.day2;

import de.cas.dcs.sync.adventofcode2021.day2.navigation.commands.CommandType;
import de.cas.dcs.sync.adventofcode2021.day2.navigation.commands.CommandsProvider;
import de.cas.dcs.sync.adventofcode2021.day2.navigation.commands.NavigationCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ResourceCommandsProvider implements CommandsProvider {
	private static final String PUZZLE_RESOURCE_NAME = "day2.file";

	@Override
	public List<NavigationCommand> getNavigationCommands() throws IOException {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(
				this.getClass()
						.getResourceAsStream("/" + PUZZLE_RESOURCE_NAME), StandardCharsets.UTF_8))) {
			return reader.lines()
					.map(s -> convert(s))
					.collect(Collectors.toList());
		}
	}

	public NavigationCommand convert(String raw) {
		String[] s = raw.split(" ");
		CommandType type = CommandType.valueOf(s[0].toUpperCase(Locale.ROOT));
		int value = Integer.parseInt(s[1]);
		return new NavigationCommand(type, value);
	}
}
