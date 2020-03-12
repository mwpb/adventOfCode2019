package com.mwpb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class WeightedEdge {
    int length;
    Point end;
    WeightedEdge(int length, Point end) {
        this.length = length;
        this.end = end;
    }
    public String toString() {
        return String.format("%s:%d", this.end, this.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightedEdge edge = (WeightedEdge) o;
        return this.length == edge.length && this.end.x == edge.end.x && this.end.y == edge.end.y;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

public class Aoc20 {

    ArrayList<ArrayList<Character>> maze;
    Map<Point, Set<WeightedEdge>> adjList;
    Map<Point, Set<Character>> nodes;

    Aoc20(String filepath) throws FileNotFoundException {
        this.adjList = new HashMap<>();
        this.nodes = new HashMap<>();
        this.maze = new ArrayList<ArrayList<Character>>();

        Scanner scanner = new Scanner(new File(filepath));
        while(scanner.hasNextLine()) {
            ArrayList<Character> al = new ArrayList<>();
            char[] line = scanner.nextLine().toCharArray();
            for (char c: line) {
                al.add(c);
            }
            this.maze.add(al);
        }
    }

    boolean inBounds(Point p) {
        return (p.x >= 0) && (p.y >= 0) && (p.x < this.maze.get(0).size()) && (p.y < this.maze.size());
    }

    boolean isClear(Point p) {
        return this.maze.get(p.y).get(p.x) == '.';
    }

    ArrayList<Point> getNeighbours(Point p) {
        ArrayList<Point> neighbours = new ArrayList<>();
        int x = p.x;
        int y = p.y;
        for (Point next: Set.of(new Point(x+1,y), new Point(x-1,y), new Point(x,y-1), new Point(x,y+1))) {
            if (this.inBounds(next)) {
                neighbours.add(next);
            }
        }
        return neighbours;
    }

    void addNode(Point p) {
        ArrayList<Point> neighbours = new ArrayList<Point>();
        neighbours.addAll(this.getNeighbours(p));
        char firstChar = this.maze.get(p.y).get(p.x);
        
        Point secondPoint = null;
        Point clearedPoint = null;
        for (Point neighbour: neighbours) {
            char c = this.maze.get(neighbour.y).get(neighbour.x);
            if (Character.isLetter(c)) {
                secondPoint = neighbour;
            } else if (c == '.') {
                clearedPoint = neighbour;
            }
        }
        char secondChar = this.maze.get(secondPoint.y).get(secondPoint.x);
        for(Point point: this.getNeighbours(secondPoint)) {
            if (this.maze.get(point.y).get(point.x) == '.') {
                clearedPoint = point;
            }
        }
        Set<Character> label = new HashSet<>();
        label.add(firstChar);
        label.add(secondChar);
        this.nodes.put(clearedPoint, label);
    }

    void addEdge(Point p1, Point p2, int length) {
        Set<WeightedEdge> tmpList = new HashSet<WeightedEdge>();
        tmpList.addAll(this.adjList.getOrDefault(p1, new HashSet<>()));
        tmpList.add(new WeightedEdge(length, p2));
        this.adjList.put(p1, tmpList);

        Set<WeightedEdge> tmpList2 = new HashSet<WeightedEdge>();
        tmpList2.addAll(this.adjList.getOrDefault(p2, new HashSet<>()));
        tmpList2.add(new WeightedEdge(length, p1));
        this.adjList.put(p2, tmpList2);
    }

    void bfs(Point p) {
        Map<Point, Integer> distances = new HashMap<>();
        Deque<Point> q = new ArrayDeque<>();

        q.add(p);
        distances.put(p, 0);

        while (q.size() > 0) {
            Point current = q.poll();
            int distance = distances.get(current);
            for (Point next: this.getNeighbours(current)) {
                if (this.isClear(next) && !distances.containsKey(next)) {
                    q.add(next);
                    distances.put(next, distance + 1);
                    if (this.nodes.containsKey(next)) {
                        this.addEdge(p, next, distance);
                    }
                }
            }
        }
    }

    void getGraph() {
        // get nodes
        for(int i = 0; i < this.maze.size(); i++) {
            for(int j = 0; j < this.maze.get(0).size(); j++) {
                if (Character.isLetter(this.maze.get(i).get(j))) {
                    this.addNode(new Point(j, i));
                }
            }
        }

        // add portals to adjList
        Map<Set<Character>, Point> labelsToPoints = new HashMap<>();
        for (Map.Entry<Point, Set<Character>> entry: this.nodes.entrySet()) {
            Point p1 = entry.getKey();
            Set<Character> labels = entry.getValue();
            if (labelsToPoints.containsKey(labels)) {
                Point p2 = labelsToPoints.get(labels);
                this.addEdge(p1, p2, 1);
                labelsToPoints.remove(labels);
            } else {
                labelsToPoints.put(labels, p1);
            }
        }

        // use bfs to add travel distances to adjList
        for (Point node: this.nodes.keySet()) {
            this.bfs(node);
        }       
    }
}