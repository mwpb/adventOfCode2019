package com.mwpb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class Aoc2Test {

    @Test
    void processCommands() throws FileNotFoundException {
        Aoc2 aoc2test = new Aoc2("1,9,10,3,2,3,11,0,99,30,40,50");
        aoc2test.processCommands();
        Assertions.assertEquals("3500,9,10,70,2,3,11,0,99,30,40,50,", aoc2test.getCommandString());

        Aoc2 aoc2test2 = new Aoc2("1,0,0,0,99");
        aoc2test2.processCommands();
        Assertions.assertEquals("2,0,0,0,99,", aoc2test2.getCommandString());

        Aoc2 aoc2test3 = new Aoc2("2,3,0,3,99");
        aoc2test3.processCommands();
        Assertions.assertEquals("2,3,0,6,99,", aoc2test3.getCommandString());

        Aoc2 aoc2test4 = new Aoc2("2,4,4,5,99,0");
        aoc2test4.processCommands();
        Assertions.assertEquals("2,4,4,5,99,9801,", aoc2test4.getCommandString());

        Aoc2 aoc2test5 = new Aoc2("1,1,1,4,99,5,6,0,99");
        aoc2test5.processCommands();
        Assertions.assertEquals("30,1,1,4,2,5,6,0,99,", aoc2test5.getCommandString());

        Aoc2 aoc2 = new Aoc2();
        aoc2.commands[1] = 12;
        aoc2.commands[2] = 2;
        aoc2.processCommands();
        Assertions.assertEquals("3895705,12,2,2,1,1,2,3,1,3,4,3,1,5,0,3,2,1,9,36,1,19,5,37,2,23,13,185,1,10,27,189,2,31,6,378,1,5,35,379,1,39,10,383,2,9,43,1149,1,47,5,1150,2,51,9,3450,1,13,55,3455,1,13,59,3460,1,6,63,3462,2,13,67,17310,1,10,71,17314,2,13,75,86570,1,5,79,86571,2,83,9,259713,2,87,13,1298565,1,91,5,1298566,2,9,95,3895698,1,99,5,3895699,1,2,103,3895701,1,10,107,0,99,2,14,0,0,",
                aoc2.getCommandString());
    }

    @Test
    void run() throws FileNotFoundException {
        Aoc2 aoc2 = new Aoc2();
        Assertions.assertEquals(19690720, aoc2.run(64, 17));
        // so answer is 100 * 64 + 17 = 6417
    }
}