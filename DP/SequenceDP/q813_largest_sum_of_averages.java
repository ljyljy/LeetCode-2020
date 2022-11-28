package DP.SequenceDP;

public class q813_largest_sum_of_averages {
    // 序列dp + 前缀和
    // 写法1 & 2
    // https://leetcode.cn/problems/largest-sum-of-averages/solution/javac-dong-tai-gui-hua-qian-zhui-he-you-9xde1/
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[] sum = new double[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        // dp[i][j]: 区间 [0, i-1] 被切分成 j 个子数组的最大平均值和
        double[][] dp = new double[n + 1][k + 1];
        // 1) dp[i][1]初始化：不分组（组数=1），则dp值为前缀平均值
        for (int i = 1; i <= n; i++) { // dp[i]与前缀和sum[i]对齐
            dp[i][1] = sum[i] / i; // 前缀平均值
        }
        // 2) 组数>1时，区间[0,i-1]需满足i>=2 且 j∈[2,k]
        for (int i = 2; i <= n; i++) { // v1：依次遍历i,j
            for (int j = 2; j <= k; j++) {
//        for (int j = 2; j <= k; j++) { // v2：依次遍历j,i
//            for (int i = j; i <= n; i++) {
                // 区间[0,i-1]共i个数, 1 <= j（组数) <= i
                for (int x = j - 1; x < i; x++) {
                    dp[i][j] = Math.max(dp[i][j],
                            dp[x][j - 1] + (sum[i] - sum[x]) / (i - x));
                    // ↑ 组数=[j-1]+[1]：区间[0,x-1], j-1组 & [x,i-1], 1组
                    //                      ↑ 共x个数, 则 x >= 组数j-1
                    // [i,j].preSum = sum[j+1]-sum[i]
                }
            }
        }
        return dp[n][k];
    }
}
