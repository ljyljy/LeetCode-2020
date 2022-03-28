package Array.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class q525_contiguous_array {
    // 法1：前缀和+哈希 - O(n)
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,-1); // 哨兵！<cur累加和=0, idx=-1>
        int cur = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            cur = nums[i] == 0? cur-1:cur+1;// 0：cur-1,1：cur+1，连续的01: 抵消
            if (map.containsKey(cur)) // 找到重复的cur, 不put！
                ans = Math.max(ans, i - map.get(cur));
            else map.put(cur, i);
        }
        return ans;
    }
}
