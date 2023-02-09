package DP.SubSeqStr;

public class q1143_longest_common_subsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
        char[] ch1 = text1.toCharArray(), ch2 = text2.toCharArray();
        int[][] dp = new int[n1 + 1][n2 + 1];  // [0, i-1] & [0, j-1]的LCS
        // int res = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (ch1[i - 1] == ch2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else { // 否则, 看max([0, i-2] & [0, j-1] 与 [0, i-1] & [0, j-2])
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // ?类比q115, 1143, 392
                }
                // if (res < dp[i][j]) res = dp[i][j];
            }
        }
        return dp[n1][n2];
    }
}
