package de.cas.dcs.sync.adventofcode2021.day2;

public class PositionTwo {

	private int x;
	private int y;
	private int aim;

	public PositionTwo() {
		x = 0;
		y = 0;
		aim = 0;
	}

	public void applyNavigationCommand(NavigationCommand command) {
		switch (command.getType()) {

			case FORWARD -> {
				x += command.getValue();
				y += aim * command.getValue();
				break;
			}
			case DOWN -> {
				aim += command.getValue();
				break;
			}
			case UP -> {
				aim -= command.getValue();
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

	public int getAim() {
		return aim;
	}
}
