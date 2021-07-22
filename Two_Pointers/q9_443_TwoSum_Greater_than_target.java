package Two_Pointers;

import java.util.Arrays;

public class q9_443_TwoSum_Greater_than_target {
    public int twoSum2(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int lf = 0, rt = n-1;
        int cnt = 0;
        while (lf < rt) {
            int sum = nums[lf] + nums[rt];
            if (sum > target) {
                cnt += rt - lf; // 固定rt,对数=[lf, rt-1]
                rt--;
            } else lf++; // ∑↑
        }
        return cnt;
    }
}
