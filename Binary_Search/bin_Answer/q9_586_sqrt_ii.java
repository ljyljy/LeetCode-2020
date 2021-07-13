package Binary_Search.bin_Answer;

public class q9_586_sqrt_ii {
    public double sqrt(double x) {
        double left = 0.0, right = x, eps = 1e-12;
        if (right < 1) right = 1; // ❤ 如0.1 -> 0.31622777

        // while (left + 1 < right)的变体↓ 且无需最后比较左右边界情况(∵eps极小)
        while (left + eps < right) { // [L, mid-1] [mid, R]
            double mid = left + (right - left) / 2.0;
            if (mid > x / mid) {
                right = mid;
            } else if (mid < x / mid) {
                left = mid;
            } else return mid; // ==
        }
        return left;
    }
}
