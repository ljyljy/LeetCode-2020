package Bit;

import java.util.Scanner;

public class HJ86_max_consecutive_bit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            calc(n);
        }
    }

    private static void calc(int n) {
        int maxCnt = 0, curCnt = 0;
        for (int i = 0; i < 32; i++) {
            int tmp = n & 1;
            if (tmp == 1) {
                curCnt++;
            } else {
                maxCnt = Math.max(maxCnt, curCnt);
                curCnt = 0;
            }
            n >>= 1;
        }
        System.out.println(maxCnt);
    }
}
