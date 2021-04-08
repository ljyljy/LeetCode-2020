package Binary_Search;

public class q153_find_minimum_in_rotated_sorted_array {
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int target = nums[n-1]; // 以nums[0] 或 nums[-1]为“二段性”分界点
        return findPivot2(nums, target);
    }

    private int findPivot(int[] nums, int target) {
        // 例：[4,5,6,7,8,9, 0(pivot),1,2,3]
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + end >> 1;
            if (nums[mid] <= target)
                end = mid; // mid==target(pivot肯定在mid左侧)
            else //  if (nums[mid] > target)
                start = mid;// 找右边
        }
        return Math.min(nums[start], nums[end]);
    }

    private int findPivot2(int[] nums, int target) {
        // 例：[4,5,6,7,8,9, 0(pivot),1,2,3] // [l, mid],[mid+1, r]
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + end >> 1;
            if (nums[mid] <= target)
                end = mid; // mid==target(pivot肯定在mid左侧)
            else //  if (nums[mid] > target)
                start = mid + 1;// 找右边
        }
        return nums[start];
    }
}
