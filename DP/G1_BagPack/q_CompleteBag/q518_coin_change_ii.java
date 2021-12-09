package DP.G1_BagPack.q_CompleteBag;

import java.util.Arrays;

public class q518_coin_change_ii {
    // 一维dp-完全背包【组合类】
    public int change(int amount, int[] coins) {
        int n = coins.length;
//        Arrays.sort(coins); // 似乎可以不写！WHY？
//        ↑ RE -- 遍历j(背包质量)本就是从小到大，物品质量次序无所谓，不影响dp的生成
        int[] dp = new int[amount+1]; // 背包大小
        dp[0] = 1;
        for (int i = 0; i < n; i++) { // 外-遍历物品
            for (int j = coins[i]; j <= amount; j++) { // 完全背包-内(正序！)
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {5,2,1};
        q518_coin_change_ii sol = new q518_coin_change_ii();
        int cnt = sol.change(amount, coins);
        System.out.println(cnt);
    }
}
