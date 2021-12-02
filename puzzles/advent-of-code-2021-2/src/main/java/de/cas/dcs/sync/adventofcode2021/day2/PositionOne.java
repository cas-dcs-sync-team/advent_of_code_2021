package de.cas.dcs.sync.adventofcode2021.day2;

public class PositionOne {

	private int x;
	private int y;

	public PositionOne() {
		x = 0;
		y = 0;
	}

	public void applyNavigationCommand(NavigationCommand command) {
		switch (command.getType()) {

			case FORWARD -> {
				x += command.getValue();
				break;
			}
			case DOWN -> {
				y += command.getValue();
				break;
			}
			case UP -> {
				y -= command.getValue();
				break;
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
