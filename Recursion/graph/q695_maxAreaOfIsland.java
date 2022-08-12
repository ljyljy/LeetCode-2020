package Recursion.graph;

import java.util.Arrays;

public class q695_maxAreaOfIsland {
    private int m, n;
    private int[][] grid;
    //    private List<Integer> connCnts = new ArrayList<>();
    private int maxConn = 0; // �����ͨ���С
    private int[][] memo;
    private int[] _x = {0, 1, 0, -1}; // �������� ��4������
    private int[] _y = {1, 0, -1, 0};

    // dfs+memo
    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid; m = grid.length; n = grid[0].length;
        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }

        // ��Դdfs
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) continue;
                maxConn = Math.max(maxConn, dfs(i, j, memo));
            }
        }
        return maxConn;
    }

    private int dfs(int x, int y, int[][] memo) {
        if (memo[x][y] != -1) return memo[x][y];
        int curCnt = 1;
        // �� �����graph��ԭ���޸ģ�visited���ã������q130, q695_HJ��
        grid[x][y] = -1;

        for (int dir = 0; dir < 4; dir++) {
            int newX = x + _x[dir], newY = y + _y[dir];
            if (!check(newX, newY)) continue;
            curCnt += dfs(newX, newY, memo);
        }

        memo[x][y] = curCnt;
        return curCnt;
    }

    private boolean check(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n && grid[x][y] == 1;
    }


    // ��2����memo����dfs
    private boolean[][] visited;
    public int maxAreaOfIsland2(int[][] grid) {
        this.grid = grid; m = grid.length; n = grid[0].length;
        visited = new boolean[m][n];
        int maxConn = 0; // �����ͨ���С
        // ��Դdfs
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (check(i, j) && !visited[i][j]) {
                    maxConn = Math.max(maxConn, dfs(i, j));
                }
            }
        }
        return maxConn;
    }

    private int dfs(int x, int y) {
        visited[x][y] = true; // ��1
        int curCnt = 1;

        // �� �����graph��ԭ���޸ģ�visited���ã������q130, q695_HJ��
        // grid[x][y] = -1; // ��2

        for (int dir = 0; dir < 4; dir++) {
            int newX = x + _x[dir], newY = y + _y[dir];
            if (!check(newX, newY) || visited[newX][newY]) continue;
            curCnt += dfs(newX, newY);
        }

        // visited[x][y] = false; // ����д WA!
        return curCnt;
    }


}
