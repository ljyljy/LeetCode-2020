package Bit;

public class q191_number_of_1_bits {
    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) { // ?����>0, n����Ϊ������
            n -= lowbit(n);
            cnt++;
        }
        return cnt;
    }

    private int lowbit(int x) {
        return x & (-x);
    }
}
