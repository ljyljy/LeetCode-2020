package Array;

public class q41_first_missing_positive {
    // O(n)-原地哈希：类比qo_3、qo_53, q287
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) { // [3,4,-1,1]->[1, 2缺,3, 4]
            // 目标：下标i处存放的数值为i+1（将[0]处3挪到【3-1】, 即下标[0/i]<->【nums[0/i]-1】）, 从前向后遍历
            // 结果：每次遍历，将j=nums[i]挪到目标位置[j-1];
            //      最后整体遍历，i!=nums[i]-1处，即为缺失正数：i+1

            // int j = nums[i]-1; // 目标挪到位置 ↓必须是while，非if！
            while (nums[i]-1 >= 0 && nums[i]-1 < n
                    && nums[i] != nums[nums[i]-1]) {
                swap(nums, i, nums[i]-1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != nums[i]-1) {
                return i+1;
            }
        }
        return n+1; // 如[1,2,3,4], 缺5
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
