package com.mwpb;

import java.util.*;

public class Aoc17 {

    Intcode intcode;
    long[] memory;
    char[][] view;
    List<String> cycle;
    int robotX;
    int robotY;
    char robotOrientation;
    List<String> instructions;
    List<String> A;
    List<String> B;
    List<String> C;
    List<Character> movementRoutine;

    Aoc17(String instructionsString) {
        this.intcode = new Intcode(instructionsString);
        this.memory = Arrays.copyOf(this.intcode.memory, this.intcode.memory.length);
        this.instructions = new LinkedList<>();
    }

    void printView(char[][] view) {
        for (char[] line: view) {
            for (char c: line) {
                System.out.print(c);
            }
            System.out.print("\n");
        }
    }

    char[][] getOutput() {
        boolean finished = false;
        String viewString = "";
        while (!finished) {
            Optional<Long> output = this.intcode.run();
            System.out.println(output);
            if (output.isEmpty()) {
                break;
            } else {
                long l = output.get();
                String c = Character.toString((char) (int) l);
                viewString += c;
            }
        }
        String[] lineStrings = viewString.split("\\r?\\n");
        char[][] view = new char[lineStrings.length][];
        for (int i = 0; i < lineStrings.length; i++) {
            view[i] = lineStrings[i].toCharArray();
        }
        this.printView(view);
//        this.view = view;
        return view;
    }

    char[][] getView() {
        boolean finished = false;
        String viewString = "";
        while (!finished) {
            Optional<Long> output = this.intcode.run();
            if (output.isEmpty()) {
                break;
            } else {
                long l = output.get();
                String c = Character.toString((char) (int) l);
                viewString += c;
            }
        }
        String[] lineStrings = viewString.split("\\r?\\n");
        char[][] view = new char[lineStrings.length][];
        for (int i = 0; i < lineStrings.length; i++) {
            view[i] = lineStrings[i].toCharArray();
        }
        this.printView(view);
        this.view = view;
        return view;
    }

    void getRobot() {
        for (int i = 0; i < this.view.length - 1; i++) {
            for (int j = 0; j < this.view[0].length - 1; j++) {
                char c = this.view[i][j];
                if (List.of('<', '>', '^', 'v').contains(c)) {
                    this.robotX = j;
                    this.robotY = i;
                    this.robotOrientation = c;
                }
            }
        }
    }

    String getString(int x, int y) {
        return String.format("%d,%d", x, y);
    }

    boolean isIntersection(char[][] view, int x, int y) {
        return x != 0 && y != 0 && y != view.length - 1 && x != view[0].length - 1 &&
            view[y][x] == '#' && view[y - 1][x] == '#' && view[y + 1][x] == '#'
                && view[y][x - 1] == '#' && view[y][x + 1] == '#';
    }

    int getSumOfAlignmentParameters(char[][] view) {
        int sum = 0;
        for (int i = 0; i < view.length; i++) {
            for (int j = 0; j < view[0].length; j++) {
                if (this.isIntersection(view, j, i)) {
                    sum += i * j;
                }
            }
        }
        System.out.println(sum);
        return sum;
    }

    boolean canMove(char direction, int x, int y) {
        if (direction == '^' && y > 0 && this.view[y - 1][x] == '#') {
            this.robotY--;
            this.robotOrientation = direction;
            return true;
        } else if (direction == '>' && x < this.view[0].length - 1 && this.view[y][x + 1] == '#') {
            this.robotX++;
            this.robotOrientation = direction;
            return true;
        } else if (direction == 'v' && y < this.view.length - 1 && this.view[y + 1][x] == '#') {
            this.robotY++;
            this.robotOrientation = direction;
            return true;
        } else if (direction == '<' && x > 0 && this.view[y][x - 1] == '#') {
            this.robotX--;
            this.robotOrientation = direction;
            return true;
        }
        return false;
    }

    char getRightOrientation(char direction) {
        switch (direction) {
            case '^': return '>';
            case '>': return 'v';
            case '<': return '^';
            case 'v': return '<';
        }
        throw new IllegalArgumentException("Direction should be one of {^, >, <, v }.");
    }

    char getLeftOrientation(char direction) {
        switch (direction) {
            case '^': return '<';
            case '>': return '^';
            case '<': return 'v';
            case 'v': return '>';
        }
        throw new IllegalArgumentException("Direction should be one of {^, >, <, v }.");
    }

    void getPath() {
        boolean finished = false;
        while (!finished) {
            if (this.canMove(this.robotOrientation, this.robotX, this.robotY)) {
                this.instructions.add("1");
            } else if (this.canMove(this.getLeftOrientation(this.robotOrientation), this.robotX, this.robotY)) {
                this.instructions.add("L");
                this.instructions.add("1");
            } else if (this.canMove(this.getRightOrientation(this.robotOrientation), this.robotX, this.robotY)) {
                this.instructions.add("R");
                this.instructions.add("1");
            } else {
                finished = true;
            }
        }
    }

    void compressInstructions() {
        List<String> compressed = new LinkedList<>();
        int count = 0;
        for (String instruction: this.instructions) {
            if (List.of("L", "R").contains(instruction)) {
                if (count > 0) {
                    compressed.add(Integer.toString(count));
                }
                compressed.add(instruction);
                count = 0;
            } else {
                count++;
            }
        }
        if (count > 0) {
            compressed.add(Integer.toString(count));
        }
        this.instructions = compressed;
    }

    List<Integer> asciiChars(List<Character> f) {
        List<Integer> ascii = new LinkedList<Integer>();
        for (char c: f) {
            ascii.add((int) c);
            ascii.add(44);
        }
        ascii.remove(ascii.size() - 1);
        ascii.add(10);
        return ascii;
    }

    List<Integer> ascii(List<String> f) {
        List<Integer> ascii = new LinkedList<Integer>();
        for (String s: f) {
            char[] chars = s.toCharArray();
            for (char c: chars) {
                ascii.add((int) c);
            }
            ascii.add(44);
        }
        ascii.remove(ascii.size() - 1);
        ascii.add(10);
        return ascii;
    }

    Map<Character, List<String>> createFunctions() {
        Map<Character, List<String>> functions = new HashMap<>();
        List<String> current = new LinkedList<>();
        Iterator<String> it = this.instructions.iterator();
        int i = 0;
        while (it.hasNext()) {
            String instruction = this.instructions.remove(0);
            if (i % 19 == 0 && i > 0) {
                List<String> l = new LinkedList<>(current);
                functions.put((char) (i / 19 + 65), l);
                current = new LinkedList<>();
            }
            current.add(instruction);
            i++;
        }
        if (current.size() > 0) {
            List<String> l = new LinkedList<>(current);
            functions.put((char) (i / 19 + 66), l);
        }
        return functions;
    }

    List<String> getSubList(List<String> fullList, int n) {
        List<String> sublist = new LinkedList<>();
        for (int j = 0; j < n; j++) {
            sublist.add(fullList.get(j));
        }
        return sublist;
    }

    List<String> removeOccurences(List<String> fullList, List<String> subList, int maxToRemove) {
        boolean finished = false;
        List<String> outputList = new LinkedList<>(fullList);
        int count = 0;
        while (!finished && count < maxToRemove) {
            int n = Collections.indexOfSubList(outputList, subList);
            if (n == -1) {
                finished = true;
            } else {
                for (int i = 0; i < subList.size(); i++) {
                    outputList.remove(n);
                }
            }
            count++;
        }
        return outputList;
    }

    void getFunctions() {
        for (int i = 1; i <= 20; i++) {
            List<String> subList1 = this.getSubList(this.instructions, i);
            List<String> remaining1 = this.removeOccurences(this.instructions, subList1, 20);
            for (int j = 1; j <= Math.min(20, remaining1.size()); j++) {
                List<String> subList2 = this.getSubList(remaining1, j);
                List<String> remaining2 = this.removeOccurences(remaining1, subList2, 20);
                for (int k = 1; k <= Math.min(20, remaining2.size()); k++) {
                    List<String> subList3 = this.getSubList(remaining2, k);
                    List<String> remaining = this.removeOccurences(remaining2, subList3, 20);
                    if (remaining.size() == 0) {
                        this.A = subList1;
                        this.B = subList2;
                        this.C = subList3;
                        return;
                    }
                }
            }
        }
    }

    void getMovementRoutine() {
        List<String> tempInstructions = new LinkedList<>(this.instructions);
        this.movementRoutine = new LinkedList<>();
        while (tempInstructions.size() > 0) {
            if (Collections.indexOfSubList(tempInstructions, this.A) == 0) {
                tempInstructions = this.removeOccurences(tempInstructions, this.A, 1);
                this.movementRoutine.add('A');
            } else if (Collections.indexOfSubList(tempInstructions, this.B) == 0) {
                tempInstructions = this.removeOccurences(tempInstructions, this.B, 1);
                this.movementRoutine.add('B');
            } else if (Collections.indexOfSubList(tempInstructions, this.C) == 0) {
                tempInstructions = this.removeOccurences(tempInstructions, this.C, 1);
                this.movementRoutine.add('C');
            }
        }
        return;
    }

    int dustVolume() {
        this.getView();
        this.getRobot();
        this.getPath();
        this.compressInstructions();
        System.out.println(this.instructions);
        this.getFunctions();
        System.out.println(this.A);
        System.out.println(this.B);
        System.out.println(this.C);
        this.getMovementRoutine();
        System.out.println(this.movementRoutine);

        this.intcode = new Intcode("1,330,331,332,109,3264,1102,1,1182,16,1101,1431,0,24,101,0,0,570,1006,570,36,1001,571,0,0,1001,570,-1,570,1001,24,1,24,1105,1,18,1008,571,0,571,1001,16,1,16,1008,16,1431,570,1006,570,14,21102,1,58,0,1105,1,786,1006,332,62,99,21102,1,333,1,21101,0,73,0,1105,1,579,1101,0,0,572,1102,0,1,573,3,574,101,1,573,573,1007,574,65,570,1005,570,151,107,67,574,570,1005,570,151,1001,574,-64,574,1002,574,-1,574,1001,572,1,572,1007,572,11,570,1006,570,165,101,1182,572,127,101,0,574,0,3,574,101,1,573,573,1008,574,10,570,1005,570,189,1008,574,44,570,1006,570,158,1106,0,81,21102,340,1,1,1106,0,177,21102,477,1,1,1105,1,177,21101,514,0,1,21101,176,0,0,1105,1,579,99,21101,0,184,0,1105,1,579,4,574,104,10,99,1007,573,22,570,1006,570,165,1001,572,0,1182,21101,375,0,1,21102,1,211,0,1105,1,579,21101,1182,11,1,21101,0,222,0,1105,1,979,21101,0,388,1,21101,233,0,0,1105,1,579,21101,1182,22,1,21102,244,1,0,1105,1,979,21102,1,401,1,21101,0,255,0,1105,1,579,21101,1182,33,1,21101,0,266,0,1106,0,979,21101,0,414,1,21101,0,277,0,1105,1,579,3,575,1008,575,89,570,1008,575,121,575,1,575,570,575,3,574,1008,574,10,570,1006,570,291,104,10,21102,1,1182,1,21102,313,1,0,1105,1,622,1005,575,327,1102,1,1,575,21102,327,1,0,1105,1,786,4,438,99,0,1,1,6,77,97,105,110,58,10,33,10,69,120,112,101,99,116,101,100,32,102,117,110,99,116,105,111,110,32,110,97,109,101,32,98,117,116,32,103,111,116,58,32,0,12,70,117,110,99,116,105,111,110,32,65,58,10,12,70,117,110,99,116,105,111,110,32,66,58,10,12,70,117,110,99,116,105,111,110,32,67,58,10,23,67,111,110,116,105,110,117,111,117,115,32,118,105,100,101,111,32,102,101,101,100,63,10,0,37,10,69,120,112,101,99,116,101,100,32,82,44,32,76,44,32,111,114,32,100,105,115,116,97,110,99,101,32,98,117,116,32,103,111,116,58,32,36,10,69,120,112,101,99,116,101,100,32,99,111,109,109,97,32,111,114,32,110,101,119,108,105,110,101,32,98,117,116,32,103,111,116,58,32,43,10,68,101,102,105,110,105,116,105,111,110,115,32,109,97,121,32,98,101,32,97,116,32,109,111,115,116,32,50,48,32,99,104,97,114,97,99,116,101,114,115,33,10,94,62,118,60,0,1,0,-1,-1,0,1,0,0,0,0,0,0,1,6,14,0,109,4,1201,-3,0,586,21002,0,1,-1,22101,1,-3,-3,21101,0,0,-2,2208,-2,-1,570,1005,570,617,2201,-3,-2,609,4,0,21201,-2,1,-2,1105,1,597,109,-4,2105,1,0,109,5,2101,0,-4,630,20101,0,0,-2,22101,1,-4,-4,21101,0,0,-3,2208,-3,-2,570,1005,570,781,2201,-4,-3,652,21001,0,0,-1,1208,-1,-4,570,1005,570,709,1208,-1,-5,570,1005,570,734,1207,-1,0,570,1005,570,759,1206,-1,774,1001,578,562,684,1,0,576,576,1001,578,566,692,1,0,577,577,21101,702,0,0,1106,0,786,21201,-1,-1,-1,1105,1,676,1001,578,1,578,1008,578,4,570,1006,570,724,1001,578,-4,578,21102,731,1,0,1105,1,786,1106,0,774,1001,578,-1,578,1008,578,-1,570,1006,570,749,1001,578,4,578,21102,1,756,0,1106,0,786,1105,1,774,21202,-1,-11,1,22101,1182,1,1,21102,1,774,0,1105,1,622,21201,-3,1,-3,1106,0,640,109,-5,2105,1,0,109,7,1005,575,802,21001,576,0,-6,20101,0,577,-5,1105,1,814,21101,0,0,-1,21101,0,0,-5,21101,0,0,-6,20208,-6,576,-2,208,-5,577,570,22002,570,-2,-2,21202,-5,47,-3,22201,-6,-3,-3,22101,1431,-3,-3,2101,0,-3,843,1005,0,863,21202,-2,42,-4,22101,46,-4,-4,1206,-2,924,21101,1,0,-1,1105,1,924,1205,-2,873,21101,0,35,-4,1106,0,924,1202,-3,1,878,1008,0,1,570,1006,570,916,1001,374,1,374,1201,-3,0,895,1101,2,0,0,1202,-3,1,902,1001,438,0,438,2202,-6,-5,570,1,570,374,570,1,570,438,438,1001,578,558,922,20102,1,0,-4,1006,575,959,204,-4,22101,1,-6,-6,1208,-6,47,570,1006,570,814,104,10,22101,1,-5,-5,1208,-5,39,570,1006,570,810,104,10,1206,-1,974,99,1206,-1,974,1101,0,1,575,21102,973,1,0,1105,1,786,99,109,-7,2105,1,0,109,6,21102,1,0,-4,21102,1,0,-3,203,-2,22101,1,-3,-3,21208,-2,82,-1,1205,-1,1030,21208,-2,76,-1,1205,-1,1037,21207,-2,48,-1,1205,-1,1124,22107,57,-2,-1,1205,-1,1124,21201,-2,-48,-2,1105,1,1041,21101,0,-4,-2,1105,1,1041,21102,-5,1,-2,21201,-4,1,-4,21207,-4,11,-1,1206,-1,1138,2201,-5,-4,1059,1201,-2,0,0,203,-2,22101,1,-3,-3,21207,-2,48,-1,1205,-1,1107,22107,57,-2,-1,1205,-1,1107,21201,-2,-48,-2,2201,-5,-4,1090,20102,10,0,-1,22201,-2,-1,-2,2201,-5,-4,1103,1202,-2,1,0,1106,0,1060,21208,-2,10,-1,1205,-1,1162,21208,-2,44,-1,1206,-1,1131,1106,0,989,21102,439,1,1,1106,0,1150,21102,477,1,1,1106,0,1150,21101,514,0,1,21101,0,1149,0,1105,1,579,99,21102,1157,1,0,1105,1,579,204,-2,104,10,99,21207,-3,22,-1,1206,-1,1138,2102,1,-5,1176,2101,0,-4,0,109,-6,2106,0,0,28,7,40,1,5,1,40,1,5,1,40,1,5,1,22,1,5,13,5,1,22,1,5,1,17,1,22,1,3,5,15,1,22,1,3,1,1,1,1,1,15,1,22,1,1,9,13,1,22,1,1,1,1,1,1,1,1,1,1,1,13,1,16,7,1,1,1,9,11,1,16,1,7,1,3,1,1,1,1,1,1,1,11,1,12,13,3,5,1,1,11,5,8,1,3,1,13,1,3,1,15,1,8,1,3,1,1,13,3,1,15,1,8,1,3,1,17,1,15,1,8,1,3,1,17,7,5,9,4,1,3,1,23,1,5,1,3,1,3,1,4,5,23,1,5,1,3,1,3,1,32,1,5,1,3,1,3,1,32,1,5,5,3,1,32,1,13,1,32,1,13,1,32,1,13,1,32,1,3,5,5,1,32,1,3,1,3,1,5,1,32,1,3,1,3,1,5,1,32,1,3,1,3,1,5,1,32,9,5,5,32,1,13,1,32,1,13,1,32,1,13,1,26,7,13,1,26,1,19,1,26,1,7,13,26,1,7,1,38,1,7,1,38,1,7,1,38,9,12");
        this.intcode.memory[0] = 2;
        List<Character> movementRoutine = new LinkedList<>(this.movementRoutine);
        this.intcode.input.addAll(this.asciiChars(movementRoutine));
        this.intcode.input.addAll(this.ascii(this.A));
        this.intcode.input.addAll(this.ascii(this.B));
        this.intcode.input.addAll(this.ascii(this.C));
        this.intcode.input.add(110);
//        this.intcode.input.add(121);
        this.intcode.input.add(10);
        System.out.println(this.intcode.input);
        this.getOutput();
//        this.getOutput();
        return 0;
    }

}
