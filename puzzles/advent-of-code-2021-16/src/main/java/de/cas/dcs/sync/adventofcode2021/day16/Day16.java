package de.cas.dcs.sync.adventofcode2021.day16;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day16 {
  private static final String PUZZLE_RESOURCE_NAME = "day16.file";

  public static void main(String[] args) throws IOException, URISyntaxException {
    Path path = Paths.get(Day16.class.getResource("/" + PUZZLE_RESOURCE_NAME).toURI());
    List<String> lines = Files.lines(path).collect(Collectors.toList());
    partOne(lines);
    partTwo(lines);
  }

  public static void partOne(List<String> lines) {
    System.out.println("partOne: " + new Day16().execute(lines, false));
  }

  public static void partTwo(List<String> lines) {
    System.out.println("partTwo: " + new Day16().execute(lines, true));
  }

  public long execute(List<String> entries, boolean partTwo) {

    return entries.stream()
        .map(NumberConversion::hexToBinary)
        .map(this::toPackage)
        .map(p -> partTwo ? p.getValue() : p.getVersionSum())
        .reduce(0L, Long::sum);
  }

  public Package toPackage(String binaryString) {
    int version = NumberConversion.binaryToDecimal(PayloadParser.getVersionBits(binaryString));
    int typeId = NumberConversion.binaryToDecimal(PayloadParser.getTypeIdBits(binaryString));
    String payload = PayloadParser.getPayloadBits(binaryString);

    final List<Package> subpackages;
    final int usedBytes;
    if (typeId != Package.TYPE_ID_LITERAL_VALUE) {
      usedBytes =
          7
              + ((payload.charAt(0) == Package.OPERATOR_LENGTH_TYPE_ID_11_BITS_VALUE)
                  ? Package.OPERATOR_LENGTH_FOR_TYPE_ID_1
                  : Package.OPERATOR_LENGTH_FOR_TYPE_ID_0);
      subpackages = extractSubpackage(payload);
    } else {
      List<String> groups = PayloadParser.literalPayloadToGroups(payload);
      usedBytes = 6 + (groups.size() * 5);
      subpackages = Collections.emptyList();
    }
    return new Package(version, typeId, payload, usedBytes, subpackages);
  }

  private List<Package> extractSubpackage(String operatorPayload) {
    List<Package> packages = new ArrayList<>();
    boolean immediatelyContainedPackets = operatorPayload.charAt(0) == '1';
    if (immediatelyContainedPackets) {
      int numberOfSubpackages = PayloadParser.getNumberOfSubpackages(operatorPayload);

      int payloadOffset = 1 + 11; // 1 for the operatorTypeBit;
      for (int i = 0; i < numberOfSubpackages; i++) {
        Package subpackage = toPackage(operatorPayload.substring(payloadOffset));
        packages.add(subpackage);
        payloadOffset += subpackage.getUsedBytesSum();
      }
    } else {
      String bitSizeBytesForSubpackages =
          PayloadParser.getBitSizeBytesForSubpackages(operatorPayload);
      int bitSizeForSubpackages = NumberConversion.binaryToDecimal(bitSizeBytesForSubpackages);
      int subpackagesOffset = 1 + bitSizeBytesForSubpackages.length(); // 1 for the operatorTypeBit;
      String payloadOfSubpackages =
          operatorPayload.substring(subpackagesOffset, subpackagesOffset + bitSizeForSubpackages);
      int payloadOffset = 0;
      while (payloadOffset < payloadOfSubpackages.length()) {
        Package subpackage = toPackage(payloadOfSubpackages.substring(payloadOffset));
        packages.add(subpackage);

        payloadOffset += subpackage.getUsedBytesSum();
      }
    }

    return packages;
  }
}
