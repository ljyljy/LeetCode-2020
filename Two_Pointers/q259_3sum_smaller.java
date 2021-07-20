package Two_Pointers;

import java.util.Arrays;

public class q259_3sum_smaller {
    public int threeSumSmaller(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int cnt = 0;
        // 法1：固定最大边
        for (int k = 2; k < n; k++) {
            int sum = target - nums[k]; // [i]+[j]<sum
            int i = 0, j = k-1;
            // System.out.println(i + ", " + j);
            while (i < j) {
                // -2(i), -2, -2, 0, 0(j), 3(k)
                if(nums[i] + nums[j] < sum) {
                    // ∵此后i需右移 ∴需要一次性将固定k&【i】的所有对数求出！
                    cnt += j-(i+1)+1; // 对数=[k]&【i】&[i+1, j]
                    i++; // ∑↑
                } else j--;// 不符合要求，∑↓
            }
        }

        // // 法2：固定最小边
        // for (int i = 0; i < n-2; i++) {
        //     int lf = i+1, rt = n-1;
        //     int sum = target - nums[i]; // [lf]+[rt]<sum
        //     // -2(i), -2(lf), -2, 0, 0, 3(rt)
        //     while (lf < rt) {
        //         if(nums[lf] + nums[rt] < sum) {
        //             cnt += rt - (lf+1) + 1; // 固定i&lf时の对数=[i]&【lf】&[lf+1, rt]
        //             lf++;  // ∑↑
        //         } else rt--;// 不符合要求，∑↓
        //     }
        // }
        return cnt;
    }
}
