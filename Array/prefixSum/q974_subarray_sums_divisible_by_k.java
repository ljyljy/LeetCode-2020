package Array.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class q974_subarray_sums_divisible_by_k {
    public int subarraysDivByK0(int[] nums, int k) {
        int n = nums.length, ans = 0;
        int[] pSum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            pSum[i] = pSum[i-1] + nums[i-1];
        }
        Map<Integer, Integer> map = new HashMap<>();

        for (int sum: pSum) { // 无需哨兵，sum=0,cnt=1自动放入map
            int target = (sum % k + k) % k;
            int cnt = map.getOrDefault(target, 0);
            ans += cnt;
            map.put(target, cnt + 1);
        }
        return ans;
    }

    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length, ans = 0;
        int[] sum = new int[n+1];

        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }

        // <余数为i∈[0,k-1], 子数组cnt>
        // 同余 - 说明 和为k的子数组 被抵消了❤
        Map<Integer, Integer> modCntMap = new HashMap<>();
        modCntMap.put(0, 1); // 哨兵
        for (int i = 1; i <= n; i++) {
            int target = ((sum[i] % k) + k)%k; //❤正余找同余, 负余先变互补（正余）再找同余!
            int cnt = modCntMap.getOrDefault(target, 0); // 余2找2，余-2(mod_R)变3(互补)再找3(mod_L)
            // System.out.println(sum[i]+ ", " + target + ", " + cnt);
            ans += cnt; // 从而，mod_R - mod_L = 0(同余)
            modCntMap.put(target, modCntMap.getOrDefault(target, 0)+1);
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(-7 % 5); // -2
        System.out.println((-7+5)%5); // -2
        System.out.println((-7%5 +5) % 5); // 3 √

        System.out.println(-12 % 5); // -2
        System.out.println((-12+5)%5); // -2
        System.out.println((-12%5 +5) % 5); // 3 √
    }
}
