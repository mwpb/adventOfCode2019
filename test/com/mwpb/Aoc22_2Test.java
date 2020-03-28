package com.mwpb;

import java.math.BigInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Aoc22_2Test {

	@Test
	void testPartOne() {
		Aoc22_2 aoc22 = new Aoc22_2(10007, 4485);
		System.out.println(aoc22.getCurrent());
		System.out.println(aoc22.n);
		aoc22.reversedInstructions();
		Assertions.assertEquals(BigInteger.valueOf(2019), aoc22.getCurrent());
	}
	@Test
	void testOne() {
		Aoc22_2 aoc22 = new Aoc22_2(11, 0);
		System.out.println(aoc22.n);
		System.out.println(aoc22.getCurrent());
		aoc22.dealIntoNewStack();
		aoc22.dealIntoNewStack();
		aoc22.dealWithIncrement(7);
		System.out.println(aoc22.getCurrent());
	}
	
	@Test
	void testTwo() {
		Aoc22_2 aoc22 = new Aoc22_2(11, 0);
		System.out.println(aoc22.n);
		System.out.println(aoc22.getCurrent());
		aoc22.dealIntoNewStack();
		aoc22.dealWithIncrement(7);
		aoc22.cut(6);
		System.out.println(aoc22.getCurrent());
	}
	@Test
	void testThree() {
		Aoc22_2 aoc22 = new Aoc22_2(11, 0);
		System.out.println(aoc22.n);
		System.out.println(aoc22.getCurrent());
		aoc22.cut(-2);
		aoc22.dealWithIncrement(9);
		aoc22.dealWithIncrement(7);
		System.out.println(aoc22.getCurrent());
	}
	
	@Test
	void testPartTwo() {
		Aoc22_2 aoc22 = new Aoc22_2(119315717514047L, 2020);
		aoc22.getBigAB();
		System.out.println(aoc22.A);
		System.out.println(aoc22.B);
		System.out.println(aoc22.run(101741582076661L));
	}
}
