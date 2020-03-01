package com.mwpb;

import java.util.*;

class BFSState {
	Point p;
	Set<Character> keysCollected;
	int numberOfKeysRemaining;
	
	BFSState(Point p, Set<Character> keysCollected, int numberOfKeysRemaining) {
		this.p = p;
		this.keysCollected = keysCollected;
		this.numberOfKeysRemaining = numberOfKeysRemaining;
	}
	
	public String toString() {
		return String.format("%s, %s, %d", this.p, this.keysCollected, this.numberOfKeysRemaining);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BFSState state = (BFSState) o;
        return state.p.equals(this.p) && state.keysCollected.equals(this.keysCollected);
    }

    @Override
    public int hashCode() {
        return 0;
    }
	
}

public class Aoc18_3 {
	
	char[][] maze;
	Map<Character, Point> keysLeft;
	Map<Character, Point> pointsLookup;
	Map<Character, ArrayList<Edge>> adjList;
	
	Set<BFSState> visited;
	
	Map<BFSState, Integer> cache;
	
	Aoc18_3(String mazeString) {
		String[] lines = mazeString.split("\n");
		
        this.maze = new char[lines.length][lines[0].length()];
        this.keysLeft = new HashMap<Character, Point>();
        this.pointsLookup = new HashMap<Character, Point>();
        this.adjList = new HashMap<Character, ArrayList<Edge>>();
        this.visited = new HashSet<>();
        
        this.cache = new HashMap<>();
        
        Set<Point> initRobots = new HashSet<>();
        for (int i = 0; i < lines.length; i++) {
            char[] line = lines[i].toCharArray();
            for (int j = 0; j < line.length; j++) {
                if (Character.isLetter(line[j]) || Set.of('0', '1', '2', '3').contains(line[j])) {
                	this.pointsLookup.put(line[j], new Point(j, i));
                    if (Character.isLetter(line[j]) && Character.isLowerCase(line[j])) {
                    	this.keysLeft.put(line[j], new Point(j, i));
                    	this.pointsLookup.put(line[j], new Point(j, i));
                    }
                }
                if (Set.of('0', '1', '2', '3').contains(line[j])) {
                	initRobots.add(new Point(j, i));
                }
                this.maze[i][j] = line[j];
            }
        }
	}
	
	boolean inBounds(Point p) {
        return (p.x >= 0) && (p.y >= 0) && (p.x < this.maze[0].length) && (p.y < this.maze.length);
    }
	
	boolean isDoor(Point p) {
		char c = this.maze[p.y][p.x];
		return Character.isLetter(c) && Character.isUpperCase(c) && !Character.isDigit(c);
	}
	
	boolean isLockedDoor(Point p, Set<Character> keysCollected) {
		char c = this.maze[p.y][p.x];
		return isDoor(p) && !keysCollected.contains(Character.toLowerCase(c));
	}
	
	int mainDistance(BFSState bs) {
		System.out.println(bs);
		if (this.cache.containsKey(bs)) {
			return this.cache.get(bs);
		} else {
			if (bs.numberOfKeysRemaining == 0) {
				return 0;
			}
			int x = bs.p.x;
			int y = bs.p.y;
			char c = this.maze[bs.p.y][bs.p.x];
			this.visited.add(bs);
			
			int newNumberOfKeysRemaining = bs.numberOfKeysRemaining;
			ArrayList<Integer> nextDistances = new ArrayList<>();
			for (Point next: List.of(new Point(x - 1, y), new Point(x + 1, y), new Point(x, y - 1), new Point(x, y + 1))) {
				Set<Character> newKeys = new HashSet<>();
				newKeys.addAll(bs.keysCollected);
				if (this.inBounds(next) && !this.visited.contains(new BFSState(next, newKeys, newNumberOfKeysRemaining))) {
					char nextC = this.maze[next.y][next.x];
					this.visited.add(new BFSState(next, newKeys, newNumberOfKeysRemaining));
					if (nextC == '#' || this.isLockedDoor(next, newKeys)) {
					} else {
						if (Character.isLetter(nextC) && Character.isLowerCase(nextC)) {
							if (newKeys.add(nextC)) {
								newNumberOfKeysRemaining--;
							}
						}
						int nextDistance = this.mainDistance(new BFSState(next, newKeys, newNumberOfKeysRemaining));
						nextDistances.add(nextDistance);
					}
				}
			}
			int min = Integer.MAX_VALUE / 2;
			if (!nextDistances.isEmpty()) {
				min = Collections.min(nextDistances) + 1;
			}
			this.cache.put(bs, min);
			return min;
		}
	}
	
}
