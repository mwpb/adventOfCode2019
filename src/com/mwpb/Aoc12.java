package com.mwpb;

import java.util.*;

class Moon {

    int x;
    int y;
    int z;
    int vx;
    int vy;
    int vz;

    Moon(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.vx = 0;
        this.vy = 0;
        this.vz = 0;
    }

    Moon(String input) {
        String[] triple = input.split(",");
        this.x = Integer.parseInt(triple[0]);
        this.y = Integer.parseInt(triple[1]);
        this.z = Integer.parseInt(triple[2]);
        this.vx = 0;
        this.vy = 0;
        this.vz = 0;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %d) + (%d, %d, %d)", this.x, this.y, this.z, this.vx, this.vy, this.vz);
    }

    int potentialEnergy() {
        return Math.abs(this.x) + Math.abs(this.y) + Math.abs(this.z);
    }

    int kineticEnergy() {
        return Math.abs(this.vx) + Math.abs(this.vy) + Math.abs(this.vz);
    }

    int totalEnergy() {
        return this.potentialEnergy() * this.kineticEnergy();
    }
}

public class Aoc12 {

    Moon[] moons;
    Map<String, Integer> states;

    Aoc12(String positionsString) {
        positionsString = positionsString.replaceAll("[<>=xyz]", "").replaceAll(" ", "");
        String[] positionsArray = positionsString.split("\n");
        Moon[] moons = new Moon[4];
        for (int i = 0; i < 4; i++) {
            moons[i] = new Moon(positionsArray[i]);
        }
        this.moons = moons;
        this.states = new HashMap<>();
    }

    void applyBinaryGravity(Moon moon1, Moon moon2) {
        if (moon1.x > moon2.x) {
            moon1.vx--;
        } else if (moon1.x < moon2.x) {
            moon1.vx++;
        }
        if (moon1.y > moon2.y) {
            moon1.vy--;
        } else if (moon1.y < moon2.y) {
            moon1.vy++;
        }
        if (moon1.z > moon2.z) {
            moon1.vz--;
        } else if (moon1.z < moon2.z) {
            moon1.vz++;
        }
    }

    void applyGravity() {
        for (Moon moon1: this.moons) {
            for (Moon moon2: this.moons) {
                if (moon1 != moon2) {
                    this.applyBinaryGravity(moon1, moon2);
                }
            }
        }
    }

    void applyVelocity() {
        for (Moon moon: this.moons) {
            moon.x += moon.vx;
            moon.y += moon.vy;
            moon.z += moon.vz;
        }
    }

    int totalEnergy() {
        int count = 0;
        for (Moon moon: this.moons) {
            count += moon.totalEnergy();
        }
        return count;
    }

    String getStateString() {
        return String.format("%s:%s:%s:%s", this.moons[0], this.moons[1], this.moons[2], this.moons[3]);
    }

    String getStateStringX() {
        return String.format("%d+%d:%d+%d:%d+%d:%d+%d", this.moons[0].x, this.moons[0].vx,
                this.moons[1].x, this.moons[1].vx, this.moons[2].x, this.moons[2].vx, this.moons[3].x, this.moons[3].vx);
    }

    String getStateStringY() {
        return String.format("%d+%d:%d+%d:%d+%d:%d+%d", this.moons[0].y, this.moons[0].vy,
                this.moons[1].y, this.moons[1].vy, this.moons[2].y, this.moons[2].vy, this.moons[3].y, this.moons[3].vy);
    }

    String getStateStringZ() {
        return String.format("%d+%d:%d+%d:%d+%d:%d+%d", this.moons[0].z, this.moons[0].vz,
                this.moons[1].z, this.moons[1].vz, this.moons[2].z, this.moons[2].vz, this.moons[3].z, this.moons[3].vz);
    }

    void run() {
        this.applyGravity();
        this.applyVelocity();
    }

    void run(int steps) {
        for (int i = 0; i < steps; i++) {
            this.run();
        }
    }

    long gcd(long a, long b) {
        while ( a > 0 && b > 0) {
            if (a > b) {
                a -= b;
            } else if (a < b) {
                b -= a;
            } else {
                return a;
            }
        }
        return a;
    }

    long lcm(long a, long b) {
        return (a * b) / this.gcd(a, b);
    }

    long findFirstRepeat() {
        int stepX = 0;
        while (!this.states.keySet().contains(this.getStateStringX())) {
            this.states.put(this.getStateStringX(), stepX);
            this.run();
            stepX++;
        }
        int stepY = 0;
        while (!this.states.keySet().contains(this.getStateStringY())) {
            this.states.put(this.getStateStringY(), stepY);
            this.run();
            stepY++;
        }
        int stepZ = 0;
        while (!this.states.keySet().contains(this.getStateStringZ())) {
            this.states.put(this.getStateStringZ(), stepZ);
            this.run();
            stepZ++;
        }
        return this.lcm(this.lcm(stepX, stepY), stepZ);
    }

}
