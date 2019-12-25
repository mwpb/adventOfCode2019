package com.mwpb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Aoc12Test {

    @Test
    void init() {
        Aoc12 aoc12 = new Aoc12("<x=-16, y=-1, z=-12>\n" +
                "<x=0, y=-4, z=-17>\n" +
                "<x=-11, y=11, z=0>\n" +
                "<x=2, y=2, z=-6>");
    }

    @Test
    void energy() {
        Moon moon = new Moon(2, 1, -3);
        moon.vx = -3;
        moon.vy = -2;
        moon.vz = 1;
        Assertions.assertEquals(6, moon.potentialEnergy());
        Assertions.assertEquals(6, moon.kineticEnergy());
        Assertions.assertEquals(36, moon.totalEnergy());
    }

    @Test
    void gcd() {
        Aoc12 aoc12 = new Aoc12("<x=-16, y=-1, z=-12>\n" +
                "<x=0, y=-4, z=-17>\n" +
                "<x=-11, y=11, z=0>\n" +
                "<x=2, y=2, z=-6>");
        Assertions.assertEquals(14, aoc12.gcd(42, 56));
        Assertions.assertEquals(4, aoc12.gcd(186028,135024));
    }

    @Test
    void lcm() {
        Aoc12 aoc12 = new Aoc12("<x=-16, y=-1, z=-12>\n" +
                "<x=0, y=-4, z=-17>\n" +
                "<x=-11, y=11, z=0>\n" +
                "<x=2, y=2, z=-6>");
        Assertions.assertEquals(12, aoc12.lcm(4, 6));
        Assertions.assertEquals(6279561168L, aoc12.lcm(186028, 135024));
        Assertions.assertEquals(303070460651184L, aoc12.lcm(6279561168L, 193052));
    }

    @Test
    void applyGravity() {
        Aoc12 aoc12 = new Aoc12("<x=-16, y=-1, z=-12>\n" +
                "<x=0, y=-4, z=-17>\n" +
                "<x=-11, y=11, z=0>\n" +
                "<x=2, y=2, z=-6>");
        aoc12.run(1000);
        System.out.println(Arrays.toString(aoc12.moons));
        System.out.println(aoc12.totalEnergy());
    }

    @Test
    void findFirstRepeat() {
        Aoc12 aoc12test1 = new Aoc12("<x=-1, y=0, z=2>\n" +
                "<x=2, y=-10, z=-7>\n" +
                "<x=4, y=-8, z=8>\n" +
                "<x=3, y=5, z=-1>");
        Assertions.assertEquals(2772, aoc12test1.findFirstRepeat());

        Aoc12 aoc12 = new Aoc12("<x=-16, y=-1, z=-12>\n" +
                "<x=0, y=-4, z=-17>\n" +
                "<x=-11, y=11, z=0>\n" +
                "<x=2, y=2, z=-6>");
        Assertions.assertEquals(303070460651184L, aoc12.findFirstRepeat());
    }

}