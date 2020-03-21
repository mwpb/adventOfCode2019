package com.mwpb;

 import org.junit.jupiter.api.Assertions;
 import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Aoc20_2Test {

    @Test
    void testFindDistance() throws FileNotFoundException {
        Aoc20_2 aoc20 = new Aoc20_2("./aoc20-test.txt");
        aoc20.getGraph();
        int d = aoc20.dijkstra(aoc20.initState, aoc20.endState);
        Assertions.assertEquals(26, d);

         Aoc20_2 aoc20test2 = new Aoc20_2("./aoc20_2-test2.txt");
         aoc20test2.getGraph();
         int d2 = aoc20test2.dijkstra(aoc20test2.initState, aoc20test2.endState);
         Assertions.assertEquals(396, d2);

         Aoc20_2 aoc20final = new Aoc20_2("./aoc20.txt");
         aoc20final.getGraph();
         System.out.println(aoc20final.nodes);
         System.out.println(aoc20final.adjList);
         System.out.println(aoc20final.initState);
         System.out.println(aoc20final.endState);
         int d3 = aoc20final.dijkstra(aoc20final.initState, aoc20final.endState);
         Assertions.assertEquals(6834, d3);
    }
}
