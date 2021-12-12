package de.cas.dcs.sync.adventofcode2021.day2.navigation.positions;

public class PositionWithAim extends Position {

  private int aim;

  public PositionWithAim() {
    this(0, 0, 0);
  }

  public PositionWithAim(int x, int y, int aim) {
    super(x, y);
    this.aim = aim;
  }

  public void increaseAim(int increaseBy) {
    this.aim += increaseBy;
  }

  public void decreaseAim(int decreaseBy) {
    this.aim -= decreaseBy;
  }

  public int getAim() {
    return aim;
  }
}
