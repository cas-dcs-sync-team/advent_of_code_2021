package de.cas.dcs.sync.adventofcode2021.day11;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartOneTest {

  @Test
  public void resultTest() throws IOException, URISyntaxException {
    // ARRANGE
    int expectedResult = 1656;
    Path path = Paths.get(this.getClass().getResource("/day11.file").toURI());
    Stream<String> lines = Files.lines(path);

    // ACT
    long actualResult = new PartOne().execute(lines);

    // ASSERT
    assertEquals(expectedResult, actualResult);
  }
}
