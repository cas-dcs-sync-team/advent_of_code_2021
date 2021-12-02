package de.cas.dcs.sync.adventofcode2021.day2.navigation.positions;

public class Position {

	private int x;
	private int y;

	public Position() {
		this(0,0);
	}

	public Position(int x, int y) {
		x = 0;
		y = 0;
	}

	public void increaseX(int increaseBy) {
		this.x += increaseBy;
	}

	public void increaseY(int increaseBy) {
		this.y += increaseBy;
	}

	public void decreaseY(int decreaseBy) {
		this.y -= decreaseBy;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
