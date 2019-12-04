package com.mwpb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Aoc1Test {

    @Test
    void getFuel() throws FileNotFoundException {
        Aoc1 aoc1 = new Aoc1();
        Assertions.assertEquals(3256794, aoc1.getFuel());
    }
}