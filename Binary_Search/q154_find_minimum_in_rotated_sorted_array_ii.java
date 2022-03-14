package Binary_Search;

public class q154_find_minimum_in_rotated_sorted_array_ii {
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int start = 0, end = n - 1;
        // ↓ 恢复“二段性” -- [2,2,3,4,5,   0(pivot),1,1,2,2,2]
        while (end > 0 && nums[start] == nums[end]) end--; // 退出后，保证end >= 0
        // case1：不存在旋转（本身升序）
        if (end == 0 || nums[start] <= nums[end])  return nums[start];
        // case2：有旋转，以nums[0] 或 nums[end-1]为“二段性”分界点
        int target = nums[0]; // 但为了避免end-1<0，故保守选nums[0]
        return findPivot2(nums, target, end);
    }

    private int findPivot2(int[] nums, int target, int end) {
        // 例：[2,2,3,4,5,   0(pivot),0,0,1,1,1,1,1] // [l, mid],[mid+1, r]
        int start = 0;
        while (start < end) {
            int mid = start + end >> 1;
            if (nums[mid] >= target)
                start = mid + 1;// 相等时(pivot肯定在mid右侧)
            else end = mid; // 往左走, 逼近pivot
        }
        return nums[start];
    }
}
