package com.mwpb;

import java.util.*;

class Position {
	int x, y, z;

	Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String toString() {
		return String.format("(%d, %d, %d)", this.x, this.y, this.z);
	}

	@Override
	public boolean equals(Object o) {

		if (o == this)
			return true;
		if (!(o instanceof Position)) {
			return false;
		}
		Position position = (Position) o;
		return position.x == this.x && position.y == this.y
				&& position.z == this.z;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.x, this.y, this.z);
	}
}

public class Aoc24_2 {

	Set<Position> currentBugs;

	Aoc24_2(String initString) {

		String lines[] = initString.split("\\r?\\n");
		var initState = new char[5][5];
		for (int i = 0; i < 5; i++) {
			initState[i] = lines[i].toCharArray();
		}

		this.currentBugs = new HashSet<>();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (initState[j][i] == '#') {
					this.currentBugs.add(new Position(i, j, 0));
				}
			}
		}

	}

	ArrayList<Position> getNeighbours(Position p) {
		ArrayList<Position> neighbours = new ArrayList<>();
		// add north
		if (p.y == 0) {
			neighbours.add(new Position(2, 1, p.z + 1));
		} else if (p.y == 3 && p.x == 2) {
			for (int i = 0; i < 5; i++) {
				neighbours.add(new Position(i, 4, p.z - 1));
			}
		} else {
			neighbours.add(new Position(p.x, p.y - 1, p.z));
		}
		// add south
		if (p.y == 4) {
			neighbours.add(new Position(2, 3, p.z + 1));
		} else if (p.y == 1 && p.x == 2) {
			for (int i = 0; i < 5; i++) {
				neighbours.add(new Position(i, 0, p.z - 1));
			}
		} else {
			neighbours.add(new Position(p.x, p.y + 1, p.z));
		}
		// add west
		if (p.x == 0) {
			neighbours.add(new Position(1, 2, p.z + 1));
		} else if (p.x == 3 && p.y == 2) {
			for (int j = 0; j < 5; j++) {
				neighbours.add(new Position(4, j, p.z - 1));
			}
		} else {
			neighbours.add(new Position(p.x - 1, p.y, p.z));
		}
		// add east
		if (p.x == 4) {
			neighbours.add(new Position(3, 2, p.z + 1));
		} else if (p.x == 1 && p.y == 2) {
			for (int j = 0; j < 5; j++) {
				neighbours.add(new Position(0, j, p.z - 1));
			}
		} else {
			neighbours.add(new Position(p.x + 1, p.y, p.z));
		}
		return neighbours;
	}

	int countNeighbouringBugs(Position p) {
		int count = 0;
		for (Position neighbour : this.getNeighbours(p)) {
			if (this.currentBugs.contains(neighbour)) {
				count++;
			}
		}
		return count;
	}

	boolean isBugNextTime(Position p) {
		if (this.currentBugs.contains(p)) {
			if (this.countNeighbouringBugs(p) == 1) {
				return true;
			}
			return false;
		} else {
			int bugsAdjacent = this.countNeighbouringBugs(p);
			if (bugsAdjacent == 1 || bugsAdjacent == 2) {
				return true;
			}
			return false;
		}
	}

	Set<Position> nextState() {
		Set<Position> nextState = new HashSet<>();
		for (Position p : this.currentBugs) {
			if (this.isBugNextTime(p)) {
				nextState.add(p);
			}
			for (Position neighbour : this.getNeighbours(p)) {
				if (this.isBugNextTime(neighbour)) {
					nextState.add(neighbour);
				}
			}
		}
		return nextState;
	}

	int run(int time) {
		for (int t = 0; t < time; t++) {
			this.currentBugs = this.nextState();
		}
		return this.currentBugs.size();
	}
}
