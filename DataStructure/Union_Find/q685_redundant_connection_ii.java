package DataStructure.Union_Find;

public class q685_redundant_connection_ii {
    // ��1�����鼯-����ͼ�л����и�����
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        // 1) ͳ�����
        int[] indegree = new int[n+1];
        for (int[] edge: edges) {
            int src = edge[0], dst = edge[1];
            indegree[dst]++;
        }
        // 2) ��ɾ���Ϊ 2 �ıߣ����Ƿ��γɻ�
        for (int i = n-1; i >= 0; i--) { // ���أ�������(�������)
            int[] edge = edges[i];
            int src = edge[0], dst = edge[1];
            if (indegree[dst] == 2) {
                // ��������ɻ��������߾���Ҫȥ����������
                if (!judgeCircle(edges, n, i)) {
                    return edge;
                }
            }
        }

        // 3) ��ɾ���Ϊ 1 �ıߣ����Ƿ��γɻ�
        for (int i = n-1; i >= 0; i--) { // ���أ�������(�������)
            int[] edge = edges[i];
            int src = edge[0], dst = edge[1];
            if (indegree[dst] == 1) {
                // ��������ɻ��������߾���Ҫȥ����������
                if (!judgeCircle(edges, n, i)) {
                    return edge;
                }
            }
        }

        return new int[]{};
    }

    private boolean judgeCircle(int[][] edges, int n, int edge2Rm) {
        UnionFind uf = new UnionFind(n+1); // ���ֵ��[1,n] -> uf[n+1]����
        for (int i = 0; i < n; i++) {
            if (i == edge2Rm) continue;
            int[] edge = edges[i];
            int v1 = edge[0], v2 = edge[1];
            if (!uf.union(v1, v2)) {
                // �ϲ�ʧ�ܣ�˵��v1��v2��ͬһ����ͨ�飬���ɻ�
                return true;
            }
        }
        return false;
    }

    class UnionFind {
        int[] father;
        int connCnt; // ��ͨ��

        public UnionFind (int n) {
            // ���Ķ������ֵ��[1, n] -> new UF(n+1)����
            father = new int[n];
            for (int i = 0; i < n; i++) {
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
            return false; // ���ڵ���ͬ������false
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
