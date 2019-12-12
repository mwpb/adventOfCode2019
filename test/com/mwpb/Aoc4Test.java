package com.mwpb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Aoc4Test {

    @Test
    void getDigits() {
        Aoc4 aoc4 = new Aoc4();
        int[] expected = new int[] {1, 2, 5, 7, 3, 0};
        Assertions.assertArrayEquals(aoc4.getDigits(125730), expected);
        int[] expected2 = new int[] {5, 4, 3, 0, 3, 0};
        Assertions.assertArrayEquals(aoc4.getDigits(543030), expected2);
    }

    @Test
    void getIndexOfLowestNonNine() {
        Aoc4 aoc4 = new Aoc4();
        Assertions.assertEquals(0, aoc4.indexOfLowestNonNine(199999));
    }

    @Test
    void getNumber() {
        Aoc4 aoc4 = new Aoc4();
        int[] input1 = new int[] {1, 2, 5, 7, 3, 0};
        Assertions.assertEquals(aoc4.getNumber(input1), 125730);
        int[] input2 = new int[] {5, 4, 3, 0, 3, 0};
        Assertions.assertEquals(aoc4.getNumber(input2), 543030);
    }

    @Test
    void checkValid() {
        Aoc4 aoc4 = new Aoc4();
        Assertions.assertEquals(aoc4.checkValid(125730), false);
        Assertions.assertEquals(aoc4.checkValid(125777), true);
        Assertions.assertEquals(aoc4.checkValid(125789), false);
    }

    @Test
    void indexOfFirstDecrease() {
        Aoc4 aoc4 = new Aoc4();
        Assertions.assertEquals(2, aoc4.indexOfFirstDecrease(349000));
    }

    @Test
    void setFromIndex() {
        Aoc4 aoc4 = new Aoc4();
        Assertions.assertEquals(349999, aoc4.setFromIndex(349000, 3, 9));
    }

    @Test
    void getNext() {
        Aoc4 aoc4 = new Aoc4();
        Assertions.assertEquals(125777, aoc4.getNext(125730));
        Assertions.assertEquals(349999, aoc4.getNext(349000));
        Assertions.assertEquals(355555, aoc4.getNext(349999));
    }

    @Test
    void allAboveInner() {
        Aoc4 aoc4test = new Aoc4();
        Assertions.assertEquals(aoc4test.allAboveInner(7, 2, false), 6);
        Assertions.assertEquals(aoc4test.allAboveInner(7, 2, true), 5);
        System.out.println(aoc4test.allAboveInner(8, 2, true));
    }

    @Test
    void countValid() {
//        Aoc4 aoc4test = new Aoc4();
//        aoc4test.UPPER_BOUND = 125800;
//        Assertions.assertEquals(5, aoc4test.countValid());
        Aoc4 aoc4 = new Aoc4();
        Assertions.assertEquals(1411, aoc4.countValid());
    }
}