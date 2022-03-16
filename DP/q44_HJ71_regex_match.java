package DP;

import java.util.Scanner;

public class q44_HJ71_regex_match {
    // 类比Q44, 但不同！！通配符只匹配字母&数字！！！！
    static char[] ss, pp;
    static int n1, n2;
    static boolean[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String p = " " + sc.nextLine(), s = " " + sc.nextLine();
            ss = s.toCharArray(); pp = p.toCharArray();
            n1 = s.length(); n2 = p.length();
            System.out.println(isMatch(ss, pp));
        }
    }

    private static boolean isMatch(char[] ss, char[] pp) {
        dp = new boolean[n1][n2];
        dp[0][0] = true;
        for (int i = 0; i < n1; i++) {
            for (int j = 1; j < n2; j++) {
                if (pp[j] != '*') {
                    dp[i][j] = (i-1>= 0 && dp[i-1][j-1]) && match(i, j);
                } else {
                    dp[i][j] = (i-1>= 0 && dp[i-1][j]) || dp[i][j-1];
                }
            }
        }
        return dp[n1-1][n2-1];

    }

    // 坑！?通配符only匹配[0-9A-Za-z]！！！！
    private static boolean match(int i, int j) {
        boolean flag = false;
        if (Character.isLetter(ss[i]) && Character.isLetter(pp[j])) {
            flag = (""+ss[i]).toLowerCase().equals((""+pp[j]).toLowerCase());
            if (flag) return true;
        }
        // s与p对应字符相同 || p对应位置为通配符'./?'(必须匹配一个字符)
        flag = flag || ss[i] == pp[j] ||
                ((""+ss[i]).matches("[\\d\\w]") && pp[j] == '?');
        return flag;
    }
}
