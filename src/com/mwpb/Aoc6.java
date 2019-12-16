package com.mwpb;

import java.io.*;
import java.util.*;

public class Aoc6 {

    Map<String, List<String>> adjList;

    Aoc6() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("aoc6.txt"));
        Map<String, List<String>> adjList = new HashMap<String, List<String>>();
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("\\)");
            List<String> newList0 = new LinkedList<String>();
            newList0.addAll(adjList.getOrDefault(line[0], new LinkedList<String>()));
            newList0.add(line[1]);
            adjList.put(line[0], newList0);
            List<String> newList1 = new LinkedList<String>();
            newList1.addAll(adjList.getOrDefault(line[1], new LinkedList<String>()));
            newList1.add(line[0]);
            adjList.put(line[1], newList1);
        }
        this.adjList = adjList;
    }

    Aoc6(String edgeList) {
        String[] lines = edgeList.split("[\\r\\n]+");
        Map<String, List<String>> adjList = new HashMap<String, List<String>>();
        for (String edge: lines) {
            String[] line = edge.split("\\)");
            List<String> newList0 = new LinkedList<String>();
            newList0.addAll(adjList.getOrDefault(line[0], new LinkedList<String>()));
            newList0.add(line[1]);
            adjList.put(line[0], newList0);
            List<String> newList1 = new LinkedList<String>();
            newList1.addAll(adjList.getOrDefault(line[1], new LinkedList<String>()));
            newList1.add(line[0]);
            adjList.put(line[1], newList1);
        }
        this.adjList = adjList;
    }

    int countOrbits() {
        Map<String, Integer> distDict = this.getDistances("COM", ")");
        int count = 0;
        for (int i: distDict.values()) {
            count += i;
        }
        return count;
    }

    Map<String, Integer> getDistances(String start, String end) {
        Queue<String> q = new LinkedList<String>();
        q.add(start);
        Map<String, Integer> distDict = new HashMap<String, Integer>();
        distDict.put(start, 0);
        int count = 0;
        while (q.size() > 0) {
            String vertex = q.remove();
            if (vertex.equals(end)) {
                break;
            }
            int dist = distDict.getOrDefault(vertex, 0) + 1;
            for (String nextVertex: this.adjList.getOrDefault(vertex, List.of())) {
                if (!distDict.containsKey(nextVertex)) {
                    q.add(nextVertex);
                    distDict.put(nextVertex, dist);
                    count += dist;
                }
            }
        }
        return distDict;
    }

    int orbitalTransfers() {
        System.out.println(this.adjList);
        System.out.println(this.getDistances("YOU", "SAN"));
        return this.getDistances("YOU", "SAN").get("SAN") - 2;
    }
}
