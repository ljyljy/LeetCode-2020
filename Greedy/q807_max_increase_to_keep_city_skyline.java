package Greedy;

public class q807_max_increase_to_keep_city_skyline {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rowMax = new int[m];
        int[] colMax = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int curH = grid[i][j];
                rowMax[i] = Math.max(rowMax[i], curH);
                colMax[j] = Math.max(colMax[j], curH);
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int curH = grid[i][j];
                res += Math.min(rowMax[i], colMax[j]) - curH;
            }
        }
        return res;
    }
}
