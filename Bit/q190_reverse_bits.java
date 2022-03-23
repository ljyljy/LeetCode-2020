package Bit;

public class q190_reverse_bits {
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) { // ? 32´Î
            res = (res << 1) + (n & 1); // ? ÒÆÎ»¼ÓÀ¨ºÅ
            n >>= 1;
            // res = res * 2 + n % 2;
            // n /= 2;
        }
        return res;
    }
}
