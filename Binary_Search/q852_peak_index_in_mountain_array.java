package Binary_Search;

public class q852_peak_index_in_mountain_array {
    // 法1：二分 O(logn), 类比q852, 162
    public int peakIndexInMountainArray1(int[] arr) {
        int n = arr.length;
        int start = 0, end = n - 1, ans = 0;
        // [L, mid], [mid+1, end]
        while (start < end) {
            int mid = start + end >> 1;
            if (arr[mid] >= arr[mid + 1]) {
                ans = mid;
                end = mid; // 往左走看看有没有更大的mid
            } else { // [mid] < [mid+1], 则[L, mid]都不可能为峰顶
                start = mid + 1;
            }
        }
        return ans;
    }

    // 法2：三分 O(logn) -- 使用两个端点将区间分成三份，然后通过每次否决三分之一的区间来逼近目标值。
    //  [l, m1]、[m1, m2] 和 [m2, r] 三段
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int start = 0, end = n - 1;
        while (start < end) {
            int m1 = start + (end - start) / 3;
            int m2 = end - (end - start) / 3;
            if (arr[m1] > arr[m2]) { // 排除[m2, r]
                end = m2 - 1;
            } else { // 排除[l, m1]
                start = m1 + 1;
            }
        }
        return start;
    }
}
