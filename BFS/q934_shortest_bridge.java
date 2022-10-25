package BFS;

import java.util.*;

public class q934_shortest_bridge {
    private static final int[] _x = {1, -1, 0, 0};
    private static final int[] _y = {0,  0, 1,-1};
    private int[][] grid;
    private int m, n;
    private Deque<int[]> queue = new ArrayDeque<>();
    // 法2：双BFS
    public int shortestBridge(int[][] grid) {
        this.grid = grid; m = grid.length; n = grid[0].length;
        Set<int[]> island1 = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) continue; // 跳过水面(0)、visited(-1)
                queue.offer(new int[]{i, j});
                grid[i][j] = -1; // visited
                BFS1(island1);
                // BFS1结束, queue为空，重新初始化为[岛1的所有点]
                // 准备进行BFS2：求island1 & 2的最短距离
                for (int[] island: island1) {
                    queue.offer(island);
                }
                return BFS2();
            }
        }
        return 0;
    }

    private void BFS1(Set<int[]> island1) {
        while (!queue.isEmpty()) {
            // 此处无需按层遍历(只需要找到岛1所有点即可)，【size可写可不写】！
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            // BFS过程中，记录下所有queue中的结点([当前岛1]的所有点)
            island1.add(cur);
            for (int dir = 0; dir < 4; dir++) {
                int newX = x + _x[dir], newY = y + _y[dir];
                //   只探索unvisited && 陆地 ↓
                if (isValid(newX, newY) && grid[newX][newY] == 1) {
                    queue.offer(new int[]{newX, newY});
                    grid[newX][newY] = -1; // visited
                }
            }
        }
    }

    private int BFS2() {
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 【勿漏！求最短路，必须按层遍历，需要for-size！】
            for (int i = 0; i < size; i++) {
                int cur[] = queue.poll(); // [当前岛1]的某点
                int x = cur[0], y = cur[1];
                for (int dir = 0; dir < 4; dir++) {
                    int newX = x + _x[dir], newY = y + _y[dir];
                    // 只探索unvisited && 岛2陆地 ↓
                    if (!isValid(newX, newY)) continue;
                    if (grid[newX][newY] == 1) {
                        return step;
                    } else if (grid[newX][newY] == 0) { // 不可为-1！
                        queue.offer(new int[]{newX, newY});
                        grid[newX][newY] = -1; // visited
                    }
                }
            }
            step++; // 写在for后（而非for前），避免return step-1
        }
        return step;
    }

    // 法1：DFS+BFS -- 推荐C的写法！
    private boolean[][] visited;
    public int shortestBridge1(int[][] grid) {
        this.grid = grid; m = grid.length; n = grid[0].length;
        visited = new boolean[m][n];
        // 1) DFS: 找到一个可行解（[当前岛屿1]的所有点）, break
        for (int i = 0; i < m; i++) {
            boolean found = false;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    found = true;
                    visited[i][j] = true;
                    dfs(i, j);
                    visited[i][j] = false;
                    break; // 只能退出内层for，还需退出外层（by flag/found）！
                }
            }
            if (found) break;
        }

        // 2) 多源BFS: 从queue(岛1)中所有点('2')，向四周广搜（将遇到的'0'变成【2】, step++），直到碰到‘1’
        int step = 0; // 翻转次数：'0'变成【2】
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                int x = loc[0], y = loc[1];
                // if (grid[x][y] == 1) return step; // 根本不会走这，因为俩岛必定不连通，一定是先下探newLoc时遇到 '1'

                for (int dir = 0; dir < 4; dir++) {
                    int newX = x + _x[dir], newY = y + _y[dir];

                    if (isValid(newX, newY) && grid[newX][newY] != 2) {
                        if (visited[newX][newY]) continue;
                        if (grid[newX][newY] == 0) {
                            visited[newX][newY] = true;
                            grid[newX][newY] = 2;
                            queue.offer(new int[]{newX, newY});
                            visited[newX][newY] = false;
                        } else if (grid[newX][newY] == 1) {
                            return step - 1;  // A.这里必须return，类比q127
                            // ↑【WHY -1? ∵step++：该层1->2的(理论)翻转次数，但是遇到1不会翻转，所以需要“恢复”为(实际)上一层/不翻转的值】
                            // ↑【与q127的minDist+1（长度）不同！】
                            // poll: 2,cur: 0,1 (step=1) -> 2,poll:2,(cur: 1)(理论step=2，实际只翻转了一次，需step-1)
                        }
                    }
                }
            }
            // step++; // 若写此处，则无需step-1
        }
        return step;
    }


    // 将当前岛屿内所有结点全部染色为1->2, 并加入队列
    private void dfs(int i, int j) {
        grid[i][j] = 2;
        queue.add(new int[]{i, j});//  queue为当前岛屿1的所有点
        // dfs当前连通块内所有结点
        for (int dir = 0; dir < 4; dir++) {
            int newX = i + _x[dir], newY = j + _y[dir];

            if (isValid(newX, newY) && grid[newX][newY] == 1) {
                if (visited[newX][newY]) continue;
                visited[newX][newY] = true;
                dfs(newX, newY);
                visited[newX][newY] = false;
            }
        }
    }

    private boolean isValid(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }
}
