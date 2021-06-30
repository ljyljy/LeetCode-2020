package Array.prefixSum;

import javax.print.attribute.standard.NumberUp;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class q9_405_submatrix_sum {
    // 1. BF 暴力 O(n^2 * m^2)
    public int[][] submatrixSum_bf(int[][] matrix) {
        int n = matrix.length, m = n == 0? 0: matrix[0].length;
        int[][] sum = getPrefixSum(matrix, n, m);

        for (int x1 = 1; x1 <= n; x1++) {
            for (int y1 = 1; y1 <= m; y1++) {
                for (int x2 = x1; x2 <= n; x2++) {
                    for (int y2 = y1; y2 <= m; y2++) {
                        int curSum = sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
                        if (curSum == 0) // 返回的下标 = 前缀和下标-1
                            return new int[][]{{x1-1, y1-1}, {x2-1, y2-1}};
                    }
                }
            }
        }
        return new int[][]{{-1, -1}, {-1, -1}};
    }

    private int[][] getPrefixSum(int[][] matrix, int n, int m) {
        int[][] sum = new int[n+1][m+1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + matrix[i-1][j-1];
        return sum;
    }



    // 法2：前缀和 & 哈希 - 时间O(n^2 * m), 空间O(m*n)
    // - 不用TreeSet也能做, 但频繁哈希映射费时！
    public int[][] submatrixSum(int[][] matrix) {
        int n = matrix.length, m = n == 0? 0: matrix[0].length;
        int[][] sum = getPrefixSum(matrix, n, m); // 预处理前缀和
        for (int top = 1; top <= n; top++) { // 上边界
            for (int bottom = top; bottom <= n; bottom++) { // 下边界
                // hash存储前缀和，每下边界重置
                Map<Integer, Integer> sumColMap = new HashMap<>(); // <cnt, col-R/L>
                sumColMap.put(0, 0);
                for (int r = 1; r <= m; r++) {
                    int sum_rt = sum[bottom][r] - sum[top-1][r];
                    // hashmap判断是否存在相同前缀和 - O(1)
                    if (sumColMap.containsKey(sum_rt)) {
                        int l = sumColMap.get(sum_rt); // 左边界(l-1)需要+1才是零矩阵！
                        return new int[][]{{top - 1, l}, {bottom - 1, r - 1}};
                    }
                    sumColMap.put(sum_rt, r);
                }
            }
        }
        return new int[][]{{-1, -1}, {-1, -1}};
    }

    // 法3【荐！】：前缀和 & 二分（抽象一维） 【【类似Q363】】
    // 时间 - O(n^2 * mlogm) , 空间 - O(m * n); n行 m列
    // floor(E e) 方法返回在这个集合中小于或者等于给定元素的最大元素，如果不存在这样的元素,返回null.
    // ceiling(E e) 方法返回在这个集合中大于或者等于给定元素的最小元素，如果不存在这样的元素,返回null.
    public int[][] submatrixSum_v23(int[][] matrix) {
        int n = matrix.length, m = n == 0? 0: matrix[0].length;
        int[][] sum = getPrefixSum(matrix, n, m); // 预处理前缀和
        for (int top = 1; top <= n; top++) { // 上边界
            for (int bottom = top; bottom <= n; bottom++) { // 下边界
                // 使用「有序集合」维护所有遍历到的右边界
                TreeSet<Integer> treeSet = new TreeSet<>();
                treeSet.add(0); // （最左边界）第0列-矩阵和为0
                Map<Integer, Integer> sumColMap = new HashMap<>(); // <sum_rt, col>
                sumColMap.put(0, 0);
                // 遍历子矩阵右边界
                for (int r = 1; r <= m; r++) {
                    int sum_rt = sum[bottom][r] - sum[top-1][r];
                    // 二分查找 左边界: cnt[r] – k ⩽ min{cnt[l−1]}
                    Integer sum_lf = treeSet.ceiling(sum_rt); // ≥(cnt[r])max
                    if (sum_lf != null && sum_lf == sum_rt) {
                        int l = sumColMap.get(sum_lf); // l-1: 左边界列，但和为0需要左边界+1
                        if (l == r && top == bottom) continue;
                        return new int[][]{{top-1, l}, {bottom-1, r-1}};
                        // 固定上下边界：cnt[l-1] == cnt[r] -> cnt[l,r]=0(左边界++才是零矩阵)
                    }
                    treeSet.add(sum_rt); // 遍历过的sum_rt加入TreeSet
                    sumColMap.put(sum_rt, r);
                }
            }
        }
        return new int[][]{{-1, -1}, {-1, -1}};
    }

    // 法4（写法同py）【荐,最优】 - O(n^2 * m)
    public int[][] submatrixSum_v21(int[][] matrix) {
        int n = matrix.length, m = n == 0? 0: matrix[0].length;
        // int[][] cnt = getPrefixSum(matrix, n, m); // 预处理前缀和
        for (int top = 1; top <= n; top++) { // 上边界
            int[] rowSum = new int[m + 1]; // 累加，压缩一维; 每上边界重置
            for (int bottom = top; bottom <= n; bottom++) { // 下边界
                // hash存储前缀和，    每下边界重置
                Map<Integer, Integer> sumColMap = new HashMap<>(); // <cnt, col-R/L>
                int prefixSum = 0; // 每下边界重置
                sumColMap.put(prefixSum, 0);
                for (int r = 1; r <= m; r++) {
                    rowSum[r] += matrix[bottom - 1][r - 1]; // 每列的累加前缀和(从top起)
                    prefixSum += rowSum[r];
                    // hashmap判断是否存在相同前缀和 - O(1)
                    if (sumColMap.containsKey(prefixSum)) { // ↓ 左边界需要++才是零矩阵！
                        int l = sumColMap.get(prefixSum);
                        return new int[][]{{top-1, l}, {bottom-1, r-1}};
                    }
                    sumColMap.put(prefixSum, r);
                }
            }
        }
        return new int[][]{{-1, -1}, {-1, -1}};
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 5, 7}, {3, 7, -8}, {4, -8, 9}};
        matrix = new int[][]{{2, -2}, {4, -4}};
        q9_405_submatrix_sum sol = new q9_405_submatrix_sum();
        int[][] res = sol.submatrixSum(matrix);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++)
                System.out.print(res[i][j] + " ");
            System.out.print(", ");
        }
    }
}
