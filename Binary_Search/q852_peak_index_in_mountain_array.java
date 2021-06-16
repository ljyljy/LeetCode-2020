package Binary_Search;

public class q852_peak_index_in_mountain_array {
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int start = 0, end = n-1, ans = 0;
        // [L, R-1(mid)]  [R, end]
        while (start < end) {
            int mid = start + end >> 1;
            if (arr[mid] >= arr[mid+1]) {
                ans = mid;
                end = mid; // 往左走看看有没有更大的mid
            } else {
                start = mid+1;
            }
        }
        return ans;
    }
}
