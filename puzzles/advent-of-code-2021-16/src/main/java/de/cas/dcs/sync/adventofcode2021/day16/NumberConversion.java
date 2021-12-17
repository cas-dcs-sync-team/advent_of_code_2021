package de.cas.dcs.sync.adventofcode2021.day16;

import java.math.BigInteger;

public class NumberConversion {
  static int binaryToDecimal(String binaryString) {
    return Integer.parseInt(new BigInteger(binaryString, 2).toString(10));
  }

  static String hexToBinary(String hexString) {
    // add 1 to save leading zeros during conversion, remove 1 at the end with substring
    return new BigInteger("1" + hexString, 16).toString(2).substring(1);
  }
}
