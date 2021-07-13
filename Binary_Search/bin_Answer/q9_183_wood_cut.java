package Binary_Search.bin_Answer;

import java.util.Arrays;

public class q9_183_wood_cut {
    private int[] L;
    private int k, n;
    public int woodCut(int[] L, int k) {
        if (L == null || L.length == 0) return 0;

        this.L = L; this.k = k; this.n = L.length;
        int left = 0, right = Arrays.stream(L).max().getAsInt();
        // ↓该模板优点：1,边界清晰 2,可以合并其他模板的功能(如:优先输出左边界etc.)
        while (left + 1 < right) { // [L, mid-1(ret左边界❤)] [mid, R]
            int mid = left + (right - left) / 2; // 溢出！left + right >> 1;
            if (check(mid)) { // 说明可以切>=k段，mid可增大(右区间)
                left = mid;
            } else {
                right = mid;
            }
        }
        // 1. ∵最大长度 ∴优先右区间 2.避免check(left==0):除数为0
        if (check(right)) return right;
        return left;

        // // WA!!! --  (right - left + 1 )溢出！
        // // TEST CASE -- [2147483644,2147483645,2147483646,2147483647], 4
        // while (left < right) { // [L, mid-1(ret左边界❤)] [mid, R]
        //     int mid = left + (right - left + 1 ) / 2;
        //     if (check(mid)) { // 说明可以切>=k段，mid可增大(右区间)
        //         left = mid;
        //     } else {
        //         right = mid - 1;
        //     }
        // }
        // return left;
    }

    // 也可以返回cnt
    private boolean check(int tryLen) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += L[i] / tryLen;
            if (cnt >= k) return true;
        }
        return false;
    }
}
