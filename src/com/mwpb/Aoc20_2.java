package com.mwpb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class State {
    Point p;
    int layer;

    State(Point p, int layer) {
        this.p = p;
        this.layer = layer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return this.p.equals(state.p) && this.layer == state.layer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.p, this.layer);
    }

    @Override
    public String toString() {
        return String.format("%s: %d", this.p, this.layer);
    }

}

class StateEdge implements Comparable<StateEdge> {
    int length;
    State end;
    StateEdge(int length, State end) {
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
        StateEdge edge = (StateEdge) o;
        return this.length == edge.length && this.end.equals(edge.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.length, this.end);
    }

    @Override
	public int compareTo(StateEdge o) {
		if (this.length < o.length) {
			return -1;
		} else if (this.length > o.length) {
			return +1;
		}
		return 0;
	}
}

public class Aoc20_2 {

    ArrayList<ArrayList<Character>> maze;
    Map<Point, Set<WeightedEdge>> adjList;
    Map<Point, Set<Character>> nodes;
    State initState, endState;

    Aoc20_2(String filepath) throws FileNotFoundException {
        this.adjList = new HashMap<>();
        this.nodes = new HashMap<>();
        this.maze = new ArrayList<ArrayList<Character>>();
        this.adjSetCache = new HashMap<>();
        this.onOutsideCache = new HashMap<>();
        this.dijkstraCache = new HashMap<>();

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

        if (label.equals(Set.of('A'))) {
            this.initState = new State(clearedPoint, 0);
        } else if (label.equals(Set.of('Z'))) {
            this.endState = new State(clearedPoint, 0);
        }
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
                        this.addEdge(p, next, distance + 1);
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

    Map<Point, Boolean> onOutsideCache;

    boolean isOnOutside(Point p) {
        if (this.onOutsideCache.containsKey(p)) {
            return this.onOutsideCache.get(p);
        } else {
            boolean isOnOutside = false;
            if (p.y == 2) isOnOutside = true;
            if (p.y == (this.maze.size() - 3)) isOnOutside = true;
            if (p.x == 2) isOnOutside = true;
            if (p.x == (this.maze.get(0).size() - 3)) isOnOutside = true;
            this.onOutsideCache.put(p, isOnOutside);
            return isOnOutside;
        }
    }

    Map<State, Set<StateEdge>> adjSetCache;

    Set<StateEdge> getAdjSet(State state) {
        if (this.adjSetCache.containsKey(state)) {
            return this.adjSetCache.get(state);
        } else {
            Set<StateEdge> adjSet = new HashSet<>();
            for (WeightedEdge edge: this.adjList.get(state.p)) {
                if (edge.length == 1) { // edge is portal
                    int layer = this.isOnOutside(state.p) ? state.layer - 1 : state.layer + 1;
                    if (layer >= 0 && layer <= this.nodes.size()) {
                        adjSet.add(new StateEdge(edge.length, new State(edge.end, layer)));
                    }
                } else { // edge is not portal
                	if (state.layer > 0 && (edge.end.equals(this.initState.p) || edge.end.equals(this.endState.p))) {
                		
                	} else {
                		adjSet.add(new StateEdge(edge.length, new State(edge.end, state.layer)));
                	}
                }
            }
            this.adjSetCache.put(state, adjSet);
            return adjSet;
        }
    }

    int dijkstra(State start, State end) {
        PriorityQueue<StateEdge> q = new PriorityQueue<>();
        Map<State, Integer> distances = new HashMap<>();
        Set<StateEdge> finalised = new HashSet<>();

        q.add(new StateEdge(0, start));
        distances.put(start, 0);
        
        while (q.size() > 0) {
            // System.out.println(q.size());

//        	System.out.println(q);
            StateEdge nearest = q.poll();

//            System.out.println(nearest);
            finalised.add(nearest);
            if (nearest.end.equals(end)) {
                return distances.get(end);
            }
            for (StateEdge next: this.getAdjSet(nearest.end)) {
                 if (!finalised.contains(next)) {
                    int alt = nearest.length + next.length;
                    if (distances.containsKey(next.end) && alt < distances.get(next.end)) {
                    	q.remove(new StateEdge(distances.get(next.end), next.end));
                    	distances.put(next.end, alt);
                    	q.add(new StateEdge(alt, next.end));
                    } else if (!distances.containsKey(next.end)) {
                    	distances.put(next.end, alt);
                    	q.add(new StateEdge(alt, next.end));
                    }
                 }
            }
        }
        return -1;
    }

    Map<State, Integer> dijkstraCache;

    int dijkstraRec(State start, State end) {
        System.out.println(start);
        if (this.dijkstraCache.containsKey(start)) {
            return this.dijkstraCache.get(start);
        } else {
            if (start.equals(end)) {
                return 0;
            }
            int dist = Integer.MAX_VALUE;
            for (StateEdge next: this.getAdjSet(start)) {
                int alt = this.dijkstraRec(next.end, end) + next.length;
                if (alt < dist) {
                    dist = alt;
                } 
            }
            this.dijkstraCache.put(start, dist);
            return dist;
        }
    }
}