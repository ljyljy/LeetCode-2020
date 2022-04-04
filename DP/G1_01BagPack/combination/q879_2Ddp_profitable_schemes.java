package DP.G1_01BagPack.combination;

import java.lang.reflect.Array;
import java.util.Arrays;

// 【A. 二维01背包，B. 组合类】 - 组合问题
//  A: ∴空间压缩后，二维背包降序j--,k--
//  B: 物品外循环（vs 排列问题-q377：物品内循环）
public class q879_2Ddp_profitable_schemes {
    private static int MOD = (int)1e9 + 7;
    // 注意点：
    // 【❤ 【dp初始化不当，对返回值的影响！】】
    // 【空间压缩后，第0维可以从0起，无需group[i-1]】
    // 【荐】法3：dp-二维背包-组合类 -- 空间压缩
    public int profitableSchemes(int hc, int minProfit, int[] group, int[] profit) {
        int n = profit.length; // 物品：工作
        int[][] dp = new int[hc+1][minProfit+1]; // 二维背包：员工数 & 最小利润(>=k)
        // ↓ 初始化：无论多少员工，利润至少为0时，方案数=1(不工作)
        for (int j = 0; j <= hc; j++) dp[j][0] = 1; // dp[:][0]=1

        for (int i = 0; i < n; i++) { // 外-遍历物品（工作）
            int members = group[i], earn = profit[i]; // dp[i]从0起，直接与下标一致
            for (int j = hc; j >= members; j--) { // 内-背包（倒序！）
                for (int k = minProfit; k >= 0; k--) {
                    // 【不可+=】,不可仅对加数dp取余(错)！
                    dp[j][k] = (dp[j][k] + dp[j-members][Math.max(0, k-earn)]) % MOD;
                }
            }
        }
        return dp[hc][minProfit];
    }

    // 【荐】法4： dfs + memo[和dp-二维01背包思路一致，更推荐法3]
    private int[] group, profit;
    private int[][][] memo;
    public int profitableSchemes_DFS(int hc, int minProfit, int[] group, int[] profit) {
        this.group = group; this.profit = profit;
        int n = group.length; // 物品：工作数
        memo = new int[n+1][hc+1][minProfit+1]; // 二维背包：员工数、最小利润
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= hc; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return dfs(n-1, hc, minProfit) % MOD;
    }

    private int dfs(int job, int hc, int minProfit) { // 物品-当前任务，背包：所需人数hc & 最小利润minProfit
        if (job < 0) {
            return minProfit == 0? 1: 0;
        }
        if (memo[job][hc][minProfit] != -1) return memo[job][hc][minProfit];

        long res = dfs(job - 1, hc, minProfit); // 不选当前job, 沿用
        if (hc >= group[job]) { // 选当前job(需确保人数足够，收益为负==收益为0->至少为0)
            res += dfs(job-1, hc-group[job], Math.max(0, minProfit-profit[job]));
        }
        memo[job][hc][minProfit] = (int)(res % MOD);
        return memo[job][hc][minProfit];
    }



    // 【初始化、返回值 vs 法3】法2：dp-二维背包-组合类 -- 空间压缩
    public int profitableSchemes2(int hc, int minProfit, int[] group, int[] profit) {
        int MOD = (int)1e9 + 7;
        int njobs = profit.length; // 物品：工作
        int[][] dp = new int[hc+1][minProfit+1]; // 二维背包：员工数 & 最小利润(>=k)
        // ↓ 初始化不当，返回值需要累加dp[][:][]
        dp[0][0] = 1; // 初始化：选0个员工,利润最少为0：只有1个方案--不工作 # dp[0][>0]=0
        for (int i = 1; i <= njobs; i++) { // 外-遍历物品（工作）
            int members = group[i-1], earn = profit[i-1]; // i从1起，需与下面下标一致
            //❤ ↑WHY group[i-1]? (dp[i=1]:选择工作0--group[0]/profit[0])
            // ↑ VS法3：压缩dp第0维后，i可以从0起，则无需group[i-1]了
            for (int j = hc; j >= members; j--) { // 内-背包（倒序！）
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] = (dp[j][k] // 不可+=,仅对加数dp取余(错)！
                            + dp[j-members][Math.max(0, k-earn)]) % MOD;
                }
            }
        }
        int ans = 0;
        for (int j = 0; j <= hc; j++) { // 不可+=,仅对加数dp取余(错)！
            ans = (ans + dp[j][minProfit]) % MOD;
        } // 必须对每次累加后的整体cnt取余！！！
        return ans;
    }

    // 法1：dp-二维背包-组合类 -- 空间不压缩
    // 【dp初始化不当，对返回值的影响！】
    public int profitableSchemes1(int hc, int minProfit, int[] group, int[] profit) {
        int MOD = (int)1e9 + 7;
        int njobs = profit.length; // 物品：工作
        int[][][] dp = new int[njobs+1][hc+1][minProfit+1]; // 二维背包：员工数 & 最小利润(>=k)
        // 初始化：或dp[0][:][0]=1 选0个工作，无论员工&利润下限多少，都只有1种方案：都不工作
        // dp[0][0][0] = 1; // ← ❤初始化不当，返回值需要累加dp[njobs][:][minProfit]
        for(int j = 0; j <= hc; j++) dp[0][j][0] = 1;

        for (int i = 1; i <= njobs; i++) { // 外-遍历物品（工作）
            int members = group[i-1], earn = profit[i-1]; // i从1起，需与下面下标一致
            //❤ ↑WHY group[i-1]? (i=1:选择工作0--group[0]/profit[0])
            for (int j = 0; j <= hc; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    dp[i][j][k] = dp[i-1][j][k] % MOD;
                    if (j >= members)
                        dp[i][j][k] = (dp[i-1][j][k]
                                + dp[i-1][j-members][Math.max(0, k-earn)]) % MOD;
                }
            }
        }
        // 当且仅当初始化 dp[0][0][0] = 1;时，需要累加∑dp[njobs][:][minProfit]
        // int ans = 0;
        // for (int j = 0; j <= hc; j++) { // 不可+=,仅对加数dp取余(错)！
        //     ans = (ans + dp[njobs][j][minProfit]) % MOD;
        // } // 必须对每次累加后的整体cnt取余！！！
        return dp[njobs][hc][minProfit];
    }
}
