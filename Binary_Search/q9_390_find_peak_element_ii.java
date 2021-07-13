package Binary_Search;

import java.util.ArrayList;
import java.util.List;

public class q9_390_find_peak_element_ii {
    private int[][] mat;
    private int n_row, n_col;
    public List<Integer> findPeakII(int[][] mat) {
        this.mat = mat;
        n_row = mat.length; n_col = mat[0].length;
        List<Integer> res = new ArrayList<>();

        int up = 0, down = n_row - 1; // 对行'二分'
        while (up < down) { // [up, mid(ret)]  [mid+1, down]
            int mid = up + down >> 1;
            int max_col = maxCol(mid); // 1.横向对比(当前行中的最大值)
            // 2. 纵向对比(若[mid][max_col]再比其上下元素都大，则找到山峰)
            int new_x, new_y;
            if (mat[mid+1][max_col] > mat[mid][max_col]) { // 下行更大,在下区间搜索
                up = mid+1;
            } else if (mat[mid-1][max_col] > mat[mid][max_col]) { // 在上区间搜索
                down = mid;
            } else { // 否则该位置为峰值，直接返回答案
                res.add(mid); res.add(max_col);
                return res;
            }
        }
        return res;
    }

    private int maxCol(int row) {
        int maxCol = -1, maxVal = Integer.MIN_VALUE;
        for (int j = 0; j < n_col; j++) {
            if (mat[row][j] > maxVal) {
                maxVal = mat[row][j];
                maxCol = j;
            }
        }
        return maxCol;
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1, 2, 3, 6,  5},
                {16,41,23,22, 6},
                {15,17,24,21, 7},
                {14,18,19,20,10},
                {13,14,11,10, 9}
        };

        q9_390_find_peak_element_ii sol = new q9_390_find_peak_element_ii();
        List<Integer> res = sol.findPeakII(mat);
        System.out.println(res); //  输出: [1,1]
//        解释: [2,2] 也是可以的. [1,1] 的元素是 41, 大于它四周的每一个元素 (2, 16, 23, 17).
    }
}
