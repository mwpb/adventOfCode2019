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
    void getGraph() throws FileNotFoundException {
        Aoc20 aoc20 = new Aoc20("./aoc20.txt");
        aoc20.getGraph();
        System.out.println(aoc20.nodes);
        System.out.println(aoc20.adjList);
    }
}
