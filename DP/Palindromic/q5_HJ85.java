package DP.Palindromic;

import java.util.Scanner;

public class q5_HJ85 {
    // Àà±Èq5
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            char[] ss = str.toCharArray();
            int n = str.length();
            boolean[][] dp = new boolean[n+1][n+1];
            dp[0][0] = true;
            int maxLen = 0;
            for (int i = n; i >= 1; i--) {
                for (int j = i; j <= n; j++) {
                    if (ss[i-1] == ss[j-1] && (j-i<=1 || dp[i+1][j-1])) {
                        dp[i][j] = true;
                        maxLen = Math.max(maxLen, j-i+1);
                    }
                }
            }
            System.out.println(maxLen);
        }
    }
}
