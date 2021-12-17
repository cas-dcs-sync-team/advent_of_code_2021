package de.cas.dcs.sync.adventofcode2021.day8;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartOneTest {
  private static final String PUZZLE_RESOURCE = "day8.file";

  @Test
  public void resultTest() throws IOException, URISyntaxException {
    // ARRANGE
    int expectedResult = 26;
    Path path = Paths.get(this.getClass().getResource("/day8.file").toURI());
    Stream<String> lines = Files.lines(path);

    // ACT
    int actualResult = PartOne.execute(lines);

    // ASSERT
    assertEquals(expectedResult, actualResult);
  }
}
