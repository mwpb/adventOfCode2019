package com.mwpb;

import java.util.*;

public class Aoc22 {
	
	Deque<Integer> deck;
	int numberOfCards;
	
	Aoc22(int numberOfCards) {
		
		this.numberOfCards = numberOfCards;
		
		this.deck = new ArrayDeque<Integer>();
		for (int i = 0; i < this.numberOfCards; i++) {
			this.deck.add(i);
		}
		
	}
	
	void dealIntoNewStack() {
		
		Deque<Integer> newDeck = new ArrayDeque<>();
		for (int i = 0; i < this.numberOfCards; i++) {
			int card = this.deck.pop();
			newDeck.push(card);
		}
		this.deck = newDeck;
		
	}
	
	void cut(int n) {
		
		if (n > 0) {
			for (int i = 0; i < n; i++) {
				int card = this.deck.pop();
				this.deck.add(card);
			}
		} else if (n < 0) {
			n = Math.abs(n);
			for (int i = 0; i < n; i++) {
				int card = this.deck.removeLast();
				this.deck.push(card);
			}
		}
		
	}
	
	void dealWithIncrement(int n) {
		Integer[] table = new Integer[this.deck.size()];
		for (int i = 0; i < this.numberOfCards; i++) {
			int card = this.deck.pop();
			table[(n * i) % this.numberOfCards] = card;
		}
		List<Integer> list = Arrays.asList(table);
		this.deck = new ArrayDeque<Integer>(list);
	}
	
	void carryOutInstructions() {
		this.dealWithIncrement(15);
		this.cut(-4394);
		this.dealWithIncrement(9);
		this.dealIntoNewStack();
		this.cut(-8068);
		this.dealWithIncrement(15);
		this.dealIntoNewStack();
		this.cut(1470);
		this.dealIntoNewStack();
		this.cut(4151);
		this.dealIntoNewStack();
		this.cut(-2438);
		this.dealIntoNewStack();
		this.cut(9852);
		this.dealWithIncrement(50);
		this.cut(-953);
		this.dealWithIncrement(8);
		this.cut(-2836);
		this.dealWithIncrement(30);
		this.cut(-2419);
		this.dealIntoNewStack();
		this.cut(2759);
		this.dealWithIncrement(66);
		this.cut(1127);
		this.dealWithIncrement(66);
		this.cut(2194);
		this.dealWithIncrement(48);
		this.cut(4710);
		this.dealWithIncrement(49);
		this.dealIntoNewStack();
		this.dealWithIncrement(59);
		this.dealIntoNewStack();
		this.dealWithIncrement(25);
		this.dealIntoNewStack();
		this.dealWithIncrement(60);
		this.cut(-2003);
		this.dealWithIncrement(2);
		this.cut(-6166);
		this.dealWithIncrement(26);
		this.cut(-6179);
		this.dealWithIncrement(4);
		this.cut(373);
		this.dealWithIncrement(53);
		this.cut(6849);
		this.dealWithIncrement(13);
		this.cut(625);
		this.dealWithIncrement(68);
		this.cut(4084);
		this.dealWithIncrement(53);
		this.cut(-6939);
		this.dealIntoNewStack();
		this.cut(-3416);
		this.dealWithIncrement(39);
		this.cut(-2671);
		this.dealWithIncrement(64);
		this.dealIntoNewStack();
		this.dealWithIncrement(75);
		this.cut(7654);
		this.dealIntoNewStack();
		this.cut(-5431);
		this.dealWithIncrement(66);
		this.cut(-370);
		this.dealIntoNewStack();
		this.cut(3316);
		this.dealWithIncrement(31);
		this.cut(312);
		this.dealWithIncrement(22);
		this.cut(71);
		this.dealWithIncrement(21);
		this.cut(562);
		this.dealWithIncrement(27);
		this.cut(8611);
		this.dealWithIncrement(67);
		this.cut(8358);
		this.dealWithIncrement(7);
		this.cut(-2957);
		this.dealWithIncrement(71);
		this.cut(1740);
		this.dealWithIncrement(31);
		this.cut(-9577);
		this.dealWithIncrement(59);
		this.cut(6104);
		this.dealWithIncrement(40);
		this.cut(-8862);
		this.dealWithIncrement(17);
		this.cut(2516);
		this.dealWithIncrement(34);
		this.cut(9594);
		this.dealIntoNewStack();
		this.cut(5182);
		this.dealWithIncrement(72);
		this.cut(-2630);
		this.dealIntoNewStack();
		this.cut(-9018);
		this.dealWithIncrement(45);
		this.cut(-1069);
		this.dealWithIncrement(28);
		this.cut(358);
		this.dealIntoNewStack();
		this.cut(-2244);
	}
	
	int get2019th() {
		for (int i = 0; i< this.numberOfCards; i++) {
			int card = this.deck.pop();
			if (card == 2019) {
				return i;
			}
		}
		return -1;
	}
	
}
