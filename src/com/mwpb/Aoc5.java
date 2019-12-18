package com.mwpb;

import javax.sound.midi.SysexMessage;
import java.io.*;
import java.util.*;

public class Aoc5 {

    int[] instructions;
    int[] originalInstructions;
    int pointer;
    List<Integer> outputs;

    Aoc5() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("aoc5-1-1.txt"));
        String line = scanner.nextLine();
        String[] splitLine = line.split(",");
        this.instructions = new int[splitLine.length];
        this.originalInstructions = new int[splitLine.length];
        for (int i = 0; i < splitLine.length; i++) {
            this.instructions[i] = Integer.parseInt(splitLine[i]);
            this.originalInstructions[i] = Integer.parseInt(splitLine[i]);
        }
        this.pointer = 0;
        this.outputs = new LinkedList<Integer>();
    }

    Aoc5(String line) {
        String[] splitLine = line.split(",");
        this.instructions = new int[splitLine.length];
        this.originalInstructions = new int[splitLine.length];
        for (int i = 0; i < splitLine.length; i++) {
            this.instructions[i] = Integer.parseInt(splitLine[i]);
            this.originalInstructions[i] = Integer.parseInt(splitLine[i]);
        }
        this.outputs = new LinkedList<Integer>();
        this.pointer = 0;
    }

    int processInstructions(int input) {
        return this.processInstructions(new LinkedList<>(Arrays.asList(input)));
    }

    int processInstructions(List<Integer> inputs) {
        return this.processInstructions(inputs, false, 0);
    }

    int processInstructions(List<Integer> inputs, boolean returnOutput, int i) {
//        int i = 0;
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
                this.instructions[destination] = inputs.remove(0);
                i++;
            } else if (opcode == 4) {
                i++;
                int first = (mode1 == 0) ? this.instructions[this.instructions[i]] : this.instructions[i];
                this.outputs.add(first);
                i++;
                this.pointer = i;
                if (returnOutput) {
                    return first;
                }
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
        if (returnOutput) {
            return -1;
        } else {
            return this.instructions[0];
        }
    }

    int runAmps(List<Integer> phases) {
        int input = 0;
        for (int i = 0; i < 5; i++) {
            this.instructions = Arrays.copyOf(this.originalInstructions, this.originalInstructions.length);
            this.outputs = new LinkedList<Integer>();
            List<Integer> newInputs = new LinkedList<Integer>(Arrays.asList(phases.get(i), input));
            this.processInstructions(newInputs);
            input = this.outputs.get(0);
        }
        return input;
    }

    int runAmpsWithFeedback(List<Integer> phases) {
        int input = 0;
        int[][] amps = new int[5][];
        int[] ampStarts = new int[5];
        for (int i = 0; i < 5; i++) {
            amps[i] = Arrays.copyOf(this.originalInstructions, this.originalInstructions.length);
            ampStarts[i] = 0;
        }
        for (int i = 0; i < 5; i++) {
            this.instructions = amps[i];
            this.outputs = new LinkedList<Integer>();
            List<Integer> newInputs = new LinkedList<Integer>(Arrays.asList(phases.get(i), input));
            System.out.println(newInputs);
            input = this.processInstructions(newInputs, true, 0);
            amps[i] = this.instructions;
            ampStarts[i] = this.pointer;
//            input = this.outputs.get(0);
        }
        boolean finished = false;
        while (!finished) {
            for (int i = 0; i < 5; i++) {
                this.instructions = amps[i];
                this.outputs = new LinkedList<Integer>();
                List<Integer> newInputs = new LinkedList<Integer>(Arrays.asList(input));
                input = this.processInstructions(newInputs, true, ampStarts[i]);
                amps[i] = this.instructions;
                ampStarts[i] = this.pointer;
                System.out.println(String.format("%d: %s", i, input));
                if (input == -1) {
                    return newInputs.get(0);
                }
            }
        }
        return input;
    }

    List<List<Integer>> getCombinations(Set<Integer> remaining) {
        if (remaining.size() == 1) {
            int i = remaining.iterator().next();
            List<List<Integer>> out = new LinkedList<List<Integer>>();
            List<Integer> singleton = new LinkedList<Integer>();
            singleton.add(i);
            out.add(singleton);
            return out;
        }
        List<List<Integer>> combinations = new LinkedList<List<Integer>>();
        for (int i: remaining) {
            Set<Integer> remainingI = new HashSet<Integer>(remaining);
            remainingI.remove(i);
            for (List<Integer> l: getCombinations(remainingI)) {
                List<Integer> listI = new LinkedList<>();
                listI.add(i);
                listI.addAll(l);
                combinations.add(listI);
            }
        }
        return combinations;
    }

    int maxSignal() {
        int signal = -1;
        Set<Integer> phaseSet = new HashSet<Integer>();
        for (int i = 0; i < 5; i++) {
            phaseSet.add(i);
        }
        List<List<Integer>> combinations = this.getCombinations(phaseSet);
        for (List<Integer> combination: combinations) {
//            System.out.println(combination);
            int output = this.runAmps(combination);
            if (output > signal) {
                signal = output;
            }
        }
        return signal;
    }

    int maxSignalWithFeedback() {
        int signal = -1;
        Set<Integer> phaseSet = new HashSet<Integer>();
        for (int i = 0; i < 5; i++) {
            phaseSet.add(i + 5);
        }
        List<List<Integer>> combinations = this.getCombinations(phaseSet);
        for (List<Integer> combination: combinations) {
            int output = this.runAmpsWithFeedback(combination);
            System.out.println(String.format("%s: %d %d", combination, output, signal));
            if (output > signal) {
                signal = output;
            }
            System.out.println(signal);
        }
        return signal;
    }

}
