package BFS.ShortestPath;

import java.util.*;

public class q1197_minimum_knight_moves_TLE {
    // 法1
    // TLE!! 但面试要求应该不高
    class Location {
        int x, y;
        public Location(){}
        public Location(int _x, int _y) {x = _x; y = _y;}
    }

    private final int[] _x = {1, 1,  -1,-1, 2, 2, -2,-2};
    private final int[] _y = {2,-2,  -2, 2, 1,-1, -1, 1};
    private int cnt;
    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) return 0;
        x = Math.abs(x);
        y = Math.abs(y);
        return bfs2(x, y);
    }

    //  法1：简单的visitedSet
    private int bfs1(int x, int y) {
        Queue<Location> queue = new ArrayDeque<>();
        queue.offer(new Location(0, 0));
        Set<Location> visited = new HashSet<>();
        visited.add(new Location(0, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();
            cnt++;
            for (int i = 0; i < size; i++) {
                Location loc = queue.poll();
                for (int dir = 0; dir < 8; dir++) {
                    int new_x = loc.x + _x[dir];
                    int new_y = loc.y + _y[dir];
                    Location new_loc = new Location(new_x, new_y);

                    if (isValid(new_x, new_y, x, y) && !visited.contains(new_loc)) {
                        if (new_x == x && new_y == y)  return cnt;

                        queue.offer(new_loc);
                        visited.add(new_loc);
                    }
                }
            }
        }
        return cnt;
    }

    // 法2：去重-结合dist--visitedDistMap
    private int bfs2(int x, int y) {
        Location src = new Location(0, 0);
        Queue<Location> queue = new ArrayDeque<>();
        queue.offer(src);
        // Set<Location> visited = new HashSet<>();
        Map<Location, Integer> visitedDist = new HashMap<>();
        visitedDist.put(src, 0); // 二维坐标 -> 一维坐标: x*col+y【本题不适合】

        while (!queue.isEmpty()) {
            int size = queue.size();
            // cnt++;
            for (int i = 0; i < size; i++) {
                Location loc = queue.poll();
                if (loc.x == x && loc.y == y)
                    return visitedDist.get(loc);

                for (int dir = 0; dir < 8; dir++) {
                    int new_x = loc.x + _x[dir];
                    int new_y = loc.y + _y[dir];
                    Location new_loc = new Location(new_x, new_y);

                    if (isValid(new_x, new_y, x, y)) {
                        if (visitedDist.containsKey(new_loc))
                            continue; // 不走回头路
                        queue.offer(new_loc);
                        visitedDist.put(new_loc, visitedDist.get(loc)+1);
                    }
                }
            }
        }
        return -1;
    }

    // 给它留一点斡旋用的距离。路径不能是往左10再往右10，往左100，再往右100. 但是可能在目标点附近为了调头或者换行、换列，要斡旋一下。下过象棋的话，感触就比较深。 5算是经验值，好像可以再小一点点，也可以6,7,8,9。但是再大了就显然没必要了
    private boolean isValid(int new_x, int new_y, int x, int y) {
        return (-5 <= new_x && new_x <= x + 5 && -5 <= new_y && new_y <= y+5);
    }

    // 法3：双向BFS -- 反而TLE了！
    private int x, y;

    public int minKnightMoves_biBFS(int x, int y) {
        if (x == 0 && y == 0) return 0;
        this.x = Math.abs(x); this.y = Math.abs(y);
        return bfs(x, y);
    }

    private int bfs (int x, int y) {
        int[] src = new int[]{0, 0}, dst = new int[]{x, y};
        Queue<int[]> queue0 = new ArrayDeque<>();
        Queue<int[]> queue1 = new ArrayDeque<>();
        Set<Integer> visited0 = new HashSet<>();
        Set<Integer> visited1 = new HashSet<>();
        queue0.offer(src); visited0.add(0);
        queue1.offer(dst); visited1.add(x * 500 + y);

        while (!queue0.isEmpty() && !queue1.isEmpty()) {
            cnt++;
            if (extendQueue(queue0, visited0, visited1)) {
                return cnt;
            }
            cnt++;
            if (extendQueue(queue1, visited1, visited0)) {
                return cnt;
            }
        }
        return 0;
    }

    private boolean extendQueue(Queue<int[]> queue, Set<Integer> visited,
                                Set<Integer> oppositeVisited) {
        // while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int[] loc = queue.poll();
            for (int k = 0; k < 8; k++) {
                int newX = loc[0] + _x[k];
                int newY = loc[1] + _y[k];
                int[] newLoc = new int[]{newX, newY};
                int newHash = newX * 500 + newY; // getHash(newX, newY);
                if (check(newX, newY)) {
                    if (oppositeVisited.contains(newHash)) { // 双向BFS"碰撞"
                        return true;
                    }
                    queue.offer(newLoc);
                    visited.add(newHash);
                }
            }
        }
        // }
        return false; // 双向BFS没相遇
    }

    private boolean check (int newX, int newY) {
        return 0 <= newX && newX <= 305 && 0 <= newY && newY <= 305;
    }

    // private int getHash(int x, int y) {
    //     return x * 500 + y;
    // }
}