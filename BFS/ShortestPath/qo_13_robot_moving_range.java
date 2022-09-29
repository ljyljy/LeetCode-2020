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
//        return bfs(); // ��1���������ӡ�
        return dfs(0, 0); // ��2����������
    }

    // DFS
    private int dfs(int i, int j) {
        if (!isValid(i, j) || visited[getIdx(i, j)]) return 0;
        visited[getIdx(i, j)] = true; // ���ڵ�(i,j)�Ϸ�, cnt++
        int cnt = 1;
        for (int dir = 0; dir < 2; dir++) {
            int newX = i + _x[dir], newY = j + _y[dir];
            cnt += dfs(newX, newY);
        }
        return cnt;
    }

    // BFS: ֻ��Ҫ��֤�����Ͻ�(0,0)��ʼ��ֻ���¡����������Ϳ��Բ��ظ����������н��
    private int bfs() {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0); // getIdx(0, 0) = 0
        // �ӣ�һ��ʼ���ֻ��������queue�������жϣ�ͳһ��queue���жϡ�poll֮��newDir֮ǰ��

        while (!queue.isEmpty()) {
            int idx = queue.poll();
            int row = idx / n, col = idx % n; // nΪ����
            if (!isValid(row, col) || visited[idx]) continue; // ��©��?
            visited[idx] = true; // �Ϸ���cnt++
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

    // ��������ת����1D <-> 2D��
    private int getIdx(int i, int j) {
        return i * n + j; // idx = i*col+j <-> i = idx / col, j = idx % col
    }
}
