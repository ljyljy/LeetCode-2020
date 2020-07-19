package BinarySearch;

public class q153_find_minimum_in_rotated_sorted_array {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;
        int target = nums[nums.length - 1];
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                end = mid;//如果mid位置上的数字小于等于最右端的数字时，区间向左移动
            } else {
                start = mid;
            }
        }
        return Math.min(nums[start], nums[end]);  //最终返回start和end位置上较小的数字即可
    }
}
