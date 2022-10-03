package Recursion.graph;

import java.util.HashSet;
import java.util.Set;

// ���q130, 200, 1254, 1905, 694
//   �ѵ㣺���л�
public class q694_number_of_distinct_islands {
    private int m, n;
    private int[][] grid;
    private static final int LAND = 1, WATER = 0;
    private final int[] _x = {0, 1, 0, -1};
    private final int[] _y = {1, 0, -1, 0}; // �ϣ��ң��£���
    private Set<String> set = new HashSet<>(); // ������String������ʹStringBuilder����Ч���أ���

    public int numDistinctIslands(int[][] grid) {
        m = grid.length; n = grid[0].length; this.grid = grid;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == LAND) {
                    // �͵�������죬ͬʱ�洢��������л����
                    StringBuilder path = new StringBuilder();
                    dfs(i, j, path, 999); // ��ʼ�ķ���������д����Ӱ����ȷ��
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
        // ǰ�����λ�ã����� (i, j)
        path.append(lastDir).append(','); // ���л�path
        for (int dir = 0; dir < 4; dir++) {
            int newX = i + _x[dir], newY = j + _y[dir];
            if (!isValid(newX, newY)) continue;
            dfs(newX, newY, path, dir);
        }
        // �������λ�ã��뿪 (i, j)
        path.append(-lastDir).append(',');
    }

    private boolean isValid(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n && grid[x][y] == LAND;
    }


}
