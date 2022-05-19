package DataStructure.Union_Find;

// ���q399, q990
public class q990_satisfiability_of_equality_equations {

    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind(26);
        // 1. ������ȵ���ĸ�γ���ͨ����
        for (String eq: equations) {
            if (eq.charAt(1) == '=') { // 'x==y'
                char x = eq.charAt(0), y = eq.charAt(3);
                uf.union(x-'a', y-'a'); // ��ĸתΪint(0-25)
            }
        }
        // 2. �ټ�鲻��ʽ��ϵ�Ƿ����
        for (String eq: equations) {
            if (eq.charAt(1) == '!') { // 'x!=y'
                char x = eq.charAt(0), y = eq.charAt(3);
                if (uf.connected(x-'a', y-'a')) {// ����ͨ���򲻷���'!='
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
            if (p != parent[p]) { // ��if�������˵ݹ飬��������while��
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
