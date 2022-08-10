package Greedy;

import java.util.Arrays;

public class q55_jump_game {
    // ��1��̰��
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int farthest = nums[0];
        for (int i = 0; i < n; i++) {
            if (i <= farthest) { // �´�ǰ�᣺����ɴ
                farthest = Math.max(farthest, i + nums[i]);
                if (farthest >= n-1) {
                    return true;
                }
            }
        }
        return false;
    }

    // ��2��dp[i] -- i������Զ��Ծ�㣬��>=n-1, ��true
    public boolean canJump22(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        int farthest = nums[0];
        for (int i = 0; i < n; i++) {
            if (i <= farthest) {
                dp[i] = Math.max(dp[i], i + nums[i]);
                farthest = Math.max(farthest, dp[i]);
                if (farthest >= n-1) return true;
            }
        }
        return false;
    }

    // ��2-2������
    public boolean canJump2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int farthest = 0;
        Arrays.fill(dp, Integer.MIN_VALUE);
        for (int i = 0; i < n; i++) {
            if (i <= farthest) {
                dp[i] = Math.max(dp[i],  i + nums[i]);
                if (dp[i] >= n-1) return true;
            } else return false;
            farthest = Math.max(farthest, dp[i]);
        }
        return dp[n-1] >= n-1;
    }

    // ��3
    public boolean canJump3(int[] nums) {
        int n = nums.length;
        int farthest = nums[0];
        int[] dp = new int[n]; // ��Զ����
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];
        for (int i = 0; i < n; i++) {
            if (i <= farthest) {
                for (int j = 0; j < i; j++) {
                    if (j + nums[j] >= i) {
                        dp[i] = Math.max(dp[i], Math.max(i + nums[i], j + nums[j]));
                        if (dp[i] >= n-1) return true;
                    }
                }
                farthest = Math.max(farthest, dp[i]);
            }
        }
//        System.out.println(Arrays.toString(dp));
        return farthest >= n-1;
    }
}
