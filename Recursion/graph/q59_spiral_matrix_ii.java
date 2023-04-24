package Recursion.graph;

public class q59_spiral_matrix_ii {
    // 顺时针：(右 下 左 上)的循环
    private final int[] _x = {0, 1, 0, -1}; // ?(row_x, col_y)勿搞反！
    private final int[] _y = {1, 0, -1, 0};
    private int n;
    private boolean[][] visited;

    public int[][] generateMatrix(int n) {
        this.n = n;
        visited = new boolean[n][n];
        int curNum = 1, dir = 0;
        int x = 0, y = 0;
        int[][] res = new int[n][n];
        while (curNum <= n * n) { // 赋值[1~n*n],循环n*n次
            res[x][y] = curNum++;
            visited[x][y] = true;
            int new_x = x + _x[dir], new_y = y + _y[dir];
            if (!isValid(new_x, new_y)) { //? ↑若直接赋值给x、y，会出发越界！
                dir = (dir + 1) % 4;
            }
            x += _x[dir];
            y += _y[dir];
        }
        return res;
    }

    private boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n && !visited[x][y];
    }
}
