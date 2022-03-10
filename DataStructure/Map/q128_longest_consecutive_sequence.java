package DataStructure.Map;

import java.util.*;

public class q128_longest_consecutive_sequence {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num: nums) set.add(num);
        int maxLen = 0;
        for (int num: nums){
            if (set.contains(num-1)) continue;
            // try找连续序列的头tmp
            int tmp = num, curLen = 1;
            while (set.contains(tmp+1)) {
                curLen++;
                tmp++;
            }
            maxLen = Math.max(maxLen, curLen);
        }
        return maxLen;
    }
}
