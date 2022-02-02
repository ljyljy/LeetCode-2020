package Bit;

import java.util.Scanner;

public class hj5_convert_16_to_10 {
    private static int convert(String str) {
        char[] chs = str.toCharArray();
        int res = 0;
        for (int i = 2; i < chs.length; i++) {
            char ch = chs[i];
            int num = 0;
            if (Character.isDigit(ch)) {
                num = ch - '0';
            } else {
                num = 10 + ch - 'A';
            }
            res = res * 16 + num;
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            System.out.println(convert(str));
        }
    }
}
