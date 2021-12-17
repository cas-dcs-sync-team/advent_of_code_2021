package de.cas.dcs.sync.adventofcode2021.day16;

import java.util.ArrayList;
import java.util.List;

public class PayloadParser {
  static String getPayloadBits(String binaryString) {
    return binaryString.substring(6);
  }

  static String getVersionBits(String binaryString) {
    return binaryString.substring(0, 3);
  }

  static String getTypeIdBits(String binaryString) {
    return binaryString.substring(3, 6);
  }

  static String getBitSizeBytesForSubpackages(String operatorPayload) {
    return operatorPayload.substring(1, 16);
  }

  static int getNumberOfSubpackages(String operatorPayload) {
    return NumberConversion.binaryToDecimal(operatorPayload.substring(1, 12));
  }

  static List<String> literalPayloadToGroups(String literalPayload) {
    List<String> groups = new ArrayList<>();
    int position = 0;

    while (position < literalPayload.length()) {
      boolean isLastgroup = literalPayload.charAt(position) == '0';
      String groupContent = literalPayload.substring(position + 1, position + 5);
      groups.add(groupContent);

      if (isLastgroup) {
        break;
      }

      position += 5;
    }

    return groups;
  }
}
