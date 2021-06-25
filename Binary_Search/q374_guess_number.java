package Binary_Search;

public class q374_guess_number {
    public int guessNumber(int n) {
        int start = 1, end = n;
        // [L, R-1(mid)] [R, end]
        while (start < end) {
            int mid = start + (end - start) / 2; // TLE:加法溢出 start + end >> 1;
            if (guess(mid) == 0) return mid;
            else if (guess(mid) == 1) // 右区间找
                start = mid + 1;
            else end = mid;
        }
        if (guess(start) == 0) return start;
        return -1;
    }

    // LeetCode实现
    private int pick = 6;
    private int guess(int num) {
        if (pick > num) return 1;
        else if (pick < num) return -1;
        else return 0; // pick == num
    }
}
