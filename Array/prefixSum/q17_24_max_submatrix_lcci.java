package Array.prefixSum;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class q17_24_max_submatrix_lcci {
    // TLE - 哈希 O(n^2 * m^2)
    public int[] getMaxMatrix_TLE(int[][] matrix) {
        int n = matrix.length, m = n == 0? 0: matrix[0].length;
        int[][] sum = getPrefixSum(matrix, n, m); // 预处理前缀和
        int maxSum = Integer.MIN_VALUE;
//        int x1 = -1, y1 = -1, x2 = -1, y2 = -2;
        int[] res = new int[4]; // {x1, y1, x2, y2}
        for (int top = 1; top <= n; top++) { // 上边界
            for (int bottom = top; bottom <= n; bottom++) { // 下边界
                Map<Integer, Integer> sumColMap = new HashMap<>(); // <sum_rt, col>
                sumColMap.put(0, 0);
                // 遍历子矩阵右边界
                for (int r = 1; r <= m; r++) {
                    int sum_rt = sum[bottom][r] - sum[top-1][r];
                    for (Integer sum_lf : sumColMap.keySet()) {
                        int l = sumColMap.get(sum_lf);
                        int curSum = sum_rt - sum_lf;
                        if (maxSum < curSum) {
                            maxSum = curSum;
                            System.out.println("sum=" + maxSum);
                            res[0] = top-1; res[1] = l;
                            res[2] = bottom-1; res[3] = r-1;
                        }
                    }
                    sumColMap.put(sum_rt, r);
                }
            }
        }
        return res;
    }

    // 【优化，荐】Hash + TreeSet 二分查找最大的子矩阵前缀和 - O(n^2 * mlogm)
    // 类似q363、q9_405
    public int[] getMaxMatrix_treeset(int[][] matrix) {
        int n = matrix.length, m = n == 0? 0: matrix[0].length;
        int[][] sum = getPrefixSum(matrix, n, m); // 预处理前缀和
        int maxSum = Integer.MIN_VALUE;
//        int x1 = -1, y1 = -1, x2 = -1, y2 = -2;
        int[] res = new int[4]; // {x1, y1, x2, y2}
        for (int top = 1; top <= n; top++) { // 上边界
            for (int bottom = top; bottom <= n; bottom++) { // 下边界
                Map<Integer, Integer> sumColMap = new HashMap<>(); // <sum_rt, col>
                sumColMap.put(0, 0);
                TreeSet<Integer> ts = new TreeSet<>();
                ts.add(0); // 第0列の前缀和=0
                for (int r = 1; r <= m; r++) {
                    int sum_rt = sum[bottom][r] - sum[top-1][r];
                    int sum_lf = ts.first();// 升序排列，取最小的左和(从而最大化curSum
                    // )
                    int l = sumColMap.get(sum_lf);
                    int curSum = sum_rt - sum_lf;
                    if (maxSum < curSum) {
                        maxSum = curSum;
                        res[0] = top-1; res[1] = l;
                        res[2] = bottom-1; res[3] = r-1;
                    }
                    sumColMap.put(sum_rt, r);
                    ts.add(sum_rt);
                }
            }
        }
        return res;
    }

    // 获取子矩阵前缀和
    private int[][] getPrefixSum(int[][] matrix, int n, int m) {
        int[][] sum = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) { // matrix下标0起 - 需减1
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + matrix[i-1][j-1];
            }
        }
        return sum;
    }

//    // 类似q53, 动归-v1 - 有错
//    public int[] getMaxMatrix_dp(int[][] matrix) {
//        int n = matrix.length, m = n == 0? 0: matrix[0].length;
//        int maxSum = Integer.MIN_VALUE;
//        int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
//        int res[] = {x1,y1, x2,y2};
//        int mod = 3;
//        for (int top = 1; top <= n; top++) {
//            int[] rowSum = new int[m+1]; // 每上边界重置
//            for (int bottom = top; bottom <= n; bottom++) {
//                int[] dp = new int[mod]; // 状态压缩-每下边界重置
//                for (int r = 1; r <= m; r++) {
//                    dp[(r-1) % mod] = rowSum[r]; // dp从0起，下标一致需-1
//                    rowSum[r] += matrix[bottom-1][r-1]; // prefixSum += rowSum[r]
//                    if (dp[(r-2) % mod] > 0)
//                        dp[(r-1) % mod] += rowSum[r];
//                    else {
//                        dp[(r-1) % mod] = rowSum[r];
//                        x1 = top-1;
//                        y1 = r-1;
//                    }
//                    if (dp[(r-1) % mod] > maxSum) {
//                        maxSum = dp[(r-1) % mod];
//                        res = new int[]{x1, y1, bottom-1, r-1};
//                    }
//                }
//            }
//        }
//        return res;
//    }

    // 【同py解法】类似q53, 动归-v2 空间状态压缩 – O(n^2 * m)
    public int[] getMaxMatrix_dp2(int[][] matrix) {
        int n = matrix.length, m = n == 0? 0: matrix[0].length;
        int maxSum = Integer.MIN_VALUE;
        int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
        int res[] = {x1,y1, x2,y2};
        for (int top = 1; top <= n; top++) {
            int[] rowSum = new int[m+1]; // 每上边界重置
            for (int bottom = top; bottom <= n; bottom++) {
//                int[] dp = new int[mod]; // 每下边界重置
                int dp = 0;  // 每下边界重置
                for (int r = 1; r <= m; r++) {
//                    dp[0] = rowSum[r];
                    rowSum[r] += matrix[bottom-1][r-1]; // prefixSum += rowSum[r]
                    if (dp > 0) dp += rowSum[r];
                    else {
                        dp = rowSum[r];
                        x1 = top-1; y1 = r-1;
                    }
                    if (dp > maxSum) {
                        maxSum = dp;
                        res = new int[]{x1, y1, bottom-1, r-1};
                    }
                }
            }
        }
        return res;
    }
}
