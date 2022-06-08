package Bit;

public class q432_isPowerOfFour {
    // 转化为q231, lowbit - O(1)
    public boolean isPowerOfFour(int n) {
        if (n <= 0) return false;
        int x = (int)Math.sqrt(n);
        return x * x == n && (x & -x) == x;
    }
}
