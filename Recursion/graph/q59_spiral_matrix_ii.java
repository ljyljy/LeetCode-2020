package Recursion.graph;

public class q59_spiral_matrix_ii {
    // ˳ʱ�룺(�� �� �� ��)��ѭ��
    private final int[] _x = {0, 1, 0, -1}; // ?(row_x, col_y)��㷴��
    private final int[] _y = {1, 0, -1, 0};
    private int n;
    private boolean[][] visited;

    public int[][] generateMatrix(int n) {
        this.n = n;
        visited = new boolean[n][n];
        int curNum = 1, dir = 0;
        int x = 0, y = 0;
        int[][] res = new int[n][n];
        while (curNum <= n * n) { // ��ֵ[1~n*n],ѭ��n*n��
            res[x][y] = curNum++;
            visited[x][y] = true;
            int new_x = x + _x[dir], new_y = y + _y[dir];
            if (!isValid(new_x, new_y)) { //? ����ֱ�Ӹ�ֵ��x��y�������Խ�磡
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
