package Binary_Search.bin_Answer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 动归、二分答案、DFS
// 子问题：分割为m个连续子数组（审题：要求连续, 相对顺序不变）
public class q410_split_array_largest_sum {
    // 【荐】法3：二分答案
    public int splitArray(int[] nums, int m) {
        // 计算「子数组各自的和的最大值」的上下界
        int max = Arrays.stream(nums).max().getAsInt();
        int sum = Arrays.stream(nums).sum();
        // 「二分答案」: 确定一个恰当的「子数组各自的和的最大值」，
        // 使得它对应的「子数组的分割数」恰好等于 m
        int start = max, end = sum;
        while (start < end) { // [L, mid], [mid+1, R]
            int mid = start + end >> 1;
            int splitCnt = trySplit(nums, mid);

            if (splitCnt > m) {
                // 如果分割数太多，说明「子数组各自的和的最大值」太小，此时需要将「子数组各自的和的最大值mid」调大
                start = mid + 1;
            } else { // 包括分割为m次时，需要继续查找【左区间】，求min
                end = mid;
            }
        }
        return start;
    }

    private int trySplit(int[] nums, int trySum) {
        int splitCnt = 1, rangeSum = 0;
        for (int num: nums) {
            if (rangeSum + num > trySum) {
                rangeSum = 0;
                splitCnt++;
            }
            rangeSum += num;
        }
        return splitCnt;
    }


    // 法2：动归(TODO: 笔记)
//    TODO: https://leetcode-cn.com/problems/split-array-largest-sum/solution/er-fen-cha-zhao-by-liweiwei1419-4/
    // 联合法1-https://leetcode-cn.com/problems/split-array-largest-sum/solution/ji-yi-hua-sou-suo-dong-tai-gui-hua-by-fe-0kzf/
    public int splitArray_DP(int[] nums, int m) {
        int len = nums.length;
        // 前缀和，preSum[i] = sum[0..i)
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        // 区间 [i..j] 的和 preSum(j + 1) - preSum(i)
        int[][] dp = new int[len][m + 1];
        // 初始化：由于要找最小值，初值赋值成为一个不可能达到的很大的值
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        // 分割数为 1 ，即不分割的情况，所有的前缀和就是依次的状态值
        for (int i = 0; i < len; i++) {
            dp[i][1] = preSum[i + 1];
        }

        // 从分割数为 2 开始递推
        for (int k = 2; k <= m; k++) {
            // 还未计算出的 i 是从 j 的最小值的下一位开始，因此是 k - 1
            for (int i = k - 1; i < len; i++) {
                // j 表示第 k - 1 个区间的最后一个元素额下标，最小值为 k - 2，最大值为 len - 2（最后一个区间至少有 1 个元素）
                for (int j = k - 2; j < i; j++) {
                    dp[i][k] = Math.min(dp[i][k], Math.max(dp[j][k - 1], preSum[i + 1] - preSum[j + 1]));
                }
            }
        }
        return dp[len - 1][m];
    }

    // 法1.DFS（memo改为int[][]，不会TLE）
    private int n; // res = Integer.MAX_VALUE; // 即 maxSubSum_min
    private int[] nums, preSum;
    private int[][] memo;

    public int splitArray_DFS(int[] nums, int m) {
        this.nums = nums;
        n = nums.length;
        memo = new int[1001][51];
        for (int i = 0; i < memo.length; i++) Arrays.fill(memo[i], -1);

        preSum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        return dfs( 1, m); // 将preSum[1, n]分割m份--子数组maxSum的最小值
    }

    private int dfs(int idx, int splitCnt) { // 起始idx（从1起）~ n，分割个数
        // ?剪枝：剩余分割个数(1) <= 剩余可选数量(n-idx+1)
        if (splitCnt > n - idx + 1) return Integer.MAX_VALUE;

        if (memo[idx][splitCnt] != -1) return memo[idx][splitCnt];

        if (splitCnt == 1) {
            int curSum = preSum[n] - preSum[idx-1]; // ∑ nums[idx~n]
            memo[idx][splitCnt] = curSum;
            return curSum;
        }

        // 将nums[idx~n]分割splitCnt次的maxSubSum_curMin
        int cur = Integer.MAX_VALUE; // maxSubSum_curMin
        for (int i = idx; i <= n; i++) {
            cur = Math.min(cur,
                    // 后半段(下探∑nums[i+1,n]分割splitCnt-1次) || 前半段(sum=∑nums[idx, i])
                    Math.max(dfs(i+1, splitCnt-1),
                            preSum[i] - preSum[idx-1]));
        }
        memo[idx][splitCnt] = cur;
        return cur;
    }

    // 法1.DFS - TLE（TODO: memo_[1001][51]）
//    private int n; // res = Integer.MAX_VALUE; // 即 maxSubSum_min
//    private int[] nums, preSum;
    private Map<String, Integer> memo_ = new HashMap<>();

    public int splitArray_DFS_TLE(int[] nums, int m) {
        this.nums = nums;
        n = nums.length;
        preSum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        return dfs0(1, m); // 将preSum[1, n]分割m份--子数组maxSum的最小值
//        return res;
    }

    private int dfs0(int idx, int splitCnt) { // 起始idx（从1起）~ n，分割个数
        // ?剩余分割个数(1) <= 剩余可选数量(n-idx+1)
        if (splitCnt > n - idx + 1) return Integer.MAX_VALUE;

        String key = idx + "_" + splitCnt;
        if (memo_.containsKey(key)) return memo_.get(key);

        if (splitCnt == 1) {
            int curSum = preSum[n] - preSum[idx-1]; // ∑ nums[idx~n]
//            res = Math.min(res, curSum);
            memo_.put(key, curSum);
            return curSum;
        }

        // 将nums[idx~n]分割splitCnt次的maxSubSum_curMin
        int cur = Integer.MAX_VALUE; // maxSubSum_curMin
        for (int i = idx; i <= n; i++) {
            cur = Math.min(cur,
            // 后半段(下探∑nums[i+1,n]分割splitCnt-1次) || 前半段(sum=∑nums[idx, i])
                    Math.max(dfs0(i+1, splitCnt-1),
                            preSum[i] - preSum[idx-1]));
        }
        memo_.put(key, cur);
        return cur;
    }

}
