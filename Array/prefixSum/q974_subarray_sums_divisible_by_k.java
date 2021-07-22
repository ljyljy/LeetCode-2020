package Array.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class q974_subarray_sums_divisible_by_k {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length, ans = 0;
        int[] sum = new int[n+1];

        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }

        // <余数为i∈[0,k-1], 子数组cnt>
        Map<Integer, Integer> modCntMap = new HashMap<>();
        modCntMap.put(0, 1); // 哨兵
        for (int i = 1; i <= n; i++) {
            // int mod = ((sum[i] % k) + k)%k; //❤正余找同余, 负余先变互补（正余）再找同余!
            int mod = sum[i] % k;
            int cnt = modCntMap.getOrDefault(mod, 0); // 余2找2，余-2(mod_R)变3(互补)再找3(mod_L)
            // System.out.println(sum[i]+ ", " + mod + ", " + cnt);
            ans += cnt; // 从而，mod_R - mod_L = 0(同余)
            modCntMap.put(mod, modCntMap.getOrDefault(mod, 0)+1);
        }
        return ans;
    }
}
