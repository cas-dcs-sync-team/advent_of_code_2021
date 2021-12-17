package de.cas.dcs.sync.adventofcode2021.day17;

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

class Day17Test {

  @ParameterizedTest
  @CsvSource({"day17-test.file,false,0", "day17-test.file,true,0"})
  public void resultTest(String resourceName, boolean partTwo, long expectedResult)
      throws IOException, URISyntaxException {
    // ARRANGE
    Path path = Paths.get(this.getClass().getResource("/" + resourceName).toURI());
    List<String> lines = Files.lines(path).collect(Collectors.toList());

    // ACT
    long actualResult = new Day17().execute(lines, partTwo);

    // ASSERT
    assertEquals(expectedResult, actualResult);
  }
}
