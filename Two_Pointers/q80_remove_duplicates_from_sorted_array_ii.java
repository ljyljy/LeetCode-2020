package Two_Pointers;

public class q80_remove_duplicates_from_sorted_array_ii {
    // 【常规 荐】法1：快慢指针 - 显式双指针
    public int removeDuplicates_v1(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n; // 特判
        int slow = 2, fast = 2;
        while (fast < n) { // 最多两个数相同
            // 如果nums[slow - 2] == nums[fast]，代表已经有两个数相等，此时nums[fast]
            // 对应的数值不能放进结果之中。反之，如果nums[slow - 2] != nums[fast]，
            // 那么nums[fast]可以放进nums[slow]中，并且slow++，记录结果的长度。
            if (nums[slow - 2] != nums[fast]) {
                nums[slow++] = nums[fast];
            } // 1 1 1 1 2 2 2 2
            fast++; // 无论如何，fast都要向前遍历各个元素
        }
        return slow; // 返回结果的长度，即slow
    }
    // 【要会】法2：抽象化 - 隐式双指针
    public int removeDuplicates_v2(int[] nums) {
        int i = 0; // 左指针slow - 初始化
        for (int num: nums) {
            if (i < 2 || nums[i-2] != num){ // i<2特判
                nums[i++] = num;// 相当于nums[slow++] = nums[fast];
            }
        }
        return i;  // 输出长度，即slow
    }
}
