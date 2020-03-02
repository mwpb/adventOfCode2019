package com.mwpb;

import java.util.*;

public class Aoc19 {
	String intcodeString;
	
	Aoc19(String intcodeString) {
		this.intcodeString = intcodeString;
	}
	
	boolean checkPoint(int x, int y) {
		Intcode intcode = new Intcode(this.intcodeString);
		intcode.input = new LinkedList<Integer>(List.of(x, y));
		Optional<Long> output = intcode.run();
		return output.get() == 1;
	}
	
	int countIn50x50() {
		int count = 0;
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				if (this.checkPoint(i, j)) {
					count++;
				}
			}
		}
		return count;
	}
	
}
