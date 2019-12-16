package com.mwpb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.sound.midi.SysexMessage;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Aoc6Test {

    @Test
    void countOrbits() throws FileNotFoundException {
        Aoc6 aoc6test = new Aoc6("COM)B\n" +
                "B)C\n" +
                "C)D\n" +
                "D)E\n" +
                "E)F\n" +
                "B)G\n" +
                "G)H\n" +
                "D)I\n" +
                "E)J\n" +
                "J)K\n" +
                "K)L");
        Assertions.assertEquals(42, aoc6test.countOrbits());

        Aoc6 aoc6test2 = new Aoc6("COM)B\n" +
                "B)C\n" +
                "C)D\n" +
                "D)E\n" +
                "E)F\n" +
                "B)G\n" +
                "G)H\n" +
                "D)I\n" +
                "E)J\n" +
                "J)K\n" +
                "K)L\n" +
                "K)YOU\n" +
                "I)SAN");
        Assertions.assertEquals(4, aoc6test2.orbitalTransfers());

        Aoc6 aoc6 = new Aoc6();
        Assertions.assertEquals(122782, aoc6.countOrbits());
        Assertions.assertEquals(271, aoc6.orbitalTransfers());
    }
}