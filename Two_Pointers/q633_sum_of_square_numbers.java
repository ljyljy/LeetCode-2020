package Two_Pointers;

public class q633_sum_of_square_numbers {
    // 法1：Math.sqrt -- O(sqrt(c))
    public boolean judgeSquareSum1(int c) {
        for (long a = 0; a*a <= c; a++) {
            double b = Math.sqrt(c - a*a);
            if (b == (int)b) return true;
        }
        return false;
    }

    // 法2：双指针  -- O(sqrt(c))
    public boolean judgeSquareSum(int c) {
        int a = 0, b = (int)Math.sqrt(c);
        while (a <= b) {
            int sum = a*a + b*b;
            if (sum == c) return true;
            else if (sum < c) a++;
            else b--;
        }
        return false;
    }
}
