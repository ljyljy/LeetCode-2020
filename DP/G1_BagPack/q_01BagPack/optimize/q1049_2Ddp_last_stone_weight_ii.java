package DP.G1_BagPack.q_01BagPack.optimize;

import java.util.Arrays;

public class q1049_2Ddp_last_stone_weight_ii {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = Arrays.stream(stones).sum();
        int bagsize = sum / 2; // 向下取整，剩余另一半重量一定≥bagsize
        int[] dp = new int[bagsize+1];
        for (int i = 0; i < n; i++) { // 外-遍历物品
            for (int j = bagsize; j >= stones[i]; j--) {// 内-遍历背包(倒序!)
                dp[j] = Math.max(dp[j], dp[j-stones[i]] + stones[i]);
            }
        }
        return (sum - dp[bagsize]) - dp[bagsize]; // 剩余另一半 - 背包这一半
    }
}
