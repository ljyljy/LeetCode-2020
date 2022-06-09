package Two_Pointers.Sliding_Window;

import java.util.Arrays;

public class q1423_maximum_points_from_cards {
    // 写法1 (q643升级版) - 滑动窗口，正难则反 - O(n-k)
    public int maxScore1(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        // 查找连续(n-k)个元素的最小和minSum，答案为总和-minSum
        int win = n - k;
        int minSum = Integer.MAX_VALUE, curSum = 0;
        int total = Arrays.stream(nums).sum();

        while (right < n) {
            curSum += nums[right++];

            while (right - left == win) {
                // 若minSum一直为INT_MAX，说明窗口大小一直没达到要求
                // 最后窗口大小==win后，恰好到末尾（即：k==n）
                minSum = Math.min(minSum, curSum);
                curSum -= nums[left++];
            }
        }
        return minSum == Integer.MAX_VALUE? total: total - minSum;
    }

    // 写法2（类比q567） - 滑动窗口，正难则反 - O(n-k)
    public int maxScore(int[] nums, int k) {
        int n = nums.length;
        // 查找连续(n-k)个元素的最小和minSum，答案为总和-minSum
        int win = n - k;
        int minSum, curSum = 0;
        int total = Arrays.stream(nums).sum();
        // 选前 n-k 个作为初始值
        for (int i = 0; i < win; ++i) {
            curSum += nums[i];
        }

        minSum = curSum;
        for (int i = win; i < n; i++) {
            curSum += nums[i] - nums[i-win]; // 写法，类比q567
            minSum = Math.min(minSum, curSum);
        }
        return total - minSum;
    }
}
