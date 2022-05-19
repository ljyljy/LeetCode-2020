package DataStructure.Union_Find;

// 类比q399, q990
public class q990_satisfiability_of_equality_equations {

    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind(26);
        // 1. 先让相等的字母形成连通分量
        for (String eq: equations) {
            if (eq.charAt(1) == '=') { // 'x==y'
                char x = eq.charAt(0), y = eq.charAt(3);
                uf.union(x-'a', y-'a'); // 字母转为int(0-25)
            }
        }
        // 2. 再检查不等式关系是否打破
        for (String eq: equations) {
            if (eq.charAt(1) == '!') { // 'x!=y'
                char x = eq.charAt(0), y = eq.charAt(3);
                if (uf.connected(x-'a', y-'a')) {// 若连通，则不符合'!='
                    return false;
                }
            }
        }
        return true;
    }

    class UnionFind {
        private int[] parent;
        public int connCnt;

        public UnionFind(int n) {
            connCnt = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int a, int b) {
            int rootA = find(a), rootB = find(b);
            if (rootA != rootB) {
                parent[rootB] = rootA;
                connCnt--;
            }
        }

        public int find(int p) {
            if (p != parent[p]) { // 是if！借助了递归，不可再用while！
                parent[p] = find(parent[p]);
            }
            return parent[p];
        }

        public boolean connected(int a, int b) {
            int rootA = find(a), rootB = find(b);
            return rootA == rootB;
        }
    }
}
