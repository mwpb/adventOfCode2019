package com.mwpb;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.*;
import java.util.*;

public class Aoc8 {

    int WIDTH = 25;
    int HEIGHT = 6;
    List<int[]> layers;
    int[] message;

    Aoc8() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("aoc8.txt"));
        this.layers = new LinkedList<int[]>();
        char[] line = scanner.nextLine().toCharArray();
        for (int i = 0; i < line.length / (this.HEIGHT * this.WIDTH); i++)  {
            int[] nextLayer = new int[this.HEIGHT * this.WIDTH];
            for (int j = 0; j < this.HEIGHT * this.WIDTH; j++) {
                nextLayer[j] = Integer.parseInt(Character.toString(line[i * this.HEIGHT * this.WIDTH+j]));
            }
            this.layers.add(nextLayer);
        }
//        System.out.println(this.layers);
        for (int[] layer: this.layers) {
//            System.out.println(Arrays.toString(layer));
        }
    }

    Aoc8(String lineString, int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        char[] line = lineString.toCharArray();
        this.layers = new LinkedList<int[]>();
        for (int i = 0; i < line.length / (this.HEIGHT * this.WIDTH); i++)  {
            int[] nextLayer = new int[this.HEIGHT * this.WIDTH];
            for (int j = 0; j < this.HEIGHT * this.WIDTH; j++) {
                nextLayer[j] = Integer.parseInt(Character.toString(line[i * this.HEIGHT * this.WIDTH+j]));
            }
            this.layers.add(nextLayer);
        }
//        System.out.println(this.layers);
        for (int[] layer: this.layers) {
//            System.out.println(Arrays.toString(layer));
        }
    }

    int countValue(int[] layer, int value) {
        int count = 0;
        for (int pixel: layer) {
            if (pixel == value) {
                count++;
            }
        }
        return count;
    }

    int onesTimesTwos() {
        int[] layerWithFewestZeros = this.layers.get(0);
        int currentZeros =  this.HEIGHT * this.WIDTH + 1;
        Iterator<int[]> it = this.layers.iterator();
        while (it.hasNext()) {
            int[] layer = it.next();
            int zeroCount = this.countValue(layer, 0);
            if (zeroCount < currentZeros) {
                currentZeros = zeroCount;
                layerWithFewestZeros = layer;
            }
        }
        return this.countValue(layerWithFewestZeros, 1) * this.countValue(layerWithFewestZeros, 2);
    }

    int[] getMessage() {
        int[] message = new int[this.WIDTH * this.HEIGHT];
        for (int i = 0; i < this.WIDTH * this.HEIGHT; i++) {
            for (int[] layer: this.layers) {
                if (layer[i] == 0 || layer[i] == 1) {
                    message[i] = layer[i];
                    break;
                }
            }
        }
        this.message = message;
        return message;
    }

    void printLine(int[] line) {
        for (int digit: line) {
            if (digit == 0) {
                System.out.print(" ");
            } else {
                System.out.print("\u2591");
            }
        }
        System.out.print("\n");
    }

    void printMessage() {
        for (int i = 0; i < 6; i++) {
            int[] line = Arrays.copyOfRange(this.message, i * this.WIDTH, (i + 1) * this.WIDTH);
            this.printLine(line);
        }
    }


}
