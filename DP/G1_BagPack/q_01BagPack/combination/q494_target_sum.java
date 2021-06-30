package DP.G1_BagPack.q_01BagPack.combination;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class q494_target_sum {
    // 法2：一维dp（01背包，组合类！）
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int SUM = Arrays.stream(nums).sum();
        if (S > SUM) return 0;
        // ∵ plus - (minus) = S, minus = SUM - plus; -->
        // ∴ plus = bagsize = (SUM + S) / 2
        if ((SUM + S) % 2 != 0) return 0; // 背包容量
        int bagsize = (SUM + S) / 2;
        int[] dp = new int[bagsize+1];
        dp[0] = 1; // 背包容积=0时，有一种（啥也不放）
        for (int i = 0; i < n; i++) { // 遍历物品（num）- 外
            for (int j = bagsize; j >= nums[i]; j--) { // 遍历背包重量（内-倒序！）
                dp[j] += dp[j-nums[i]]; // 本题：【组合类，01背包】（问方案数）
                // ↓ 其他(最优化，01)：容量为j的背包最多能装多少价值（如：416分割等和子集）
                // dp[j] = Math.max(dp[j], dp[j-nums[i]] + nums[i]);
            }
        }
        return dp[bagsize];
    }


    // 法1-2：DFS+memo()[PS: memo=int[n+1][S+1]不可！因为sum可能为负数]
    Map<String, Integer> memo2 = new HashMap<>();
    public int findTargetSumWays2(int[] nums, int S) {
        int SUM = Arrays.stream(nums).sum();
        if (S > SUM) return 0; // 剪枝
        return dfs2(nums, S,0, 0);
    }

    private int dfs2(int[] nums, int S, int sum, int idx) {
        String key = idx + "_" + sum;
        if (idx >= nums.length) {
            if (sum == S) return 1;
            else return 0;
        }
        if (memo2.containsKey(key)) return memo2.get(key);

        int res = 0;
        int plus = dfs(nums, S, sum+nums[idx], idx+1);
        int minus = dfs(nums, S, sum-nums[idx], idx+1);
        res = res + plus + minus;
        memo2.put(key, res);
        // memo2.put(new Pair<>(cnt, idx), res);
        // memo2[idx][cnt] = res;
        return res;
    }

    // 法1：DFS+memo(面试不能用pair!)   ↓ <key=<cnt, idx>, val=cnt>
    Map<Pair<Integer, Integer>, Integer> memo = new HashMap<>();
    public int findTargetSumWays1(int[] nums, int S) {
        return dfs(nums, S,0, 0);
    }

    private int dfs(int[] nums, int S, int sum, int idx) {
        if (idx == nums.length) {
            if (sum == S) return 1;
            return 0;
        }
        if (memo.containsKey(new Pair<>(sum, idx)))
            return memo.get(new Pair<>(sum, idx));

        int plus = dfs(nums, S, sum+nums[idx], idx+1);
        int minus = dfs(nums, S, sum-nums[idx], idx+1);
        int res = plus + minus;
        memo.put(new Pair<>(sum, idx), res);
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int target = 3;
        q494_target_sum sol = new q494_target_sum();
        int cnt = sol.findTargetSumWays2(nums, target);
        System.out.println(cnt);
    }
}
