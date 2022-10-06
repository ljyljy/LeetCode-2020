package BFS.ShortestPath;

import java.util.*;

public class q9_571_build_post_office_ii {

    static class GridType {
        // 成员变量必须为static，才可访问到！
        static final int EMPTY = 0;
        static final int HOUSE = 1;
        static final int WALL = 2;
    }

    private final int[] _x = {0,  0, 1, -1};
    private final int[] _y = {1, -1, 0, 0};
    private int m, n;
    private int[][] grid;

    // todo: 方法2(略)： 枚举房子位置（房子数量小时，更优）, 见《9z_高频2021_ch7》

    // 方法1：枚举邮局位置/空地（空地数量小时，更优）
    public int shortestDistance(int[][]grid) {
        m = grid.length; n = grid[0].length; this.grid = grid;
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == GridType.EMPTY) {
                    Map<Integer, Integer> dists = bfs(i, j); // 所有房子到邮局<i,j>的最短距离map
                    minDist = Math.min(minDist, getDistSum(dists));
                }
            }
        }
        return minDist == Integer.MAX_VALUE? -1: minDist;
    }

    private int getDistSum(Map<Integer, Integer> dists) {
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = getID(i, j);
                if (grid[i][j] == GridType.HOUSE) {
                    if (!dists.containsKey(idx)) {
//                        System.out.println("INVALID!");
                        return Integer.MAX_VALUE;
                    }
                    sum += dists.get(idx);
                }
            }
        }
        return sum;
    }

    // 计算邮局<i,j>到所有点（非墙）的最短距离map
    private Map<Integer, Integer> bfs(int i, int j) {
        Map<Integer, Integer> dists = new HashMap<>(); // <ID(x, y), dist-to-point(i,j)>
        Queue<Integer> deque = new ArrayDeque<>();
        int startIdx = getID(i,j);
        dists.put(startIdx, 0);
        deque.add(startIdx);

        while (!deque.isEmpty()) {
            int curIdx = deque.poll();
            int x = curIdx / n, y = curIdx % n;
            for (int dir = 0; dir < 4; dir++) {
                int newX = x + _x[dir], newY = y + _y[dir];
                int newIdx = getID(newX, newY);
                if (!isValid(newX, newY)) continue; // 跳过墙！
                if (dists.containsKey(newIdx)) continue; // visited, 简单图中BFS先遍历到的为较短路径
                dists.put(newIdx, dists.get(curIdx) + 1); // 距离map可以记录邮局<i,j>到所有点的距离（空地or房子）
//                if (grid[newX][newY] != GridType.HOUSE) { // 【勿漏if, WHY?】【不加if也对！】
                    deque.offer(newIdx); // 只下探【空地】！其他房子不探！
//                }
            }
        }
        return dists;
    }

    private boolean isValid(int x, int y) { // 空地or房子均可遍历/下探
        return 0 <= x && x < m && 0 <= y && y < n && grid[x][y] != GridType.WALL;
    }

    private int getID(int i, int j) {
        return i * n + j;
    }

    public static void main(String[] args) {
        int[][] grid = {{0,1,0,0,0}, {1,0,0,2,1}, {0,1,0,0,0}};
        q9_571_build_post_office_ii sol = new q9_571_build_post_office_ii();
        int dist = sol.shortestDistance(grid);
        System.out.println(dist);

        int[][] grid2 = {{0,1,0}, {1,0,1}, {0,1,0}};
        System.out.println(sol.shortestDistance(grid2));

        int[][] grid3 = {{0,1,0,0,0}, {1,0,1,2,1}, {0,1,0,0,0}};
        System.out.println(sol.shortestDistance(grid3));
    }
}


