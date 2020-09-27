package com.mwpb;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Aoc19 {
	String intcodeString;
	Map<Point, Boolean> cache;

	Aoc19(String intcodeString) {
		this.intcodeString = intcodeString;
		this.cache = new HashMap<>();
	}

	boolean checkPoint(Point p) {
		if (this.cache.containsKey(p)) {
			return this.cache.get(p);
		} else { 
			Intcode intcode = new Intcode(this.intcodeString);
			intcode.input = new LinkedList<Long>(
					List.of((long) p.x, (long) p.y));
			Optional<Long> output = intcode.run();
			boolean isInBeam = output.get() ==  1;

			this.cache.put(p, isInBeam);
			return isInBeam;
		}
	}

	void printBox(int x, int y) {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				System.out.print(this.checkPoint(new Point(i, j)) ? "1" : "0");
			}
			System.out.print("\n");
		}
	}

	int countIn50x50() {
		int count = 0;
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				if (this.checkPoint(new Point(i, j))) {
					count++;
				}
			}
		}
		return count;
	}

	boolean checkBox(Point p) {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				// System.out.println(String.format("%s: %b", new Point(i + p.x,
				// j + p.y), this.checkPoint(new Point(i + p.x, j + p.y))));
				if (!this.checkPoint(new Point(i + p.x, j + p.y))) {
					return false;
				}
			}
		}
		return true;
	}

	Optional<Point> checkRow(int y) {
		int x = 0;
		while (!this.checkPoint(new Point(x, y))) {
			x++;
		}
		int first = x;
		while (this.checkPoint(new Point(x, y))) {
			x++;
		}
		int last = x;
		// System.out.println(String.format("%d - %d = %d", last, first, last -
		// first));
		for (int i = first; i < last + 1 - 100; i++) {
			Point p = new Point(i, y);
			if (this.checkBox(p)) {
				return Optional.of(p);
			}
		}
		return Optional.empty();
	}

	Point findSantaShip() {
		int y = 975;
		Optional<Point> pOption = Optional.empty();
		do {
			System.out.println(y);
			pOption = this.checkRow(y);
			y++;
		} while (pOption.isEmpty() && y < 10000);
		return pOption.get();
	}

}
