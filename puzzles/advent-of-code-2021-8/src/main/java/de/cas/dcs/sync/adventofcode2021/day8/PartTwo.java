package de.cas.dcs.sync.adventofcode2021.day8;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.cas.dcs.sync.adventofcode2021.day8.Digit.*;

public class PartTwo {
  private static final String PUZZLE_RESOURCE_NAME = "day8.file";
  private static final List<Integer> searchedDigits =
      Arrays.asList(
          ONE_DIGIT_SEGEMTS_COUNT,
          FOUR_DIGIT_SEGEMTS_COUNT,
          SEVEN_DIGIT_SEGEMTS_COUNT,
          EIGHT_DIGIT_SEGEMTS_COUNT);

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(PartTwo.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
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
      String[] example = digit.example();
      String seven = getSeven(example);
      String one = getOne(example);
      String eight = getEight(example);
      String four = getFour(example);
      String nine = getNine(four, seven, example);

      String segment0 = getExtraLetterAsSegemt(one, seven);
      String segment6 = getExtraLetterAsSegemt(four + seven, nine);
      String segment4 = getExtraLetterAsSegemt(nine, eight);

      String three = getDigit(segment0 + one + segment6, 5, example);

      String segment3 = getExtraLetterAsSegemt(one + segment6 + segment0, three);

      String segment1 = getExtraLetterAsSegemt(one + segment3, four);

      String two = getDigit(segment0 + segment3 + segment4 + segment6, 5, example);

      String segment2 = getExtraLetterAsSegemt(segment0 + segment3 + segment4 + segment6, two);
      String segment5 = getExtraLetterAsSegemt(segment2, one);

      Map<String, Integer> segmentsToInteger = new HashMap<>();

      String zero = segment0 + segment1 + segment2 + segment4 + segment5 + segment6;
      String five = segment0 + segment1 + segment3 + segment5 + segment6;
      String six = segment0 + segment1 + segment3 + segment4 + segment5 + segment6;

      segmentsToInteger.put(zero, 0);
      segmentsToInteger.put(one, 1);
      segmentsToInteger.put(two, 2);
      segmentsToInteger.put(three, 3);
      segmentsToInteger.put(four, 4);
      segmentsToInteger.put(five, 5);
      segmentsToInteger.put(six, 6);
      segmentsToInteger.put(seven, 7);
      segmentsToInteger.put(eight, 8);
      segmentsToInteger.put(nine, 9);

      String resultNumber = "";

      for (String result : digit.result()) {
        for (String key : segmentsToInteger.keySet()) {
          if (result.length() == key.length() && containsAllSegments(result, key)) {
            resultNumber += segmentsToInteger.get(key);
          }
        }
      }

      count += Integer.parseInt(resultNumber);
    }

    return count;
  }

  private static String getExtraLetterAsSegemt(String knownValues, String checkForNewSegment) {
    for (int i = 0; i < checkForNewSegment.length(); i++) {
      String segemtLetter = String.valueOf(checkForNewSegment.charAt(i));
      if (!knownValues.contains(segemtLetter)) {
        return segemtLetter;
      }
    }
    throw new IllegalArgumentException(
        "could not resolve: knownValues=" + knownValues + ", segemtLetter=" + checkForNewSegment);
  }

  private static String getNine(String four, String seven, String[] example) {
    List<String> byLength = getByLength(6, example);

    return byLength.stream()
        .filter(s -> containsAllSegments(four, s) && containsAllSegments(seven, s))
        .findFirst()
        .get();
  }

  private static String getDigit(String segmentsToContain, int length, String[] example) {
    List<String> byLength = getByLength(length, example);

    return byLength.stream()
        .filter(s -> containsAllSegments(segmentsToContain, s))
        .findFirst()
        .get();
  }

  private static boolean containsAllSegments(String segmentToContain, String toCheck) {
    for (int i = 0; i < segmentToContain.length(); i++) {
      String segemtLetter = String.valueOf(segmentToContain.charAt(i));
      if (!toCheck.contains(segemtLetter)) {
        return false;
      }
    }
    return true;
  }

  private static String getSeven(final String[] values) {
    return getByLength(SEVEN_DIGIT_SEGEMTS_COUNT, values).get(0);
  }

  private static String getOne(final String[] values) {
    return getByLength(ONE_DIGIT_SEGEMTS_COUNT, values).get(0);
  }

  private static String getEight(final String[] values) {
    return getByLength(EIGHT_DIGIT_SEGEMTS_COUNT, values).get(0);
  }

  private static String getFour(final String[] values) {
    return getByLength(FOUR_DIGIT_SEGEMTS_COUNT, values).get(0);
  }

  private static List<String> getByLength(final int length, final String[] values) {
    return Arrays.stream(values).filter(s -> s.length() == length).collect(Collectors.toList());
  }
}
