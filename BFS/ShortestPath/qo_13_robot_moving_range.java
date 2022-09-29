package BFS.ShortestPath;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class qo_13_robot_moving_range {
    private static int[] _x = {0, 1, -1, 0};
    private static int[] _y = {1, 0, 0, -1};
    private boolean[] visited;
    private int cnt = 0;
    private int m, n, k;
    public int movingCount(int m, int n, int k) {
        this.m = m; this.n = n; this.k = k;
        visited = new boolean[m*n];
//        return bfs(); // 法1：【见：坑】
        return dfs(0, 0); // 法2：荐，更短
    }

    // DFS
    private int dfs(int i, int j) {
        if (!isValid(i, j) || visited[getIdx(i, j)]) return 0;
        visited[getIdx(i, j)] = true; // 本节点(i,j)合法, cnt++
        int cnt = 1;
        for (int dir = 0; dir < 2; dir++) {
            int newX = i + _x[dir], newY = j + _y[dir];
            cnt += dfs(newX, newY);
        }
        return cnt;
    }

    // BFS: 只需要保证从左上角(0,0)开始，只向下、右搜索，就可以不重复搜索完所有结果
    private int bfs() {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0); // getIdx(0, 0) = 0
        // 坑：一开始起点只单纯放入queue，不做判断！统一在queue内判断【poll之后，newDir之前】

        while (!queue.isEmpty()) {
            int idx = queue.poll();
            int row = idx / n, col = idx % n; // n为列数
            if (!isValid(row, col) || visited[idx]) continue; // 勿漏！?
            visited[idx] = true; // 合法，cnt++
            cnt++;

            for (int dir = 0; dir < 2; dir++) {
                int newX = row + _x[dir], newY = col + _y[dir];
                int newIdx = getIdx(newX, newY);
//                if (isValid(newX, newY) && !visited[newIdx]) {
//                    visited[newIdx] = true;
                queue.offer(newIdx);
//                    path.add(newIdx);
//                }
            }
        }
        return cnt; // path.size();
    }

    private int getBitSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    private boolean isValid(int i, int j) {
        int bitSum = getBitSum(i) + getBitSum(j);
        return 0 <= i && i < m && 0 <= j && j < n && bitSum <= k;
    }

    // 矩阵坐标转换（1D <-> 2D）
    private int getIdx(int i, int j) {
        return i * n + j; // idx = i*col+j <-> i = idx / col, j = idx % col
    }
}
