package com.mwpb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Intcode {

	long[] memory;
	int pointer;
	List<Long> input;
	int relativeBase;
	int sizeOfOutOfBounds = 10;

	Intcode(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		String[] strings = scanner.nextLine().split(",");
		long[] memory = new long[strings.length * this.sizeOfOutOfBounds];
		for (int i = 0; i < strings.length; i++) {
			memory[i] = Long.parseLong(strings[i]);
		}
		this.pointer = 0;
		this.input = new LinkedList<Long>();
		this.memory = memory;
		scanner.close();
	}

	Intcode(String line) {
		line = line.replaceAll("\\s+", "");
		String[] strings = line.split(",");
		long[] memory = new long[strings.length * this.sizeOfOutOfBounds];
		for (int i = 0; i < strings.length; i++) {
			memory[i] = Long.parseLong(strings[i]);
		}
		this.pointer = 0;
		this.input = new LinkedList<Long>();
		this.memory = memory;
	}

	void addInput(String s) {
		for (char c : s.toCharArray()) {
			long l = (int) c;
			this.input.add(l);
		}
	}

	int getOpcode() {
		return (int) this.memory[this.pointer] % 100;
	}

	int first() {
		int mode = (int) (this.memory[this.pointer] / 100) % 10;
		long first = 0;
//        System.out.println(this.relativeBase + (int) this.memory[this.pointer + 1]);
		switch (mode) {
		case 0:
			first = (int) this.memory[this.pointer + 1];
			break;
		case 1:
			first = this.pointer + 1;
			break;
		case 2:
			first = this.relativeBase + (int) this.memory[this.pointer + 1];
			break;
		}
		return (int) first;
	}

	int second() {
		int mode = (int) (this.memory[this.pointer] / 1000) % 10;
		long second = 0;
		switch (mode) {
		case 0:
			second = (int) this.memory[this.pointer + 2];
			break;
		case 1:
			second = this.pointer + 2;
			break;
		case 2:
			second = this.relativeBase + (int) this.memory[this.pointer + 2];
			break;
		}
		return (int) second;
	}

	int third() {
		int mode = (int) (this.memory[this.pointer] / 10000) % 10;
		long third = 0;
		switch (mode) {
		case 0:
		case 1:
			third = (int) this.memory[this.pointer + 3];
			break;
		case 2:
			third = this.relativeBase + (int) this.memory[this.pointer + 3];
			break;
		}
		return (int) third;
	}

	void processCode1() {
		this.memory[this.third()] = this.memory[this.first()]
				+ this.memory[this.second()];
		this.pointer += 4;
	}

	void processCode2() {
		this.memory[this.third()] = this.memory[this.first()]
				* this.memory[this.second()];
		this.pointer += 4;
	}

	void processCode3() {
//        this.memory[this.getLiteralFirstParameter()] = this.input.remove(0);
//        System.out.println(this.getFirstParameter());
		this.memory[this.first()] = this.input.remove(0);
		this.pointer += 2;
	}

	long processCode4() {
		long output = this.memory[this.first()];
		this.pointer += 2;
		return output;
	}

	void processCode5() {
		if (this.memory[this.first()] != 0) {
			this.pointer = (int) this.memory[this.second()];
		} else {
			this.pointer += 3;
		}
	}

	void processCode6() {
		if (this.memory[this.first()] == 0) {
			this.pointer = (int) this.memory[this.second()];
		} else {
			this.pointer += 3;
		}
	}

	void processCode7() {
		if (this.memory[this.first()] < this.memory[this.second()]) {
			this.memory[this.third()] = 1;
		} else {
			this.memory[this.third()] = 0;
		}
		this.pointer += 4;
	}

	void processCode8() {
		if (this.memory[this.first()] == this.memory[this.second()]) {
			this.memory[this.third()] = 1;
		} else {
			this.memory[this.third()] = 0;
		}
		this.pointer += 4;
	}

	void processCode9() {
		this.relativeBase += this.memory[this.first()];
//        System.out.println(this.relativeBase);
		this.pointer += 2;
	}

	Optional<Long> run() {
		boolean finished = false;
		while (!finished) {
//            System.out.println(String.format("%s: %d", Arrays.toString(this.memory), this.memory[this.pointer]));
			switch (this.getOpcode()) {
			case 1:
				processCode1();
				break;
			case 2:
				processCode2();
				break;
			case 3:
				processCode3();
				break;
			case 4:
				return Optional.of(processCode4());
			case 5:
				this.processCode5();
				break;
			case 6:
				this.processCode6();
				break;
			case 7:
				this.processCode7();
				break;
			case 8:
				this.processCode8();
				break;
			case 9:
				this.processCode9();
				break;
			case 99:
				finished = true;
				break;
			}
		}
		return Optional.empty();
	}

	Optional<Long> runInputs() {
		boolean finished = false;
		while (!finished) {
//            System.out.println(String.format("%s: %d", Arrays.toString(this.memory), this.memory[this.pointer]));
			switch (this.getOpcode()) {
			case 1:
				processCode1();
				break;
			case 2:
				processCode2();
				break;
			case 3:
				if (this.input.size() > 0) {
					processCode3();
				} else {
					return Optional.empty();
				}
				break;
			case 4:
				return Optional.of(processCode4());
			case 5:
				this.processCode5();
				break;
			case 6:
				this.processCode6();
				break;
			case 7:
				this.processCode7();
				break;
			case 8:
				this.processCode8();
				break;
			case 9:
				this.processCode9();
				break;
			case 99:
				finished = true;
				break;
			}
		}
		return Optional.empty();
	}

	Deque<Long> runInputsToOutputs() {
		var q = new ArrayDeque<Long>();
		Optional<Long> next;
		do {
			next = this.runInputs();
			if (next.isPresent()) {
				q.add(next.get());
			}
		} while (next.isPresent());
		return q;
	}

	String runAll() {
		StringBuilder sb = new StringBuilder();
		Optional<Long> output = this.run();
		while (output.isPresent()) {
			if (output.get() > 255) {
				sb.append(output);
			}
			sb.append((char) (long) output.get());
			output = this.run();
		}
		return sb.toString();
	}

}
