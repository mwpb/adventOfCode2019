package com.mwpb;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Aoc8Test {

    @Test
    void onesTimesTwos() throws FileNotFoundException {
        Aoc8 aoc8test = new Aoc8();
        System.out.println(aoc8test.onesTimesTwos());
    }

    @Test
    void getMessage() throws FileNotFoundException {
        Aoc8 aoc8test = new Aoc8();
        aoc8test.getMessage();
        aoc8test.printMessage();
//        Aoc8 aoc8test2 = new Aoc8("0222112222120000", 2, 2);
//        aoc8test2.getMessage();
//        aoc8test2.printMessage();
    }
}