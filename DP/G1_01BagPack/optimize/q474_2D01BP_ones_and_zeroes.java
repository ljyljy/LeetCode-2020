package DP.G1_01BagPack.optimize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class q474_2D01BP_ones_and_zeroes {
    // [荐！]法2：01背包("一维"dp--压缩空间[物品], "二维"背包 质量(bagsize = m & n))
    public int findMaxForm(String[] strs, int m, int n) {
        int N = strs.length;
        int[][] dp = new int[m+1][n+1]; // "二维"背包（重量1=n0, 重量2=n1）
        // dp[0][0] = 0;
        for (int i = 0; i < N; i++) { // 外-遍历物品

            int[] cnt = new int[2]; // cnt[0/1]: 当前strs[i]中0/1的个数 - "二维"背包
            String curStr = strs[i];
            for (char ch : curStr.toCharArray()) cnt[ch-'0']++; // ↓("一维"dp)

            for (int j1 = m; j1 >= cnt[0]; j1--) { // 内-遍历背包重量(倒序！)
                for (int j2 = n; j2 >= cnt[1]; j2--) {
                    dp[j1][j2] = Math.max(dp[j1][j2], // 不选，沿用"前者"（∵j-- ∴无后效性，不会覆盖）
                                        dp[j1-cnt[0]][j2-cnt[1]]+1); // +1：选这个子集，总个数+1
                }
            }
        }
        return dp[m][n];
    }

    // 法1【推荐！数组优化memo】：DFS + memo
    private int[][][] memo;
    public int findMaxForm1(String[] strs, int m, int n) {
        int len = strs.length;
        memo = new int[len][m+1][n+1];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return dfs(0, strs, m, n);
    }

    // m: 剩余0数，n：剩余1数; idx: 当前遍历到第idx个str
    private int dfs(int idx, String[] strs, int m, int n) {
        if (idx >= strs.length || m < 0 || n < 0) return 0;
        if (memo[idx][m][n] != -1) return memo[idx][m][n];
        String str = strs[idx];
        int[] cnts = new int[2];
        for (char ch: str.toCharArray()) cnts[ch - '0']++;

        int notChoose = dfs(idx+1, strs, m, n);
        int choose = 0;
        if (m - cnts[0] >= 0 && n - cnts[1] >= 0) // 勿漏❤：选择->必须要加if，否则可能导致结果dp=dfs()+1=0+1=1，但本应为0！
            choose = dfs(idx+1, strs, m - cnts[0], n - cnts[1]) + 1; // +1：选这个子集，总个数+1
        int res = Math.max(choose, notChoose);
        memo[idx][m][n] = res;
        return res;
    }

    // 写法1-2
    private int dfs1(String[] strs, int idx, int m, int n) {
        if (idx >= strs.length || m < 0 || n < 0) return 0;
        if (memo[idx][m][n] != -1) return memo[idx][m][n];
        String str = strs[idx];

        int n0 = 0, n1 = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') n0++;
            else if (str.charAt(i) == '1') n1++;
        }

        memo[idx][m][n] = dfs1(strs, idx+1, m, n); // 不选(strs[idx])，直接下探
        if (m >= n0 && n >= n1)// 勿漏❤：选择->必须要加if，否则可能导致结果dp=dfs(==0)+1=1，但本应为0！
            memo[idx][m][n] = Math.max(memo[idx][m][n],
                                        dfs1(strs, idx+1, m-n0, n-n1)+1); // 选，继续下探
        return memo[idx][m][n];
    }



    // 法1-3：memo
    Map<String, Integer> memo2 = new HashMap<>();
    public int findMaxForm2(String[] strs, int m, int n) {
        return dfs(strs, 0, m, n, memo2);
    }

    private int dfs(String[] strs, int idx, int m, int n,
                    Map<String, Integer> memo2) {
        if (idx >= strs.length || m < 0 || n < 0) return 0;
        // if (m == 0 && n == 0) return 1;  // WA!
        String key = idx + "_" + m + "_" + n;
        if (memo2.containsKey(key)) return memo2.get(key);

        String curStr = strs[idx];
        int[] cnt = new int[2];
        for (char ch: curStr.toCharArray()) cnt[ch - '0']++;

        int choose = 0, notChoose = 0;
        notChoose = dfs(strs, idx+1, m, n, memo2); // 不选
        if (cnt[0] <= m && cnt[1] <= n) // 勿漏❤：选择->必须要加if，否则可能导致结果dp=dfs(==0)+1=1，但本应为0！
            choose = dfs(strs, idx+1, m-cnt[0], n-cnt[1], memo2)+1;
        int res = Math.max(choose, notChoose);
        memo2.put(key, res);
        return res;
    }



}
