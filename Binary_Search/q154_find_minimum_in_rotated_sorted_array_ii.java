package Binary_Search;

public class q154_find_minimum_in_rotated_sorted_array_ii {
    // 法1：以nums[0]为target，找min（若有旋转，ans一定位于0右侧）
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int start = 0, end = n - 1;
        // ↓ 恢复“二段性” -- [2,2,3,4,5,   0(pivot),1,1,2,2,2]
        while (end > 0 && nums[start] == nums[end]) end--; // 退出后，保证end >= 0
        // case1：不存在旋转（本身升序）
        if (end == 0 || nums[start] <= nums[end])  return nums[start];
        // case2：有旋转，以nums[0] 或 nums[end-1]为“二段性”分界点
        int target = nums[0];
        return findPivot1(nums, target, end);
    }

    private int findPivot1(int[] nums, int target, int end) {
        // 例：[2,2,3,4,5,   0(pivot),0,0,1,1,1,1,1] // [l, mid],[mid+1, r]
        int start = 0;
        while (start < end) {
            int mid = start + end >> 1;
            if (nums[mid] >= target)
                start = mid + 1;// 相等时(pivot肯定在mid右侧 ∵有旋转)
            else end = mid; // 往左走, 逼近pivot
        }
        return nums[start];
    }


    // 法2：以nums[end]为target，找min（若有旋转，ans一定位于end左侧）
    public int findMin2(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int end = n-1;
        while (end > 0 && nums[end] == nums[0]) end--;
        if (nums[0] <= nums[end]) return nums[0];
        int target = nums[end]; // 以end为target
        return findPivot2(nums, 0, end, target);
    }

    private int findPivot2(int[] nums, int start, int end, int target) {
        while (start < end) { // [l, mid],[mid+1, r]
            int mid = start + end >> 1;
            if (nums[mid] <= target) {
                end = mid; // 相等，往左边找
            } else start = mid + 1;
        }
        return nums[start];
    }
}
