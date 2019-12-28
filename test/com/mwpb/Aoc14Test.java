package com.mwpb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Aoc14Test {

    @Test
    void init() {
        Aoc14 aoc14 = new Aoc14("10 ORE => 10 A\n" +
                "1 ORE => 1 B\n" +
                "7 A, 1 B => 1 C\n" +
                "7 A, 1 C => 1 D\n" +
                "7 A, 1 D => 1 E\n" +
                "7 A, 1 E => 1 FUEL");
        System.out.println(aoc14.reactions);
    }

    @Test
    void fuelRequred() {
        Aoc14 aoc14test = new Aoc14("10 ORE => 10 A\n" +
                "1 ORE => 1 B\n" +
                "7 A, 1 B => 1 C\n" +
                "7 A, 1 C => 1 D\n" +
                "7 A, 1 D => 1 E\n" +
                "7 A, 1 E => 1 FUEL");
        Assertions.assertEquals(31, aoc14test.fuelRequired());

        Aoc14 aoc14test2 = new Aoc14("9 ORE => 2 A\n" +
                "8 ORE => 3 B\n" +
                "7 ORE => 5 C\n" +
                "3 A, 4 B => 1 AB\n" +
                "5 B, 7 C => 1 BC\n" +
                "4 C, 1 A => 1 CA\n" +
                "2 AB, 3 BC, 4 CA => 1 FUEL\n");
        Assertions.assertEquals(165, aoc14test2.fuelRequired());

        Aoc14 aoc14test3 = new Aoc14("157 ORE => 5 NZVS\n" +
                "165 ORE => 6 DCFZ\n" +
                "44 XJWVT, 5 KHKGT, 1 QDVJ, 29 NZVS, 9 GPVTF, 48 HKGWZ => 1 FUEL\n" +
                "12 HKGWZ, 1 GPVTF, 8 PSHF => 9 QDVJ\n" +
                "179 ORE => 7 PSHF\n" +
                "177 ORE => 5 HKGWZ\n" +
                "7 DCFZ, 7 PSHF => 2 XJWVT\n" +
                "165 ORE => 2 GPVTF\n" +
                "3 DCFZ, 7 NZVS, 5 HKGWZ, 10 PSHF => 8 KHKGT");
        Assertions.assertEquals(13312, aoc14test3.fuelRequired());

        Aoc14 aoc14test4 = new Aoc14("2 VPVL, 7 FWMGM, 2 CXFTF, 11 MNCFX => 1 STKFG\n" +
                "17 NVRVD, 3 JNWZP => 8 VPVL\n" +
                "53 STKFG, 6 MNCFX, 46 VJHF, 81 HVMC, 68 CXFTF, 25 GNMV => 1 FUEL\n" +
                "22 VJHF, 37 MNCFX => 5 FWMGM\n" +
                "139 ORE => 4 NVRVD\n" +
                "144 ORE => 7 JNWZP\n" +
                "5 MNCFX, 7 RFSQX, 2 FWMGM, 2 VPVL, 19 CXFTF => 3 HVMC\n" +
                "5 VJHF, 7 MNCFX, 9 VPVL, 37 CXFTF => 6 GNMV\n" +
                "145 ORE => 6 MNCFX\n" +
                "1 NVRVD => 8 CXFTF\n" +
                "1 VJHF, 6 MNCFX => 4 RFSQX\n" +
                "176 ORE => 6 VJHF");
        Assertions.assertEquals(180697, aoc14test4.fuelRequired());

        Aoc14 aoc14test5 = new Aoc14("171 ORE => 8 CNZTR\n" +
                "7 ZLQW, 3 BMBT, 9 XCVML, 26 XMNCP, 1 WPTQ, 2 MZWV, 1 RJRHP => 4 PLWSL\n" +
                "114 ORE => 4 BHXH\n" +
                "14 VRPVC => 6 BMBT\n" +
                "6 BHXH, 18 KTJDG, 12 WPTQ, 7 PLWSL, 31 FHTLT, 37 ZDVW => 1 FUEL\n" +
                "6 WPTQ, 2 BMBT, 8 ZLQW, 18 KTJDG, 1 XMNCP, 6 MZWV, 1 RJRHP => 6 FHTLT\n" +
                "15 XDBXC, 2 LTCX, 1 VRPVC => 6 ZLQW\n" +
                "13 WPTQ, 10 LTCX, 3 RJRHP, 14 XMNCP, 2 MZWV, 1 ZLQW => 1 ZDVW\n" +
                "5 BMBT => 4 WPTQ\n" +
                "189 ORE => 9 KTJDG\n" +
                "1 MZWV, 17 XDBXC, 3 XCVML => 2 XMNCP\n" +
                "12 VRPVC, 27 CNZTR => 2 XDBXC\n" +
                "15 KTJDG, 12 BHXH => 5 XCVML\n" +
                "3 BHXH, 2 VRPVC => 7 MZWV\n" +
                "121 ORE => 7 VRPVC\n" +
                "7 XCVML => 6 RJRHP\n" +
                "5 BHXH, 4 VRPVC => 5 LTCX");
        Assertions.assertEquals(2210736, aoc14test5.fuelRequired());

        Aoc14 aoc14 = new Aoc14("4 QBQB, 2 NTLZ => 2 DPJP\n" +
                "5 SCSDX, 3 WBLBS => 5 GVPG\n" +
                "128 ORE => 1 WCQS\n" +
                "14 LHMZ => 2 SWBFV\n" +
                "5 NZJV, 1 MCLXC => 2 BSRT\n" +
                "1 WJHZ => 6 HRZV\n" +
                "5 SPNML, 1 QTVZL => 6 HBGD\n" +
                "1 BSRT, 1 JRBM, 1 GVPG => 2 XVDQT\n" +
                "10 CBQSB => 6 NRXGX\n" +
                "6 TBFQ => 7 QPXS\n" +
                "1 LKSVN => 1 FBFC\n" +
                "39 CBQSB => 7 PSLXZ\n" +
                "3 HBGD, 4 RCZF => 4 ZCTS\n" +
                "2 BMDV, 6 DPJP => 1 RCZF\n" +
                "1 GPBXP, 11 SWBFV, 12 XSBGR, 7 ZCLVG, 9 VQLN, 12 HRZV, 3 VLDVB, 3 QTVZL, 12 DVSD, 62 PSLXZ => 1 FUEL\n" +
                "10 CFPG, 1 TBFQ => 3 NHKZB\n" +
                "24 QLMJ => 1 SCSDX\n" +
                "2 VKHZC => 1 SMLPV\n" +
                "3 SMLPV, 11 NZJV, 1 HTSXK => 2 GPBXP\n" +
                "1 SCKB => 3 TBFQ\n" +
                "3 VKHZC, 2 XVDQT => 6 PHJH\n" +
                "3 QBQB => 3 XHWH\n" +
                "19 NHKZB, 3 MBQVK, 10 HTSXK, 2 GXVQG, 8 VKHZC, 1 XHWH, 1 RCZF => 5 ZCLVG\n" +
                "1 GVPG => 4 QTVZL\n" +
                "4 TMHMV => 7 LHMZ\n" +
                "5 NRXGX, 9 NTLZ, 3 PSLXZ => 1 BMDV\n" +
                "10 MCLXC => 3 VKHZC\n" +
                "1 KTLR => 1 VLDVB\n" +
                "5 HTSXK => 6 TMHMV\n" +
                "5 LKSVN, 1 CGQHF, 11 WJHZ => 1 HGZC\n" +
                "15 XHWH, 1 WBLBS => 4 NZJV\n" +
                "3 MCLXC => 9 KTLR\n" +
                "1 CBQSB => 1 SCKB\n" +
                "140 ORE => 4 LKSVN\n" +
                "2 NZJV, 8 XVDQT, 1 PHJH => 8 GXVQG\n" +
                "21 NJXV, 1 XHWH, 12 TMHMV, 1 QPXS, 10 ZCTS, 3 TBFQ, 1 VLDVB => 7 DVSD\n" +
                "4 QLMJ, 2 LKSVN => 1 NTLZ\n" +
                "1 LKSVN => 4 QBQB\n" +
                "1 SPNML, 3 CPBQ => 4 BKLPC\n" +
                "2 CFPG => 5 MCLXC\n" +
                "147 ORE => 7 CGQHF\n" +
                "7 HGZC, 5 QLMJ => 3 CFPG\n" +
                "3 LCLQV, 3 MLXGB, 1 NTLZ => 8 JRBM\n" +
                "4 NHWG => 5 GPQN\n" +
                "2 XHWH => 7 WBLBS\n" +
                "7 CGFN, 2 RCZF, 13 NHWG, 1 VLDVB, 3 PHJH, 9 CBQSB => 9 XSBGR\n" +
                "181 ORE => 7 WJHZ\n" +
                "8 WJHZ => 9 CBQSB\n" +
                "3 BTQWK, 8 BKLPC => 2 CGFN\n" +
                "3 SCSDX => 3 NJXV\n" +
                "6 JTBM, 23 GPQN => 1 VQLN\n" +
                "23 MCLXC, 1 NTLZ => 7 SPNML\n" +
                "1 SPNML => 2 JTBM\n" +
                "1 BMDV => 7 HTSXK\n" +
                "1 WBLBS => 9 NHWG\n" +
                "4 FBFC, 1 LKSVN, 4 VKHZC => 7 CPBQ\n" +
                "1 WCQS => 7 QLMJ\n" +
                "1 BMDV, 2 DPJP => 6 MBQVK\n" +
                "3 XHWH, 5 QLMJ => 4 LCLQV\n" +
                "1 CBQSB, 2 PSLXZ => 2 MLXGB\n" +
                "3 NHWG => 9 BTQWK");
        System.out.println(aoc14.fuelRequired());
//        Assertions.assertEquals(2210736, aoc14.fuelRequired());
    }

    @Test
    void fuelForOre() {

        Aoc14 aoc14test3 = new Aoc14("157 ORE => 5 NZVS\n" +
                "165 ORE => 6 DCFZ\n" +
                "44 XJWVT, 5 KHKGT, 1 QDVJ, 29 NZVS, 9 GPVTF, 48 HKGWZ => 1 FUEL\n" +
                "12 HKGWZ, 1 GPVTF, 8 PSHF => 9 QDVJ\n" +
                "179 ORE => 7 PSHF\n" +
                "177 ORE => 5 HKGWZ\n" +
                "7 DCFZ, 7 PSHF => 2 XJWVT\n" +
                "165 ORE => 2 GPVTF\n" +
                "3 DCFZ, 7 NZVS, 5 HKGWZ, 10 PSHF => 8 KHKGT");
//        Assertions.assertEquals(82892753, aoc14test3.fuelForOre());
//
        Aoc14 aoc14test4 = new Aoc14("2 VPVL, 7 FWMGM, 2 CXFTF, 11 MNCFX => 1 STKFG\n" +
                "17 NVRVD, 3 JNWZP => 8 VPVL\n" +
                "53 STKFG, 6 MNCFX, 46 VJHF, 81 HVMC, 68 CXFTF, 25 GNMV => 1 FUEL\n" +
                "22 VJHF, 37 MNCFX => 5 FWMGM\n" +
                "139 ORE => 4 NVRVD\n" +
                "144 ORE => 7 JNWZP\n" +
                "5 MNCFX, 7 RFSQX, 2 FWMGM, 2 VPVL, 19 CXFTF => 3 HVMC\n" +
                "5 VJHF, 7 MNCFX, 9 VPVL, 37 CXFTF => 6 GNMV\n" +
                "145 ORE => 6 MNCFX\n" +
                "1 NVRVD => 8 CXFTF\n" +
                "1 VJHF, 6 MNCFX => 4 RFSQX\n" +
                "176 ORE => 6 VJHF");
        Assertions.assertEquals(5586022, aoc14test4.fuelForOre());
//
        Aoc14 aoc14test5 = new Aoc14("171 ORE => 8 CNZTR\n" +
                "7 ZLQW, 3 BMBT, 9 XCVML, 26 XMNCP, 1 WPTQ, 2 MZWV, 1 RJRHP => 4 PLWSL\n" +
                "114 ORE => 4 BHXH\n" +
                "14 VRPVC => 6 BMBT\n" +
                "6 BHXH, 18 KTJDG, 12 WPTQ, 7 PLWSL, 31 FHTLT, 37 ZDVW => 1 FUEL\n" +
                "6 WPTQ, 2 BMBT, 8 ZLQW, 18 KTJDG, 1 XMNCP, 6 MZWV, 1 RJRHP => 6 FHTLT\n" +
                "15 XDBXC, 2 LTCX, 1 VRPVC => 6 ZLQW\n" +
                "13 WPTQ, 10 LTCX, 3 RJRHP, 14 XMNCP, 2 MZWV, 1 ZLQW => 1 ZDVW\n" +
                "5 BMBT => 4 WPTQ\n" +
                "189 ORE => 9 KTJDG\n" +
                "1 MZWV, 17 XDBXC, 3 XCVML => 2 XMNCP\n" +
                "12 VRPVC, 27 CNZTR => 2 XDBXC\n" +
                "15 KTJDG, 12 BHXH => 5 XCVML\n" +
                "3 BHXH, 2 VRPVC => 7 MZWV\n" +
                "121 ORE => 7 VRPVC\n" +
                "7 XCVML => 6 RJRHP\n" +
                "5 BHXH, 4 VRPVC => 5 LTCX");
        Assertions.assertEquals(460664, aoc14test5.fuelForOre());

        Aoc14 aoc14 = new Aoc14("4 QBQB, 2 NTLZ => 2 DPJP\n" +
                "5 SCSDX, 3 WBLBS => 5 GVPG\n" +
                "128 ORE => 1 WCQS\n" +
                "14 LHMZ => 2 SWBFV\n" +
                "5 NZJV, 1 MCLXC => 2 BSRT\n" +
                "1 WJHZ => 6 HRZV\n" +
                "5 SPNML, 1 QTVZL => 6 HBGD\n" +
                "1 BSRT, 1 JRBM, 1 GVPG => 2 XVDQT\n" +
                "10 CBQSB => 6 NRXGX\n" +
                "6 TBFQ => 7 QPXS\n" +
                "1 LKSVN => 1 FBFC\n" +
                "39 CBQSB => 7 PSLXZ\n" +
                "3 HBGD, 4 RCZF => 4 ZCTS\n" +
                "2 BMDV, 6 DPJP => 1 RCZF\n" +
                "1 GPBXP, 11 SWBFV, 12 XSBGR, 7 ZCLVG, 9 VQLN, 12 HRZV, 3 VLDVB, 3 QTVZL, 12 DVSD, 62 PSLXZ => 1 FUEL\n" +
                "10 CFPG, 1 TBFQ => 3 NHKZB\n" +
                "24 QLMJ => 1 SCSDX\n" +
                "2 VKHZC => 1 SMLPV\n" +
                "3 SMLPV, 11 NZJV, 1 HTSXK => 2 GPBXP\n" +
                "1 SCKB => 3 TBFQ\n" +
                "3 VKHZC, 2 XVDQT => 6 PHJH\n" +
                "3 QBQB => 3 XHWH\n" +
                "19 NHKZB, 3 MBQVK, 10 HTSXK, 2 GXVQG, 8 VKHZC, 1 XHWH, 1 RCZF => 5 ZCLVG\n" +
                "1 GVPG => 4 QTVZL\n" +
                "4 TMHMV => 7 LHMZ\n" +
                "5 NRXGX, 9 NTLZ, 3 PSLXZ => 1 BMDV\n" +
                "10 MCLXC => 3 VKHZC\n" +
                "1 KTLR => 1 VLDVB\n" +
                "5 HTSXK => 6 TMHMV\n" +
                "5 LKSVN, 1 CGQHF, 11 WJHZ => 1 HGZC\n" +
                "15 XHWH, 1 WBLBS => 4 NZJV\n" +
                "3 MCLXC => 9 KTLR\n" +
                "1 CBQSB => 1 SCKB\n" +
                "140 ORE => 4 LKSVN\n" +
                "2 NZJV, 8 XVDQT, 1 PHJH => 8 GXVQG\n" +
                "21 NJXV, 1 XHWH, 12 TMHMV, 1 QPXS, 10 ZCTS, 3 TBFQ, 1 VLDVB => 7 DVSD\n" +
                "4 QLMJ, 2 LKSVN => 1 NTLZ\n" +
                "1 LKSVN => 4 QBQB\n" +
                "1 SPNML, 3 CPBQ => 4 BKLPC\n" +
                "2 CFPG => 5 MCLXC\n" +
                "147 ORE => 7 CGQHF\n" +
                "7 HGZC, 5 QLMJ => 3 CFPG\n" +
                "3 LCLQV, 3 MLXGB, 1 NTLZ => 8 JRBM\n" +
                "4 NHWG => 5 GPQN\n" +
                "2 XHWH => 7 WBLBS\n" +
                "7 CGFN, 2 RCZF, 13 NHWG, 1 VLDVB, 3 PHJH, 9 CBQSB => 9 XSBGR\n" +
                "181 ORE => 7 WJHZ\n" +
                "8 WJHZ => 9 CBQSB\n" +
                "3 BTQWK, 8 BKLPC => 2 CGFN\n" +
                "3 SCSDX => 3 NJXV\n" +
                "6 JTBM, 23 GPQN => 1 VQLN\n" +
                "23 MCLXC, 1 NTLZ => 7 SPNML\n" +
                "1 SPNML => 2 JTBM\n" +
                "1 BMDV => 7 HTSXK\n" +
                "1 WBLBS => 9 NHWG\n" +
                "4 FBFC, 1 LKSVN, 4 VKHZC => 7 CPBQ\n" +
                "1 WCQS => 7 QLMJ\n" +
                "1 BMDV, 2 DPJP => 6 MBQVK\n" +
                "3 XHWH, 5 QLMJ => 4 LCLQV\n" +
                "1 CBQSB, 2 PSLXZ => 2 MLXGB\n" +
                "3 NHWG => 9 BTQWK");
        System.out.println(aoc14.fuelForOre());
    }
}