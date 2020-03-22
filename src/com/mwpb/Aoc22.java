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
		// implement
	}
	
}
