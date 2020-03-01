package com.mwpb;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.text.html.Option;
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
    
    Point(Point p) {
    	this.x = p.x;
    	this.y = p.y;
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
    int y0;
    int x1;
    int y1;
    char direction;
    int magnitude;
    int xmin;
    int xmax;
    int ymin;
    int ymax;
    String orientation;

    Line(int x0, int y0, char direction, int magnitude) {
        this.x0 = x0;
        this.y0 = y0;
        this.direction = direction;
        this.magnitude = magnitude;
        if (this.direction == 'R') {
            this.y1 = this.y0;
            this.x1 = this.x0 + this.magnitude;
        } else if (this.direction == 'L') {
            this.y1 = this.y0;
            this.x1 = this.x0 - this.magnitude;
        } else if (this.direction == 'U') {
            this.y1 = this.y0 + this.magnitude;
            this.x1 = this.x0;
        } else if (this.direction == 'D') {
            this.y1 = this.y0 - this.magnitude;
            this.x1 = this.x0;
        }
        this.xmin = Math.min(this.x0, this.x1);
        this.xmax = Math.max(this.x0, this.x1);
        this.ymin = Math.min(this.y0, this.y1);
        this.ymax = Math.max(this.y0, this.y1);
        if (this.direction == 'L' || this.direction == 'R') {
            this.orientation = "hor";
        } else {
            this.orientation = "ver";
        }
    }

    public String toString() {
        return String.format("%c %d from (%d, %d)", this.direction, this.magnitude, this.x0, this.y0);
    }
}

public class Aoc3 {

    List<Line> path1;
    List<Line> path2;
    Set<Point> intersections;

    Aoc3() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("aoc3-1-input.txt"));
        this.path1 = this.getPath(scanner.nextLine().split(","));
        this.path2 = this.getPath(scanner.nextLine().split(","));
        this.intersections = new HashSet<Point>();
    }

    Aoc3(String pathString1, String pathString2) {
        this.path1 = this.getPath(pathString1.split(","));
        this.path2 = this.getPath(pathString2.split(","));
        this.intersections = new HashSet<Point>();
    }

    List<Line> getPath(String[] pathStrings) {
        List<Line> path = new LinkedList<Line>();
        int lx = 0;
        int ly = 0;
        for (String lineString: pathStrings) {
            char direction = lineString.charAt(0);
            int magnitude = Integer.parseInt(lineString.substring(1));
            Line l = new Line(lx, ly, direction, magnitude);
            lx = l.x1;
            ly = l.y1;
            path.add(l);
        }
        return path;
    }

    List<Point> lineIntersections(Line l1, Line l2) {
        List<Point> crossings = new LinkedList<Point>();
        if (l1.orientation == l2.orientation && (l1.direction == 'R' || l1.direction == 'L')) {
            int xmaxmin = Math.max(l1.xmin, l2.xmin);
            int xminmax = Math.min(l1.xmax, l2.xmax);
            if (l1.y0 == l2.y0) {
                for (int i = xmaxmin; i <= xminmax; i++) {
                    crossings.add(new Point(i, l1.y0));
                }
            }
        } else if (l1.orientation == l2.orientation && (l1.direction == 'U' || l1.direction == 'D')) {
            int ymaxmin = Math.max(l1.ymin, l2.ymin);
            int yminmax = Math.min(l1.ymax, l2.ymax);
            if (l1.x0 == l2.x0) {
                for (int i = ymaxmin; i <= yminmax; i++) {
                    crossings.add(new Point(l1.x0, i));
                }
            }
        } else if (l1.orientation != l2.orientation && (l1.direction == 'L' || l1.direction == 'R')) {
            if ((l1.y0 <= l2.ymax && l2.ymin <= l1.y0) && (l2.x0 <= l1.xmax && l1.xmin <= l2.x0)) {
                crossings.add(new Point(l2.x0, l1.y0));
            }
        } else if (l1.orientation != l2.orientation && (l1.direction == 'U' || l1.direction == 'D')) {
            if ((l2.y0 <= l1.ymax && l1.ymin <= l2.y0) && (l1.x0 <= l2.xmax && l2.xmin <= l1.x0)) {
                crossings.add(new Point(l1.x0, l2.y0));
            }
        }
        return crossings;
    }

    void addIntersections(Line l1, Line l2) {
        List<Point> pointList = this.lineIntersections(l1, l2);
        for (Point p: pointList) {
            this.intersections.add(p);
        }
    }

    void getIntersections() {
        for (Line l1: this.path1) {
            for (Line l2: this.path2) {
                this.addIntersections(l1, l2);
            }
        }
    }

    int distanceToFirstHit(List<Line> path, Point p) {
        int dist = 0;
        Line constLine = new Line(p.x, p.y, 'R', 0);
        for (Line l: path) {
            List<Point> intersectionOpt = this.lineIntersections(l, constLine);
            if (intersectionOpt.size() == 0) {
                dist += l.magnitude;
            } else {
                return dist + Math.abs(l.x0-p.x) + Math.abs(l.y0-p.y);
            }
        }
        return dist;
    }

    int getDistance() {
        this.getIntersections();
        int currentDistance = Integer.MAX_VALUE;
        for (Point intersection: this.intersections) {
            int dist = Math.abs(intersection.x) + Math.abs(intersection.y);
            if (dist < currentDistance && dist != 0) {
                currentDistance = dist;
            }
        }
        return currentDistance;
    }

    int getMinimalSignalDelay() {
        this.getIntersections();
        int minimalSignalDelay = Integer.MAX_VALUE;
        for (Point intersection: this.intersections) {
            int dist1 = this.distanceToFirstHit(this.path1, intersection);
            int dist2 = this.distanceToFirstHit(this.path2, intersection);
            int dist = dist1 + dist2;
            if (dist1 + dist2 < minimalSignalDelay && dist != 0) {
                minimalSignalDelay = dist;
            }

        }
        return minimalSignalDelay;
    }
}
