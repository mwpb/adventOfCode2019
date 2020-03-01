package com.mwpb;

import java.io.*;

import java.util.*;

import com.google.gson.Gson;


class Aoc18_2 {
	char[][] maze;
	Map<Character, Point> keysLeft;
	Map<Character, Point> pointsLookup;
	Map<Character, ArrayList<Edge>> adjList;
	
	Map<PointState, Set<Edge>> nextKeysCache;
	Map<RobotsState, Integer> stepsCache;
	
	
	Aoc18_2(String mazeString) {
		String[] lines = mazeString.split("\n");
		
        this.maze = new char[lines.length][lines[0].length()];
        this.keysLeft = new HashMap<Character, Point>();
        this.pointsLookup = new HashMap<Character, Point>();
        this.adjList = new HashMap<Character, ArrayList<Edge>>();
        this.nextKeysCache = new HashMap<>();
        this.stepsCache = new HashMap<>();
        
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

    void bfs(Point p1) {
        Deque<Point> q = new ArrayDeque<>(List.of(p1));
        Set<Point> visited = new HashSet<>(List.of(p1));
        Map<Point, Integer> distances = new HashMap<>();
        distances.put(p1, 0);
        char currentChar = this.maze[p1.y][p1.x];
        while (q.size() > 0) {
            Point current = q.remove();
            int distance = distances.get(current);
            int x = current.x;
            int y = current.y;
            for (Point next: List.of(new Point(x - 1, y), new Point(x + 1, y), new Point(x, y - 1), new Point(x, y + 1))) {
            	if (!visited.contains(next) && this.inBounds(next)) {
            		char nextChar = this.maze[next.y][next.x];
            		if (nextChar == '.') {
                		q.add(next);
                		distances.put(next, distance + 1);
                	} else if (Character.isLetter(nextChar) || Set.of('0', '1', '2', '3').contains(nextChar)) {
                		ArrayList<Edge> tmpList = this.adjList.getOrDefault(currentChar, new ArrayList<Edge>());
                		tmpList.add(new Edge(distance + 1, nextChar));
                		this.adjList.put(currentChar, tmpList);
                	}
            	}
            	visited.add(next);
            }
        }
    }
    
    void getAdjList() {
    	for (Map.Entry<Character, Point> entry: this.pointsLookup.entrySet()) {
    		this.bfs(entry.getValue());
    	}
    }
    
    Set<Edge> nextKeys(PointState ps) {
    	if (this.nextKeysCache.containsKey(ps)) {
    		return this.nextKeysCache.get(ps);
    	} else {
    		Set<Character> keysCollected = ps.keysCollected;
        	char c = ps.c;
        	Deque<Edge> q = new ArrayDeque<>();
        	Map<Character, Integer> distance = new HashMap<>();
        	Set<Edge> nextKeys = new HashSet<>();
        	
        	q.addAll(this.adjList.get(c));
        	keysCollected.add(c);
        	
        	distance.put(c, 0);
        	for (Edge e: this.adjList.get(c)) {
        		distance.put(e.end, e.length);
        	}
        	
        	while (q.size() > 0) {
        		Edge edge = q.poll();
        		if (Character.isLowerCase(edge.end) && !keysCollected.contains(edge.end) && c != edge.end) {
        			nextKeys.add(edge);
        		} else if (!keysCollected.contains(Character.toLowerCase(edge.end))) {
        		} else {
        			for (Edge e: this.adjList.get(edge.end)) {
        				int newDist = edge.length + e.length;
        				if (!distance.containsKey(e.end)) {
        					distance.put(e.end, newDist);
        					q.add(new Edge(newDist, e.end));
        				}
        			}
        		}
        	}
        	this.nextKeysCache.put(ps, nextKeys);
        	return nextKeys;
    	}
    }
    
    int mainDistance(RobotsState rs) {
    	if (this.stepsCache.containsKey(rs)) {
    		return this.stepsCache.get(rs);
    	} else {
    		if (rs.numberOfKeysRemaining == 0) {
        		return 0;
        	}
    		int best = Integer.MAX_VALUE / 2;
    		for (Character robot: rs.robots) {
    			Set<Character> newKeysCollected = new HashSet<>();
    			newKeysCollected.addAll(rs.keysCollected);
    			PointState ps = new PointState(robot, newKeysCollected);
            	int nFind = rs.numberOfKeysRemaining;
            	char c = ps.c;
            	Set<Character> keysCollected = new HashSet<Character>();
            	keysCollected.addAll(ps.keysCollected);
            	keysCollected.add(c);
            	
            	for (Edge edge: this.nextKeys(ps)) {
            		Set<Character> newRobots = new HashSet<>();
            		newRobots.addAll(rs.robots);
            		newRobots.remove(robot);
            		newRobots.add(edge.end);
            		
            		Set<Character> newKeys = new HashSet<>();
            		newKeys.addAll(keysCollected);
            		newKeys.add(edge.end);
            		int dist = edge.length + this.mainDistance(new RobotsState(newRobots, newKeys, nFind - 1));
            		if (dist < best) {
            			best = dist;
            		}
            	}
    		}
        	this.stepsCache.put(rs, best);
        	return best;
    	}
    }
    
}