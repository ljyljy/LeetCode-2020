package BFS.ShortestPath;

import java.util.*;

public class q9_571_build_post_office_ii {

    static class GridType {
        // ��Ա��������Ϊstatic���ſɷ��ʵ���
        static final int EMPTY = 0;
        static final int HOUSE = 1;
        static final int WALL = 2;
    }

    private final int[] _x = {0,  0, 1, -1};
    private final int[] _y = {1, -1, 0, 0};
    private int m, n;
    private int[][] grid;

    // todo: ����2(��)�� ö�ٷ���λ�ã���������Сʱ�����ţ�, ����9z_��Ƶ2021_ch7��

    // ����1��ö���ʾ�λ��/�յأ��յ�����Сʱ�����ţ�
    public int shortestDistance(int[][]grid) {
        m = grid.length; n = grid[0].length; this.grid = grid;
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == GridType.EMPTY) {
                    Map<Integer, Integer> dists = bfs(i, j); // ���з��ӵ��ʾ�<i,j>����̾���map
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

    // �����ʾ�<i,j>�����е㣨��ǽ������̾���map
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
                if (!isValid(newX, newY)) continue; // ����ǽ��
                if (dists.containsKey(newIdx)) continue; // visited, ��ͼ��BFS�ȱ�������Ϊ�϶�·��
                dists.put(newIdx, dists.get(curIdx) + 1); // ����map���Լ�¼�ʾ�<i,j>�����е�ľ��루�յ�or���ӣ�
//                if (grid[newX][newY] != GridType.HOUSE) { // ����©if, WHY?��������ifҲ�ԣ���
                    deque.offer(newIdx); // ֻ��̽���յء����������Ӳ�̽��
//                }
            }
        }
        return dists;
    }

    private boolean isValid(int x, int y) { // �յ�or���Ӿ��ɱ���/��̽
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


