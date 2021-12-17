package de.cas.dcs.sync.adventofcode2021.day17;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Test {

  @ParameterizedTest
  @CsvSource({"20,30,-10,-5,false,6|9", "20,30,-10,-5,true,112"})
  public void resultTest(
      long x_min, long x_max, long y_min, long y_max, boolean partTwo, String expectedResult) {
    // ARRANGE
    TargetArea targetArea = new TargetArea(x_min, x_max, y_min, y_max);

    // ACT
    String actualResult = new Day17().execute(targetArea, partTwo);

    // ASSERT
    assertEquals(expectedResult, actualResult);
  }
}
