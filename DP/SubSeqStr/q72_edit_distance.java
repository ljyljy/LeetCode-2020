package DP.SubSeqStr;

public class q72_edit_distance {
    public int minDistance(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        char[] ch1 = word1.toCharArray(), ch2 = word2.toCharArray();
        int[][] dp = new int[n1+1][n2+1];
        for (int i = 0; i <= n1; i++) dp[i][0] = i;
        for (int j = 0; j <= n2; j++) dp[0][j] = j;

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (ch1[i-1] == ch2[j-1]) {
                    dp[i][j] = dp[i - 1][j - 1]; // 沿用
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], // 沿用前结尾+现末尾替换1次
                            Math.min(dp[i - 1][j], dp[i][j - 1])) + 1; // 增/删1次
                }
            }
        }
        return dp[n1][n2];
    }
}
