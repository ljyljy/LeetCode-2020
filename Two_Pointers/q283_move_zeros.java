package Two_Pointers;//Given an array nums, write a function to move all 0's to the end of it while m
//aintaining the relative order of the non-zero elements.
//
// Example:
//
//
//Input: [0,1,0,3,12]
//Output: [1,3,12,0,0]
//
// Note:
//
//
// You must do this in-place without making a copy of the array.
// Minimize the total number of operations.
// Related Topics 数组 双指针

// 解法：
// 1.loop, count zeros
// 2.开新数组
// 3.index（在原数组上操作） ❤ 最快


//leetcode submit region begin(Prohibit modification and deletion)
public class q283_move_zeros {
    // 解法3
    public void moveZeroes(int[] nums) {
        int j = 0; // j处的num为0, 记录待填充的非零数的下标
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) { // 将i处的num赋值给j处
                nums[j] = nums[i];
                if (i != j) {
                    nums[i] = 0; // 将原来i处填补上0
                }
                j++; // 待填充的非零数的下标++
            }
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)



