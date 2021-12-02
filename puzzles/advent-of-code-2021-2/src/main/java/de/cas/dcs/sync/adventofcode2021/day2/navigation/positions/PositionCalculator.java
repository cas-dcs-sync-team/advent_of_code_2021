package de.cas.dcs.sync.adventofcode2021.day2.navigation.positions;

import de.cas.dcs.sync.adventofcode2021.day2.navigation.commands.NavigationCommand;
import de.cas.dcs.sync.adventofcode2021.day2.navigation.navigators.Navigator;

import java.util.List;

public class PositionCalculator<T extends Position> {

	private final Navigator<T> navigator;

	public PositionCalculator(Navigator<T> navigator) {
		this.navigator = navigator;
	}

	public Position calculateFinalPosition(List<NavigationCommand> commands, int startX, int startY) {
		T startPosition = navigator.getPositionOf(startX, startY);
		commands.forEach(command -> navigator.addNavigationToPosition(command, startPosition));
		return startPosition;
	}
}
