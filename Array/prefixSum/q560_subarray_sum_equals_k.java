package Array.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class q560_subarray_sum_equals_k {
    // 法1：前缀和 + 哈希
    public int subarraySum(int[] nums, int target) {
        int n = nums.length, cnt = 0;
        int[] preSum = new int[n+1];
        for (int i = 1; i <= n; i++)
            preSum[i] = preSum[i-1] + nums[i-1];

        Map<Integer, Integer> map = new HashMap<>(); // <preSum, cnt>
        // 法1-2：无需哨兵
        for (int sum: preSum) {
            // 先获得前缀和为 sum - target 的个数，加到计数变量ans里
            int key = sum - target;
            cnt += map.getOrDefault(key, 0);
            // 然后维护 preSumFreqMap 的定义
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }

        // 法1：哨兵<sum=0, cnt=1>
//        map.put(0, 1);
//        for (int i = 1; i <= n; i++) {
//            // 先获得前缀和为 preSum - target 的个数，加到计数变量ans里
//            int key = preSum[i] - target;
//            cnt += map.getOrDefault(key, 0);
//            // 然后维护 preSumFreqMap 的定义
//            map.put(preSum[i], map.getOrDefault(preSum[i], 0)+1);
//        }
        return cnt;
    }


    // 写法2
    public int subarraySum0(int[] nums, int target) {
        int n = nums.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }

        Map<Integer, Integer> map = new HashMap<>();

        int cnt = 0;
        for (int sum_i: sum) {
            int key = sum_i - target;
            if (map.containsKey(key)) {
                cnt += map.get(key);
            }
            map.put(sum_i, map.getOrDefault(sum_i, 0) + 1);
        }
        return cnt;
    }


}
