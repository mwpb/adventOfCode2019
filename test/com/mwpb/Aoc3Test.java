package com.mwpb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class Aoc3Test {

    @Test
    void getDistance() throws FileNotFoundException {

        Aoc3 aoc3test = new Aoc3("R8,U5,L5,D3","U7,R6,D4,L4");
        Assertions.assertEquals(6, aoc3test.getDistance());

        Aoc3 aoc3test2 = new Aoc3("R75,D30,R83,U83,L12,D49,R71,U7,L72","U62,R66,U55,R34,D71,R55,D58,R83");
        Assertions.assertEquals(159, aoc3test2.getDistance());

        Aoc3 aoc3test3 = new Aoc3("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51","U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");
        Assertions.assertEquals(135, aoc3test3.getDistance());

        Aoc3 aoc3 = new Aoc3();
        Assertions.assertEquals(1337, aoc3.getDistance());
    }

    @Test
    void getMinimalSignalDelay() throws FileNotFoundException {

        Aoc3 aoc3test = new Aoc3("R8,U5,L5,D3","U7,R6,D4,L4");
        Assertions.assertEquals(30, aoc3test.getMinimalSignalDelay());

        Aoc3 aoc3test2 = new Aoc3("R75,D30,R83,U83,L12,D49,R71,U7,L72","U62,R66,U55,R34,D71,R55,D58,R83");
        Assertions.assertEquals(610, aoc3test2.getMinimalSignalDelay());

        Aoc3 aoc3test3 = new Aoc3("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51","U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");
        Assertions.assertEquals(410, aoc3test3.getMinimalSignalDelay());

        Aoc3 aoc3 = new Aoc3();
        System.out.println(aoc3.getMinimalSignalDelay());
        Assertions.assertEquals(65356, aoc3.getMinimalSignalDelay());
    }

    @Test
    void addIntersections() throws FileNotFoundException {

        Aoc3 aoc3test = new Aoc3("R8,U5,L5,D3","U7,R6,D4,L4");
        Line l1 = new Line(0, 0, 'R', 10);
        Line l2 = new Line(2, -2, 'U', 13);
        aoc3test.addIntersections(l1, l2);
        Set<Point> expected = new HashSet<Point>();
        expected.add(new Point(2, 0));
        Assertions.assertEquals(expected, aoc3test.intersections);

        Line l3 = new Line(4, 3, 'R', 3);
        Line l4 = new Line(6, 3, 'R', 10);
        expected.add(new Point(6, 3));
        expected.add(new Point(7, 3));
        aoc3test.addIntersections(l3, l4);
        Assertions.assertEquals(expected, aoc3test.intersections);
    }
}