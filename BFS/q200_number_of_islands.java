package BFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class q200_number_of_islands {
    class Location {
        int x, y;
        public Location(){}
        public Location(int _x, int _y) {x = _x; y = _y;}
    }

    private static final int[] _x = {1, -1, 0, 0};
    private static final int[] _y = {0,  0, 1,-1};
    private int m, n, cnt = 0;
    private char[][] grid;
    private boolean[][] visited;
    // 法1：BFS
    public int numIslands(char[][] grid) {
        m = grid.length; n = grid[0].length; this.grid = grid;
        visited = new boolean[m][n];
        // 遍历网格中的每一个点，都以其为起点进行BFS
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') continue; // 勿漏！
                if (!visited[i][j]) {
                    bfs(i, j); // ↑ 判断的是visited，而非isValid
                    cnt++; // iff. 非visited[i][j]的陆地，才能cnt++
                }
            }
        }
        return cnt;
    }

    private void bfs(int x, int y) {
        Queue<Location> queue = new ArrayDeque<>();
        queue.offer(new Location(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Location loc = queue.poll();
                for (int j = 0; j < 4; j++) { // 4个方向(上下左右)
                    int new_x = loc.x + _x[j];
                    int new_y = loc.y + _y[j];
                    if (!isValid(new_x, new_y)) continue;
                    queue.offer(new Location(new_x, new_y));
                    visited[new_x][new_y] = true;
                }
            }
        }
    }

    // 易错！！❤
    private boolean isValid(int x, int y) {
        // 法1
        return 0 <= x && x < m && 0 <= y && y < n
                && !visited[x][y] && grid[x][y] == '1'; // 本行易漏！！❤

//        // 写法2
//        if (x < 0 || x >= m || y < 0 || y >= n) return false;
//        // ❤ 不重复BFS! 避免死循环!!!
//        if (visited[x][y]) return false;
//
//        // int isValid = Integer.parseInt(String.valueOf(grid[x][y]));
//        // return isValid == 0? false: true;
//        return grid[x][y] != '0';

    }


}
