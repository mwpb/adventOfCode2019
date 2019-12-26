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
        }
    }

    boolean isFinished(Map<String, Integer> reactantsRequired) {
//        if (reactantsRequired.getOrDefault("ORE", 0) > 100) return true;
        for (Map.Entry<String, Integer> entry: reactantsRequired.entrySet()) {
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

    int fuelRequired() {
        Map<String, Integer> reactantsRequired = new HashMap<>();
        reactantsRequired.put("FUEL", 1);
        while (!this.isFinished(reactantsRequired)) {
//            System.out.println(reactantsRequired);
            Map.Entry<String, Integer> entry = this.getNext(reactantsRequired);
            String outputName = entry.getKey();
            int outputQuantity = entry.getValue();
            Reaction reaction = this.reactions.get(outputName);
            int reactionOutputQuantity = reaction.outputQuantity;
            for (Map.Entry<String, Integer> reactionEntry: reaction.reactants.entrySet()) {
                String inputName = reactionEntry.getKey();
                int inputQuantity = reactionEntry.getValue();
                int old = reactantsRequired.getOrDefault(inputName, 0);
                reactantsRequired.put(inputName, old + inputQuantity);
            }
            reactantsRequired.put(outputName, outputQuantity - reactionOutputQuantity);
        }
        return reactantsRequired.get("ORE");
    }

}
