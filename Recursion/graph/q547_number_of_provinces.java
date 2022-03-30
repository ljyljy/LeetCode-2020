package Recursion.graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class q547_number_of_provinces {
    // 法1：DFS
    int n;
    int[][] isConnected;
    public int findCircleNum_DFS(int[][] isConnected) {
        this.n = isConnected.length;
        this.isConnected = isConnected;
        boolean[] visited = new boolean[n];
        int connCnt = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                connCnt++;// 若当前顶点 i 未被访问，说明又是一个新的连通域，则遍历新的连通域且cnt+=1.
                dfs(i, visited);
            }
        }
        return connCnt;
    }

    private void dfs(int i, boolean[] visited) {
        visited[i] = true;
        for (int j = 0; j < n; j++) {
            // 继续遍历与顶点 i 相邻的顶点（使用 visited 数组防止重复访问）
            if (isConnected[i][j] == 1 && !visited[j]) {
                dfs(j, visited);
            }
        }
    }

    // 法2：BFS
    public int findCircleNum_BFS(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int connCnt = 0;
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                connCnt++;
                queue.offer(i);
                visited[i] = true;

                while (!queue.isEmpty()) {
//                    int size = queue.size();
//                    for (int k = 0; k < size; k++) {
                        int cur = queue.poll();
                        for (int nxt = 0; nxt < n; nxt++) {
                            if (isConnected[cur][nxt] == 1 && !visited[nxt]) {
                                queue.offer(nxt);
                                visited[nxt] = true;
                            }
                        }
//                    }
                }
            }
        }
        return connCnt;
    }

    // 法3：UF并查集，类比q9_591
    public int findCircleNum(int[][] isConnected) {
        n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.connCnt;
    }

    class UnionFind {
        int[] father;
        int connCnt;

        public UnionFind (int n) {
            connCnt = n; // 初始化连通块 = n（全不连通）
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }

        public void union(int a, int b) {
            int rootA = findR(a), rootB = findR(b);
            if (rootA != rootB) { // 【勿漏！】
                father[rootA] = rootB;
                connCnt--;
            }
        }

        public boolean find(int a, int b) {
            return findR(a) == findR(b);
        }

        private int findR(int x) {
            if (father[x] != x) {
                father[x] = findR(father[x]);
            }
            return father[x];
        }
    }

}
