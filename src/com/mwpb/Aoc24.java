package com.mwpb;

import java.util.*;

public class Aoc24 {
	char[][] currentState;
	int t;
	Map<String, ArrayList<Integer>> previousStates = new HashMap<>();

	Aoc24(char[][] initState) {
		this.currentState = initState;
	}

	Aoc24(String initString) {

		String lines[] = initString.split("\\r?\\n");
		var initState = new char[5][5];
		for (int i = 0; i < 5; i++) {
			initState[i] = lines[i].toCharArray();
		}

		this.currentState = initState;
		this.t = 0;
		this.previousStates.put(this.getKey(this.currentState),
				new ArrayList<>(List.of(0)));

	}

	String getKey(char[][] chars) {
		StringBuffer sb = new StringBuffer();
		for (char[] line : chars) {
			for (char c : line) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	boolean isInBounds(int i, int j) {
		return (i >= 0) && (j >= 0) && (i < 5) && (j < 5);
	}

	int countAdjacent(int i, int j) {
		int count = 0;
		for (var next : List.of(new Point(i + 1, j), new Point(i - 1, j),
				new Point(i, j - 1), new Point(i, j + 1))) {
			if (this.isInBounds(next.x, next.y)
					&& this.currentState[next.x][next.y] == '#') {
				count++;
			}
		}
		return count;
	}

	char[][] nextState() {
		char[][] nextState = new char[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				int count = this.countAdjacent(i, j);
//				System.out.println(count);
				if (this.currentState[i][j] == '#' && count == 1) {
					nextState[i][j] = '#';
				} else if (this.currentState[i][j] == '#' && count != 1) {
					nextState[i][j] = '.';
				} else if (this.currentState[i][j] == '.'
						&& (count == 1 || count == 2)) {
					nextState[i][j] = '#';
				} else {
					nextState[i][j] = this.currentState[i][j];
				}
			}
		}
		return nextState;
	}

	void addState(char[][] state) {
		this.t++;
		var tmp = this.previousStates.getOrDefault(state, new ArrayList<>());
		tmp.add(t);
		this.previousStates.put(this.getKey(state), tmp);
		this.currentState = state;
	}

	int getBiodiversityRating(char[][] state) {
		int biodiversityRating = 0;
		int multiplier = 1;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (state[i][j] == '#') {
					biodiversityRating += multiplier;
				}
				multiplier *= 2;
			}
		}
		return biodiversityRating;
	}

	char[][] firstRepeat() {
		boolean finished = false;
		while (!finished) {
			char[][] nextState = this.nextState();
			if (this.previousStates.containsKey(this.getKey(nextState))) {
				return nextState;
			}
			this.addState(nextState);
		}
		return new char[5][5];
	}

}
