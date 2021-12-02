package de.cas.dcs.sync.adventofcode2021.day2;

import de.cas.dcs.sync.adventofcode2021.day2.navigation.commands.CommandType;
import de.cas.dcs.sync.adventofcode2021.day2.navigation.commands.CommandsProvider;
import de.cas.dcs.sync.adventofcode2021.day2.navigation.commands.NavigationCommand;
import de.cas.dcs.sync.adventofcode2021.day2.navigation.positions.Position;
import de.cas.dcs.sync.adventofcode2021.day2.navigation.positions.PositionCalculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Puzzle2 {

	private final PositionCalculator<? extends Position> positionCalculator;

	public Puzzle2(PositionCalculator<? extends Position> positionCalculator) {
		this.positionCalculator = positionCalculator;
	}

	public int calculate(CommandsProvider commandsProvider) throws IOException {
		Position finalPosition = positionCalculator
				.calculateFinalPosition(commandsProvider.getNavigationCommands(), 0, 0);
		return finalPosition.getX() * finalPosition.getY();
	}
}
