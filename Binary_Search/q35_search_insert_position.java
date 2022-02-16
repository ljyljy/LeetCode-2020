package Binary_Search;

public class q35_search_insert_position {
    // https://leetcode-cn.com/problems/search-insert-position/solution/te-bie-hao-yong-de-er-fen-cha-fa-fa-mo-ban-python-/
    // 类比Q34
    // 法1：特判答案在右边界处
    public int searchInsert1(int[] nums, int target) {
        int n = nums.length;
        int start = 0, end = n-1;
        if (target > nums[end]) return n;
        // 在区间 nums[left..right] 里查找第 1 个大于等于 target 的元素的下标
        while (start < end) { // [L, mid], [mid+1, R]
            int mid = start + end >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid+1;
            } else { // 取等时，答案就是mid，在左区间（或在加if等，return mid）
                end = mid;
            }
        }
        return start;
    }

    // 法2：无需特判右边界 - 直接end初始化为n
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int start = 0, end = n;
        while (start < end) { // [L, mid], [mid+1, R]
            int mid = start + end >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid+1;
            } else { // 取等时，答案就是mid，在左区间（或在加if等，return mid）
                end = mid;
            }
        }
        return start;
    }
}
