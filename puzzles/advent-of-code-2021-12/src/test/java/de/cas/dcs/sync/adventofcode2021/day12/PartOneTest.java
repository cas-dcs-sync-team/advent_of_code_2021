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

class PartOneTest {

  @ParameterizedTest
  @CsvSource({"day12-small.file,10", "day12-medium.file,19", "day12-large.file,226"})
  public void resultTest(String resourceName, int expectedResult)
      throws IOException, URISyntaxException {
    // ARRANGE
    Path path = Paths.get(this.getClass().getResource("/" + resourceName).toURI());
    Stream<String> lines = Files.lines(path);

    // ACT
    long actualResult = new PartOne().execute(lines);

    // ASSERT
    assertEquals(expectedResult, actualResult);
  }
}
