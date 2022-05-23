package DP;

import java.util.*;

public class q300_longest_increasing_subsequence {
    // 法2: DP - 类比q334（TLE）
    public int lengthOfLIS_DP(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int LIS = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (nums[j] < nums[i]) // 满足'递增'
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                if (LIS < dp[i]) LIS = dp[i];
            }
        }
        return LIS;
    }

    // 法1: TLE - 同q491、求所有递增子序列
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    int ans = 0;

    public int lengthOfLIS_TLE(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        dfs(nums, 0);
        // System.out.println(res);
        return ans;
    }

    private void dfs(int[] nums, int idx) {
        if (path.size() > 0) {
            res.add(new ArrayList<>(path));
            if (path.size() > ans) ans = path.size();
            // return;
        }
        Set<Integer> usdSet = new HashSet<>();
        for (int i = idx; i < nums.length; i++) {
            if (usdSet.contains(nums[i]) ||
                    (!path.isEmpty() && path.peekLast() >= nums[i]))
                continue;

            usdSet.add(nums[i]);
            // ans++; // 不可改全局变量，最终会置0
            path.addLast(nums[i]);
            dfs(nums, i + 1);
            path.removeLast();
            // ans--;
        }
    }
}