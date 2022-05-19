package Recursion.partition_based;

import java.util.Arrays;

public class q312_burst_balloons {
    private int[][] memo;
    private int[] val;
    public int maxCoins(int[] nums) {
        int n = nums.length;
        val = new int[n+2]; // 首尾加哨兵=1
//        for (int i = 1; i <= n; i++) {
//            val[i] = nums[i-1];
//        }
        System.arraycopy(nums, 0, val, 1, n);
        val[0] = val[n+1] = 1;

        memo = new int[n+2][n+2];
        for (int i = 0; i <= n+1; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(0, n+1);
    }

    // 倒序看：戳气球->添加气球
    // 开区间(left, right)的最大硬币数=val[left]*val[mid]*val(right)
    //      其中，left < mid < right（∴ L < R - 1; mid∈[L+1,R-1]）
    private int dfs(int left, int right) {
        if (left >= right-1) return 0;
        if (memo[left][right] != -1) return memo[left][right];

        for (int mid = left+1; mid <= right-1; mid++) {
            int sum = val[left] * val[mid] * val[right];
            sum += dfs(left, mid) + dfs(mid, right);
            memo[left][right] = Math.max(memo[left][right], sum);
        }
        return memo[left][right];
    }


    // TODO: 法2：DP
    // https://leetcode.cn/problems/burst-balloons/solution/chuo-qi-qiu-by-leetcode-solution/
}
