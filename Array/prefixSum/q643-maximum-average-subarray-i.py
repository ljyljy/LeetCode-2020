class Solution:
    def findMaxAverage(self, nums, k):
        n = len(nums)
        prefix_sum = [0 for i in range(n + 1)]
        for i in range(1, n + 1):
            prefix_sum[i] = prefix_sum[i - 1] + nums[i - 1]
        max_sum = prefix_sum[k]

        for i in range(n - k + 1):  # i + k < n + 1
            tmp_sum = prefix_sum[i + k] - prefix_sum[k]
            max_sum = max(max_sum, tmp_sum)  # # 求连续k个子数组的最大和
        return max_sum / k
