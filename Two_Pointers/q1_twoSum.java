package Two_Pointers;

import java.util.*;

public class q1_twoSum {
    // 【荐】法1：一遍哈希 - O(n)
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> numIdxMap = new HashMap<>();
        int n = nums.length;

        for (int i = 0; i < n; i++){
            if (numIdxMap.containsKey(target - nums[i]))
                return new int[]{numIdxMap.get(target - nums[i]), i};
            numIdxMap.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
    
    // 【若返回num-荐；返回idx - 不荐】
    // 法2：双指针 - 前提：排序 + 索引数组❤ O(nlogn)
    public int[] twoSum(int[] nums, int target) {
        // 还需 【索引数组❤】为了重现排序后被打乱的下标
        int n = nums.length;
        int[][] numIdx = new int[n][2];
        for (int i = 0; i < n; i++) {
            numIdx[i] = new int[]{nums[i], i};
        }
        Arrays.sort(numIdx, (o1, o2) -> (o1[0] - o2[0])); // 按首元素nums[i]升序

        // 单调 i↑ j↓
        int i = 0, j = n - 1;
        while(i < j) {
            if (i > 0 && numIdx[i][0] == numIdx[i-1][0]) continue; // 去重
            if (numIdx[i][0] + numIdx[j][0] == target){
//                System.out.println(numIdx[i][0] + ", " + numIdx[j][0] + " -- " + i + ", " + j);
                return new int[]{numIdx[i][1] , numIdx[j][1]}; // 排序后i,j的原始下标
            } else if (numIdx[i][0] + numIdx[j][0] > target) j--;
            else i++;
        }
        return new int[]{-1, -1};
    }
}
