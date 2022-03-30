package DataStructure.Union_Find;

public class q261_graph_valid_tree {
    public boolean validTree(int n, int[][] edges) {
        if (n-1 != edges.length) return false; // 边数=节点数n-1=edges个数
        UnionFind uf = new UnionFind(n);
        for (int[] edge: edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.connCnt == 1;
    }

    // UF并查集，类比q547，q9_591，q261
    class UnionFind {
        int[] father;
        int connCnt;
        public UnionFind(int n) {
            connCnt = n;
            father = new int[n];
            for (int i = 0; i < n; i++){
                father[i] = i;
            }
        }

        public void union(int a, int b) {
            int rootA = findR(a), rootB = findR(b);
            if (rootA != rootB) {
                father[rootA] = rootB;
                connCnt--;
            }
        }

        public int findR(int x) {
            if (father[x] != x) {
                father[x] = findR(father[x]);
            }
            return father[x];
        }

        public boolean find(int a, int b) {
            return findR(a) == findR(b);
        }
    }
}
