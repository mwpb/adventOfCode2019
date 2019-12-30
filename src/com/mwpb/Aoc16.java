package com.mwpb;

import java.util.Arrays;

public class Aoc16 {

    int signalLength;
    int[] signal;
    int[] BASE_PATTERN;
    int[] cumulatives;

    Aoc16(String inputString) {
        char[] chars = inputString.toCharArray();
        this.signalLength = chars.length;
        this.signal = new int[this.signalLength];
        for (int i = 0; i < this.signalLength; i++) {
            this.signal[i] = Integer.parseInt(Character.toString(chars[i]));
        }
        this.BASE_PATTERN = new int[] {0, 1, 0, -1};
    }

    void getCumulatives() {
        this.cumulatives = new int[this.signal.length];
        int current = 0;
        for (int i = 0; i < this.signal.length; i++) {
            this.cumulatives[i] = this.signal[i] + current;
            current += this.signal[i];
        }
    }

    int updatePosition(int position) {
        int sum = 0;
        for (int i = 0; i < this.signal.length / (position + 1); i++) {
            int indexOfFirstInBlock = Math.max((position + 1) * i - 1, 0);
            int indexOfLastInBlock = (position + 1) * (i + 1) - 2;
            if (indexOfLastInBlock >= 0) {
                int coefficient = this.BASE_PATTERN[((indexOfFirstInBlock + 1) / (position + 1)) % 4];
//                System.out.println(String.format("%d - %d: %d", indexOfFirstInBlock, indexOfLastInBlock, coefficient));
                if (coefficient == 1 || coefficient == -1) {
                    int lower = (indexOfFirstInBlock - 1 < 0) ? 0 : this.cumulatives[indexOfFirstInBlock - 1];
                    int blockSum = this.cumulatives[indexOfLastInBlock] - lower;
                    sum += blockSum * coefficient;
                }
            }
        }
        int indexOfFirstInBlock = (this.signal.length / (position + 1)) * (position + 1) - 1;
        int indexOfLastInBlock = this.signal.length - 1;
        int coefficient = this.BASE_PATTERN[((indexOfFirstInBlock + 1) / (position + 1)) % 4];
//        System.out.println(String.format("%d - %d: %d", indexOfFirstInBlock, indexOfLastInBlock, coefficient));
        if (coefficient == 1 || coefficient == -1) {
            int lower = (indexOfFirstInBlock - 1 < 0) ? 0 : this.cumulatives[indexOfFirstInBlock - 1];
            int blockSum = this.cumulatives[indexOfLastInBlock] - lower;
            sum += blockSum * coefficient;
        }
        return Math.abs(sum) % 10;
    }

    void update() {
        this.getCumulatives();
        int[] output = new int[this.signal.length];
        for (int i = 0; i < this.signal.length; i++) {
            output[i] = this.updatePosition(i);
        }
        this.signal = output;
    }

    int realMessage() {
        int skip = 0;
        for (int i = 0; i < 7; i++) {
            skip += this.signal[i] * Math.pow(10, 6 - i);
        }
        int[] realSignal = new int[10000 * this.signalLength];
        for (int i = 0; i < 10000 * this.signalLength; i++) {
            realSignal[i] = this.signal[i % this.signalLength];
        }
        this.signal = realSignal;
        this.signalLength = this.signal.length;
        for (int i = 0; i < 100; i++) {
            this.update();
        }
        int output = 0;
        for (int i = 0; i < 8; i++) {
            output += this.signal[skip + i] * Math.pow(10, 7 - i);
        }
        return output;
    }

}
