package de.cas.dcs.sync.adventofcode2021.day16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberConversionTest {

  @Test
  public void hexToBinaryTest() {
    // ARRANGE
    final String input = "D2FE28";
    final String expectedBinaryString = "110100101111111000101000";

    // ACT
    String actualResult = NumberConversion.hexToBinary(input);

    // ASSERT
    assertEquals(expectedBinaryString, actualResult);
  }

  @Test
  public void binaryToDecimalTest() {
    // ARRANGE
    final String input = "110";
    final int expectedBinaryString = 6;

    // ACT
    int actualResult = NumberConversion.binaryToDecimal(input);

    // ASSERT
    assertEquals(expectedBinaryString, actualResult);
  }
}
