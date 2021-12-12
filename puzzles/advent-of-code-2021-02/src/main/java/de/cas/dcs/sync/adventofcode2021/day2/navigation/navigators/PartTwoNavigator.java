package de.cas.dcs.sync.adventofcode2021.day2.navigation.navigators;

import de.cas.dcs.sync.adventofcode2021.day2.navigation.commands.NavigationCommand;
import de.cas.dcs.sync.adventofcode2021.day2.navigation.positions.PositionWithAim;

public class PartTwoNavigator implements Navigator<PositionWithAim> {
	@Override
	public void addNavigationToPosition(NavigationCommand command, PositionWithAim currentPosition) {
		switch (command.getType()) {

			case FORWARD -> {
				int x = command.getValue();
				currentPosition.increaseX(x);
				currentPosition.increaseY(currentPosition.getAim() * x);
				break;
			}
			case DOWN -> {
				currentPosition.increaseAim(command.getValue());
				break;
			}
			case UP -> {
				currentPosition.decreaseAim(command.getValue());
				break;
			}
		}
	}

	@Override
	public PositionWithAim getPositionOf(int x, int y) {
		return new PositionWithAim(x, y, 0);
	}
}
