package Recursion.graph;

import java.util.HashSet;
import java.util.Set;

// 类比q130, 200, 1254, 1905, 694
//   难点：序列化
public class q694_number_of_distinct_islands {
    private int m, n;
    private int[][] grid;
    private static final int LAND = 1, WATER = 0;
    private final int[] _x = {0, 1, 0, -1};
    private final int[] _y = {1, 0, -1, 0}; // 上，右，下，左
    private Set<String> set = new HashSet<>(); // 必须是String，不能使StringBuilder（无效判重！）

    public int numDistinctIslands(int[][] grid) {
        m = grid.length; n = grid[0].length; this.grid = grid;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == LAND) {
                    // 淹掉这个岛屿，同时存储岛屿的序列化结果
                    StringBuilder path = new StringBuilder();
                    dfs(i, j, path, 999); // 初始的方向可以随便写，不影响正确性
                    set.add(path.toString());
                }
            }
        }
//        for (String sb: set) {
//            System.out.println(sb);
//        }
        return set.size();
    }

    private void dfs(int i, int j, StringBuilder path, int lastDir) {
        grid[i][j] = WATER;
        // 前序遍历位置：进入 (i, j)
        path.append(lastDir).append(','); // 序列化path
        for (int dir = 0; dir < 4; dir++) {
            int newX = i + _x[dir], newY = j + _y[dir];
            if (!isValid(newX, newY)) continue;
            dfs(newX, newY, path, dir);
        }
        // 后序遍历位置：离开 (i, j)
        path.append(-lastDir).append(',');
    }

    private boolean isValid(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n && grid[x][y] == LAND;
    }


}
