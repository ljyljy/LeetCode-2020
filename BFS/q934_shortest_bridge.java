package BFS;

import java.util.*;

public class q934_shortest_bridge {
    private static final int[] _x = {1, -1, 0, 0};
    private static final int[] _y = {0,  0, 1,-1};
    private int[][] grid;
    private int m, n;
    private Deque<int[]> queue = new ArrayDeque<>();
    // ��2��˫BFS
    public int shortestBridge(int[][] grid) {
        this.grid = grid; m = grid.length; n = grid[0].length;
        Set<int[]> island1 = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) continue; // ����ˮ��(0)��visited(-1)
                queue.offer(new int[]{i, j});
                grid[i][j] = -1; // visited
                BFS1(island1);
                // BFS1����, queueΪ�գ����³�ʼ��Ϊ[��1�����е�]
                // ׼������BFS2����island1 & 2����̾���
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
            // �˴����谴�����(ֻ��Ҫ�ҵ���1���е㼴��)����size��д�ɲ�д����
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            // BFS�����У���¼������queue�еĽ��([��ǰ��1]�����е�)
            island1.add(cur);
            for (int dir = 0; dir < 4; dir++) {
                int newX = x + _x[dir], newY = y + _y[dir];
                //   ֻ̽��unvisited && ½�� ��
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
            // ����©�������·�����밴���������Ҫfor-size����
            for (int i = 0; i < size; i++) {
                int cur[] = queue.poll(); // [��ǰ��1]��ĳ��
                int x = cur[0], y = cur[1];
                for (int dir = 0; dir < 4; dir++) {
                    int newX = x + _x[dir], newY = y + _y[dir];
                    // ֻ̽��unvisited && ��2½�� ��
                    if (!isValid(newX, newY)) continue;
                    if (grid[newX][newY] == 1) {
                        return step;
                    } else if (grid[newX][newY] == 0) { // ����Ϊ-1��
                        queue.offer(new int[]{newX, newY});
                        grid[newX][newY] = -1; // visited
                    }
                }
            }
            step++; // д��for�󣨶���forǰ��������return step-1
        }
        return step;
    }

    // ��1��DFS+BFS -- �Ƽ�C��д����
    private boolean[][] visited;
    public int shortestBridge1(int[][] grid) {
        this.grid = grid; m = grid.length; n = grid[0].length;
        visited = new boolean[m][n];
        // 1) DFS: �ҵ�һ�����н⣨[��ǰ����1]�����е㣩, break
        for (int i = 0; i < m; i++) {
            boolean found = false;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    found = true;
                    visited[i][j] = true;
                    dfs(i, j);
                    visited[i][j] = false;
                    break; // ֻ���˳��ڲ�for�������˳���㣨by flag/found����
                }
            }
            if (found) break;
        }

        // 2) ��ԴBFS: ��queue(��1)�����е�('2')�������ܹ��ѣ���������'0'��ɡ�2��, step++����ֱ��������1��
        int step = 0; // ��ת������'0'��ɡ�2��
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                int x = loc[0], y = loc[1];
                // if (grid[x][y] == 1) return step; // �����������⣬��Ϊ�����ض�����ͨ��һ��������̽newLocʱ���� '1'

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
                            return step - 1;  // A.�������return�����q127
                            // ����WHY -1? ��step++���ò�1->2��(����)��ת��������������1���ᷭת��������Ҫ���ָ���Ϊ(ʵ��)��һ��/����ת��ֵ��
                            // ������q127��minDist+1�����ȣ���ͬ����
                            // poll: 2,cur: 0,1 (step=1) -> 2,poll:2,(cur: 1)(����step=2��ʵ��ֻ��ת��һ�Σ���step-1)
                        }
                    }
                }
            }
            // step++; // ��д�˴���������step-1
        }
        return step;
    }


    // ����ǰ���������н��ȫ��ȾɫΪ1->2, ���������
    private void dfs(int i, int j) {
        grid[i][j] = 2;
        queue.add(new int[]{i, j});//  queueΪ��ǰ����1�����е�
        // dfs��ǰ��ͨ�������н��
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
