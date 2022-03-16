package Binary_Search;

public class q162_findPeakElement {
//    数组的两端nums[-1] = nums[n] = -∞ 都是负无穷，因此数组无论是单调递增还是单调递减，又或者是成起伏状，数组中必定包含一个峰值。
    // if1
    public int findPeakElement1(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) { // start、end最小为0、2
            int mid = start + (end - start) / 2; // mid >= 1
//            if (nums[mid - 1] < nums[mid]) { // 上升序列, 右半段必有↓
//                start = mid; // 在右半段找peak
//            } else
            if (nums[mid] > nums[mid + 1]) { // 下降序列，左半段必有↑
                end = mid;// 在左半段找peak
            } else {
//                return mid; // 恰好为peak
                start = mid;
            }
        }
        if (nums[start] < nums[end]) return end; // 右半段必有↓
        else return start; // 左半段必有↑
    }

    // if2
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) { // start、end最小为0、2
            int mid = start + (end - start) / 2; // mid >= 1
            if (nums[mid - 1] < nums[mid]) { // 上升序列, 右半段必有↓
                start = mid; // 在右半段找peak
            } else {
                end = mid;
            }
        }
        if (nums[start] < nums[end]) return end; // 右半段必有↓
        else return start; // 左半段必有↑
    }

}
