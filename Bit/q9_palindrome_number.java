package Bit;

public class q9_palindrome_number {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        long res = 0; // 整数反转后可能溢出Integer.MAX_VALUE（2^31-1）
        int origin = x;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return origin == res;
    }
}
