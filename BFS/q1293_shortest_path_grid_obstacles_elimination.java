package BFS;

import java.util.*;

// ?����άBFS��
public class q1293_shortest_path_grid_obstacles_elimination {
    private static final int[] _x = {0, 1, 0, -1};
    private static final int[] _y = {-1, 0, 1, 0};
    private static int m, n, k;
    private int[][] grid;

    public int shortestPath(int[][] grid, int k) {
        this.grid = grid; m = grid.length; n = grid[0].length;
        if (m == 1 && n == 1) return 0;
        this.k = Math.min(k, m + n - 3); // ��֦�������m+n-1��,��-2(��ֹ����ϰ���): ���m+n-3���ϰ���

        return bfs();
    }

    private int bfs() {
        Deque<int[]> queue = new ArrayDeque<>(); // [x, y, remainBlocks]
        queue.offer(new int[]{0, 0, k});
        boolean[][][] visited = new boolean[m][n][k+1];
        visited[0][0][k] = true;
        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1], rest = cur[2];
                for (int dir = 0; dir < 4; dir++) {
                    int nx = x + _x[dir], ny = y + _y[dir];
                    if (!isValid(nx, ny)) continue;
                    // ���q1293��HJ22_1_3
                    // 1) ���ϰ���, �Կ�����rest���ϰ���
                    if (grid[nx][ny] == 0 && !visited[nx][ny][rest]) {
                        if (nx == m-1 && ny == n-1) {
                            return step;
                        }
                        visited[nx][ny][rest] = true;
                        queue.offer(new int[]{nx, ny, rest});
                    } // 2) �����ϰ���(��Ҫ����), ʣ�������rest-1���ϰ���
                    else if (grid[nx][ny] == 1 && rest-1>= 0 && !visited[nx][ny][rest-1]) {
                        visited[nx][ny][rest-1] = true;
                        queue.offer(new int[]{nx, ny, rest-1});
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }
}
