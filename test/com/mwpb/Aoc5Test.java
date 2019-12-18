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

    @Test
    void runAmps() {
        Aoc5 aoc5test = new Aoc5("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0");
        List<Integer> phases = new LinkedList<Integer> (Arrays.asList(4, 3, 2, 1, 0));
        Assertions.assertEquals(43210, aoc5test.runAmps(phases));

        Aoc5 aoc5test1 = new Aoc5("3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0");
        List<Integer> phases1 = new LinkedList<Integer> (Arrays.asList(0, 1, 2, 3, 4));
        Assertions.assertEquals(54321, aoc5test1.runAmps(phases1));

        Aoc5 aoc5test2 = new Aoc5("3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0");
        List<Integer> phases2 = new LinkedList<Integer> (Arrays.asList(1, 0, 4, 3, 2));
        Assertions.assertEquals(65210, aoc5test2.runAmps(phases2));
    }

    @Test
    void maxSignal() throws FileNotFoundException {
        Aoc5 aoc5test = new Aoc5("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0");
        Assertions.assertEquals(43210, aoc5test.maxSignal());

        Aoc5 aoc5test1 = new Aoc5("3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0");
        Assertions.assertEquals(54321, aoc5test1.maxSignal());

        Aoc5 aoc5test2 = new Aoc5("3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0");
        Assertions.assertEquals(65210, aoc5test2.maxSignal());

        Aoc5 aoc5 = new Aoc5("3,8,1001,8,10,8,105,1,0,0,21,38,63,80,105,118,199,280,361,442,99999,3,9,102,5,9,9,1001,9,3,9,1002,9,2,9,4,9,99,3,9,1001,9,4,9,102,4,9,9,101,4,9,9,102,2,9,9,101,2,9,9,4,9,99,3,9,1001,9,5,9,102,4,9,9,1001,9,4,9,4,9,99,3,9,101,3,9,9,1002,9,5,9,101,3,9,9,102,5,9,9,101,3,9,9,4,9,99,3,9,1002,9,2,9,1001,9,4,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,99");
        Assertions.assertEquals(118936, aoc5.maxSignal());
    }

    @Test
    void maxSignalWithFeedback() throws FileNotFoundException {
        Aoc5 aoc5test = new Aoc5("3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5");
        Assertions.assertEquals(139629729, aoc5test.maxSignalWithFeedback());

        Aoc5 aoc5test2 = new Aoc5("3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10");
        Assertions.assertEquals(18216, aoc5test2.maxSignalWithFeedback());

        Aoc5 aoc5 = new Aoc5("3,8,1001,8,10,8,105,1,0,0,21,38,63,80,105,118,199,280,361,442,99999,3,9,102,5,9,9,1001,9,3,9,1002,9,2,9,4,9,99,3,9,1001,9,4,9,102,4,9,9,101,4,9,9,102,2,9,9,101,2,9,9,4,9,99,3,9,1001,9,5,9,102,4,9,9,1001,9,4,9,4,9,99,3,9,101,3,9,9,1002,9,5,9,101,3,9,9,102,5,9,9,101,3,9,9,4,9,99,3,9,1002,9,2,9,1001,9,4,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,99");
        Assertions.assertEquals(57660948, aoc5.maxSignalWithFeedback());
    }
}