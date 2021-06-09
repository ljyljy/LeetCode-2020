package DP.G1_BagPack.q_01BagPack.optimize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class q474_ones_and_zeroes {
    // [荐！]法2：01背包("一维"dp--压缩空间[物品], "二维"背包)
    public int findMaxForm(String[] strs, int m, int n) {
        int N = strs.length;
        int[][] dp = new int[m+1][n+1]; // "二维"背包（重量1=n0, 重量2=n1）
        // dp[0][0] = 0;
        for (int i = 0; i < N; i++) { // 外-遍历物品
            int[] cnt = new int[2]; // cnt[0/1]: 当前strs[i]中0/1的个数
            String curStr = strs[i];
            for (char ch : curStr.toCharArray())
                cnt[ch-'0']++;                      // ("一维"dp时↓)
            for (int j1 = m; j1 >= cnt[0]; j1--) { // 内-遍历背包重量(倒序！)
                for (int j2 = n; j2 >= cnt[1]; j2--) {
                    dp[j1][j2] = Math.max(dp[j1][j2],
                                        1+dp[j1-cnt[0]][j2-cnt[1]]);
                }
            }
        }
        return dp[m][n];
    }

    // 法1：DFS + memo
    private int[][][] memo;
    public int findMaxForm1(String[] strs, int m, int n) {
        int len = strs.length;
        memo = new int[len][m+1][n+1];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return dfs(strs, 0, m, n);
    }
    // m: 剩余0数，n：剩余1数; idx: 当前遍历到第idx个str
    private int dfs(String[] strs, int idx, int m, int n) {
        if (idx >= strs.length) return 0;
        if (memo[idx][m][n] != -1) return memo[idx][m][n];
        String str = strs[idx];
        int n0 = 0, n1 = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') n0++;
            else if (str.charAt(i) == '1') n1++;
        }
        memo[idx][m][n] = dfs(strs, idx+1, m, n); // 不选(strs[idx])，直接下探
        if (m >= n0 && n >= n1)
            memo[idx][m][n] = Math.max(memo[idx][m][n],
                    dfs(strs, idx+1, m-n0, n-n1)+1); // 选，继续下探
        return memo[idx][m][n];
    }


}
