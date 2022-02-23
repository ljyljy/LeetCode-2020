package Binary_Search.bin_Answer;

public class q69_sqrtx {
    public int mySqrt(int x) {
        int left = 0, right = x;
        // 错！！[L, mid] [mid+1, R]
        while (left < right) { // [L, mid-1] [mid, R]
            int mid = left + right + 1 >> 1;
            // 为避免溢出，勿写 "nums[mid] * nums[mid]"
            if (mid > x / mid) { // mid缩小，向左区间搜索
                right = mid-1;
            } else if (mid < x / mid) {// mid还可以提高
                left = mid;
            } else return mid; // mid * mid == x
        }
        return left;
    }


    public int mySqrt_WA(int x) {
        int start = 0, end = x;
        while (start < end) { // [L, mid] [mid+1, R]
            int mid = start + end >> 1;
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) { // mid还可以提高
                start = mid + 1;
            } else end = mid;
        }
        return start;
    }
}
