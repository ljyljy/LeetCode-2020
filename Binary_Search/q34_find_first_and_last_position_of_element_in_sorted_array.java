package Binary_Search;

public class q34_find_first_and_last_position_of_element_in_sorted_array {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) return res;
        res[0] = searchFirst(nums, target);
        res[1] = searchLast(nums, target);
        return res;
    }

    private int searchFirst(int[] nums, int target) {
        int start = 0, end = nums.length-1;
        while (start < end) { // [L, mid] [mid+1, R]
            int mid = start + end >> 1;
            if (target <= nums[mid]) {
                end = mid;
            } else{
                start = mid+1;
            }
        }
        return nums[start] == target? start: -1;
    }

    private int searchLast(int[] nums, int target) {
        int start = 0, end = nums.length-1;
        while (start < end) { // [L, mid-1] [mid, R]
            int mid = start + end + 1 >> 1;
            if (target < nums[mid]) {
                end = mid-1;
            } else{ // >=
                start = mid;
            }
        }
        return nums[start] == target? start: -1;
    }
}
