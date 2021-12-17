package de.cas.dcs.sync.adventofcode2021.day16;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayloadParserTest {
  @Test
  public void literalPayloadToGroupsTest() {
    // ARRANGE
    final String input = "101111111000101000";
    final List<String> expectedGroups = Arrays.asList("0111", "1110", "0101");

    // ACT
    final List<String> actualGroups = PayloadParser.literalPayloadToGroups(input);

    // ASSERT
    assertEquals(expectedGroups, actualGroups);
  }

  @Test
  public void getBitSizeForSubpackagesTest() {
    // ARRANGE
    final String payload = "00000000000110111101000101001010010001001000000000";
    final int expectedLength = 27;

    // ACT
    final int actualLength =
        NumberConversion.binaryToDecimal(PayloadParser.getBitSizeBytesForSubpackages(payload));

    // ASSERT
    assertEquals(expectedLength, actualLength);
  }

  @Test
  public void getNumberOfSubpackagesTest() {
    // ARRANGE
    final String payload = "10000000001101010000001100100000100011000001100000";
    final int epextedNumber = 3;

    // ACT
    final int actualNumber = PayloadParser.getNumberOfSubpackages(payload);

    // ASSERT
    assertEquals(epextedNumber, actualNumber);
  }
}
