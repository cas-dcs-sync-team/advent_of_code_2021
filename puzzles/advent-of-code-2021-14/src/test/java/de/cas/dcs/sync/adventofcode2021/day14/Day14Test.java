package de.cas.dcs.sync.adventofcode2021.day14;

import org.junit.jupiter.api.Test;
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
import static org.junit.jupiter.api.Assertions.fail;

class Day14Test {

  @ParameterizedTest
  @CsvSource({"day14-test.file,false,1588", "day14-test.file,true,2188189693529"})
  public void resultTest(String resourceName, boolean partTwo, long expectedResult)
      throws IOException, URISyntaxException {
    // ARRANGE
    Path path = Paths.get(this.getClass().getResource("/" + resourceName).toURI());
    List<String> lines = Files.lines(path).collect(Collectors.toList());

    // ACT
    long actualResult = new Day14().execute(lines, partTwo);

    // ASSERT
    assertEquals(expectedResult, actualResult);
  }

  @Test
  public void ifExecutingMainMethod_thenNoExceptionWillBeThrown() {
    try {
      Day14.main(new String[0]);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception: " + e);
    }
  }
}
