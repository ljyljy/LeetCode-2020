package DataStructure.Union_Find;

public class q685_redundant_connection_ii {
    // 法1：并查集-有向图判环【有根树】
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        // 1) 统计入度
        int[] indegree = new int[n+1];
        for (int[] edge: edges) {
            int src = edge[0], dst = edge[1];
            indegree[dst]++;
        }
        // 2) 先删入度为 2 的边，看是否形成环
        for (int i = n-1; i >= 0; i--) { // 返回：最后出现(逆序遍历)
            int[] edge = edges[i];
            int src = edge[0], dst = edge[1];
            if (indegree[dst] == 2) {
                // 如果不构成环，这条边就是要去掉的那条边
                if (!judgeCircle(edges, n, i)) {
                    return edge;
                }
            }
        }

        // 3) 再删入度为 1 的边，看是否形成环
        for (int i = n-1; i >= 0; i--) { // 返回：最后出现(逆序遍历)
            int[] edge = edges[i];
            int src = edge[0], dst = edge[1];
            if (indegree[dst] == 1) {
                // 如果不构成环，这条边就是要去掉的那条边
                if (!judgeCircle(edges, n, i)) {
                    return edge;
                }
            }
        }

        return new int[]{};
    }

    private boolean judgeCircle(int[][] edges, int n, int edge2Rm) {
        UnionFind uf = new UnionFind(n+1); // 结点值∈[1,n] -> uf[n+1]即可
        for (int i = 0; i < n; i++) {
            if (i == edge2Rm) continue;
            int[] edge = edges[i];
            int v1 = edge[0], v2 = edge[1];
            if (!uf.union(v1, v2)) {
                // 合并失败，说明v1、v2在同一个连通块，构成环
                return true;
            }
        }
        return false;
    }

    class UnionFind {
        int[] father;
        int connCnt; // 连通块

        public UnionFind (int n) {
            // 不改动：结点值∈[1, n] -> new UF(n+1)即可
            father = new int[n];
            for (int i = 0; i < n; i++) {
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
            return false; // 根节点相同，返回false
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
}
