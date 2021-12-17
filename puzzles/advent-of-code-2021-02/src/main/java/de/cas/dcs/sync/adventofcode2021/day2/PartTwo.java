package de.cas.dcs.sync.adventofcode2021.day2;

import de.cas.dcs.sync.adventofcode2021.day2.navigation.commands.DefaultCommandsProvider;
import de.cas.dcs.sync.adventofcode2021.day2.navigation.navigators.PartTwoNavigator;
import de.cas.dcs.sync.adventofcode2021.day2.navigation.positions.PositionCalculator;
import de.cas.dcs.sync.adventofcode2021.day2.navigation.positions.PositionWithAim;

import java.io.IOException;

public class PartTwo {
  private final Puzzle2 puzzle2;

  public PartTwo() {
    PositionCalculator<PositionWithAim> positionPositionCalculator =
        new PositionCalculator<>(new PartTwoNavigator());
    this.puzzle2 = new Puzzle2(positionPositionCalculator);
  }

  public static void main(String[] args) throws IOException {
    System.out.println(new PartTwo().getPuzzle2().calculate(new DefaultCommandsProvider()));
  }

  public Puzzle2 getPuzzle2() {
    return puzzle2;
  }
}
