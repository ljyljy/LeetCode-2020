package Bit;

import java.util.*;

public class q137_single_number_of_array_iii {
    // 推荐-位运算
    public int singleNumber_bit(int[] nums) {
        int mod = 3;
        int[] bitcnts = new int[32];
        // 统计各个bit位‘1’的个数
        for (int num: nums) {
            for (int i = 0; i < 32; i++) {
                if (((num >> i) & 1) == 1) {
                    bitcnts[i]++;
                }
            }
        }
        // %mod：按位抵消mod个相同的元素
        // 落单（mod != 0）的即为所求的第i位为1
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if (bitcnts[i] % mod != 0) {
                res |= (1 << i);
            }
        }
        return res;
    }

    // 法1：常规 map
    public int singleNumber1(int[] nums) {
        Map<Integer, Integer> numCntMap = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (numCntMap.containsKey(nums[i])) {
                numCntMap.put(nums[i], numCntMap.get(nums[i])+1);
            } else{
                numCntMap.put(nums[i], 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : numCntMap.entrySet()) {
            if (entry.getValue() == 1)
                return entry.getKey();
        }
        return -1;
    }

    // 法2：3*(a+b+c) - (a+a+a+b+b+b+c) = 3c - c = 2c
    public int singleNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        long sum = 0, uniqueSum = 0; // int求和会溢出！
        for (int num: nums) {
            set.add(num);
            sum += num;
        }
        for (Integer uniq: set)
            uniqueSum += uniq;
        long res =  (3 * uniqueSum - sum) / 2;
        return (int)res; // res以long定义，最后再强转int！
    }

    // 法3：位运算
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) { // ∵32位int
            int sum_i = 0; // 第i位求和
            // 提取从右往左数第i位的数值, 将所有nums[i] 第i位数值进行求和
            for (int num : nums)
                sum_i += ((num >> i) & 1);
            // 如果没办法被3整除，那么说明落单的那个数的【第i位是1】不是0
            if (sum_i % 3 == 1)
                res |= (1 << i); // 构造【第i位是1】
        }
        return res;
    }

    public static void main(String[] args) {
        int res = 1;
        System.out.println(res << 3);
    }
}
