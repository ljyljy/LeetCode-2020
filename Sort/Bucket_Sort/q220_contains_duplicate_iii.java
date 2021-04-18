package Sort.Bucket_Sort;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class q220_contains_duplicate_iii {
    // 法1：滑动窗口(长度k) + 有序集合（TreeSet）
    // 遍历x∈nums，查找:
    // 1) y∈[x-t, y-t] 等价于 满足 y=(x-t)min <= (x+t);
    // 2)下标 i-j <= k
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k < 0) return false;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long diff = (long)nums[i] - (long)t,
                  sum = (long)nums[i] + (long)t;
            Long y = set.ceiling(diff); // ❤返回set中 ≥ num的[最小]元素
            if (y != null && y <= sum) return true;

            set.add((long)nums[i]);
            if (set.size() > k) // i >= k
                set.remove((long)nums[i-k]);
        }
        return false;
    }

    // 法2：桶排序
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k < 0) return false;
        int n = nums.length;
        Map<Long, Long> map = new HashMap<>(); // <id, nums[i]>
        long bw = (long)t + 1; // bucket_width 桶宽
        for (int i = 0; i < n; i++) {
            long id = getID(nums[i], bw);
            if (map.containsKey(id)) return true;
            if (map.containsKey(id-1) && Math.abs(nums[i] - map.get(id-1)) < bw)
                return true;
            if (map.containsKey(id+1) && Math.abs(nums[i] - map.get(id+1)) < bw)
                return true;

            map.put(id, (long)nums[i]);
            if (map.size() > k) // i >= k
                map.remove(getID(nums[i-k], bw)); // rm(key)
        }
        return false;
    }

    private long getID(long num, long bw) {
        if (num >= 0)
            return num / bw;
        return (num + 1) / bw - 1; // num < 0
    }
}
