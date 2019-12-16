package com.mwpb;

import javax.sound.midi.SysexMessage;
import java.io.*;
import java.util.*;

public class Aoc5 {

    int[] instructions;
    List<Integer> outputs;

    Aoc5() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("aoc5-1-1.txt"));
        String line = scanner.nextLine();
        String[] splitLine = line.split(",");
        this.instructions = new int[splitLine.length];
        for (int i = 0; i < splitLine.length; i++) {
            this.instructions[i] = Integer.parseInt(splitLine[i]);
        }
        this.outputs = new LinkedList<Integer>();
    }

    Aoc5(String line) {
        String[] splitLine = line.split(",");
        this.instructions = new int[splitLine.length];
        for (int i = 0; i < splitLine.length; i++) {
            this.instructions[i] = Integer.parseInt(splitLine[i]);
        }
        this.outputs = new LinkedList<Integer>();
    }

    int processInstructions(int input) {
        int i = 0;
        while (i < this.instructions.length) {
//            System.out.println(String.format("%d %d %d %d", this.instructions[i], this.instructions[i+1], this.instructions[i+2], this.instructions[i+3]));
//            System.out.println(Arrays.toString(this.instructions));
            int opcode = this.instructions[i] % 100;
            int mode1 =  (this.instructions[i] / 100) % 10;
            int mode2 =  (this.instructions[i] / 1000) % 10;
            int mode3 =  (this.instructions[i] / 10000) % 10;
            if (opcode == 1) {
                i++;
                int first = (mode1 == 0) ? this.instructions[this.instructions[i]] : this.instructions[i];
                i++;
                int second = (mode2 == 0) ? this.instructions[this.instructions[i]] : this.instructions[i];
                i++;
                int destination = this.instructions[i];
                this.instructions[destination] = first + second;
                i++;
            } else if (opcode == 2) {
                i++;
                int first = (mode1 == 0) ? this.instructions[this.instructions[i]] : this.instructions[i];
                i++;
                int second = (mode2 == 0) ? this.instructions[this.instructions[i]] : this.instructions[i];
                i++;
                int destination = this.instructions[i];
                this.instructions[destination] = first * second;
                i++;
            } else if (opcode == 3) {
                i++;
                int destination = this.instructions[i];
                this.instructions[destination] = input;
                i++;
            } else if (opcode == 4) {
                i++;
                int first = (mode1 == 0) ? this.instructions[this.instructions[i]] : this.instructions[i];
                this.outputs.add(first);
                i++;
            } else if (opcode == 5) {
                i++;
                int first = (mode1 == 0) ? this.instructions[this.instructions[i]] : this.instructions[i];
                if (first != 0) {
                    i++;
                    i = (mode2 == 0) ? this.instructions[this.instructions[i]] : this.instructions[i];
                } else {
                    i++;
                    i++;
                }
            } else if (opcode == 6) {
                i++;
                int first = (mode1 == 0) ? this.instructions[this.instructions[i]] : this.instructions[i];
                if (first == 0) {
                    i++;
                    i = (mode2 == 0) ? this.instructions[this.instructions[i]] : this.instructions[i];
                } else {
                    i++;
                    i++;
                }
            } else if (opcode == 7) {
                i++;
                int first = (mode1 == 0) ? this.instructions[this.instructions[i]] : this.instructions[i];
                i++;
                int second = (mode2 == 0) ? this.instructions[this.instructions[i]] : this.instructions[i];
                i++;
                int destination = this.instructions[i];
                this.instructions[destination] = (first < second) ? 1: 0;
                i++;
            } else if (opcode == 8) {
                i++;
                int first = (mode1 == 0) ? this.instructions[this.instructions[i]] : this.instructions[i];
                i++;
                int second = (mode2 == 0) ? this.instructions[this.instructions[i]] : this.instructions[i];
                i++;
                int destination = this.instructions[i];
                this.instructions[destination] = (first == second) ? 1: 0;
                i++;
            } else if (opcode == 99) {
                break;
            }

        }
        return this.instructions[0];
    }

}
