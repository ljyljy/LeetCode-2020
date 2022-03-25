package Recursion.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class HJ43_maze {
    static int[] _row = {0, 1, 0, -1};
    static int[] _col = {1, 0, -1, 0};
    static Deque<Loc> path = new ArrayDeque<>();
    static Deque<Loc> bestPath = new ArrayDeque<>();
    static boolean[][] visited;
    static int[][] maze;
    static int m, n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            m = sc.nextInt(); n = sc.nextInt(); // m行，n列
            maze = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    maze[i][j] = sc.nextInt();
                }
            }

            visited = new boolean[m][n];
            dfs(0, 0);

            for (Loc loc: bestPath) {
                System.out.println(loc);
            }
        }
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;
        Loc curLoc = new Loc(x, y);
        path.addLast(curLoc);

        if (x == m-1 && y == n-1) { // 保证唯一解
            if (bestPath.isEmpty() || path.size() < bestPath.size())
                bestPath = new ArrayDeque<>(path);
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int newX = x + _row[dir], newY = y + _col[dir];
            Loc newLoc = new Loc(newX, newY);
            if (check(newLoc) && !visited[newX][newY])
                dfs(newX, newY);
        }

        visited[x][y] = false;
        path.removeLast();
    }

    private static boolean check(Loc loc) {
        return 0 <= loc.x && loc.x < m && 0 <= loc.y && loc.y < n
                && maze[loc.x][loc.y] != 1;
    }

    static class Loc {
        int x, y;
        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x +"," + y +")";
        }
    }
}
