package BFS;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}

public class q9_611_knight_shortest_path {
    private final int[] _x = {1, 1,  -1,-1, 2, 2, -2,-2};
    private final int[] _y = {2,-2,  -2, 2, 1,-1, -1, 1};
    private int m, n;
    private boolean[][] grid;
    public int shortestPath(boolean[][] grid, Point src, Point end) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        m = grid.length; n = grid[0].length; this.grid = grid;

        return bfs(src, end);
    }

    private int bfs(Point src, Point end) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(src);
        // Set<Point> visited = new HashSet<>(); // 可以结合dist
        // Map<Point, Integer> visited_dist = new HashMap<>(); // TLE!
        Map<Integer, Integer> visited_dist = new HashMap<>(); // ↑优化: 二维坐标->一维x*col+y
        visited_dist.put(src.x*n + src.y, 0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point loc = queue.poll();
                if (loc.x == end.x && loc.y == end.y)
                    return visited_dist.get(loc.x*n + loc.y);

                for (int dir = 0; dir < 8; dir++) {
                    int new_x = loc.x + _x[dir];
                    int new_y = loc.y + _y[dir];
                    Point new_loc = new Point(new_x, new_y);

                    if (isValid(new_x, new_y)) {
                        if (visited_dist.containsKey(new_x*n + new_y))
                            continue; // 不走回头路
                        queue.offer(new_loc);
                        visited_dist.put(new_x*n + new_y, visited_dist.get(loc.x*n + loc.y)+1);
                    }
                }
            }
        }
        return -1;

    }

    private boolean isValid(int new_x, int new_y) {
        if (new_x < 0 || new_x >= m || 0 > new_y || new_y >= n) {
            return false;
        }
        return !grid[new_x][new_y];
    }
}
