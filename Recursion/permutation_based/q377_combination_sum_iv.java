package Recursion.permutation_based;

import java.util.HashMap;
import java.util.Map;

public class q377_combination_sum_iv {
    // 法3-1：DP动归 - 时间O(target^2 * n), 空间O(target^2)
    public int combinationSum4_dp1(int[] nums, int target) {
        // int len = target;
        // int[][] dp = new int[target+1][len+1];
        // dp[0][0] = 1;
        // int cnt = 0;
        // for (int i = 0; i <= target; i++) {
        //     for (int j = 1; j <= len; j++) {
        //         for (int num : nums) {
        //             if (i >= num)
        //                 dp[i][j] += dp[i-num][j-1];
        //         }
        //         if(i == target) cnt += dp[target][j];
        //     }
        // }
        // return cnt;

        int len = target; // nums[i]最小为1，∴答案最大长度为 target
        // ❤ dp[i][j]:组合长度为 i，凑成总和为 j 的方案数↓
        int[][] dp = new int[len+1][target+1]; // dp[len][cnt]
        dp[0][0] = 1; // dp[0][1:] = 0
        int cnt = 0;
        // 状态转移方程：dp[len][cnt] = ∑dp[len-1][cnt-nums[i]]
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= target; j++) {
                for (int num: nums)
                    if (j - num >= 0)
                        dp[i][j] += dp[i-1][j-num];
            }
            cnt += dp[i][target];
        }
        return cnt;
    }

    // 法3-2【荐！】：DP动归2【完全背包 组合】 - 时间O(target*n), 空间O(target)
    public int combinationSum4(int[] nums, int target) {
        int bagsize = target;
        int[] dp = new int[bagsize+1];
        dp[0] = 1;
        for (int i = 1; i <= bagsize; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0)
                    dp[i] += dp[i-nums[j]]; // 组合类
            }
        }
        return dp[bagsize];
    }

    // 法2-1（可）: DFS + memo
    Map<String, Integer> memo = new HashMap<>();
    public int combinationSum4_memo0(int[] nums, int target) {
        return dfs_memo(nums, 0, target, memo);
    }

    private int dfs_memo(int[] nums, int idx, int target,
                         Map<String, Integer> memo){
        String key = idx + "_" + target;
        if (memo.containsKey(key)) return memo.get(key);
        if (target == 0)  return 1;

        int cnt_ = 0;
        for (int i = idx; i < nums.length; i++) {
            if (target-nums[i] < 0) continue;
            cnt_ += dfs_memo(nums, idx, target-nums[i], memo);
        }
        memo.put(key, cnt_);
        return cnt_;
    }

    // 法2-2: DFS + memo
    public int combinationSum4_memo(int[] nums, int target) {
        Map<Integer, Integer> memo = new HashMap<>(); // <剩余和, cnt>
        // 1. nums元素不重复 2.元素可以重复取！ -- 下探仍为idx
        return dfs2(nums, target, memo);
    }

    private int dfs2(int[] nums, int remain, Map<Integer, Integer> memo) {
        if (remain < 0) return 0; // 也可以不加 因为for内有if
        if (remain == 0) return 1;
        if (memo.containsKey(remain)) return memo.get(remain);
        else memo.put(remain, 0);

        int res = 0; // 控制某一树层
        for (int i = 0; i < nums.length; i++) {
            if (remain - nums[i] < 0) continue;
            res += dfs2(nums, remain - nums[i], memo);
        }
        memo.put(remain, res);
        return memo.get(remain);
    }

    // 法1: 普通DFS (TLE)
    // Deque<Integer> path = new ArrayDeque<>();
    // List<List<Integer>> res = new ArrayList<>();
    int cnt = 0;
    public int combinationSum4_TLE(int[] nums, int target) {
        // 1. nums元素不重复 2.元素可以重复取！ -- 下探仍为idx
        dfs(nums, target, 0);
        // System.out.println(res);
        return cnt;
    }

    private void dfs(int[] nums, int target, int idx) {
        if (target < 0) return;
        if (target == 0) {
            // res.add(new ArrayList<>(path));
            cnt++;
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            // path.addLast(nums[i]);
            dfs(nums, target - nums[i], idx);
            // path.removeLast();
        }
    }

    // 法1-2: 普通DFS (TLE)
    private int res;
    public int combinationSum4_TLE2(int[] nums, int target) {
        dfs(nums, target, 0, 0);
        return res;
    }

    private void dfs(int[] nums, int target, int idx, int curSum) {
        if (curSum == target) {
            res++;
            return;
        }

        // 全排列 i从0起[如:{1,2} & {2, 1(❤逆序：又从0起)}]（不同于组合、排列startIdx）
        for (int i = 0; i < nums.length; i++) {
            if (curSum + nums[i] > target) continue;
            dfs(nums, target, i, curSum + nums[i]);
        }
    }

}
