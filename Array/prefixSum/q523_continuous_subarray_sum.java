package Array.prefixSum;

import java.util.*;

public class q523_continuous_subarray_sum {
    // 法2：前缀和 + 同余定理(哈希) -- O(n)
    public boolean checkSubarraySum2(int[] nums, int k) {
        int n = nums.length;
        if (n < 2) return false;
        Map<Integer, Integer> map = new HashMap<>();// <余数, idx>
        map.put(0, -1); // 若前缀和直接被k整除(余数=0)，只需前缀和下标(从0开始)-(-1)>2即可满足
        // 1)同余定理：(a-b)%k=0, 则 a%k == b%k, 即 (sum[i]-sum[j])%k=0,则两数同余
        int reminder = 0;
        for (int i = 0; i < n; i++) {
            reminder = (reminder + nums[i]) % k;
            if (map.containsKey(reminder)) {
                if (i - map.get(reminder) >= 2)
                    return true;
            } else map.put(reminder, i);
        }
        return false;
    }

    // 法2-写法2：前缀和idx必须从0开始！且map哨兵为<余数为0,idx为-1>！
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        if (n < 2) return false;
        int[] preSum = new int[n+1];
        preSum[0] = nums[0];
        for (int i = 1; i <n; i++) {
            preSum[i] = preSum[i-1] + nums[i];
        }
        Map<Integer, Integer> map = new HashMap<>(); // <余数, idx>
        map.put(0, -1); // 若前缀和直接被k整除(余数=0)，只需前缀和下标(从1开始)-0>2即可满足
        // 1)同余定理：(a-b)%k=0, 则 a%k == b%k, 即 (sum[i]-sum[j])%k=0,则两数同余
        for (int i = 0; i < n; i++) {
            int reminder = preSum[i] % k;
            if(map.containsKey(reminder)) {
                // 2)∵子数组和必须是k的倍数(还需满足前缀和>=k, 否则0%k=0)
                int preIdx = map.get(reminder);
                if (i - (preIdx) >= 2)
                    return true;
            } else map.put(reminder, i);
        }
        return false;
    }

    // 法1：朴素前缀和 - TLE -- O(n^2)
    public boolean checkSubarraySum_TLE(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n+1];
        for (int i = 1; i <=n; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        for (int i = 0; i <= n; i++) {
            for (int j = i+2; j <= n; j++){
                if ((preSum[j] - preSum[i]) % k == 0){
                    System.out.println("preSum[j]="+preSum[j]+", preSum[i]="+preSum[i]+", j="+j+", i="+i);
                    return true;
                }
            }
        }
        return false;
    }
}
