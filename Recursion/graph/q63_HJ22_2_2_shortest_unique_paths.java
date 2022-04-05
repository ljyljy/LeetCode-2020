package Recursion.graph;

import java.util.*;

/**
 * 5 5
 * 0 1
 * 3 3
 * 1
 * 2 2
 */

// ¿‡±»q1293£¨q63
public class q63_HJ22_2_2_shortest_unique_paths {
    private static final int[] _x = {0, 1, 0, -1};
    private static final int[] _y = {-1, 0, 1, 0};
    private static int m, n;
    private static Deque<Loc> path = new ArrayDeque<>();
//    private static List<List<Loc>> res = new ArrayList<>();
    private static TreeMap<Integer, Integer> res = new TreeMap<>(); // <pathLen, pathCnt>
    private static Loc start, end;
    private static int pathCnt, minLen = Integer.MAX_VALUE;
    private static Map<Loc, Integer> memo = new HashMap<>(); // <Loc, minLen>
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt(); n = sc.nextInt();
        int startX = sc.nextInt(), startY = sc.nextInt();
        int endX = sc.nextInt(), endY = sc.nextInt();
        start = new Loc(startX, startY);
        end = new Loc(endX, endY);
        int blockCnt = sc.nextInt();

        List<Loc> blocks = new ArrayList<>();
        for (int i = 0; i < blockCnt; i++) {
            int x = sc.nextInt(), y = sc.nextInt();
            blocks.add(new Loc(x, y));
        }
        boolean[][] visited = new boolean[m][n];
        dfs(start, blocks, memo, visited);

//        int pathCnt = 0;
//        for (List<Loc> pathi: res) {
//            if (minLen > pathi.size()) {
//                minLen = pathi.size();
//            }
//        }
//        for (List<Loc> pathi: res) {
//            if (minLen == pathi.size()) {
//                pathCnt++;
//            }
//        }
        minLen = res.firstKey();
        pathCnt = res.get(minLen);
        System.out.println(pathCnt + " " + minLen);
    }

    private static void dfs(Loc loc, List<Loc> blocks, Map<Loc, Integer> memo, boolean[][] visited) {
        if (!isValid(loc)) return;
        if (isEqual(loc, end)) {
//            res.add(new ArrayList<>(path));
            res.put(path.size(), res.getOrDefault(path.size(), 0) + 1);
            return;
        }

        visited[loc.x][loc.y] = true;
        for (int dir = 0; dir < 4; dir++) {
            int newX = loc.x + _x[dir], newY = loc.y + _y[dir];
            Loc newLoc = new Loc(newX, newY);
            if (blocks.contains(newLoc)) continue;
            if (isValid(newLoc) && !visited[newX][newY]) {
                visited[newLoc.x][newLoc.y] = true;
                path.addLast(newLoc);
                dfs(newLoc, blocks, memo, visited);
                path.removeLast();
                visited[newLoc.x][newLoc.y] = false;
            }
        }
    }

    private static boolean isValid(Loc loc) {
        int x = loc.x, y = loc.y;
        return 0 <= x && x < m && 0 <= y && y < n;
    }

    public static boolean isEqual(Loc loc1, Loc loc2) {
        return loc1.x == loc2.x && loc1.y == loc2.y;
    }

    static class Loc {
        public int x, y;

        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Loc)) return false;
            Loc loc = (Loc) o;
            return x == loc.x && y == loc.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
