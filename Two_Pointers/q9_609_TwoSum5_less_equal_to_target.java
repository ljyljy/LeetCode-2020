package Two_Pointers;

import java.util.Arrays;

public class q9_609_TwoSum5_less_equal_to_target {
    // 法1：双指针
    public int twoSum5(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int lf = 0, rt = n-1;
        int cnt = 0;
        while (lf < rt) {
            int sum = nums[lf] + nums[rt];
            if (sum <= target) {
                cnt += rt - lf; // 固定lf: 对数=[lf+1, rt]
                lf++;
            } else rt--; // ∑↓
        }
        return cnt;
    }
}
