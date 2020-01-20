package com.mwpb;

import javax.crypto.spec.ChaCha20ParameterSpec;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

class State {
    char lastKey;
    Set<Character> keysLeft;
    State(char currentKey, Set<Character> keysLeft) {
        this.lastKey = currentKey;
        this.keysLeft = keysLeft;
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
        return String.format("%c: %s", this.lastKey, this.keysLeft);
    }
}

class Path {
    int length;
    Set<Character> doorsOnPath;

    Path() {
        this.length = 0;
        this.doorsOnPath = Set.of();
    }

    @Override
    public String toString() {
        return String.format("(%d, doors: %s)", this.length, this.doorsOnPath);
    }

    int getWeight(Set<Character> remainingKeys) {
        for (char door: this.doorsOnPath) {
            if (remainingKeys.contains(Character.toLowerCase(door))) {
                return Integer.MAX_VALUE / 2;
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

    List<Point> bfs(Point p1) {
        Deque<Point> q = new ArrayDeque<>(List.of(p1));
        Set<Point> visited = new HashSet<>(List.of(p1));
        Map<Point, List<Point>> path = new HashMap<>();
        path.put(p1, List.of());
        while (q.size() > 0) {
            Point current = q.remove();
            List<Point> currentPath = new LinkedList<>(path.get(current));
            currentPath.add(current);
//            System.out.println(String.format("%s: %s", current, currentPath));
            int x = current.x;
            int y = current.y;
            for (Point next: List.of(new Point(x - 1, y), new Point(x + 1, y), new Point(x, y - 1), new Point(x, y + 1))) {
//                System.out.println(String.format("Next: %s: %c", next, this.maze[next.y][next.x]));
                if (this.inBounds(next) && !visited.contains(next) && this.maze[next.y][next.x] != '#') {
                    if (this.isKey(next)) {
                        List<Point> newPoints = new LinkedList<>(currentPath);
                        newPoints.add(next);
                        Path newPath = this.createPath(newPoints);
//                        System.out.println(String.format("new points: %s", newPath));
                        this.distances.put(String.format("%c%c", this.maze[p1.y][p1.x], this.maze[next.y][next.x]), newPath);
                        this.distances.put(String.format("%c%c", this.maze[next.y][next.x], this.maze[p1.y][p1.x]), newPath);
                    }
                    path.put(next, currentPath);
                    visited.add(next);
                    q.add(next);
                }
            }
        }
        return List.of();
    }

    Path createPath(List<Point> points) {
        Path path = new Path();
        path.length = points.size();
        path.doorsOnPath = new HashSet<>();
        for (Point p: points) {
            char c = this.maze[p.y][p.x];
            if (Character.isLetter(c) && Character.isUpperCase(c)) {
                path.doorsOnPath.add(c);
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

    int getKeyDist(char key1, char key2) {
        return this.distances.get(String.format("%c%c", key1, key2)).getWeight(this.keysRemaining.keySet());
    }

    Set<Character> getCharsFromPath(String path) {
        Set<Character> chars = new HashSet<>();
        for (char c: path.toCharArray()) {
            chars.add(c);
        }
        return chars;
    }

    State getClosestUnvisitedState(Map<State, Integer> distEstimates, Set<State> visited) {
        int bestDist = Integer.MAX_VALUE / 2;
        State bestState = new State('@', Set.of());
        for (State state: distEstimates.keySet()) {
            if (!visited.contains(state)) {
                int dist = this.distances.get(String.format("%c%c", '@', state.lastKey)).getWeight(state.keysLeft);
                if (dist < bestDist) {
                    bestDist = dist;
                    bestState = state;
                }
            }
        }
        return bestState;
    }

    void dijkstra() {
//        Set<Character> keysLeft = new HashSet<>(this.keysRemaining.keySet());
        Map<State, Integer> distEstimates = new HashMap<>();
//        distEstimates.put(new State('@', keysLeft), 0);
//        keysLeft.remove(new State('@', keysLeft));
        Set<State> visited = new HashSet<>();
        State initState = new State('@', this.keysRemaining.keySet());
//        visited.add(initState);
        distEstimates.put(initState, 0);
        while (distEstimates.keySet().size() > 0) {
            State nearestState = this.getClosestUnvisitedState(distEstimates, visited);
            if (nearestState.equals(new State('@', Set.of()))) {
                break;
            }
            visited.add(nearestState);
            System.out.println(String.format("state: %s, distEstimates: %s", nearestState, distEstimates));
            Set<Character> newKeysLeft = new HashSet<>(nearestState.keysLeft);
            for (char key: newKeysLeft) {
                Set<Character> modKeysLeft = new HashSet<>(newKeysLeft);
                modKeysLeft.remove(key);
                State newState = new State(key, modKeysLeft);
                int alt = distEstimates.getOrDefault(nearestState,Integer.MAX_VALUE / 2) +
                        this.distances.get(String.format("%c%c", key, nearestState.lastKey)).getWeight(modKeysLeft);
                System.out.println(String.format("newState: %s, alt: %d", newState, alt));
                if (alt < distEstimates.getOrDefault(newState, Integer.MAX_VALUE / 2)) {
                    distEstimates.put(newState, alt);
                }
            }
            System.out.println(distEstimates);
        }
        System.out.println(distEstimates);
        return;
    }

}
