package de.cas.dcs.sync.adventofcode2021.day2;

import de.cas.dcs.sync.adventofcode2021.day2.navigation.commands.DefaultCommandsProvider;
import de.cas.dcs.sync.adventofcode2021.day2.navigation.navigators.PartOneNavigator;
import de.cas.dcs.sync.adventofcode2021.day2.navigation.positions.Position;
import de.cas.dcs.sync.adventofcode2021.day2.navigation.positions.PositionCalculator;

import java.io.IOException;

public class PartOne {
  private final Puzzle2 puzzle2;

  public PartOne() {
    PositionCalculator<Position> positionPositionCalculator =
        new PositionCalculator<>(new PartOneNavigator());
    this.puzzle2 = new Puzzle2(positionPositionCalculator);
  }

  public static void main(String[] args) throws IOException {
    System.out.println(new PartOne().getPuzzle2().calculate(new DefaultCommandsProvider()));
  }

  public Puzzle2 getPuzzle2() {
    return puzzle2;
  }
}
