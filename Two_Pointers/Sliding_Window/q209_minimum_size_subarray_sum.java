package Two_Pointers.Sliding_Window;

public class q209_minimum_size_subarray_sum {
    // 法1：双指针 / 滑动窗口
    public int minSubArrayLen(int sum, int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int tmpSum = 0, minLen = Integer.MAX_VALUE;
        while (right < n) {
//            while(right < n && tmpSum < sum) {
                tmpSum += nums[right++];
//            }
            // while退出，满足tmpSum >= sum。缩小窗口，左边界++
            while (tmpSum >= sum) {
                minLen = Math.min(minLen, right - left);
                tmpSum -= nums[left++];
            }
        }
        return minLen == Integer.MAX_VALUE? 0: minLen;
    }
}
