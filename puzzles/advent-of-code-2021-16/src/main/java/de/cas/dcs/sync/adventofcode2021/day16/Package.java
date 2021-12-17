package de.cas.dcs.sync.adventofcode2021.day16;

import java.util.List;
import java.util.stream.Collectors;

public record Package(int version, int type, String payload, int usedBytes, List<Package> subpackage) {
	public static final int TYPE_ID_LITERAL_VALUE = 4;
	public static final char OPERATOR_LENGTH_TYPE_ID_11_BITS_VALUE = '1';
	public static final int OPERATOR_LENGTH_FOR_TYPE_ID_1 = 11;
	public static final int OPERATOR_LENGTH_FOR_TYPE_ID_0 = 15;

	public int getVersionSum() {
		int versionSum = version;

		for(Package subpackage : this.subpackage) {
			versionSum += subpackage.getVersionSum();
		}

		return versionSum;
	}

	public int getUsedBytesSum() {
		int usedBytes = this.usedBytes;

		for(Package subpackage : this.subpackage) {
			usedBytes += subpackage.getUsedBytesSum();
		}

		return usedBytes;
	}

	public long getValue() {
		long value = 0;
		if(type == 0) {
			for(Package subpackage : this.subpackage) {
				value += subpackage.getValue();
			}
		}
		if(type == 1) {
			value = 1;
			for(Package subpackage : this.subpackage) {
				value *= subpackage.getValue();
			}
		}
		if(type == 2) {
			value = Integer.MAX_VALUE;
			for(Package subpackage : this.subpackage) {
				long subpackageValue = subpackage.getValue();
				if(subpackageValue < value) {
					value = subpackageValue;
				}
			}
		}
		if(type == 3) {
			value = Integer.MIN_VALUE;
			for(Package subpackage : this.subpackage) {
				long subpackageValue = subpackage.getValue();
				if(subpackageValue > value) {
					value = subpackageValue;
				}
			}
		}
		if(type == 4) {
			value = NumberConversion.binaryToDecimal(PayloadParser.literalPayloadToGroups(this.payload).stream().collect(Collectors.joining()));
		}
		if(type == 5) {
			value = this.subpackage.get(0).getValue() > this.subpackage.get(1).getValue() ? 1 : 0;
		}
		if(type == 6) {
			value = this.subpackage.get(0).getValue() < this.subpackage.get(1).getValue() ? 1 : 0;
		}
		if(type == 7) {
			value = this.subpackage.get(0).getValue() == this.subpackage.get(1).getValue() ? 1 : 0;
		}

		return value;
	}
}
