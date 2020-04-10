package com.mwpb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Aoc24_2Test {

	@Test
	void testConstructor() {
		Aoc24_2 aoc24 = new Aoc24_2("....#\r\n" + "#..#.\r\n" + "#..##\r\n"
				+ "..#..\r\n" + "#....");
		System.out.println(aoc24.currentBugs);
	}

	@Test
	void testGetNeighbours() {
		Aoc24_2 aoc24 = new Aoc24_2("....#\r\n" + "#..#.\r\n" + "#..##\r\n"
				+ "..#..\r\n" + "#....");
		System.out.println(aoc24.getNeighbours(new Position(1, 1, 0)));
		System.out.println(aoc24.getNeighbours(new Position(1, 2, 0)));
		System.out.println(aoc24.getNeighbours(new Position(3, 2, 0)));
		System.out.println(aoc24.getNeighbours(new Position(4, 1, 0)));
	}

	@Test
	void testCountNeighouringBugs() {
		Aoc24_2 aoc24 = new Aoc24_2("....#\r\n" + "#..#.\r\n" + "#..##\r\n"
				+ "..#..\r\n" + "#....");
		System.out.println(aoc24.countNeighbouringBugs(new Position(3, 2, 0)));
	}

	@Test
	void testRun() {
		Aoc24_2 aoc24test = new Aoc24_2("....#\r\n" + "#..#.\r\n" + "#..##\r\n"
				+ "..#..\r\n" + "#....");
		Assertions.assertEquals(99, aoc24test.run(10));

		Aoc24_2 aoc24 = new Aoc24_2("##.#.\r\n" + ".##..\r\n" + "##.#.\r\n"
				+ ".####\r\n" + "###..");
		System.out.println(aoc24.run(200));
	}
}
