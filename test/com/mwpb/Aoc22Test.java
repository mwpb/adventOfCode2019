package com.mwpb;

import org.junit.jupiter.api.Test;

public class Aoc22Test {
	
	@Test
	void testConstructor() {
		Aoc22 aoc22 = new Aoc22(10);
		System.out.println(aoc22.deck);
		aoc22.deck.pop();
		System.out.println(aoc22.deck);
	}
	
	@Test
	void testDealIntoNewStack() {
		Aoc22 aoc22 = new Aoc22(10);
		System.out.println(aoc22.deck);
		aoc22.dealIntoNewStack();
		System.out.println(aoc22.deck);
	}
	
	@Test
	void testCut() {
		Aoc22 aoc22 = new Aoc22(10007);
		System.out.println(aoc22.deck);
		aoc22.cut(3);
		System.out.println(aoc22.deck);
		
		Aoc22 aoc22_2 = new Aoc22(10);
		System.out.println(aoc22_2.deck);
		aoc22_2.cut(-4);
		System.out.println(aoc22_2.deck);
	}
	
}
