package com.mwpb;

import java.util.*;

class Vector implements Comparable<Vector> {
    double angle;
    double distance;
    int x;
    int y;

    Vector(int y0, int x0, int y1, int x1) {
        double posNegAngle = (Math.PI / 2) + Math.atan2(y1 - y0, x1 - x0);
        this.angle = (posNegAngle < 0) ? (posNegAngle + 2 * Math.PI) : posNegAngle;
        this.distance = Math.sqrt(Math.pow(y1 - y0, 2) + Math.pow(x1 - x0, 2));
        this.x = x1;
        this.y = y1;
    }

    @Override
    public int compareTo(Vector o) {
        if (this.angle < o.angle) {
            return -1;
        } else if (this.angle > o.angle) {
            return 1;
        } else {
            if (this.distance < o.distance) {
                return -1;
            } else if (this.distance > o.distance) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("(%d, %d: %s)", this.x, this.y, this.angle);
    }
}

public class Aoc10 {

    char[][] map;
    int height;
    int width;
    int numberInLineOfSight;
    List<Vector> vectors;
    List<Vector> bestVectors;

    Aoc10(String input) {
        String[] lines = input.split("\n");
        char[][] map = new char[lines.length][];
        for (int i = 0; i < lines.length; i++) {
            map[i] = lines[i].toCharArray();
        }
        this.map = map;
        this.height = lines.length;
        this.width = lines[0].length();
    }

    int countInLineOfSight(int y, int x) {
        List<Vector> vectors = new LinkedList<Vector>();
        Set<Double> angles = new HashSet<Double>();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (this.map[i][j] == '#' && !(x == j && y == i)) {
                    Vector vector = new Vector(y, x, i, j);
                    vectors.add(vector);
                    angles.add(vector.angle);
                }
            }
        }
        this.vectors = vectors;
//        for (UnitVector vector: vectors) {
//            System.out.println(vector);
//        }
        return angles.size();
    }

    String bestStation() {
        int bestCount = 0;
        String coordinates = "";
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (this.map[i][j] == '#') {
                    int inLineOfSight = this.countInLineOfSight(i, j);
                    if (inLineOfSight > bestCount) {
                        bestCount = inLineOfSight;
                        coordinates = String.format("%d,%d", j, i);
                        this.bestVectors = new LinkedList<>(this.vectors);
                    }
//                    System.out.println(String.format("%d,%d:%d", j, i, inLineOfSight));
                }
            }
        }
        this.numberInLineOfSight = bestCount;
        return coordinates;
    }

    int twoHundreth() {
        Collections.sort(this.bestVectors);
        System.out.println(this.bestVectors);
        Map<Double, List<Vector>> previousAtSameAngle = new HashMap<>();
        List<Vector> addFullRotations = new LinkedList<Vector>();
        for (Vector vector: this.bestVectors) {
            List<Vector> old = previousAtSameAngle.getOrDefault(vector.angle, new LinkedList<Vector>());
            vector.angle += 2 * Math.PI * old.size();
            old.add(vector);
            previousAtSameAngle.put(vector.angle, old);
        }
        Collections.sort(this.bestVectors);
        System.out.println(this.bestVectors);
        System.out.println(this.bestVectors.get(199));
        Vector twoHundredth = this.bestVectors.get(199);
        return twoHundredth.x * 100 + twoHundredth.y;
    }
}
