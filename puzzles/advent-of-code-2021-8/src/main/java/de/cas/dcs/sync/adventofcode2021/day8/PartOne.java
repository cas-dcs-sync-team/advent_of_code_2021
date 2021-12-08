package de.cas.dcs.sync.adventofcode2021.day8;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.cas.dcs.sync.adventofcode2021.day8.Digit.*;

public class PartOne {
  private static final String PUZZLE_RESOURCE_NAME = "day8.file";
  private static final List<Integer> searchedDigits =
      Arrays.asList(
          ONE_DIGIT_SEGEMTS_COUNT,
          FOUR_DIGIT_SEGEMTS_COUNT,
          SEVEN_DIGIT_SEGEMTS_COUNT,
          EIGHT_DIGIT_SEGEMTS_COUNT);

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(PartOne.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    Stream<String> lines = Files.lines(path);
    System.out.println(execute(lines));
  }

  public static int execute(Stream<String> lines) {
    List<Digit> digits =
        lines
            .map(s -> s.split("\\|"))
            .map(s -> new Digit(s[0].split(" "), s[1].split(" ")))
            .collect(Collectors.toList());

    int count = 0;

    for (Digit digit : digits) {
      for (String result : digit.result()) {
        if (isSearchedDigit(result)) {
          count++;
        }
      }
    }

    return count;
  }

  private static boolean isSearchedDigit(final String value) {
    return searchedDigits.contains(value.length());
  }
}
