package com.mwpb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Aoc2 {

    int[] commands;
    boolean shouldHalt;
    int[] original;

    Aoc2() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("aoc2-1-input.txt"));
        String[] commandStrings = scanner.nextLine().split(",");
        this.commands = new int[commandStrings.length];
        this.original = new int[commandStrings.length];
        for (int i = 0; i < commandStrings.length; i++) {
            this.commands[i] = Integer.parseInt(commandStrings[i]);
            this.original[i] = Integer.parseInt(commandStrings[i]);
        }
    }

    Aoc2(String commandString) {
        String[] commandStrings = commandString.split(",");
        this.commands = new int[commandStrings.length];
        for (int i = 0; i < commandStrings.length; i++) {
            this.commands[i] = Integer.parseInt(commandStrings[i]);
        }
    }

    void processFour(int a, int b, int c, int d) {
        switch (a) {
            case 1: this.commands[d] = this.commands[b] + this.commands[c]; break;
            case 2: this.commands[d] = this.commands[b] * this.commands[c]; break;
            case 99: this.shouldHalt = true; break;
        }
    }

    void processCommands() {
        for (int i = 0; i < this.commands.length / 4; i++) {
            int a = 4 * i;
            this.processFour(this.commands[a], this.commands[a+1], this.commands[a+2], this.commands[a+3]);
        }
    }

    int run(int noun, int verb) {
        this.commands[1] = noun;
        this.commands[2] = verb;
        this.processCommands();
        return this.commands[0];
    }

    String getCommandString() {
        String out = "";
        for (int i: this.commands) {
            out += Integer.toString(i)+",";
        }
        return out;
    }

    void reset() {
        for (int i = 0; i < this.commands.length; i++) {
            this.commands[i] = this.original[i];
        }
    }

}
