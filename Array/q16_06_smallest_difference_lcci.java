package Array;

import java.util.Arrays;

public class q16_06_smallest_difference_lcci {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a); Arrays.sort(b);
        int n1 = a.length, n2 = b.length;
        int i = 0, j = 0;
        long minDiff = Long.MAX_VALUE; // 整数-inf-inf会溢出！
        while (i < n1 && j < n2) {
            if (a[i] == b[j]) {
                return 0;
            }
            minDiff = Math.min(Math.abs(minDiff), Math.abs(a[i] - b[j]));
            if (a[i] > b[j]) {
                j++;
            } else {
                i++;
            }
        }
        return (int)minDiff;
    }
}
