package DataStructure.Union_Find;

public class q685_redundant_connection_ii {
    // ��1�����鼯-�л�
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n);
        for (int[] edge: edges) {
            if (!uf.union(edge[0], edge[1])) {
                return new int[]{edge[0], edge[1]}; // ���أ����յ��³ɻ��ı�
            }
        }
        return new int[]{};
    }

    class UnionFind {
        int[] father;
        int connCnt; // ��ͨ��

        public UnionFind (int n) {
            father = new int[n+1]; // �Ķ�1�����ֵ��[1, n]
            for (int i = 1; i <= n; i++) {
                father[i] = i;
            }
            connCnt = n; // ��ʼ����ͨ�� = n��ȫ����ͨ��
        }

        private boolean union(int a, int b) {
            // �޸�2��unionΪbool�ͣ�����������ͬһ����ͨ�飬��unionʧ�ܣ���
            int rootA = findR(a), rootB = findR(b);
            if (rootA != rootB) {
                father[rootA] = rootB;
                connCnt--;
                return true;
            }
            return false; // ���ڵ���ͬ������true
        }

        private int findR(int x) {
            if (x != father[x]) {
                father[x] = findR(father[x]);
            }
            return father[x]; // ���ɷ���x��WA!
        }

        private boolean find(int a, int b) {
            return findR(a) == findR(b);
        }
    }
}
