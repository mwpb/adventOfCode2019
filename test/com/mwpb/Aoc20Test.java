package com.mwpb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Aoc20Test {
    @Test
    void testConstructor() throws FileNotFoundException {
        Aoc20 aoc20 = new Aoc20("./aoc20.txt");
        System.out.println(aoc20.maze);
    }

    @Test
    void testGetGraph() throws FileNotFoundException {
        Aoc20 aoc20 = new Aoc20("./aoc20-test.txt");
        aoc20.getGraph();
        System.out.println(aoc20.nodes);
        System.out.println(aoc20.adjList);
        System.out.println(aoc20.initPoint);
        System.out.println(aoc20.endPoint);
    }

    @Test
    void testFindDistance() throws FileNotFoundException {
        Aoc20 aoc20 = new Aoc20("./aoc20-test.txt");
        aoc20.getGraph();
        int d = aoc20.dijkstra(aoc20.initPoint, aoc20.endPoint);
        Assertions.assertEquals(23, d);

        Aoc20 aoc20test2 = new Aoc20("./aoc20-test2.txt");
        aoc20test2.getGraph();
        int d2 = aoc20test2.dijkstra(aoc20test2.initPoint, aoc20test2.endPoint);
        Assertions.assertEquals(58, d2);

        Aoc20 aoc20final = new Aoc20("./aoc20.txt");
        aoc20final.getGraph();
        int d3 = aoc20final.dijkstra(aoc20final.initPoint, aoc20final.endPoint);
        System.out.println(d3);
    }
}
