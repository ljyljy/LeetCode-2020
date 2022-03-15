package DP;

public class q72_161_one_edit_distance {
    char[] ss, pp;
    int n1, n2;
    // 类比q72
    public boolean isOneEditDistance(String s, String p) {
        ss = s.toCharArray(); pp = p.toCharArray();
        n1 = ss.length; n2 = pp.length;
        int[][] dp = new int[n1+1][n2+1];
        for (int i = 0; i <= n1; i++) dp[i][0] = i;
        for (int j = 0; j <= n2; j++) dp[0][j] = j;

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (ss[i-1] == pp[j-1]) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                }
            }
        }
        return dp[n1][n2] == 1;
    }
}
