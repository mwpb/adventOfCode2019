package com.mwpb;
import java.util.*;
import java.io.*;

public class Aoc1 {

    List<Integer> masses;

    Aoc1() throws FileNotFoundException {
        List<Integer> masses = new LinkedList<Integer>();
        Scanner scanner = new Scanner(new File("./aoc1-1-input.txt"));
        while (scanner.hasNext()) {
            masses.add(Integer.parseInt(scanner.nextLine()));
        }
        this.masses = masses;
    }

    int getFuel() {
        int fuel = 0;
        for (int mass: this.masses) {
            fuel += (mass / 3) - 2;
        }
        return fuel;
    }

    int getModuleFuel(int mass) {
        int fuel = Math.max((mass / 3) - 2, 0);
        int currentFuel = fuel;
        while (currentFuel > 0) {
            currentFuel = Math.max((currentFuel / 3) - 2, 0);
            fuel += currentFuel;
        }
        return fuel;
    }

    int getAdjustedFuel() {
        int fuel = 0;
        for (int mass: this.masses) {
            fuel += this.getModuleFuel(mass);
        }
        return fuel;
    }
}
