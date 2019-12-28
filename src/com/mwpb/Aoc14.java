package com.mwpb;

import java.util.*;

class Reactant {

    String name;
    int quantity;

    Reactant(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    Reactant(String inputString) {
        inputString = inputString.trim();
        String[] inputs = inputString.split(" ");
        this.quantity = Integer.parseInt(inputs[0]);
        this.name = inputs[1];
    }

    @Override
    public String toString() {
        return String.format("Reactant(%s, %d)", this.name, this.quantity);
    }
}

class Reaction {
    int outputQuantity;
    Map<String, Integer> reactants;

    Reaction(int outputQuantity, Map<String, Integer> reactants) {
        this.outputQuantity = outputQuantity;
        this.reactants = reactants;
    }

    @Override
    public String toString() {
        return String.format("*%d = %s", this.outputQuantity, this.reactants);
    }
}

public class Aoc14 {
    Map<String, Reaction> reactions;

    Aoc14(String reactionsString) {
        Scanner scanner = new Scanner(reactionsString);
        Map<String, Reaction> reactions = new HashMap<>();
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("=>");
            String[] inputStrings = line[0].split(",");
            Map<String, Integer> inputs = new HashMap<>();
            for (String inputString: inputStrings) {
                Reactant input = new Reactant(inputString);
                inputs.put(input.name, input.quantity);
            }
            Reactant output = new Reactant(line[1]);
            reactions.put(output.name, new Reaction(output.quantity, inputs));
            this.reactions = reactions;
            Map<String, Long> reactantsRequired = new HashMap<>();
            reactantsRequired.put("FUEL", 1L);
        }
    }

    boolean isFinished(Map<String, Long> reactantsRequired) {
//        if (reactantsRequired.getOrDefault("ORE", 0) > 100) return true;
        for (Map.Entry<String, Long> entry: reactantsRequired.entrySet()) {
            if (!entry.getKey().equals("ORE") && entry.getValue() > 0) {
                return false;
            }
        }
        return true;
    }

    Map.Entry<String, Integer> getNext(Map<String, Integer> reactantsRequired) {
        for (Map.Entry<String, Integer> entry: reactantsRequired.entrySet()) {
            if (!entry.getKey().equals("ORE") && entry.getValue() > 0) {
                return entry;
            }
        }
        throw new IllegalArgumentException("Not finished.");
    }

    Map.Entry<String, Long> getNextLong(Map<String, Long> reactantsRequired) {
        for (Map.Entry<String, Long> entry: reactantsRequired.entrySet()) {
            if (!entry.getKey().equals("ORE") && entry.getValue() > 0) {
                return entry;
            }
        }
        throw new IllegalArgumentException("Not finished.");
    }

    long fuelRequired() {
        return this.fuelRequired(1L);
    }

    long fuelRequired(long initFuel) {
        Map<String, Long> reactantsRequired = new HashMap<>();
        reactantsRequired.put("FUEL", initFuel);
        while (!this.isFinished(reactantsRequired)) {
//            System.out.println(reactantsRequired);
            Map.Entry<String, Long> entry = this.getNextLong(reactantsRequired);
            String outputName = entry.getKey();
            long outputQuantity = entry.getValue();
            Reaction reaction = this.reactions.get(outputName);
            long reactionOutputQuantity = reaction.outputQuantity;
            long n = (long) Math.ceil( (float) outputQuantity / (float) reactionOutputQuantity);
            for (Map.Entry<String, Integer> reactionEntry: reaction.reactants.entrySet()) {
                String inputName = reactionEntry.getKey();
                long inputQuantity = reactionEntry.getValue();
                long old = reactantsRequired.getOrDefault(inputName, 0L);
                reactantsRequired.put(inputName, old + (n) * inputQuantity);
            }
            reactantsRequired.put(outputName, outputQuantity - (n)  * reactionOutputQuantity);
        }
        return reactantsRequired.get("ORE");
    }

    long fuelForOre() {
        long upperBound = 1000000000L;
        long lowerBound = 1L;
        while (lowerBound < upperBound) {
            if (lowerBound == upperBound) {
                return upperBound;
            } else if (lowerBound == upperBound - 1) {
                System.out.println(String.format("%d - %d", lowerBound, upperBound));
                long fr = this.fuelRequired(upperBound);
                System.out.println(fr);
                if (fr < 1000000000000L) {
                    return upperBound;
                } else {
                    return lowerBound;
                }
            }
//            System.out.println(String.format("%d - %d", lowerBound, upperBound));
            long i = (lowerBound + upperBound) / 2;
            long fr = this.fuelRequired(i);
            System.out.println(fr);
            if (fr > 1000000000000L) {
                upperBound = i;
            } else if (fr == 1000000000000L) {
                return i;
            } else {
                lowerBound = i;
            }
//            System.out.println(String.format("%d - %d", lowerBound, upperBound));
        }
        return lowerBound;
    }

}
