package DataStructure.Map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class q1460_make_two_arrays_equal_by_reversing_sub_arrays {
    public boolean canBeEqual(int[] target, int[] arr) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int num: target) {
            map1.put(num, map1.getOrDefault(num, 0) + 1);
        }
        for (int num: arr) {
            map2.put(num, map2.getOrDefault(num, 0) + 1);
        }
        if (map1.size() != map2.size()) return false;

        for (Map.Entry<Integer, Integer> entry: map1.entrySet()) {
            int num = entry.getKey(), val1 = entry.getValue();
            int val2 = map2.getOrDefault(num, 0);
            if (val1 != val2) return false;
        }
        return true;
    }

    // 排序后，比较相等
    public boolean canBeEqual1(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(target, arr);
    }
}
