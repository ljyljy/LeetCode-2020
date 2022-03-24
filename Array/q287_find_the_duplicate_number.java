package Array;

public class q287_find_the_duplicate_number {
    // 时间O(n)，空间O(1)-原地哈希：类比q41、qo_3、qo_53, q287
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // i+1 = nums[i] -> i = nums[i]-1
            while (nums[i]-1 >= 0 && nums[i]-1 < n &&
                    nums[i] != nums[nums[i]-1]) {
                swap(nums, i, nums[i]-1);
            }
        }
        return nums[n-1]; // 如sorted: [1,2,3,4,2]
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
