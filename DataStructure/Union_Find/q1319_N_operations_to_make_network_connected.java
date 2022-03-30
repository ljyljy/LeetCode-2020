package DataStructure.Union_Find;

import java.util.*;

public class q1319_N_operations_to_make_network_connected {
    // ��1��dfs
    Map<Integer, List<Integer>> connMap = new HashMap<>();// ��ӱ�
    boolean[] visited;
    public int makeConnected_DFS(int n, int[][] connections) {
        if (n-1 > connections.length) return -1; // ����=�ڵ���n-1 <= edges����(�ɲ�����)

        // ������ӱ�
        for (int i = 0; i < n; i++) connMap.put(i, new ArrayList<>());
        for (int[] conn: connections) {
            int node0 = conn[0], node1 = conn[1];
            connMap.get(node0).add(node1);
            connMap.get(node1).add(node0);
        }
        // dfs������ͨ��
        int connCnt = 0;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
                connCnt++;
            }
        }
        return connCnt - 1; // '����'������ = ��ͨ���� - 1
    }

    private void dfs(int i) {
        visited[i] = true;
        for (int j: connMap.get(i)) {
            if (!visited[j])
                dfs(j);
        }
    }

    // ��2�����鼯���Ƽ���
    public int makeConnected(int n, int[][] connections) {
        if (n-1 > connections.length) return -1;  // ����=�ڵ���n-1 <= edges����(�ɲ�����)
        UnionFind uf = new UnionFind(n);
        for (int[] conn: connections) {
            uf.union(conn[0], conn[1]);
        }
        return uf.connCnt - 1;
    }

    class UnionFind {
        int[] father;
        int connCnt;

        public UnionFind (int n) {
            connCnt = n; // ��ʼ����ͨ�� = n��ȫ����ͨ��
            father = new int[n];
            for (int i = 0; i < n; i++) {
                father[i] = i;
            }
        }

        public void union(int a, int b) {
            int rootA = findR(a), rootB = findR(b);
            if (rootA != rootB) { // ����©����
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
