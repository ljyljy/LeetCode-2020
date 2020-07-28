package BinarySearch;

public class q9_458_last_position_of_target {
    public int lastPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {// 可能存在多个位置的数值相同
                start = mid; // 因为要找最后一个, 故因继续向【后】搜索（与当前mid处值相等的位置）
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        // 这里也要注意！！！
        // 当退出while后，也要优先判断end！
        if (nums[end] == target) return end;
        if (nums[start] == target) return start;
        return -1;
    }
}