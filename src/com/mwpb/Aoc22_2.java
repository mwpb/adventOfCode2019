package com.mwpb;

import java.math.BigInteger;

public class Aoc22_2 {
	
	int start;
	BigInteger n, a, b; // x \mapsto ax +b (mod n)
	BigInteger A, B;
	Aoc22_2(long n, int start) {
		this.n = BigInteger.valueOf(n);
		this.a = BigInteger.valueOf(1);
		this.b = BigInteger.valueOf(0);
		this.start = start;
	}
	
	BigInteger getCurrent() {
		BigInteger i = this.a.multiply(BigInteger.valueOf(this.start)).mod(this.n);
		return i.add(this.b).mod(this.n);
	}
	
	void cut(int c) {
		this.b = this.b.add(BigInteger.valueOf(c)).mod(this.n);
//		System.out.println(": "+this.getCurrent());
	}
	
	void dealIntoNewStack() {
		this.a = this.a.negate().mod(this.n);
		this.b = this.b.add(BigInteger.valueOf(1)).negate().mod(this.n);
//		System.out.println(": "+this.getCurrent());
	}
	
	void dealWithIncrement(int c) {
		BigInteger modInv = BigInteger.valueOf(c).modInverse(this.n);
		this.a = this.a.multiply(modInv).mod(this.n);
		this.b = this.b.multiply(modInv).mod(this.n);
//		System.out.println(": "+this.getCurrent());
	}
	
	void reversedInstructions() {
		this.cut(-2244);
		this.dealIntoNewStack();
		this.cut(358);
		this.dealWithIncrement(28);
		this.cut(-1069);
		this.dealWithIncrement(45);
		this.cut(-9018);
		this.dealIntoNewStack();
		this.cut(-2630);
		this.dealWithIncrement(72);
		this.cut(5182);
		this.dealIntoNewStack();
		this.cut(9594);
		this.dealWithIncrement(34);
		this.cut(2516);
		this.dealWithIncrement(17);
		this.cut(-8862);
		this.dealWithIncrement(40);
		this.cut(6104);
		this.dealWithIncrement(59);
		this.cut(-9577);
		this.dealWithIncrement(31);
		this.cut(1740);
		this.dealWithIncrement(71);
		this.cut(-2957);
		this.dealWithIncrement(7);
		this.cut(8358);
		this.dealWithIncrement(67);
		this.cut(8611);
		this.dealWithIncrement(27);
		this.cut(562);
		this.dealWithIncrement(21);
		this.cut(71);
		this.dealWithIncrement(22);
		this.cut(312);
		this.dealWithIncrement(31);
		this.cut(3316);
		this.dealIntoNewStack();
		this.cut(-370);
		this.dealWithIncrement(66);
		this.cut(-5431);
		this.dealIntoNewStack();
		this.cut(7654);
		this.dealWithIncrement(75);
		this.dealIntoNewStack();
		this.dealWithIncrement(64);
		this.cut(-2671);
		this.dealWithIncrement(39);
		this.cut(-3416);
		this.dealIntoNewStack();
		this.cut(-6939);
		this.dealWithIncrement(53);
		this.cut(4084);
		this.dealWithIncrement(68);
		this.cut(625);
		this.dealWithIncrement(13);
		this.cut(6849);
		this.dealWithIncrement(53);
		this.cut(373);
		this.dealWithIncrement(4);
		this.cut(-6179);
		this.dealWithIncrement(26);
		this.cut(-6166);
		this.dealWithIncrement(2);
		this.cut(-2003);
		this.dealWithIncrement(60);
		this.dealIntoNewStack();
		this.dealWithIncrement(25);
		this.dealIntoNewStack();
		this.dealWithIncrement(59);
		this.dealIntoNewStack();
		this.dealWithIncrement(49);
		this.cut(4710);
		this.dealWithIncrement(48);
		this.cut(2194);
		this.dealWithIncrement(66);
		this.cut(1127);
		this.dealWithIncrement(66);
		this.cut(2759);
		this.dealIntoNewStack();
		this.cut(-2419);
		this.dealWithIncrement(30);
		this.cut(-2836);
		this.dealWithIncrement(8);
		this.cut(-953);
		this.dealWithIncrement(50);
		this.cut(9852);
		this.dealIntoNewStack();
		this.cut(-2438);
		this.dealIntoNewStack();
		this.cut(4151);
		this.dealIntoNewStack();
		this.cut(1470);
		this.dealIntoNewStack();
		this.dealWithIncrement(15);
		this.cut(-8068);
		this.dealIntoNewStack();
		this.dealWithIncrement(9);
		this.cut(-4394);
		this.dealWithIncrement(15);
	}
	
	void getBigAB() {
		this.reversedInstructions();
		this.A = this.a;
		this.B = this.b;
	}
	
	BigInteger run(long mLong) {
		BigInteger m = BigInteger.valueOf(mLong);
		BigInteger APowM = this.A.modPow(m, this.n);
		BigInteger AMinusOneInv = this.a.subtract(BigInteger.valueOf(1)).modInverse(this.n);
		BigInteger constantBottom = this.b.multiply(AMinusOneInv).mod(this.n);
		BigInteger rhs = constantBottom.multiply(APowM.subtract(BigInteger.valueOf(1))).mod(this.n);
		BigInteger lhs = APowM.multiply(BigInteger.valueOf(this.start)).mod(this.n);
		return lhs.add(rhs).mod(this.n);
	}
}
