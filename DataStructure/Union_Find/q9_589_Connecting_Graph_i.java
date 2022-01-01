package DataStructure.Union_Find;

public class q9_589_Connecting_Graph_i {
    private int[] father;
    public q9_589_Connecting_Graph_i(int n) {
        father = new int[n+1];
        for (int i = 0; i <= n; i++)
            father[i] = i; // 下标1开始！
    }

    public void connect(int a, int b) {
        int rootA = findR(a), rootB = findR(b);
        if (rootA != rootB) // TODO: 优化—按秩合并
            father[rootA] = rootB;

    }

    public boolean query(int a, int b) {
        return findR(a) == findR(b);
    }

    private int findR(int x) { // 路径压缩：图示《DS之美》p167
        if (father[x] != x)
            father[x] = findR(father[x]);
        return father[x];
    }
}
