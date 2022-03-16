package Two_Pointers;

import java.util.*;

public class q170_two_sum_iii_DS {
    Map<Integer, Integer> map;
    /** Initialize your data structure here. */
    public q170_two_sum_iii_DS() {
        map = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int val) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int curNum = entry.getKey(), curCnt = entry.getValue();
            if (map.containsKey(val - curNum)) {
                // 若curNum == val - curNum，但其频数仅为1，则不算
                if (val - curNum == curNum && curCnt <= 1) continue;
                return true;
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
