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
    // 【荐】解法3 - 【优化写次数】
    public void moveZeroes0(int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        while (i < n && j < n) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
            i++;
        }
        while (j < n) nums[j++] = 0;
    }

    public void moveZeroes(int[] nums) {
        int read = 0, write = 0, n = nums.length; // write处的num【不论是否为0】, 都是【待填充】下标
        while (read < n && write < n) {
            // ❤判断nums【read】是否为零，而非nums[write]
            if (nums[read] != 0) {
                nums[write++] = nums[read];
            }
            read++; // 合并：nums[read]不论是否为0，write都需++
        } // 退出后, 将[write, n)的元素全置0
        while (write < n) {
            nums[write++] = 0;
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)



