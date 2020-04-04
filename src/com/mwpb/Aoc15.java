package com.mwpb;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Aoc15 {

	Intcode intcode;
	Map<String, List<String>> adjList;
	Set<String> visited;
	int systemX;
	int systemY;

	Aoc15(String instructionsString) {
		this.intcode = new Intcode(instructionsString);
		this.adjList = new HashMap<String, List<String>>();
		this.visited = new HashSet<>();
	}

	String getString(int x, int y) {
		return String.format("%d,%d", x, y);
	}

	void addEdge(int x0, int y0, int x1, int y1) {
		List<String> oldForward = this.adjList.getOrDefault(
				this.getString(x0, y0), new LinkedList<>(List.of()));
		List<String> oldBackward = this.adjList.getOrDefault(
				this.getString(x1, y1), new LinkedList<>(List.of()));
		oldForward.add(this.getString(x1, y1));
		oldBackward.add(this.getString(x0, y0));
		this.adjList.put(this.getString(x0, y0), oldForward);
		this.adjList.put(this.getString(x1, y1), oldBackward);
	}

	boolean tryMove(int x, int y, int direction) {
		this.intcode.input = new LinkedList<>(List.of((long) direction));
		long output = this.intcode.run().get();
		int newx = x;
		int newy = y;
		if (direction == 1) {
			newy++;
		} else if (direction == 2) {
			newy--;
		} else if (direction == 3) {
			newx--;
		} else if (direction == 4) {
			newx++;
		}
		if (output == 0) {
			visited.add(this.getString(newx, newy));
			return false;
		} else {
			visited.add(this.getString(newx, newy));
			this.addEdge(x, y, newx, newy);
			if (output == 2) {
				this.systemX = newx;
				this.systemY = newy;
			}
			return true;
		}
	}

	void dfs() {
		this.visited.add("0,0");
		Deque<String> searchPath = new ArrayDeque<>();
		searchPath.push("0,0");
		while (searchPath.size() > 0) {
			String[] nodeStringArray = searchPath.peek().split(",");
			System.out.println(searchPath);
			int x = Integer.parseInt(nodeStringArray[0]);
			int y = Integer.parseInt(nodeStringArray[1]);
			if (!visited.contains(this.getString(x, y + 1))) {
				if (this.tryMove(x, y, 1)) {
					searchPath.push(this.getString(x, y + 1));
				}
			} else if (!visited.contains(this.getString(x, y - 1))) {
				if (this.tryMove(x, y, 2)) {
					searchPath.push(this.getString(x, y - 1));
				}
			} else if (!visited.contains(this.getString(x - 1, y))) {
				if (this.tryMove(x, y, 3)) {
					searchPath.push(this.getString(x - 1, y));
				}
			} else if (!visited.contains(this.getString(x + 1, y))) {
				if (this.tryMove(x, y, 4)) {
					searchPath.push(this.getString(x + 1, y));
				}
			} else {
				searchPath.pop();
				if (searchPath.size() > 0) {
					String[] nodeStringArray2 = searchPath.peek().split(",");
					int x2 = Integer.parseInt(nodeStringArray2[0]);
					int y2 = Integer.parseInt(nodeStringArray2[1]);
					int direction2;
					if (x == x2) {
						if (y < y2) {
							direction2 = 1;
						} else {
							direction2 = 2;
						}
					} else {
						if (x < x2) {
							direction2 = 4;
						} else {
							direction2 = 3;
						}
					}
					System.out.println(String.format("(%d, %d) , (%d, %d): %d",
							x, y, x2, y2, direction2));
					this.intcode.input = new LinkedList<>(
							List.of((long) direction2));
					this.intcode.run().get();
				}
			}
		}
	}

	int bfs() {
		return this.bfs(0, 0).get(this.getString(this.systemX, this.systemY));
	}

	Map<String, Integer> bfs(int startX, int startY) {
		Set<String> visited = new HashSet<>();
		Map<String, Integer> distDict = new HashMap<>();
		Deque<String> q = new ArrayDeque<>();
		q.add(this.getString(startX, startY));
		visited.add(this.getString(startX, startY));
		distDict.put(this.getString(startX, startY), 0);
		while (q.size() > 0) {
			System.out.println(q);
			String node = q.remove();
			int dist = distDict.get(node);
			for (String adjacent : this.adjList.get(node)) {
				if (!visited.contains(adjacent)) {
					q.add(adjacent);
					visited.add(adjacent);
					distDict.put(adjacent, dist + 1);
				}
			}
		}
		return distDict;
	}

}
