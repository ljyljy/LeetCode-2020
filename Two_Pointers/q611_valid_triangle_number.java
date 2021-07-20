package Two_Pointers;

import java.util.Arrays;

public class q611_valid_triangle_number {
    // 法1: 双指针O(n^2)
    public int triangleNumber1(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int cnt = 0;
        // 三数之和 - 变题
        // 双指针: for 循环最大边 i，接下来的任务就是在 0~i-1 之间找到两数之和 > Si
        for (int i = 2; i < n; i++) {
            int lf = 0, rt = i-1;
            while (lf < rt) { // 2(lf), 2, 3, 4(rt), 5(i)
                if (nums[lf] + nums[rt] > nums[i]) {
                    cnt += rt - lf; // 固定rt&iの对数=[lf, rt-1] & [rt] & [i]
                    rt--; // 计算新数对(固定rt_new&i)
                } else lf++;
            }
        }
        return cnt;
    }
}
