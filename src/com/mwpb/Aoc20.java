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
}

public class Aoc20 {

    ArrayList<ArrayList<Character>> maze;
    Map<Point, ArrayList<WeightedEdge>> adjList;
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

    void getGraph() {
        for(int i = 0; i < this.maze.size(); i++) {
            for(int j = 0; j < this.maze.get(0).size(); j++) {
                if (Character.isLetter(this.maze.get(i).get(j))) {
                    this.addNode(new Point(j, i));
                }
            }
        }
        // add portals to adjList
        // use bfs to add travel distances to adjList
    }
}