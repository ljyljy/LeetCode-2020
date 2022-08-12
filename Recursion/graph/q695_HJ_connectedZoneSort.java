package Recursion.graph;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 1、求等于0的连通块
 * 2、按照连通块的大小，进行升序输出
 * 【类比q130， 200】
 * **/
public class q695_HJ_connectedZoneSort {
    private static int m = 5, n = 4;
    private static int[][] graph;
    private static boolean[][] visited;
    private static List<Integer> connCnts = new ArrayList<>();
    private static int[] _x = {0, 1, 0, -1, 1, 1, -1, -1}; // 上下左右，对角线 共8个方向
    private static int[] _y = {1, 0, -1, 0, 1, -1, 1, -1};

    public static void main(String[] args) {
        graph = new int[m][n];
        visited = new boolean[m][n];

        System.out.println("init 0-1 graph: ");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = new Random().nextInt(2);
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

        // 法1：dfs 查找连通块，必须对graph做原地修改，visited无用！【类比q130】
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 0 && !visited[i][j]) {
//                    visited[i][j] = true;
                    connCnts.add(dfs(i, j));
//                    visited[i][j] = false;
                }
            }
        }

        // 法2：并查集（略），参考q130

        System.out.println("find 0-zone, sort:");
        Collections.sort(connCnts);
        for (int cnt: connCnts) {
            System.out.print(cnt + " ");
        }

    }

    private static int dfs(int i, int j) {
        int cnt = 1;
        //        ↓ 必须对graph做原地修改，visited无用（因为之前回溯后置F，实则不可！）【类比q130】
        graph[i][j] = -1; // DFS的时候一定要将她改掉！不然会影响判断！可以不加visited，改掉以后自然就visited了
        for (int dir= 0; dir < 4; dir++) {
            int newX = i + _x[dir], newY = j + _y[dir];
            if (!check(newX, newY)) continue;
            if (visited[newX][newY]) continue;
//            visited[newX][newY] = true; // 可以不加，直接graph改为-1
            cnt += dfs(newX, newY);
//            visited[newX][newY] = false; // WA! 这里不可以置false！类比q130,200
        }


        return cnt;
    }

    private static boolean check(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n && graph[x][y] == 0;
    }


}
