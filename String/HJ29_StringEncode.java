package String;

import java.util.Scanner;

public class HJ29_StringEncode {
    private static String L1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static String L2 = "BCDEFGHIJKLMNOPQRSTUVWXYZAbcdefghijklmnopqrstuvwxyza1234567890";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str1 = sc.nextLine();
            String str2 = sc.nextLine();
            process(str1, 1); // 1: º”√‹
            process(str2, -1); // 2£∫Ω‚√‹
        }
    }

    private static void process(String s, int type) {
        StringBuilder res = new StringBuilder();
        if (type == 1) {
            for (char ch: s.toCharArray()) {
                int idx = L1.indexOf(ch);
                res.append(L2.charAt(idx));
            }
        } else if (type == -1) {
            for (char ch: s.toCharArray()) {
                int idx = L2.indexOf(ch);
                res.append(L1.charAt(idx));
            }
        }
        System.out.println(res);
    }
}

