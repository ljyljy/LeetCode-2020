package Array.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class q560_subarray_sum_equals_k {
    // 法1：前缀和 + 哈希
    public int subarraySum(int[] nums, int k) {
        int n = nums.length, ans = 0;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++)
            sum[i] = sum[i-1] + nums[i-1];

        Map<Integer, Integer> map = new HashMap<>(); // <preSum, cnt>
        map.put(0, 1);
        for (int i = 1; i <= n; i++) {
            // 先获得前缀和为 preSum - k 的个数，加到计数变量ans里
            int target = sum[i] - k;
            ans += map.getOrDefault(target, 0);
            // 然后维护 preSumFreqMap 的定义
            map.put(sum[i], map.getOrDefault(sum[i], 0)+1);
        }
        return ans;
    }


}
