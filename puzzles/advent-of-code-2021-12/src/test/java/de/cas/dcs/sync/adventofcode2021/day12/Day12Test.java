package de.cas.dcs.sync.adventofcode2021.day12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

  @ParameterizedTest
  @CsvSource({
    "day12-small.file,10,false",
    "day12-medium.file,19,false",
    "day12-large.file,226,false",
    "day12-small.file,36,true",
    "day12-medium.file,103,true",
    "day12-large.file,3509,true"
  })
  public void resultTest(String resourceName, int expectedResult, boolean visistOneSmallCaveTwice)
      throws IOException, URISyntaxException {
    // ARRANGE
    Path path = Paths.get(this.getClass().getResource("/" + resourceName).toURI());
    List<String> lines = Files.lines(path).collect(Collectors.toList());

    // ACT
    long actualResult = new Day12().execute(lines, visistOneSmallCaveTwice);

    // ASSERT
    assertEquals(expectedResult, actualResult);
  }
}
