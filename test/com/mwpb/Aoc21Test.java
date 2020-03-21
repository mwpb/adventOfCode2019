package com.mwpb;

 import org.junit.jupiter.api.Assertions;
 import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Aoc21Test {

    @Test
    void testWalker() throws FileNotFoundException {
        Aoc21 aoc21 = new Aoc21();
        aoc21.programmeWalker();
        String out = aoc21.intcode.runAll();
        System.out.println(out);
    }
    
    @Test
    void testRunner() {
    	Aoc21 aoc21 = new Aoc21();
        aoc21.programmeRunner();
        String out = aoc21.intcode.runAll();
        System.out.println(out);
    }
}
