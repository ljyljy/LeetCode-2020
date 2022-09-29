package BFS.ShortestPath;

import java.util.*;

public class q1197_minimum_knight_moves_new {
    // 最新版本：坐标2D->1D, 类比q1197,130,200, qo_13
    // ❤二维坐标转换一维坐标（Queue内WA，仅visited可）、无限网格：ncol & isValid边界斡旋
    //     // Location类做hash会很慢，导致LTE!
    //    // 解决: 使用int[]{x, y}
    private final int[] _x = {1, 1,  -1,-1, 2, 2, -2,-2};
    private final int[] _y = {2,-2,  -2, 2, 1,-1, -1, 1};
    private int endX, endY, cnt = 0, ncol = 500;
    private Set<Integer> visited = new HashSet<>();
    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) return 0;
        endX = Math.abs(x);
        endY = Math.abs(y);
        return bfs();
    }

    private int bfs() {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0}); // 起始点(0,0)
        visited.add(0);

        while (!queue.isEmpty()) {
            cnt++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                // int x = curIdx / ncol, y = curIdx % ncol; // 坐标转换1D->2D【坑WHY：Queue内使用1D WA! 但是visited AC？】

                for (int dir = 0; dir < 8; dir++) {
                    // int newX = x + _x[dir], newY = y + _y[dir];
                    int newX = loc[0] + _x[dir], newY = loc[1] + _y[dir];
                    int newIdx = getID(newX, newY);
                    if (newX == endX && newY == endY) return cnt;

                    if (isValid(newX, newY) && !visited.contains(newIdx)) {
                        visited.add(newIdx);
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
        }
        return -1;
    }

    private boolean isValid(int x, int y) {
        return -5 <= x && x <= 305 && -5 <= y && y <= 305;
    }

    private int getID(int x, int y) {
        return x * ncol + y;
    }
}
