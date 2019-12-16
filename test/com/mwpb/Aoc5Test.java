package com.mwpb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.sound.midi.SysexMessage;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class Aoc5Test {

    @Test
    void processInstructions() throws FileNotFoundException {
        Aoc5 aoc5test0 = new Aoc5("1002,4,3,4,33");
        aoc5test0.processInstructions(5);

        Aoc5 aoc5 = new Aoc5();
        aoc5.processInstructions(1);
        Assertions.assertEquals(16489636, aoc5.outputs.get(aoc5.outputs.size() - 1));

        Aoc5 aoc5test = new Aoc5("3,9,8,9,10,9,4,9,99,-1,8");
        aoc5test.processInstructions(8);
        Assertions.assertEquals(1, aoc5test.outputs.get(0));

        Aoc5 aoc5test2 = new Aoc5("3,9,8,9,10,9,4,9,99,-1,8");
        aoc5test2.processInstructions(7);
        Assertions.assertEquals(0, aoc5test2.outputs.get(0));

        Aoc5 aoc5test3 = new Aoc5("3,9,7,9,10,9,4,9,99,-1,8");
        aoc5test3.processInstructions(8);
        Assertions.assertEquals(0, aoc5test3.outputs.get(0));

        Aoc5 aoc5test4 = new Aoc5("3,9,7,9,10,9,4,9,99,-1,8");
        aoc5test4.processInstructions(4);
        Assertions.assertEquals(1, aoc5test4.outputs.get(0));

        Aoc5 aoc5test5 = new Aoc5("3,3,1108,-1,8,3,4,3,99");
        aoc5test5.processInstructions(8);
        Assertions.assertEquals(1, aoc5test5.outputs.get(0));

        Aoc5 aoc5test6 = new Aoc5("3,3,1108,-1,8,3,4,3,99");
        aoc5test6.processInstructions(7);
        Assertions.assertEquals(0, aoc5test6.outputs.get(0));

        Aoc5 aoc5test7 = new Aoc5("3,3,1107,-1,8,3,4,3,99");
        aoc5test7.processInstructions(8);
        Assertions.assertEquals(0, aoc5test7.outputs.get(0));

        Aoc5 aoc5test8 = new Aoc5("3,3,1107,-1,8,3,4,3,99");
        aoc5test8.processInstructions(4);
        Assertions.assertEquals(1, aoc5test8.outputs.get(0));

        Aoc5 aoc5test9 = new Aoc5("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9");
        aoc5test9.processInstructions(1);
        Assertions.assertEquals(1, aoc5test9.outputs.get(0));

        Aoc5 aoc5test10 = new Aoc5("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9");
        aoc5test10.processInstructions(0);
        Assertions.assertEquals(0, aoc5test10.outputs.get(0));

        Aoc5 aoc5test11 = new Aoc5("3,3,1105,-1,9,1101,0,0,12,4,12,99,1");
        aoc5test11.processInstructions(1);
        Assertions.assertEquals(1, aoc5test11.outputs.get(0));

        Aoc5 aoc5test12 = new Aoc5("3,3,1105,-1,9,1101,0,0,12,4,12,99,1");
        aoc5test12.processInstructions(0);
        Assertions.assertEquals(0, aoc5test12.outputs.get(0));

        Aoc5 aoc5test13 = new Aoc5("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99");
        aoc5test13.processInstructions(2);
        Assertions.assertEquals(999, aoc5test13.outputs.get(0));

        Aoc5 aoc5test14 = new Aoc5("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99");
        aoc5test14.processInstructions(8);
        Assertions.assertEquals(1000, aoc5test14.outputs.get(0));

        Aoc5 aoc5test15 = new Aoc5("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99");
        aoc5test15.processInstructions(14);
        Assertions.assertEquals(1001, aoc5test15.outputs.get(0));

        Aoc5 aoc5second = new Aoc5();
        aoc5second.processInstructions(5);
        Assertions.assertEquals(9386583, aoc5second.outputs.get(0));
    }
}