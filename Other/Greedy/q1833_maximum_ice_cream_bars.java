package Other.Greedy;

import java.util.Arrays;

public class q1833_maximum_ice_cream_bars {
    // 法1：排序 + 贪心（优先买最便宜的蛋糕）
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int cnt = 0;
        for (int i = 0; i < costs.length && coins >= 0; i++) {
            coins -= costs[i];
            if (coins >= 0) cnt++;
        }
        return cnt;
    }

    // TLE!!
    public int maxIceCream_DP(int[] costs, int coins) {
        int bagsize = coins; // 现金
        int[] dp = new int[bagsize+1];
        dp[0] = 0; // 现金为0时，最多买0个雪糕
        for (int i = 0; i < costs.length; i++) {
            for (int j = bagsize; j >= costs[i]; j--) {
                // 注意是比较max!!!
                dp[j] = Math.max(dp[j], dp[j-costs[i]] + 1);
            }
        }
        return dp[bagsize];
    }
}
