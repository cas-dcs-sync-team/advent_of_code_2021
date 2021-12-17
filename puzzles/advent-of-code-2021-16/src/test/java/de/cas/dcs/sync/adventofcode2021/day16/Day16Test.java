package de.cas.dcs.sync.adventofcode2021.day16;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day16Test {

  @ParameterizedTest
  @CsvSource({
    "38006F45291200,false,9",
    "EE00D40C823060,false,14",
    "8A004A801A8002F478,false,16",
    "620080001611562C8802118E34,false,12",
    "C0015000016115A2E0802F182340,false,23",
    "A0016C880162017C3686B18A3D4780,false,31",
    "C200B40A82,true,3",
    "CE00C43D881120,true,9",
    "880086C3E88112,true,7",
    "04005AC33890,true,54",
    "D8005AC2A8F0,true,1",
    "F600BC2D8F,true,0",
    "9C005AC2F8F0,true,0",
    "9C0141080250320F1802104A08,true,1"
  })
  public void resultTest(String input, boolean partTwo, long expectedResult)
      throws IOException, URISyntaxException {
    // ARRANGE

    // ACT
    long actualResult = new Day16().execute(Collections.singletonList(input), partTwo);

    // ASSERT
    assertEquals(expectedResult, actualResult);
  }
}
