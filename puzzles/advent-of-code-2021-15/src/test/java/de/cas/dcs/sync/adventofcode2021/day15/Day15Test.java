package de.cas.dcs.sync.adventofcode2021.day15;

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

class Day15Test {

  @ParameterizedTest
  @CsvSource({
    "day15-smallest-test.file,false,3",
    "day15-smaller-test.file,false,4",
    "day15-go-left-test.file,false,10",
    "day15-test.file,false,40",
    "day15-test.file,true,315",
    "day15-test-with-out-resizing.file,false,315"
  })
  public void resultTest(String resourceName, boolean partTwo, long expectedResult)
      throws IOException, URISyntaxException {
    // ARRANGE
    Path path = Paths.get(this.getClass().getResource("/" + resourceName).toURI());
    List<String> lines = Files.lines(path).collect(Collectors.toList());

    // ACT
    long actualResult = new Day15().execute(lines, partTwo);

    // ASSERT
    assertEquals(expectedResult, actualResult);
  }
}
