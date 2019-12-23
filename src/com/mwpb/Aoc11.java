package com.mwpb;

import java.util.*;

enum Direction {
    NORTH, EAST, SOUTH, WEST;

    Direction turn(long m) {
        if (m == 0) {
            switch (this) {
                case NORTH: return WEST;
                case EAST: return NORTH;
                case SOUTH: return EAST;
                case WEST: return SOUTH;
            }
        } else {
            switch (this) {
                case NORTH: return EAST;
                case EAST: return SOUTH;
                case SOUTH: return WEST;
                case WEST: return NORTH;
            }
        }
        return this;
    }
}

public class Aoc11 {

    Intcode intcode;
    long[][] canvas;

    Aoc11(String instructionString) {
        this.intcode = new Intcode(instructionString);
        this.canvas = new long[60][200];
    }

    int runRobot(boolean startOnWhite) {
        Set<String> panelsPainted = new HashSet<>();
        int x = 100;
        int y = 35;
        boolean finished = false;
        Direction direction = Direction.NORTH;
        if (startOnWhite) {
            this.canvas[y][x] = 1;
        }
        while (!finished) {
            intcode.input = new LinkedList<Integer>(List.of((int) this.canvas[y][x]));
//            System.out.println(String.format("%d, %d", x, y));
            Optional<Long> n = this.intcode.run();
            if (n.isEmpty()) {
                finished = true;
            } else {
                this.canvas[y][x] = n.get();
                panelsPainted.add(String.format("%d,%d", x, y));
//                System.out.println(this.canvas[y][x]);
                Optional<Long> l = this.intcode.run();
                if (l.isEmpty()) {
                    finished = true;
                } else {
                    direction = direction.turn(l.get());
                    switch (direction) {
                        case NORTH: y++; break;
                        case SOUTH: y--; break;
                        case EAST: x++; break;
                        case WEST: x--; break;
                    }
                }
            }
        }
        return panelsPainted.size();
    }

}
