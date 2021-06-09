package DP.G1_BagPack.q_01BagPack.combination;

public class q879_2Ddp_profitable_schemes {
    // 【荐】法2：dp-二维背包-组合类 -- 空间压缩
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int MOD = (int)1e9 + 7;
        int len = profit.length; // 物品：工作
        int[][] dp = new int[n+1][minProfit+1]; // 二维背包：员工数 & 最小利润(>=k)
//        for (int i = 0; i <= n; i++) {
//            dp[i][0] = 1;
//        } // 或dp[:][0]=1 √
        dp[0][0] = 1; // 初始化：选0个员工,利润最少为0：只有1个方案--不工作 # dp[0][>0]=0
        for (int i = 1; i <= len; i++) { // 外-遍历物品（工作）
            int members = group[i-1], earn = profit[i-1]; // i从1起，需与下面下标一致
            //❤ ↑WHY group[i-1]? (i=1:选择工作0--group[0]/profit[0])
            for (int j = n; j >= members; j--) { // 内-背包（倒序！）
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] = (dp[j][k] // 不可+=,仅对加数dp取余(错)！
                            + dp[j-members][Math.max(0, k-earn)]) % MOD;
                }
            }
        }
        int cnt = 0;
        for (int j = 0; j <= n; j++) { // 不可+=,仅对加数dp取余(错)！
            cnt = (cnt + dp[j][minProfit]) % MOD;
        } // 必须对每次累加后的整体cnt取余！！！
        return cnt;
    }

    // 法1：dp-二维背包-组合类 -- 空间不压缩
    public int profitableSchemes1(int n, int minProfit, int[] group, int[] profit) {
        int MOD = (int)1e9 + 7;
        int len = profit.length; // 物品：工作
        int[][][] dp = new int[len+1][n+1][minProfit+1]; // 二维背包：员工数 & 最小利润(>=k)
        // 初始化：或dp[0][:][0]=1 选0个工作，无论员工&利润下限多少，都只有1种方案：都不工作
        dp[0][0][0] = 1;
        for (int i = 1; i <= len; i++) { // 外-遍历物品（工作）
            int members = group[i-1], earn = profit[i-1]; // i从1起，需与下面下标一致
            //❤ ↑WHY group[i-1]? (i=1:选择工作0--group[0]/profit[0])
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    dp[i][j][k] = dp[i-1][j][k] % MOD;
                    if (j >= members)
                        dp[i][j][k] = (dp[i-1][j][k]
                                + dp[i-1][j-members][Math.max(0, k-earn)]) % MOD;
                }
            }
        }
        int cnt = 0;
        for (int j = 0; j <= n; j++) { // 不可+=,仅对加数dp取余(错)！
            cnt = (cnt + dp[len][j][minProfit]) % MOD;
        } // 必须对每次累加后的整体cnt取余！！！
        return cnt;
    }
}
