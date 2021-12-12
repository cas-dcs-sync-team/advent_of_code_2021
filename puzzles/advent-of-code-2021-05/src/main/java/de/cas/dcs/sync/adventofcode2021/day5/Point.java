package de.cas.dcs.sync.adventofcode2021.day5;

public record Point(int x, int y) {
	public Point increaseX() {
		return new Point(this.x + 1, this.y);
	}

	public Point increaseY() {
		return new Point(x, this.y + 1);
	}

	public Point decreaseX() {
		return new Point(this.x - 1, this.y);
	}

	public Point decreaseY() {
		return new Point(x, this.y - 1);
	}
}
