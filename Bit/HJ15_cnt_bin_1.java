package Bit;

import java.util.*;

public class HJ15_cnt_bin_1 {
    // ��1��Integer.toBinaryString(num)
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String binStr = Integer.toBinaryString(num);
        int cnt = 0;
        for (char ch: binStr.toCharArray()) {
            if (ch == '1') {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    // ��2��λ����
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int cnt = 0;
        while (num > 0) {
            if ( (num&1) == 1) { // ��ȡnumĩβ��bit
                cnt++;
            }
            num >>= 1;
        }
        System.out.println(cnt);
    }
}