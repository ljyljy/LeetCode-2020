package Array.prefixSum;

import java.util.TreeSet;

public class q363_max_sum_of_rectangle_no_larger_than_k {
    // 暴力 - 时间 O(m^2 * n^2), 空间 O(m * n)
    public int maxSumSubmatrix_bf(int[][] matrix, int k) {
        int n = matrix.length, m = n == 0? 0: matrix[0].length;
        int[][] sum = getPrefixSum(matrix, n, m);
        int maxSum = Integer.MIN_VALUE;

        for (int x1 = 1; x1 <= n; x1++) {
            for (int y1 = 1; y1 <= m; y1++) {
                for (int x2 = x1; x2 <= n; x2++) {
                    for (int y2 = y1; y2 <= m; y2++) {
                        int curSum = sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
                        if (curSum <= k)
                            maxSum = Math.max(maxSum, curSum);
                    }
                }
            }
        }
        return maxSum;
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

    // 法2：前缀和 & 二分（抽象一维） 【类似Q9_390、Q9_405】
    // 时间 - O(n^2 * mlogm) , 空间 - O(m * n); n行 m列
    public int maxSumSubmatrix_treeset(int[][] matrix, int k) {
        int n = matrix.length, m = n == 0? 0: matrix[0].length;
        int[][] sum = getPrefixSum(matrix, n, m); // 预处理前缀和
        int maxSum = Integer.MIN_VALUE;
        for (int top = 1; top <= n; top++) { // 上边界
            for (int bottom = top; bottom <= n; bottom++) { // 下边界
                // 使用「有序集合」维护所有遍历到的右边界
                TreeSet<Integer> treeSet = new TreeSet<>();
                treeSet.add(0); // （最左边界）第0列-矩阵和为0
                // 遍历子矩阵右边界
                for (int r = 1; r <= m; r++) {
                    int sum_rt = sum[bottom][r] - sum[top-1][r];
                    // 二分查找 左边界: sum[r] – k ⩽ min{sum[l−1]}
                    Integer sum_lf = treeSet.ceiling(sum_rt - k); // ≥(sum[r]-k)max
                    if (sum_lf != null) {
                        int curSum = sum_rt - sum_lf;
                        maxSum = Math.max(maxSum, curSum);
                    }
                    treeSet.add(sum_rt); // 遍历过的sum_rt加入TreeSet
                }
            }
        }
        return maxSum;
    }

    // 法2-进阶优化 - 最大化【二分收益】 -- 枚举较短的，二分较长的(n!=m时)
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int n = matrix.length, m = n == 0? 0: matrix[0].length;
        int[][] sum = getPrefixSum(matrix, n, m); // 预处理前缀和
        int maxSum = Integer.MIN_VALUE;
        boolean fixR = m > n; // 列数m > 行数n时，固定上下(n)&右，二分 左
        // 反之，行数n更大时，固定左右(m)&下，二分查找 上边界
        for (int top_L = 1; top_L <= (fixR? n:m); top_L++) { // 上or左边界
            for (int bot_R = top_L; bot_R <= (fixR? n:m); bot_R++) { // 下or右
                TreeSet<Integer> ts = new TreeSet<>();
                ts.add(0); // 左/上の起始边界,前缀和=0
                for (int r_down = 1; r_down <= (fixR? m:n); r_down++) { // 右or下边界
                    int sum_r_down = fixR? sum[bot_R][r_down] - sum[top_L-1][r_down]
                            : sum[r_down][bot_R] - sum[r_down][top_L-1]; // sum[下][R] - sum[下][L-1]
                    Integer sum_l_up = ts.ceiling(sum_r_down - k);
                    if (sum_l_up != null)
                        maxSum = Math.max(maxSum, sum_r_down - sum_l_up);
                    ts.add(sum_r_down);
                }
            }
        }
        return maxSum;
    }
}
