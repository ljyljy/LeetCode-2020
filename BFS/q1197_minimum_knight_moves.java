package BFS;

import java.util.*;

public class q1197_minimum_knight_moves {

    private final int[] _x = {1, 1,  -1,-1, 2, 2, -2,-2};
    private final int[] _y = {2,-2,  -2, 2, 1,-1, -1, 1};
    private int cnt;

    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) return 0;
        x = Math.abs(x);
        y = Math.abs(y);
        return bfs(x, y);
    }

    // 法1：cnt++，使用Set<Integer>, 将Location转换为int hash
    private int bfs(int x, int y) {
        Location src = new Location(0, 0);
        Queue<Location> queue = new ArrayDeque<>();
        queue.offer(src);
        Set<Integer> visited = new HashSet<>();
        // Map<Integer, Integer> visitedDist = new HashMap<>();
        visited.add(getID(src)); // 二维坐标 -> 一维坐标: x*col(>300)+y

        while (!queue.isEmpty()) {
            int size = queue.size();
            cnt++;
            for (int i = 0; i < size; i++) {
                Location loc = queue.poll();
                for (int dir = 0; dir < 8; dir++) {
                    int newX = loc.x + _x[dir];
                    int newY = loc.y + _y[dir];
                    if (newX == x && newY == y) return cnt;

                    Location newLoc = new Location(newX, newY);
                    int newIdx = getID(newLoc);

                    if (isValid(newX, newY)) {
                        if (visited.contains(newIdx))
                            continue; // 不走回头路
                        queue.offer(newLoc);
                        visited.add(newIdx);
                    }
                }
            }
        }
        return -1;
    }

    // 给它留一点斡旋用的距离。路径不能是往左10再往右10，往左100，再往右100. 但是可能在目标点附近为了调头或者换行、换列，要斡旋一下。下过象棋的话，感触就比较深。 5算是经验值，好像可以再小一点点，也可以6,7,8,9。但是再大了就显然没必要了
    // NOTICE:
    // |x| + |y| <= 300  -> |x| <= 300, |y| <= 300
    // 虽然终点 (x, y) 满足 |x| <= 300, |y| <= 300
    // 但是从终点出发可以往8个方向走，经过的点不一定满足该条件，所以要来留出空间以免哈希碰撞，即 LIMIT * 2
    // 测试用例 assert(minKnightMoves(-34, -156), 78)
    private boolean isValid(int new_x, int new_y) {
        return (-5 <= new_x && new_x <= 305 && -5 <= new_y && new_y <= 305);
    }

    private int getID(Location loc) {
        return loc.x * 500 + loc.y;
    }



    // 写法1-2：visited （set） -> visitedDist（map）
    private int bfs2(int x, int y) {
        Location src = new Location(0, 0);
        Queue<Location> queue = new ArrayDeque<>();
        queue.offer(src);
        // Set<Location> visited = new HashSet<>();
        Map<Integer, Integer> visitedDist = new HashMap<>();
        visitedDist.put(src.x*500+src.y, 0);
        // 二维坐标 -> 一维坐标: x*col(>300)+y，否则TLE!!!

        while (!queue.isEmpty()) {
            int size = queue.size();
            // cnt++;
            for (int i = 0; i < size; i++) {
                Location loc = queue.poll();
                int hash = loc.x * 500 + loc.y;
                if (loc.x == x && loc.y == y)
                    return visitedDist.get(hash);

                for (int dir = 0; dir < 8; dir++) {
                    int new_x = loc.x + _x[dir];
                    int new_y = loc.y + _y[dir];
                    Location new_loc = new Location(new_x, new_y);
                    int newHash = new_loc.x * 500 + new_loc.y;

                    if (isValid(new_x, new_y)) {
                        if (visitedDist.containsKey(newHash))
                            continue; // 不走回头路
                        queue.offer(new_loc);
                        visitedDist.put(newHash, visitedDist.get(hash)+1);
                    }
                }
            }
        }
        return -1;
    }

    // Location类做hash会很慢，导致LTE!
    // 解决: 使用int[]{x, y}
    class Location {
        int x, y;
        public Location(){}
        public Location(int _x, int _y) {x = _x; y = _y;}
    }

}
