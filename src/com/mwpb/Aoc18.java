package com.mwpb;

import java.util.*;

class State implements Comparable<State> {
    char lastKey;
    Set<Character> keysLeft;
    int distEstimateFromInit;
    State(char currentKey, Set<Character> keysLeft) {
        this.lastKey = currentKey;
        this.keysLeft = keysLeft;
        this.distEstimateFromInit = Integer.MAX_VALUE - 1;
    }
    
    State(char currentKey, Set<Character> keysLeft, int dist) {
        this.lastKey = currentKey;
        this.keysLeft = keysLeft;
        this.distEstimateFromInit = dist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return lastKey == state.lastKey &&
                keysLeft.equals(state.keysLeft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastKey, keysLeft);
    }

    @Override
    public String toString() {
//    	return String.format("%c:", this.lastKey);
        return String.format("%c: %d: %s", this.lastKey, this.distEstimateFromInit, this.keysLeft);
    }

	@Override
	public int compareTo(State o) {
		int dist = this.distEstimateFromInit;
		int oDist = o.distEstimateFromInit;
		if (dist < oDist) {
			return -1;
		} else if (dist > oDist) {
			return 1;
		} else {
			return 0;
		}
	}
    
    
}

class Path {
    int length;
    Set<Character> doorsOnPath;
    Set<Character> keysOnPath;

    Path() {
        this.length = 0;
        this.doorsOnPath = new HashSet<>();;
        this.keysOnPath = new HashSet<>();
    }

    @Override
    public String toString() {
        return String.format("(%d, doors: %s, keys: %s)", this.length, this.doorsOnPath, this.keysOnPath);
    }

    int getWeight(Set<Character> remainingKeys) {
        for (char door: this.doorsOnPath) {
            if (remainingKeys.contains(Character.toLowerCase(door))) {
                return Integer.MAX_VALUE - 1;
            }
        }
        for (char door: this.keysOnPath) {
            if (remainingKeys.contains(Character.toLowerCase(door))) {
                return Integer.MAX_VALUE - 1;
            }
        }
        return this.length;
    }
}

public class Aoc18 {

    char[][] maze;
    Map<Character, Point> keysRemaining;
    Map <String, Path> distances = new HashMap<>();
    int robotX;
    int robotY;

    Aoc18(String mazeString) {
        this.distances = new HashMap<>();
        String[] lines = mazeString.split("\n");
        this.maze = new char[lines.length][lines[0].length()];
        this.keysRemaining = new HashMap<>();
        for (int i = 0; i < lines.length; i++) {
            char[] line = lines[i].toCharArray();
            for (int j = 0; j < line.length; j++) {
                if (line[j] == '@') {
                    this.robotX = j;
                    this.robotY = i;
                    this.keysRemaining.put(line[j], new Point(j, i));
                } else if (Character.isLetter(line[j]) && Character.isLowerCase(line[j])) {
                    this.keysRemaining.put(line[j], new Point(j, i));
                }
                this.maze[i][j] = line[j];
            }
        }
    }

    boolean isClear(Point p) {
        return this.maze[p.y][p.x] == '.' || Character.isLowerCase(this.maze[p.y][p.x]) ||
                !this.keysRemaining.containsKey(Character.toLowerCase(this.maze[p.y][p.x]));
    }

    boolean isWall(Point p) {
        return this.maze[p.y][p.x] == '#';
    }

    boolean inBounds(Point p) {
        return (p.x >= 0) && (p.y >= 0) && (p.x < this.maze[0].length) && (p.y < this.maze.length);
    }

    boolean isKey(Point p) {
        return Character.isLetter(this.maze[p.y][p.x]) && Character.isLowerCase(this.maze[p.y][p.x]);
    }
    
    boolean isDoor(Point p, Set<Character> keysLeft) {
    	if (!keysLeft.contains(Character.toLowerCase(this.maze[p.y][p.x]))) {
    		return false;
    	}
    	return Character.isLetter(this.maze[p.y][p.x]) && Character.isUpperCase(this.maze[p.y][p.x]);
    }

    void bfs(Point p1) {
        Deque<Point> q = new ArrayDeque<>(List.of(p1));
        Set<Point> visited = new HashSet<>(List.of(p1));
        Map<Point, List<Point>> path = new HashMap<>();
        path.put(p1, List.of());
        while (q.size() > 0) {
//        	System.out.println(q);
            Point current = q.remove();
            int x = current.x;
            int y = current.y;
            for (Point next: List.of(new Point(x - 1, y), new Point(x + 1, y), new Point(x, y - 1), new Point(x, y + 1))) {
                if (this.inBounds(next) && !visited.contains(next) && this.maze[next.y][next.x] != '#') {
                	List<Point> currentPath = new LinkedList<>(path.get(current));
                    if (this.isKey(next)) {
                    	List<Point> newPoints = new LinkedList<>(currentPath);
//                        newPoints.add(next);
                        Path newPath = this.createPath(newPoints);
                        this.distances.put(String.format("%c%c", this.maze[p1.y][p1.x], this.maze[next.y][next.x]), newPath);
                        this.distances.put(String.format("%c%c", this.maze[next.y][next.x], this.maze[p1.y][p1.x]), newPath);
                    }
                    currentPath.add(next);
                    q.add(next);
                    path.put(next, currentPath);
                    visited.add(next);
                }
            }
        }
    }
    
    

    Path createPath(List<Point> points) {
        Path path = new Path();
        path.length = points.size() + 1;
        path.doorsOnPath = new HashSet<>();
        for (Point p: points) {
            char c = this.maze[p.y][p.x];
            if (Character.isLetter(c) && Character.isUpperCase(c)) {
                path.doorsOnPath.add(c);
            } else if (Character.isLetter(c) && Character.isLowerCase(c)) {
            	path.keysOnPath.add(c);
            }
        }
        return path;
    }

    void getDistances() {
        for (char key1: this.keysRemaining.keySet()) {
            for (char key2: this.keysRemaining.keySet()) {
                if (key1 != key2) {
                    if (!distances.containsKey(String.format("%c%c", key1, key2))) {
                        this.bfs(this.keysRemaining.get(key1));
                    }
                } else {
                    distances.put(String.format("%c%c", key1, key2), new Path());
                }
            }
        }
//        System.out.println(distances);
    }

    int getPointDist(Point p1, Point p2) {
//        System.out.println(p1);
//        System.out.println(p2);
        return this.distances.get(String.format("%c%c",
                this.maze[p1.y][p1.x], this.maze[p2.y][p2.x])).getWeight(this.keysRemaining.keySet());
    }

//    int getKeyDist(char key1, char key2) {
//        return this.distances.get(String.format("%c%c", key1, key2)).getWeight(this.keysRemaining.keySet());
//    }

    Set<Character> getCharsFromPath(String path) {
        Set<Character> chars = new HashSet<>();
        for (char c: path.toCharArray()) {
            chars.add(c);
        }
        return chars;
    }
    
    State getClosestToInitState(Map<State, Integer> distEstimates, Map<State, Integer> finalisedDists) {
    	int bestDist = Integer.MAX_VALUE - 1;
    	State bestState = new State('x', new HashSet<>());
    	for (State state: distEstimates.keySet()) {
    		if (!finalisedDists.containsKey(state)) {
    			int dist = distEstimates.get(state);
        		if (dist < bestDist) {
        			bestDist = dist;
        			bestState = state;
        		}
    		}
    	}
    	return bestState;
    }
    
    int getDist(Character c1, State state) {
    	Path p = this.distances.get(String.format("%c%c", c1, state.lastKey));
    	return p.getWeight(state.keysLeft);
    }
    
    Map<Character, Integer> getNextKeysWithDistances(Character lastKey, Set<Character> keysLeft) {
//    	System.out.println(lastKey);
//    	System.out.println(keysLeft);
    	Map<Character, Integer> keysWithDistances = new HashMap<>();
    	for (Character key: keysLeft) {
    		Path p = this.distances.get(String.format("%c%c", lastKey, key));
//    		System.out.println(key);
//    		System.out.println(p.getWeight(keysLeft));
    		if (p.getWeight(keysLeft) < Integer.MAX_VALUE / 2) {
    			keysWithDistances.put(key, p.length);
    		}
    	}
    	return keysWithDistances;
    }

    int dijkstra() {
    	PriorityQueue<State> distEstimates = new PriorityQueue<>();
//    	Map<State, Integer> distEstimates = new HashMap<>();
    	Map<State, Integer> finalisedDists = new HashMap<>();
    	Set<Character> keysLeft = new HashSet<>(this.keysRemaining.keySet());
    	keysLeft.remove('@');
    	State initState = new State('@', keysLeft);
    	initState.distEstimateFromInit = 0;
    	distEstimates.add(initState);
    	while (distEstimates.size() > 0) {
//    		System.out.println(distEstimates);
    		State nearestToInit = distEstimates.poll();
    		if (!finalisedDists.containsKey(nearestToInit)) {
    			System.out.println(nearestToInit);
        		finalisedDists.put(nearestToInit, nearestToInit.distEstimateFromInit);
        		if (nearestToInit.keysLeft.size() == 0) {
        			System.out.println(finalisedDists);
        			return nearestToInit.distEstimateFromInit;
        		}
//        		System.out.println(distEstimates);
//        		System.out.println(finalisedDists);
        		Map<Character, Integer> nextChars = this.getNextKeysWithDistances(nearestToInit.lastKey, nearestToInit.keysLeft);
//        		System.out.println(nextChars);
        		for (Character c: nextChars.keySet()) {
        			Set<Character> newKeysLeft = new HashSet<>(nearestToInit.keysLeft);
        			newKeysLeft.remove(c);
        			State state = new State(c, newKeysLeft, nextChars.get(c) + nearestToInit.distEstimateFromInit);
    				distEstimates.add(state);
        		}
        		distEstimates.remove(nearestToInit);
    		}
    	}
    	return -1;
    }

}