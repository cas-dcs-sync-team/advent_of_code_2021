package de.cas.dcs.sync.adventofcode2021.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Day12Test {

  @ParameterizedTest
  @CsvSource({
    "day12-small.file,36,true",
    "day12-medium.file,103,true",
    "day12-large.file,3509,true"
  })
  public void resulTest(String resourceName, int expectedResult, boolean visistOneSmallCaveTwice)
      throws IOException, URISyntaxException {
    // ARRANGE
    Path path = Paths.get(this.getClass().getResource("/" + resourceName).toURI());
    Stream<String> lines = Files.lines(path);

    // ACT
    long actualResult = new Day12().execute(lines, visistOneSmallCaveTwice);

    // ASSERT
    assertEquals(expectedResult, actualResult);
  }
}
