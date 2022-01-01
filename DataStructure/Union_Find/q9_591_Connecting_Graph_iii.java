package DataStructure.Union_Find;

public class q9_591_Connecting_Graph_iii {
    private int[] father;
    private int connCnt;
    public q9_591_Connecting_Graph_iii(int n) {
        father = new int[n+1];
        connCnt = n;
        for (int i = 0; i <= n; i++)
            father[i] = i;

    }

    public void connect(int a, int b) {
        int rootA = findR(a), rootB = findR(b);
        if (rootA != rootB) {
            father[rootA] = rootB; // 必须是rootA/B，而非a/b!!!
            connCnt--;
        }
    }

    public int query() {
        return connCnt;
    }

    private int findR(int x) {
        if (father[x] != x) {
            father[x] = findR(father[x]);
        }
        return father[x];
    }
}
