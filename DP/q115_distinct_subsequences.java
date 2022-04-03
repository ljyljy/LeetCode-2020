package DP;

public class q115_distinct_subsequences {
    public int numDistinct(String s, String t) {
        int n1 = s.length(), n2 = t.length();
        char[] ch1 = s.toCharArray(), ch2 = t.toCharArray();
        int[][] dp = new int[n1+1][n2+1]; // 字符串匹配s[i-1] t[j-1]的方案数
        // 初始化：1) dp[0,1:]=0(模式串非空，不可能匹配s="", 方案数=0)
        dp[0][0] = 1; // 2) 空&空匹配1种方案
        // 勿漏? 3) dp[1:, 0]=1(模式串为空，任意s都匹配空串t, 方案数=1)
        for (int i = 0; i <= n1; i++) dp[i][0] = 1;

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (ch1[i-1] == ch2[j-1]) {
                    // 1) [i-1][j-1] & 当前[i][j]共同组成一种方案
                    // 2) 不考虑当前[i][j]组合，形成另一种方案：[i-1][j] <- 模式串j不动
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j]; // ?类比q115, 1143, 392
                } else { // 无法考虑当前[i][j]&[i-1][j-1]，故只有一种方案:[i-1][j]
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n1][n2];
    }
}
