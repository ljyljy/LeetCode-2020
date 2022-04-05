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
}
