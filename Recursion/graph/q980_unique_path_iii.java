package Recursion.graph;

public class q980_unique_path_iii {
    private int[] _x = {1, -1, 0, 0};
    private int[] _y = {0,  0, 1, -1};
    private int[][] grid;
    private int m, n;
    public int uniquePathsIII(int[][] grid) {
        this.grid = grid; m = grid.length; n = grid[0].length;
        //���ߵ��յ�grid[i][j] == 2, step++, ����ֱ�ӳ�ʼ��Ϊ1
        int startX = 0, startY = 0, step = 1;
        //������ȡ��ʼλ�ú�ͳ���ܲ���
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    startX = i;
                    startY = j;
                    continue;
                }
                if (grid[i][j] == 0) step++; // ͳ������'0'�ĸ���
            }
        }

        boolean[][] visited = new boolean[m][n];
        visited[startX][startY] = true; // ��1
        return dfs(startX, startY, step, visited);
    }

    private int dfs(int i, int j, int step, boolean[][] visited) {
        // �յ�ʱ��ʣ��stepӦΪ0
        if (grid[i][j] == 2) return step == 0? 1: 0;
        // grid[i][j] = -1; // ��2
        // visited[i][j] = true; // ��3

        int cnt = 0;
        for (int dir = 0; dir < 4; dir++) {
            int newX = i + _x[dir], newY = j + _y[dir];
            if (isValid(newX, newY) && !visited[newX][newY]) {
                visited[newX][newY] = true; // ��1
                cnt += dfs(newX, newY, step-1, visited);
                visited[newX][newY] = false;
            }

        }
        // grid[i][j] = 0;
        // visited[i][j] = false;
        return cnt;
    }

    private boolean isValid(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n
                && grid[x][y] != -1;
    }
}
