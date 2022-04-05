package DataStructure.Union_Find;

public class q685_redundant_connection_ii {
    // 法1：并查集-判环
    public int[] findRedundantConnection(int[][] edges) {
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
}
