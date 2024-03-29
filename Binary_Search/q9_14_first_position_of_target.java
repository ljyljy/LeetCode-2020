package Binary_Search;

public class q9_14_first_position_of_target {
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) { // 可能有多个位置
                end = mid;// 为了找到first(需要向前查找)
                // return mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else{
                end = mid;
            }
        }

        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }
}