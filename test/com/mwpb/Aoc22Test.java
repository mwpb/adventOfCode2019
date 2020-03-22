package com.mwpb;

import org.junit.jupiter.api.Assertions;
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
	
	@Test
	void testDealWithIncrement() {
		Aoc22 aoc22 = new Aoc22(10);
		System.out.println(aoc22.deck);
		aoc22.dealWithIncrement(3);
		System.out.println(aoc22.deck);
	}
	
	@Test
	void firstTest() {
		Aoc22 aoc22 = new Aoc22(10);
		System.out.println(aoc22.deck);
		aoc22.dealWithIncrement(7);
		aoc22.dealIntoNewStack();
		aoc22.dealIntoNewStack();
		System.out.println(aoc22.deck);
	}
	
	@Test
	void secondTest() {
		Aoc22 aoc22 = new Aoc22(10);
		System.out.println(aoc22.deck);
		aoc22.cut(6);
		aoc22.dealWithIncrement(7);
		aoc22.dealIntoNewStack();
		System.out.println(aoc22.deck);
	}
	
	@Test
	void thirdTest() {
		Aoc22 aoc22 = new Aoc22(10);
		System.out.println(aoc22.deck);
		aoc22.dealWithIncrement(7);
		aoc22.dealWithIncrement(9);
		aoc22.cut(-2);
		System.out.println(aoc22.deck);
	}
	
	@Test
	void fourthTest() {
		Aoc22 aoc22 = new Aoc22(10);
		System.out.println(aoc22.deck);
		aoc22.dealIntoNewStack();
		aoc22.cut(-2);
		aoc22.dealWithIncrement(7);
		aoc22.cut(8);
		aoc22.cut(-4);
		aoc22.dealWithIncrement(7);
		aoc22.cut(3);
		aoc22.dealWithIncrement(9);
		aoc22.dealWithIncrement(3);
		aoc22.cut(-1);
		System.out.println(aoc22.deck);
	}
	
	@Test
	void testFullInstructions() {
		Aoc22 aoc22 = new Aoc22(10007);
		System.out.println(aoc22.deck);
		aoc22.carryOutInstructions();
		System.out.println(aoc22.deck);
		Assertions.assertEquals(4485, aoc22.get2019th());
	}
	
	@Test
	void testHugeShuffle() {
		Aoc22 aoc22 = new Aoc22(10007);
		System.out.println(aoc22.deck);
		for (Long i = 0L; i < 101741582076661L; i++) {
			aoc22.carryOutInstructions();
		}
		System.out.println(aoc22.deck);
		Assertions.assertEquals(4485, aoc22.get2019th());
	}
	
}



























