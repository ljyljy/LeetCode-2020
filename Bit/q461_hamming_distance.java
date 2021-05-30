package Bit;

public class q461_hamming_distance {
    // 法1：O(n) - 时间复杂度：O(C * n)，C 固定为 32
    //空间复杂度：O(1)
    public int hammingDistance1(int x, int y) {
        int ans = 0;
        for (int i = 31; i >= 0; i--) {
            int bit1 = (x >> i) & 1, bit2 = (y >> i) & 1;
            if (bit1 != bit2) ans++;
        }
        return ans;
    }
    // 法2：O(logC) - 数(异或xor)中有几个1
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int ans = 0;
        while (xor != 0) {
            ans += xor & 1;
            xor >>= 1;
        }
        return ans;
    }
}
