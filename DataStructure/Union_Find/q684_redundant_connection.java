package DataStructure.Union_Find;

import java.util.*;

public class q684_redundant_connection {
    // ��1�����鼯-�л�
    public int[] findRedundantConnection_UF(int[][] edges) {
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

    // ��2��BFS-�������� -������ͼ���л�
    // ������ɾ����=1�ĵ㣬�����ʣ���!=1�����л�������ͬ������ͼ��ɾ�����=0�ĵ㡿
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        List<List<Integer>> graph = new ArrayList<>(); // �ڽӱ�
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        boolean[][] isConn= new boolean[n][n];
        int[] degree = new int[n];

        for (int[] edge: edges) { // ����ͼ����<�ڽӱ��Ƿ�ֱ������[]>
            int v1 = edge[0]-1, v2 = edge[1]-1; // ���ֵ-1��[0,n)
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
            isConn[v1][v2] = true;
            isConn[v2][v1] = true;
            degree[v1]++;
            degree[v2]++;
        }

        // BFS - ��������
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {// ����ͼ����Ϊ1����Ϊ���
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
                if (degree[nxtV] == 1) queue.offer(nxtV); // ����ͼ����Ϊ1����Ϊ���
            }
        }

        for (int i = n-1; i >= 0; i--) {
            int[] edge = edges[i]; // �������
            int v1 = edge[0]-1, v2 = edge[1]-1; // ���ֵ-1��[0,n)
            // System.out.println(v1 + ", " + v2);
            if (isConn[v1][v2]) {
                return new int[]{v1+1, v2+1};// �ָ�Ϊԭʼ���ֵ��+1��
            }
        }
        return new int[]{};
    }
}
