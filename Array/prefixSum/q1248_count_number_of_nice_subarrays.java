package Array.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class q1248_count_number_of_nice_subarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        int oddCnt = 0, ans = 0;

        // <奇数个数1~n，符合的子数组の个数>
        Map<Integer, Integer> oddNumCntMap = new HashMap<>();
        oddNumCntMap.put(0, 1); // 哨兵<奇数个数0个，符合的有1个>
        // ↑ 当oddCnt == k时，需要用到该哨兵（计数+1，否则会少算这个情况）

        for (int num : nums) {
            oddCnt += num & 1; // 奇数&1=1; 偶数&1=0;
            int target = oddCnt - k;
            ans += oddNumCntMap.getOrDefault(target, 0);
            oddNumCntMap.put(oddCnt, oddNumCntMap.getOrDefault(oddCnt, 0)+1);
        }
        return ans;
    }
}
