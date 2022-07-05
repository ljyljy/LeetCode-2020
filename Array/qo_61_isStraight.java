package Array;

import java.util.Arrays;

public class qo_61_isStraight {
    public boolean isStraight(int[] nums) {
        int n = 5; // nums.length;
        Arrays.sort(nums);
        int k = 0;
        while (k < n && nums[k] == 0) k++; // 退出时，k指向首个不为零元素
        for (int i = k; i+1 < n; i++) {
            if (nums[i] == nums[i+1])
                return false;
        }
        return nums[n-1] - nums[k] <= 4;// 无相等牌面，max-min<=4，成立
    }

    // 写法2
    public boolean isStraight2(int[] nums) {
        int n = 5;
        Arrays.sort(nums);
        int min = -1, max = nums[n-1];
        for (int i = 0; i+1 < n; i++) {
            if (nums[i] == 0) continue;
            if (min == -1) min = nums[i]; // 数组升序，第一个非0元素为min
            if (nums[i] != 0 && nums[i] == nums[i+1]) return false; // 顺子不可相等
        }
        return max - min <= 4;
    }
}
