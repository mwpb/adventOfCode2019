package com.mwpb;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Point {

    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return String.format("(%d, %d)", this.x, this.y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final Point p = (Point) obj;
        return (this.x == p.x) && (this.y == p.y);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

class Line {
    int x0;
    int x1;
    int y0;
    int y1;

    Line(int x0, int x1, int y0, int y1) {
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
    }

}

public class Aoc3 {

    String[] path1;
    String[] path2;
    Set<Point> points1;
    Set<Point> points2;

    Aoc3() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("aoc3-1-input.txt"));
        this.path1 = scanner.nextLine().split(",");
        this.path2 = scanner.nextLine().split(",");
        this.addPoints();
    }

    Aoc3(String pathString1, String pathString2) {
        this.path1 = pathString1.split(",");
        this.path2 = pathString2.split(",");
        this.addPoints();
    }

    Set<Point> getPoints(String[] path) {
        Set<Point> points = new HashSet<Point>();
        int x = 0;
        int y = 0;
        for (String command: path) {
            char direction = command.charAt(0);
            int magnitude = Integer.parseInt(command.substring(1));
//            System.out.println(String.format("%d in %s", magnitude, direction));
            if (direction == 'U') {
                for (int i = 0; i < magnitude; i++) {
                    y += 1;
                    points.add(new Point(x, y));
                }
            } else if (direction == 'D') {
                for (int i = 0; i < magnitude; i++) {
                    y -= 1;
                    points.add(new Point(x, y));
                }
            } else if (direction == 'L') {
                for (int i = 0; i < magnitude; i++) {
                    x -= 1;
                    points.add(new Point(x, y));
                }
            } else if (direction == 'R') {
                for (int i = 0; i < magnitude; i++) {
                    x += 1;
                    points.add(new Point(x, y));
                }
            }
        }
        return points;
    }

    boolean linesIntersect(Line l1, Line l2) {
        boolean notIntersectX = (Math.min(l1.x0, l1.x1) > Math.max(l2.x0, l2.x1)) || (Math.min(l2.x0, l2.x1) > Math.max(l1.x0, l1.x1));
        boolean notIntersectY = (Math.min(l1.y0, l1.y1) > Math.max(l2.y0, l2.y1)) || (Math.min(l2.y0, l2.y1) > Math.max(l1.y0, l1.y1));
        return !notIntersectX && !notIntersectY;
    }

    boolean isIntersection(int x, int y) {
        return (this.points1.contains(new Point(x, y))) && (this.points2.contains(new Point(x, y)));
    }

    boolean checkCircle(int r) {
        for (int i = 0; i <= r; i++) {
            if (this.isIntersection(r-i, i)) {
                return true;
            }
            if (this.isIntersection(-i, r-i)) {
                return true;
            }
            if (this.isIntersection(i-r, -i)) {
                return true;
            }
            if (this.isIntersection(i, i-r)) {
                return true;
            }
        }
        return false;
    }

    void addPoints() {
        this.points1 = this.getPoints(this.path1);
        this.points2 = this.getPoints(this.path2);
    }

    int getDistance() {
        int r = 1;
        boolean finished = false;
        while (!finished) {
//            System.out.println(r);
            if (this.checkCircle(r)) {
                finished = true;
            } else {
                r++;
            }
        }
        return r;
    }
}
