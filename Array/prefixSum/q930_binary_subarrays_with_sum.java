package Array.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class q930_binary_subarrays_with_sum {
    // 新版本
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length, ans = 0;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }

        Map<Integer, Integer> map = new HashMap<>(); // <前缀和, 频次cnt>
        for (int s: sum) {
            ans += map.getOrDefault(s - goal, 0);
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        return ans;
    }

    // v2
    public int numSubarraysWithSum1(int[] nums, int goal) {
        int n = nums.length;
        int[] sum = new int[n+1];
        Map<Integer, Integer> map = new HashMap<>(); // <前缀和, 频次cnt>
        map.put(0, 1); // ❤勿漏！！sum[0]=0, cnt为1次
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + nums[i-1];
            // ❤ ↓ 这样会重复计算！ 必须逐渐加入map!!!
            // map.put(sum[i], map.getOrDefault(sum[i], 0)+1);
        }

        int ans = 0;
        // 重复计算！
        // for (int i = 0; i < n; i++) {
        //     int num1 = sum[i]; // sum[x] - sum[i] = goal; -> sum[x] = goal + sum[i];; num = sum[i] - goal;
        //     int cnt = map.getOrDefault(sum[i] + goal, 0);
        //     ans += cnt;
        // }

        // map必须依次加入！如：从小到大遍历，前缀和递增，查询map中(R-goal)是否存在(累加cnt)
        // 注意i∈[0, n-1], 以确保sum_R必定在sum_L严格左边 ∈ sum[1~n]
        for (int i = 0; i < n; i++) {
            int sum_R = sum[i+1], sum_L = sum_R - goal; // sum_R[i] - sum_L[0~(i-1)] = goal
            ans += map.getOrDefault(sum_L, 0);
            map.put(sum_R, map.getOrDefault(sum_R, 0)+1);
        }

        return ans;
    }
}
