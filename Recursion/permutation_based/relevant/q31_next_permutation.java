package Recursion.permutation_based.relevant;

import java.util.Arrays;

public class q31_next_permutation {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        // 从后往前找到第一个相邻升序对
        // [1,2,3,8,(5[i-1/拐点], 7[i]),6,4]
        for (int i = n-1; i-1 >= 0; i--) {
            if (nums[i] > nums[i-1]) { // 找到拐点【i-1】
                // 原地修改下一个排列：[6, 457]
                Arrays.sort(nums, i, n); // 拐点后[i,end) 升序-> [4,6,7]
                for (int j = i; j < n; j++) { // 从后面j=[i,end)找比拐点大的最小数，与拐点替换
                    if (nums[j] > nums[i-1]) {
                        swap(nums, i-1, j);
                        return;
                    }
                }
            }
        }
        // 补充：如果nums本身降序，那么下一个排列为重新开始循环（升序排列）。
        Arrays.sort(nums);
        return;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
