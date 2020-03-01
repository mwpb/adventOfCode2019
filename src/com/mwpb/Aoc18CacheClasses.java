package com.mwpb;

import java.util.Set;

class Edge implements Comparable<Edge> {
	int length;
	char end;
	
	Edge(int length, char end) {
		this.length = length;
		this.end = end;
	}
	
	public String toString() {
		return String.format("%c:%d", this.end, this.length);
	}

	@Override
	public int compareTo(Edge o) {
		if (this.length < o.length) {
			return -1;
		} else if (this.length > o.length) {
			return +1;
		}
		return 0;
	}
	
}

class PointState {
	char c;
	Set<Character> keysCollected;
	
	PointState(char c, Set<Character> keysCollected) {
		this.c = c;
		this.keysCollected = keysCollected;
	}
	
	public String toString() {
		return String.format("%c, %s", this.c, this.keysCollected);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointState state = (PointState) o;
//        System.out.println(this.c == state.c && state.keysCollected.equals(this.keysCollected));
        return state.c == this.c && state.keysCollected.equals(this.keysCollected);
    }

    @Override
    public int hashCode() {
        return 0;
    }
	
}

class StepsState {
	PointState ps;
	int numberOfKeysRemaining;
	
	StepsState(PointState ps, int nFind) {
		this.ps = ps;
		this.numberOfKeysRemaining = nFind;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StepsState state = (StepsState) o;
        return state.numberOfKeysRemaining == this.numberOfKeysRemaining && state.ps.equals(this.ps);
    }

    @Override
    public int hashCode() {
        return 0;
    }
    
    public String toString() {
    	return String.format("%s, %d", this.ps, this.numberOfKeysRemaining);
    }
}

class RobotsState {
	Set<Character> robots;
	Set<Character> keysCollected;
	int numberOfKeysRemaining;
	
	RobotsState(Set<Character> robots, Set<Character> keysCollected, int nFind) {
		this.robots = robots;
		this.keysCollected = keysCollected;
		this.numberOfKeysRemaining = nFind;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RobotsState rs = (RobotsState) o;
		return this.robots.equals(rs.robots) && this.keysCollected.equals(rs.keysCollected);// && this.nFind == rs.nFind;
	}
	
	@Override
    public int hashCode() {
        return 0;
    }
	
	public String toString() {
		return String.format("%s, %s, %d", this.robots, this.keysCollected, this.numberOfKeysRemaining);
	}
	
}