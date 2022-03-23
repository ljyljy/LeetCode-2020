package Bit;

public class q191_number_of_1_bits {
    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) { // ?而非>0, n可以为负数！
            n -= lowbit(n);
            cnt++;
        }
        return cnt;
    }

    private int lowbit(int x) {
        return x & (-x);
    }
}
