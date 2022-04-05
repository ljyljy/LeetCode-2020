package DataStructure.Union_Find;

import java.util.*;

public class q684_redundant_connection {
    // 法1：并查集-判环
    public int[] findRedundantConnection_UF(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n);
        for (int[] edge: edges) {
            if (!uf.union(edge[0], edge[1])) {
                return new int[]{edge[0], edge[1]}; // 返回：最终导致成环的边
            }
        }
        return new int[]{};
    }

    class UnionFind {
        int[] father;
        int connCnt; // 连通块

        public UnionFind (int n) {
            father = new int[n+1]; // 改动1：结点值∈[1, n]
            for (int i = 1; i <= n; i++) {
                father[i] = i;
            }
            connCnt = n; // 初始化连通块 = n（全不连通）
        }

        private boolean union(int a, int b) {
            // 修改2：union为bool型！（若本就在同一个连通块，则union失败！）
            int rootA = findR(a), rootB = findR(b);
            if (rootA != rootB) {
                father[rootA] = rootB;
                connCnt--;
                return true;
            }
            return false; // 根节点相同，返回true
        }

        private int findR(int x) {
            if (x != father[x]) {
                father[x] = findR(father[x]);
            }
            return father[x]; // 不可返回x，WA!
        }

        private boolean find(int a, int b) {
            return findR(a) == findR(b);
        }
    }

    // 法2：BFS-拓扑排序 -【无向图】判环
    // 【持续删除度=1的点，最后若剩余度!=1，则有环】【不同于有向图，删除入度=0的点】
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        List<List<Integer>> graph = new ArrayList<>(); // 邻接表
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        boolean[][] isConn= new boolean[n][n];
        int[] degree = new int[n];

        for (int[] edge: edges) { // 无向图构建<邻接表、是否直连、度[]>
            int v1 = edge[0]-1, v2 = edge[1]-1; // 结点值-1∈[0,n)
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
            isConn[v1][v2] = true;
            isConn[v2][v1] = true;
            degree[v1]++;
            degree[v2]++;
        }

        // BFS - 拓扑排序
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {// 无向图，度为1，即为起点
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int curV = queue.poll();
            for (int nxtV: graph.get(curV)) {
                degree[curV]--;
                degree[nxtV]--;
                isConn[curV][nxtV] = false;
                isConn[nxtV][curV] = false;
                if (degree[nxtV] == 1) queue.offer(nxtV); // 无向图，度为1，即为起点
            }
        }

        for (int i = n-1; i >= 0; i--) {
            int[] edge = edges[i]; // 倒序查找
            int v1 = edge[0]-1, v2 = edge[1]-1; // 结点值-1∈[0,n)
            // System.out.println(v1 + ", " + v2);
            if (isConn[v1][v2]) {
                return new int[]{v1+1, v2+1};// 恢复为原始结点值（+1）
            }
        }
        return new int[]{};
    }
}
