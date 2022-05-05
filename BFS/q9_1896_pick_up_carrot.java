package BFS;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class q9_1896_pick_up_carrot {
    private int[] _x = {0, 0, 1, -1};
    private int[] _y = {1, -1, 0, 0};
    private int n, m;
    public int pickCarrots(int[][] carrot) {
        n = carrot.length; m = carrot[0].length;
        int startX = (n-1) / 2, startY = (m-1) / 2;
        int cnt = 0;
//        Set<int[]> visited = new HashSet<>();
//        visited.add(new int[]{startX, startY});
        boolean[][] visited = new boolean[n][m];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;
        cnt += carrot[startX][startY];

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                int x = loc[0], y = loc[1];
                int curMaxCnt = 0, curX = -1, curY = -1;
                for (int dir = 0; dir < 4; dir++) {
                    int newX = x + _x[dir], newY = y + _y[dir];
                    // 选取四个方向的最大者作为下一个方向
                    if (check(newX, newY) && !visited[newX][newY]) { // visited避免成环
                        if (curMaxCnt < carrot[newX][newY]) {
                            curMaxCnt = carrot[newX][newY];
                            curX = newX;
                            curY = newY;
                        }
                    }
                }
                if (check(curX, curY) && !visited[curX][curY]) {
                    visited[curX][curY] = true;
                    cnt += carrot[curX][curY];
                    queue.offer(new int[]{curX, curY});
                }
            }
        }
        return cnt;
    }

    private boolean check(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static void main(String[] args) {
        int n = 4, m = 4;
        int[][] carrot = new int[][]{
                {5, 7,  6,  3},
                {2, 4,  8, 12},
                {3, 5, 10,  7},
                {4, 16, 4, 17}};
        q9_1896_pick_up_carrot sol = new q9_1896_pick_up_carrot();
        System.out.println(sol.pickCarrots(carrot));

    }
}
