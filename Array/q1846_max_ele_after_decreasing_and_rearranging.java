package Array;

import java.util.Arrays;

public class q1846_max_ele_after_decreasing_and_rearranging {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            if (arr[i] - arr[i-1] > 1)
                arr[i] = arr[i-1] + 1;
        }
        return arr[n-1];
    }
}
