package com.mwpb;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Aoc24Test {
	@Test
	void testConstructor() {
		Aoc24 aoc24 = new Aoc24("....#\r\n" + "#..#.\r\n" + "#..##\r\n"
				+ "..#..\r\n" + "#....");
	}

	@Test
	void firstSeenBefore() {
		Aoc24 aoc24 = new Aoc24("....#\r\n" + "#..#.\r\n" + "#..##\r\n"
				+ "..#..\r\n" + "#....");
		var firstRepeat = aoc24.firstRepeat();
		System.out.println(Arrays.deepToString(firstRepeat));
	}

	@Test
	void partOne() {
		Aoc24 aoc24test = new Aoc24("....#\r\n" + "#..#.\r\n" + "#..##\r\n"
				+ "..#..\r\n" + "#....");
		var firstRepeatTest = aoc24test.firstRepeat();
		Assertions.assertEquals(2129920,
				aoc24test.getBiodiversityRating(firstRepeatTest));

		Aoc24 aoc24 = new Aoc24("##.#.\r\n" + ".##..\r\n" + "##.#.\r\n"
				+ ".####\r\n" + "###..");
		var firstRepeat = aoc24.firstRepeat();
		System.out.println(aoc24.getBiodiversityRating(firstRepeat));
	}
}
