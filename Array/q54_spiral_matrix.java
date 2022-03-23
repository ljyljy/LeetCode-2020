package Array;

import java.util.ArrayList;
import java.util.List;

public class q54_spiral_matrix {
    private boolean[][] visited;
    private int m, n;
    private int[] _row = {0, 1, 0, -1}; // À≥ ±’Î?
    private int[] _col = {1, 0, -1, 0};
    public List<Integer> spiralOrder(int[][] matrix) {
        m = matrix.length; n = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        visited = new boolean[m][n];
        int row = 0, col = 0, dir = 0;
        for (int i = 0; i < m * n; i++) { // —≠ª∑m*n¥Œ
            res.add(matrix[row][col]);
            visited[row][col] = true;
            int new_row = row + _row[dir], new_col = col + _col[dir];
            if (!isValid(new_row, new_col)) {
                dir = (dir + 1) % 4;
            }
            row += _row[dir]; col += _col[dir];
        }
        return res;
    }

    private boolean isValid(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n && !visited[x][y];
    }
}
