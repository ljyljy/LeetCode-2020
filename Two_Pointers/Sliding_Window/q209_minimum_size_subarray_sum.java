package Two_Pointers.Sliding_Window;

public class q209_minimum_size_subarray_sum {
    // ��1��˫ָ�� / ��������
    public int minSubArrayLen(int sum, int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int tmpSum = 0, minLen = Integer.MAX_VALUE;
        while (right < n) {
//            while(right < n && tmpSum < sum) {
                tmpSum += nums[right++];
//            }
            // while�˳�������tmpSum >= sum����С���ڣ���߽�++
            while (tmpSum >= sum) {
                minLen = Math.min(minLen, right - left);
                tmpSum -= nums[left++];
            }
        }
        return minLen == Integer.MAX_VALUE? 0: minLen;
    }
}
