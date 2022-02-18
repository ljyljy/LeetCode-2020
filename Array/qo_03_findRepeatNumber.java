package Array;

public class qo_03_findRepeatNumber {
    public int findRepeatNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 目标：下标i处存放的数值为i（将[0]处2挪到nums【2】, 即下标[0]<->【nums[2]】）, 从前向后遍历
            // 结果：每次遍历，将j=nums[i]挪到目标位置[j]
            while (nums[i] != i) { // [2,0,1,1]->[0,1,2,1重复]
                int j = nums[i]; // 将不匹配的j移动到nums[j]处，即↓
                swap(nums, i, j); // [1,0×,2√,1] -> [0√,1,2√,1]
                if (nums[i] == nums[j]) { // ∵j>=i, ∴前面挪过同样的数，重复了
                    return nums[j];
                }

            }
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

