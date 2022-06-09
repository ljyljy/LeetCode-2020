package DP;

public class q718_maximum_length_of_repeated_subarray {
    public int findLength(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[][] dp = new int[n1+1][n2+1];  // [0, i-1]&[0, j-1]的最长重复子数组
        int maxLen = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (nums1[i-1] == nums2[j-1])  // else dp[i,j]=0
                    dp[i][j] = dp[i-1][j-1] + 1;
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return maxLen;
    }
}
