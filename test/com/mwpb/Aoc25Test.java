package com.mwpb;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Sets;

public class Aoc25Test {

	@Test
	void testInteractive() throws IOException {
		Aoc25 aoc25 = new Aoc25();

		Set<String> items = Set.of("coin", "easter egg", "monolith", "jam",
				"mug", "shell", "space heater", "fuel cell");
		Set<Set<String>> subsets = Sets.powerSet(items);

		Deque<String> commands = new ArrayDeque<>();
		commands.addAll(aoc25.getPreamble());

		for (Set<String> subset : subsets) {
			commands.addAll(aoc25.getDropCommands(items));
			commands.addAll(aoc25.getTakeCommands(subset));
			commands.add("north");
		}

		aoc25.intcode.interactive(commands);
	}
}
