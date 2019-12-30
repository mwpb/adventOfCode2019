package com.mwpb;

import java.util.*;

public class Aoc17 {

    Intcode intcode;

    Aoc17(String instructionsString) {
        this.intcode = new Intcode(instructionsString);
    }

    void printView(char[][] view) {
        for (char[] line: view) {
            for (char c: line) {
                System.out.print(c);
            }
            System.out.print("\n");
        }
    }

    char[][] getView() {
        boolean finished = false;
        String viewString = "";
        while (!finished) {
            Optional<Long> output = this.intcode.run();
            if (output.isEmpty()) {
                break;
            } else {
                long l = output.get();
                viewString += Character.toString((char) (int) l);
            }
        }
        String[] lineStrings = viewString.split("\\r?\\n");
        char[][] view = new char[lineStrings.length][];
        for (int i = 0; i < lineStrings.length; i++) {
            view[i] = lineStrings[i].toCharArray();
        }
        this.printView(view);
        return view;
    }

    boolean isIntersection(char[][] view, int x, int y) {
        return x != 0 && y != 0 && y != view.length - 1 && x != view[0].length - 1 &&
            view[y][x] == '#' && view[y - 1][x] == '#' && view[y + 1][x] == '#'
                && view[y][x - 1] == '#' && view[y][x + 1] == '#';
    }

    int getSumOfAlignmentParameters(char[][] view) {
        int sum = 0;
        for (int i = 0; i < view.length; i++) {
            for (int j = 0; j < view[0].length; j++) {
                if (this.isIntersection(view, j, i)) {
                    sum += i * j;
                }
            }
        }
        System.out.println(sum);
        return sum;
    }

}
