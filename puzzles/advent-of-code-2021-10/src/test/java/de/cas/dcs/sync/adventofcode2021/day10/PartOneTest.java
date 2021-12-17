package de.cas.dcs.sync.adventofcode2021.day10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class PartOneTest {

  @Test
  public void resultTest() throws IOException, URISyntaxException {
    // ARRANGE
    int expectedResult = 26397;
    Path path = Paths.get(this.getClass().getResource("/day10.file").toURI());
    Stream<String> lines = Files.lines(path);

    // ACT
    int actualResult = PartOne.execute(lines);

    // ASSERT
    assertEquals(expectedResult, actualResult);
  }
}
