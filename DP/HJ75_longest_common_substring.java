package DP;

import java.util.Scanner;

public class HJ75_longest_common_substring {
    // 类比q1143：最长公共子序列LCS
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine(), p = sc.nextLine();
            System.out.println(getLCStr(s, p));
        }
    }

    private static int getLCStr(String s, String p) {
        int n1 = s.length(), n2 = p.length();
        int[][] dp = new int[n1+1][n2+1]; // s[0,i-1]与p[0,j-1]的最长公共子串(连续)
        int maxLen = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (s.charAt(i-1) == p.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return maxLen;
    }
}
