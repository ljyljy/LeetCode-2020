package Recursion.graph;

public class q980_unique_path_iii {
    private int[] _x = {1, -1, 0, 0};
    private int[] _y = {0,  0, 1, -1};
    private int[][] grid;
    private int m, n;
    public int uniquePathsIII(int[][] grid) {
        this.grid = grid; m = grid.length; n = grid[0].length;
        //当走到终点grid[i][j] == 2, step++, 这里直接初始化为1
        int startX = 0, startY = 0, step = 1;
        //遍历获取起始位置和统计总步数
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    startX = i;
                    startY = j;
                    continue;
                }
                if (grid[i][j] == 0) step++; // 统计所有'0'的个数
            }
        }

        boolean[][] visited = new boolean[m][n];
        visited[startX][startY] = true; // 法1
        return dfs(startX, startY, step, visited);
    }

    private int dfs(int i, int j, int step, boolean[][] visited) {
        // 终点时，剩余step应为0
        if (grid[i][j] == 2) return step == 0? 1: 0;
        // grid[i][j] = -1; // 法2
        // visited[i][j] = true; // 法3

        int cnt = 0;
        for (int dir = 0; dir < 4; dir++) {
            int newX = i + _x[dir], newY = j + _y[dir];
            if (isValid(newX, newY) && !visited[newX][newY]) {
                visited[newX][newY] = true; // 法1
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
