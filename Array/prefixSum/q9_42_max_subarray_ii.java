package Array.prefixSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 类比：q53 -> 合唱队、q9_42
public class q9_42_max_subarray_ii {
    // 从左至右，从右至左分别计算最大子数组和，然后刷一遍左右数组，找到最佳的划分位置，复杂度n+n+n
    // ↑ 类比：合唱队
    private int[] dp_L, dp_R;
    private int n;

    public int maxTwoSubArrays(List<Integer> numss) {
        n = numss.size();
        if (n == 2) return numss.get(0) + numss.get(1);
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = numss.get(i);
        }
        System.out.println(Arrays.toString(nums));
        getMaxSum_LR(nums);

        int maxSum = Integer.MIN_VALUE;
        for (int split = 1; split < n; split++) {
            // 左侧最大和[0:split) + 右侧最大和[split:n)
            maxSum = Math.max(maxSum, dp_L[split - 1] + dp_R[split]);
        }
        return maxSum;
    }

    // 从左至右，从右至左分别计算最大子数组和
    // 其他写法：前缀和（略）
    private void getMaxSum_LR(int[] nums) {
        dp_L = new int[n]; // 从左到右 最大子数组和
        dp_R = new int[n]; // 从左到右 最大子数组和
        dp_L[0] = nums[0];
        dp_R[n - 1] = nums[n - 1];

        for (int i = 1; i < n; i++) {
//            dp_L[i] = Math.max(nums[i], dp_L[i-1] + nums[i]);
            dp_L[i] = Math.max(nums[i], dp_L[i - 1] + nums[i]); // q53：[0,i]的最大连续子数组和
        }
        // ?求全局[0:i]当前最大子数组和，类比q9_43
        for (int i = 1; i < n; i++) {
            dp_L[i] = Math.max(dp_L[i], dp_L[i - 1]); // [0,i]的dp_L目前的最大值
        }

        System.out.println("dp_L: ");
        System.out.println(Arrays.toString(dp_L));

        // ?求全局[i:n)当前最大子数组和，类比q9_43
        for (int i = n - 2; i >= 0; i--) {
            dp_R[i] = Math.max(nums[i], dp_R[i + 1] + nums[i]);// q53逆序: [i,n]的最大连续子数组和
        }

        for (int i = n - 2; i >= 0; i--) {
            dp_R[i] = Math.max(dp_R[i], dp_R[i + 1]); // [i,n]的dp_R目前的最大值
        }

        System.out.println("dp_R: ");
        System.out.println(Arrays.toString(dp_R));
    }

    public static void main(String[] args) {
        System.out.println("exp01:");
        List<Integer> numss = new ArrayList<>();
        int[] nums = new int[]{1, 3, -1, 2, -1, 2};
        for (int num : nums) {
            numss.add(num);
        }
        q9_42_max_subarray_ii sol = new q9_42_max_subarray_ii();
        System.out.println(sol.maxTwoSubArrays(numss));

        System.out.println("exp02:");
        List<Integer> numss2 = new ArrayList<>();
        int[] nums2 = new int[]{-1, -2, -3, -100, -1, -50};
        for (int num : nums2) {
            numss2.add(num);
        }
        System.out.println(sol.maxTwoSubArrays(numss2));
    }
}
