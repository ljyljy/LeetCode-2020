package DataStructure.Map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class q1711_count_good_meals {
    public int countPairs(int[] deliciousness) {
        final int MOD = 1000000007;
        int maxVal = Arrays.stream(deliciousness).max().getAsInt();
        int maxSum = maxVal * 2;
        int n_pairs = 0;
        Map<Integer, Integer> map = new HashMap<>(); // 给定数组中各个数的频数(∵有重复数字)
        int n = deliciousness.length;
        for (int num : deliciousness) {
            // 普通方法：遍历num1 & num2，看num1+num2是否是2的幂 - O(n^2) - TLE!!
            // 反向思考：遍历num1 & 2的幂，看sum-num1是否存在于给定数组/map中
            //          - O(nlog(maxSum)=O(logmaxVal)=O(logC)
            for (int sum = 1; sum <= maxSum; sum <<= 1) { // sum: 2的幂递增(1,2,4...)
                // 找到了pair< num1, num2(即sum-num1) >，
                int cnt = map.getOrDefault(sum - num, 0);
                                        // 无需cnt +1 ↑，因为后面对num加了1
                n_pairs = (n_pairs + cnt) % MOD;
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // for (Map.Entry<Integer, Integer> entry: map.entrySet())
        //     System.out.println(entry.getKey() + ", " + entry.getValue());
        return n_pairs;
    }
}
