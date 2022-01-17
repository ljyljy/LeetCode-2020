package DataStructure.Deque;

import java.util.Arrays;

public class q392_is_subsequence {
    public boolean isSubsequence(String pat, String str) {
        if (pat == null || pat.length() == 0) return true;
        int n1 = pat.length(), n2 = str.length();
        char[] ch1 = pat.toCharArray(), ch2 = str.toCharArray();
        int[][] dp = new int[n1+1][n2+1];
        dp[0][0] = 0;
        Arrays.fill(dp[1], 0);
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (ch1[i-1] == ch2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = dp[i][j-1];// 模式串下标i不动
                }
            }
        }
        return dp[n1][n2] == n1;
    }
}
