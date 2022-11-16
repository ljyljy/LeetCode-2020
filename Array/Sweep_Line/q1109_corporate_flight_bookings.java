package Array.Sweep_Line;

public class q1109_corporate_flight_bookings {
    // 法1：差分, O(n)
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n + 1];
        for (int i = 0; i < bookings.length; i++) {
            int lf = bookings[i][0] - 1, rt = bookings[i][1] - 1;
            int cnt = bookings[i][2];
            diff[lf] += cnt;
            diff[rt + 1] -= cnt;
        }
        int[] res = new int[n];
        res[0] = diff[0];
        for (int i = 1; i < n; i++) {
            res[i] = diff[i] + res[i - 1];
        }
        return res;
    }

    // 法0：暴力
    public int[] corpFlightBookings_BF(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int[] booking : bookings) {
            int first = booking[0], last = booking[1];
            int seats = booking[2];
            for (int i = first - 1; i < last; i++) { // idx: No.-1
                res[i] += seats;
            }
        }
        return res;
    }

    // 法1：线段树（todo）
    // https://leetcode.cn/problems/corporate-flight-bookings/solution/gong-shui-san-xie-yi-ti-shuang-jie-chai-fm1ef/
    class Node {
        int l, r, v, add;

        Node(int _l, int _r) {
            l = _l;
            r = _r;
        }
    }

    int N = 20009;
    Node[] tr = new Node[N * 4];

    void pushup(int u) {
        tr[u].v = tr[u << 1].v + tr[u << 1 | 1].v;
    }

    void pushdown(int u) {
        int add = tr[u].add;
        tr[u << 1].v += add;
        tr[u << 1].add += add;
        tr[u << 1 | 1].v += add;
        tr[u << 1 | 1].add += add;
        tr[u].add = 0;
    }

    void build(int u, int l, int r) {
        tr[u] = new Node(l, r);
        if (l != r) {
            int mid = l + r >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
        }
    }

    void update(int u, int l, int r, int v) {
        if (l <= tr[u].l && tr[u].r <= r) {
            tr[u].v += v;
            tr[u].add += v;
        } else {
            pushdown(u);
            int mid = tr[u].l + tr[u].r >> 1;
            if (l <= mid) update(u << 1, l, r, v);
            if (r > mid) update(u << 1 | 1, l, r, v);
            pushup(u);
        }
    }

    int query(int u, int l, int r) {
        if (l <= tr[u].l && tr[u].r <= r) {
            return tr[u].v;
        } else {
            pushdown(u);
            int mid = tr[u].l + tr[u].r >> 1;
            int ans = 0;
            if (l <= mid) ans += query(u << 1, l, r);
            if (r > mid) ans += query(u << 1 | 1, l, r);
            return ans;
        }
    }

    public int[] corpFlightBookings_SegmentTree(int[][] bs, int n) {
        build(1, 1, n);
        for (int[] bo : bs) {
            update(1, bo[0], bo[1], bo[2]);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = query(1, i + 1, i + 1);
        }
        return ans;
    }
}
