package com.mwpb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class Aoc1Test {

    @Test
    void getFuel() throws FileNotFoundException {
        Aoc1 aoc1 = new Aoc1();
        Assertions.assertEquals(3256794, aoc1.getFuel());
    }

    @Test
    void getAdjustedFuel() throws FileNotFoundException {
        Aoc1 aoc1 = new Aoc1();
        Assertions.assertEquals(2, aoc1.getModuleFuel(14));
        Assertions.assertEquals(966, aoc1.getModuleFuel(1969));
        Assertions.assertEquals(50346, aoc1.getModuleFuel(100756));
        Assertions.assertEquals(4882337, aoc1.getAdjustedFuel());
    }
}