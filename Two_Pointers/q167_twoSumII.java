package Two_Pointers;

public class q167_twoSumII {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n-1;
        while (left < right) {
            int curSum = nums[left] + nums[right];
            if (curSum == target) return new int[]{left+1, right+1};
            else if (curSum < target) left++;
            else right--;
        }
        return new int[]{-1, -1};
    }
}
