package Binary_Search;

public class q33_search_in_rotated_sorted_array {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[start] < nums[mid]) { // 左半段【升序->可减治】
                if (nums[start] <= target && target <= nums[mid])
                    end = mid; // 在前半段搜
                else start = mid; // target处于非(旋转)排序段
            } else { // 右半段【升序->可减治】
                if (nums[mid] <= target && target <= nums[end])
                    start = mid; // 在后半段搜
                else end = mid; // target处于非(旋转)排序段
            }
        }
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }
}
