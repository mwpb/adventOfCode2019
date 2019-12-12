package com.mwpb;

import jdk.jshell.execution.JdiDefaultExecutionControl;

import java.util.*;

public class Aoc4 {

    int LOWER_BOUND = 125730;
    int UPPER_BOUND = 579381;

    int[] getDigits(int n) {
        int[] digits = new int[6];
        for (int i = 0; i < 6; i++) {
            digits[5-i] = (int) ((n / Math.pow(10, i)) % 10);
        }
        return digits;
    }

    int getNumber(int[] digits) {
        int n = 0;
        for (int i = 0; i < 6; i++) {
            n += Math.pow(10, 5-i) * digits[i];
        }
        return n;
    }

    boolean checkValid(int n) {
        if (n > this.UPPER_BOUND || n < this.LOWER_BOUND) {
            return false;
        }
        int[] digits = this.getDigits(n);
        boolean containsRepeat = false;
        for (int i = 0; i < 5; i++) {
            int first = digits[i];
            int second = digits[i+1];
            if (second < first) {
                return false;
            } else if (second == first) {
                containsRepeat = true;
            }
        }
        return containsRepeat;
    }

    boolean checkValidUpdated(int n) {
        int k = this.indexOfFirstDecrease(n);
        if (k != -1) {
            return false;
        }
        int[] digits = this.getDigits(n);
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int i = 0; i < 6; i++) {
            counts.put(digits[i], counts.getOrDefault(digits[i], 0) + 1);
        }
        return counts.containsValue(2);
    }

    int indexOfLowestNonNine(int n) {
        int[] digits = this.getDigits(n);
        for (int i = 5; i >= 0; i--) {
            if (digits[i] != 9) {
                return i;
            }
        }
        return -1;
    }

    int indexOfFirstDecrease(int n) {
        int[] digits = this.getDigits(n);
        for (int i = 0; i < 5; i++) {
            int first = digits[i];
            int second = digits[i+1];
            if (second < first) {
                return i;
            }
        }
        return -1;
    }

    int setFromIndex(int n, int j, int val) {
        int[] digits = this.getDigits(n);
        for (int i = j; i < 6; i++) {
            digits[i] = val;
        }
        return this.getNumber(digits);
    }

    int getNext(int n) {
        int[] digits = this.getDigits(n);
        int k = this.indexOfFirstDecrease(n);
        if (k >= 0) {
            return this.setFromIndex(n, k, digits[k]);
        }
        int j = this.indexOfLowestNonNine(n);
        if (j >= 0) {
            return this.setFromIndex(n, j, digits[j]+1);
        }
        return 999999;
    }

    int countValid() {
        int count = 0;
        int n = this.LOWER_BOUND;
        while (n <= this.UPPER_BOUND) {
            if (this.checkValidUpdated(n)) {
                count++;
            }
            n = this.getNext(n);
        }
        return count;
    }

}
