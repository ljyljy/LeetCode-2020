package Array.prefixSum;

public class q304_range_sum_query_2d_immutable {
    class NumMatrix {
        int[][] sum;
        public NumMatrix(int[][] matrix) {
            int n = matrix.length, m = n == 0? 0: matrix[0].length;
            //前缀和数组下标从 1 开始 --  [n + 1][m + 1]
            sum = new int[n+1][m+1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) { // sum[i][j]下标1起 -- matrix[i-1][j-1]下标0起
                    sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + matrix[i-1][j-1];
                }
            }
        }

        // 时间 O(n*m), 空间O(n*m)
        public int sumRegion(int x1, int y1, int x2, int y2) {
            x1++; y1++; x2++; y2++;
            return sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
        }
    }

    public static void main(String[] args) {
        q304_range_sum_query_2d_immutable sol = new q304_range_sum_query_2d_immutable();
        int[][] matrix = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = (int)Math.random() * 10;
                System.out.println(matrix[i][j] + " ");
            }
            System.out.println();
        }

        NumMatrix numMatrix = sol.new NumMatrix(matrix);
        int sum = numMatrix.sumRegion(2,1,4,3);
        System.out.println("sum = " + sum);
    }
}
