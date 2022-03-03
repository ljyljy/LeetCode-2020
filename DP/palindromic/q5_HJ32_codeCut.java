package DP.palindromic;

import java.util.Scanner;

public class q5_HJ32_codeCut {
    // LC-Q5.最长回文子串.长度
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String code = sc.nextLine();
            System.out.println(getLongestLen(code));
        }
    }

    private static int getLongestLen(String s) {
        int n = s.length();
        if (n == 1) return 1;
        char[] ss = s.toCharArray();
        boolean[][] dp = new boolean[n+1][n+1]; // s[i-1, j-1]能否构成回文串
        int maxLen = 1;
        for (int i = n; i >= 1; i--) {
            for (int j = i; j <= n; j++) {
                if (ss[i-1] == ss[j-1] && (j-i<=1 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                    maxLen = Math.max(maxLen, j-i+1);
                }
            }
        }
        return maxLen;
    }
}
