package DataStructure.Union_Find;

public class q9_590_Connecting_Graph_ii {
    private int[] father, cnt;
    public q9_590_Connecting_Graph_ii(int n) {
        father = new int[n+1];
        cnt = new int[n+1];
        for (int i = 1; i <= n; i++) {
            father[i] = i;
            cnt[i] = 1;
        }
    }

    public void connect(int a, int b) {
        int rootA = findR(a), rootB = findR(b);
        if (rootA != rootB) {
            father[rootA] = rootB;
            cnt[rootB] += cnt[rootA];
        }
    }

    public int query(int a) {
        return cnt[findR(a)];
    }

    private int findR(int x) {
        if (father[x] != x) { // Â·¾¶Ñ¹Ëõ£º<DSÖ®ÃÀ>p167
            father[x] = findR(father[x]);
        }
        return father[x];
    }
}
