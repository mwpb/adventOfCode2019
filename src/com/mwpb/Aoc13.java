package com.mwpb;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Aoc13 {

	Intcode intcode;
	int[][] tiles;
	int score;

	Aoc13(String instructionsString) {
		this.intcode = new Intcode(instructionsString);
		this.tiles = new int[100][100];
	}

	Optional<Integer> getBall() {
		for (int[] row : this.tiles) {
			for (int i = 0; i < row.length; i++) {
				if (row[i] == 4) {
					return Optional.of(i);
				}
			}
		}
		return Optional.empty();
	}

	Optional<Integer> getPaddle() {
		for (int[] row : this.tiles) {
			for (int i = 0; i < row.length; i++) {
				if (row[i] == 3) {
					return Optional.of(i);
				}
			}
		}
		return Optional.empty();
	}

	void movePaddle() {
		Optional<Integer> ballOpt = this.getBall();
		Optional<Integer> paddleOpt = this.getPaddle();
		if (ballOpt.isPresent() && paddleOpt.isPresent()) {
			if (ballOpt.get() < paddleOpt.get()) {
				this.intcode.input = new LinkedList<>(List.of(-1L));
			} else if (paddleOpt.get() < ballOpt.get()) {
				this.intcode.input = new LinkedList<>(List.of(1L));
			} else {
				this.intcode.input = new LinkedList<>(List.of(0L));
			}
		}
	}

	boolean getTile() throws IOException {
		this.movePaddle();
		Optional<Long> opt = this.intcode.run();
		if (opt.isEmpty()) {
			return true;
		} else {
			long x = opt.get();
			long y = this.intcode.run().get();
			long tileId = this.intcode.run().get();
			if (x == -1 && y == 0) {
				this.score = (int) tileId;
				return false;
			} else {
				this.tiles[(int) y][(int) x] = (int) tileId;
				return false;
			}
		}
	}

	void run() throws IOException {
		boolean finished = false;
		while (!finished) {
			finished = this.getTile();
		}
	}

	void play() throws IOException {
		this.intcode.memory[0] = 2;
		boolean finished = false;
		while (!finished) {
			finished = this.getTile();
		}
	}

	int countBlocks() {
		int count = 0;
		for (int[] row : this.tiles) {
			for (int i : row) {
				if (i == 2) {
					count++;
				}
			}
		}
		return count;
	}

}
