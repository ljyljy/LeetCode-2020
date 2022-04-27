package Array.prefixSum;

// 类比：q53 -> 合唱队、q9_42
/*
分区类型DP：一个数组分区为k个部分或k个操作（如买卖股票）
使用局部最大值和全局最大值
localMax[n][k]定义最大和，n必须有第n个元素和分成k个部分。
globalMax[n][k] 定义最大总和，n 可能没有第 n 个元素并划分为 k 个部分。

localMax[n][k]->既然第n个一定要包含，那么想一想怎么能包含第n个，分两种情况：
要么第n个元素为一组，则表示其他n-1个必须组成k -1 组，所以它是 globalMax[n-1][k-1]
或与 n-1 在同一组中的第 n 个元素，这意味着 n-1 个元素必须已经形成 k 个组
并且 (n- 1)第一个元素必须在分区中，所以它是localMax[n-1][k]
localMax[n][k] = max(globalMax[n-1][k-1], local[n-1][k]) + 数字[n-1];

globalMax[n][k] -> 因为第n个可能包含也可能不包含，所以有两种情况。如果包含第n个，则为localMax[n][k]，
如果不包含，则表示其他n-1必须为k组和全局最大值，因此为globalMax[n-1][k]。
globalMax[n][k] = max(globalMax[n-1][k], localMax[n][k])
 */
public class q9_43_max_subarray_iii {
    // 法1：dfs+memo
    public int maxSubArray(int[] nums, int k) {
        Integer[][] memo = new Integer[nums.length][k + 1]; // 默认为null
        return dfs(nums, k, 0, memo);
    }

    private int dfs(int[] nums, int groupCnt, int idx, Integer[][] memo) {
        if (groupCnt == 0 || idx >= nums.length) return 0;
        if (memo[idx][groupCnt] != null) return memo[idx][groupCnt];

        int prevSum = 0, min = 0, max = Integer.MIN_VALUE, global = Integer.MIN_VALUE;

        for (int i = idx; i < nums.length - groupCnt + 1; i++) {
            prevSum += nums[i];
            if (max > (prevSum - min)) {
                min = Math.min(min, prevSum);
                continue;
            }
            max = Math.max(max, prevSum - min);
            min = Math.min(min, prevSum);

            int rest = dfs(nums, groupCnt - 1, i + 1, memo);
            global = Math.max(global, max + rest);
        }

        memo[idx][groupCnt] = global;
        return global;
    }

    // 法2：dp
    public int maxSubArray_dp(int[] nums, int k) {
        int n = nums.length;
        if (n < k) return 0;

        int[][] dp_local = new int[n + 1][k + 1]; // dp_local[i][j]: 共i个数，求j组不连续子数组和（i>=j,每组至少1个数）--
        int[][] dp_global = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i < j) break;
//                 写法1
                // 防止i == j时，dp公式被[j-1][j](非法)覆盖
                dp_local[j - 1][j] = Integer.MIN_VALUE;
                dp_global[j - 1][j] = Integer.MIN_VALUE;

                dp_local[i][j] = Math.max(dp_global[i - 1][j - 1], dp_local[i - 1][j]) + nums[i - 1]; // 选第i个数字
                dp_global[i][j] = Math.max(dp_global[i - 1][j], dp_local[i][j]); // 不选（沿用） or 选
//                // 写法2
//                if (i == j) {
//                    dp_local[i][j] = dp_global[i-1][j-1] + nums[i-1];
//                    dp_global[i][j] = dp_local[i][j];
//                } else {
//                    dp_local[i][j] = Math.max(dp_global[i-1][j-1], dp_local[i-1][j]) + nums[i-1];
//                    dp_global[i][j] = Math.max(dp_local[i][j], dp_global[i-1][j]);
//                }
            }
        }
        return dp_global[n][k];
    }


    public static void main(String[] args) {
        System.out.println("exp01:");
        int[] nums = new int[]{1, 3, -1, 2, -1, 2};
        q9_43_max_subarray_iii sol = new q9_43_max_subarray_iii();
        System.out.println(sol.maxSubArray(nums, 2));

        System.out.println("exp02:");
        int[] nums2 = new int[]{-1, -2, -3, -100, -1, -50};
        System.out.println(sol.maxSubArray(nums2, 2));
    }
}
