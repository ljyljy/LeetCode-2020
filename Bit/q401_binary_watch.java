package Bit;

import java.util.*;

public class q401_binary_watch {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    res.add(h + ":" + (m < 10? "0": "") + m);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // test
        for (int i = 0; i < 10; i++) {
//            System.out.println(i + ": " + Integer.bitCount(i) + "----" + Integer.toBinaryString(i));
            System.out.printf("%d:%d ---- %4s\n", i, Integer.bitCount(i), Integer.toBinaryString(i));
        }

        // case
        q401_binary_watch sol = new q401_binary_watch();
        int turnedOn = 5;
        System.out.println(sol.readBinaryWatch(turnedOn));
    }
}
