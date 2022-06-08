package Bit;

public class q231_power_of_two {
    // 法1(推荐) -- lowbit -- O(1)
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & -n) == n; // 或 && (n > 0)
    }

    // 法3 -- O(1)
    public boolean isPowerOfTwo3(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // 法2 -- O(logN)
    public boolean isPowerOfTwo2(int n) {
        if (n <= 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }
}
