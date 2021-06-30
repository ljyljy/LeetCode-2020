//Given an array of integers, return indices of the two numbers such that they a
//dd up to a specific target.
//
// You may assume that each input would have exactly one solution, and you may n
//ot use the same element twice.
//
// Example:
//
//
//Given nums = [2, 7, 11, 15], target = 9,
//
//Because nums[0] + nums[1] = 2 + 7 = 9,
//return [0, 1].
//
// Related Topics 数组 哈希表


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class q1_two_sum {
    // 3. 一遍哈希 时间O(n), 空间O(n)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int resNum = target - nums[i];
            if (map.containsKey(resNum) && i != map.get(resNum)){
                return new int[]{i, map.get(resNum)};
            }
            map.put(nums[i], i); // num:idx
        }
        // 不合法参数异常
        throw new IllegalArgumentException("No two cnt solution");
    }

    // 2. 二遍哈希 时间O(n), 空间O(n)
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            map.put(nums[i], i); // num：idx
        }

        for (int i = 0; i < nums.length; ++i) {
            int resNum = target - nums[i];
//            int resIdx = map.get(resNum);
            if (map.containsKey(resNum) && i != map.get(resNum)) {
                return new int[]{i, map.get(resNum)};
            }
        }
        return null;
    }

    // 1. 暴力法 O(n^2)
    public int[] twoSum1(int[] nums, int target) {
        int[] res = new int[2];
        int numSize = nums.length;
        for (int i = 0; i < numSize-1; ++i) {
            for (int j = i+1; j < numSize; ++j) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
//                    return res;
                }
            }
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
