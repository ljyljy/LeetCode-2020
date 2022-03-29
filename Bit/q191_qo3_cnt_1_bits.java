package Bit;

public class q191_qo3_cnt_1_bits {
    public int[] countBits(int n) {
        int[] res = new int[n+1];
        for (int i = 0; i <= n; i++) {
            res[i] = cntOne(i);
        }
        return res;
    }

    private int cntOne(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt += (n & 1); // ·¨2£ºx -=lowbit(x); cnt++;
            n >>= 1;
        }
        return cnt;
    }
}
