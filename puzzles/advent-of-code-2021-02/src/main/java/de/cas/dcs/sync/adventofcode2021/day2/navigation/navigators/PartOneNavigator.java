package de.cas.dcs.sync.adventofcode2021.day2.navigation.navigators;

import de.cas.dcs.sync.adventofcode2021.day2.navigation.commands.NavigationCommand;
import de.cas.dcs.sync.adventofcode2021.day2.navigation.positions.Position;

public class PartOneNavigator implements Navigator<Position> {
	@Override
	public void addNavigationToPosition(NavigationCommand command, Position currentPosition) {
		switch (command.getType()) {

			case FORWARD -> {
				currentPosition.increaseX(command.getValue());
				break;
			}
			case DOWN -> {
				currentPosition.increaseY(command.getValue());
				break;
			}
			case UP -> {
				currentPosition.decreaseY(command.getValue());
				break;
			}
		}
	}

	@Override
	public Position getPositionOf(int x, int y) {
		return new Position(x, y);
	}
}
