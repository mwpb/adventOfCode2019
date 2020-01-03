package com.mwpb;

import java.util.*;

public class Aoc18 {

    char[][] maze;
    Map<Character, Point> keysRemaining;
    Map<Character, Point> doors;
    int robotX;
    int robotY;

    Aoc18(String mazeString) {
        String[] lines = mazeString.split("\n");
        this.maze = new char[lines.length][lines[0].length()];
        this.keysRemaining = new HashMap<>();
        this.doors = new HashMap<>();
        for (int i = 0; i < lines.length; i++) {
            char[] line = lines[i].toCharArray();
            for (int j = 0; j < line.length; j++) {
                if (line[j] == '@') {
                    this.robotX = j;
                    this.robotY = i;
                } else if (Character.isLetter(line[j]) && Character.isLowerCase(line[j])) {
                    this.keysRemaining.put(line[j], new Point(j, i));
                } else if (Character.isLetter(line[j]) && Character.isUpperCase(line[j])) {
                    this.doors.put(line[j], new Point(j, i));
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

    Map<Point, Integer> getDistDict(Point start) {
        Queue<Point> q = new ArrayDeque<>();
        Map<Point, Integer> distDict = new HashMap<>();
        Set<Point> visited = new HashSet<>();
        Map<Character, Point> letters = new HashMap<>();
        q.add(start);
        distDict.put(start, 0);
        visited.add(start);
        while (q.size() > 0) {
            Point current = q.remove();
            int x = current.x;
            int y = current.y;
            int dist = distDict.get(current);
            for (Point next: List.of(new Point(x - 1, y), new Point(x + 1, y), new Point(x, y - 1), new Point(x, y + 1))) {
                if (this.inBounds(next) && !this.isWall(next) && !visited.contains(next)) {
                    if (this.isClear(next)) {
                        q.add(next);
                    }
                    distDict.put(next, dist + 1);
                    visited.add(next);
                }
            }
        }
        return distDict;
    }

    Point getBestKey(Map<Point, Integer> distDict) {
        int currentDist = Integer.MAX_VALUE;
        Point currentPoint = new Point(-1, -1);
        for (Map.Entry<Character, Point> entry: this.keysRemaining.entrySet()) {
            System.out.println(entry);
            Point lowerPoint = entry.getValue();
            Point upperPoint = this.doors.get(Character.toUpperCase(entry.getKey()));
            if (distDict.containsKey(lowerPoint) && distDict.containsKey(upperPoint)) {
                int totalDist = distDict.get(lowerPoint) + distDict.get(upperPoint);
                if (totalDist < currentDist) {
                    currentDist = totalDist;
                    currentPoint = lowerPoint;
                }
            }
        }
        return currentPoint;
    }


}
