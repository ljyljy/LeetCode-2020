package Sort.Bucket_Sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.LongStream;

public class q220_contains_duplicate_iii {
    // 法1-1（掌握）：滑窗 + Treeset
    // |x-y| <= t -> x-t <= y <= x+t（【防止INT溢出】-> 设为Long）
    // 类比q219, 220
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
//        long[] nums_L = Arrays.stream(nums).mapToLong(Long::valueOf).toArray();
        TreeSet<Long> tset = new TreeSet<>();
        int n = nums.length;
        int left = 0, right = 0;
        while (right < n) {
            Long num = (long)nums[right];
            Long upper = (long)num + (long)t, bottom = (long) num - (long)t;
            Long num_j = tset.ceiling(bottom); // ❤返回tset中 ≥ bottom的[最小]元素
            if (num_j != null && num_j <= upper) {
                return true;
            }
            tset.add(num);
            right++;

            while (right - left > k) { // 保证窗口<=k
                tset.remove((long)nums[left++]);
            }
        }
        return false;
    }

    // 写法2：流处理 - int[]转long[]
    public boolean containsNearbyAlmostDuplicate_mapToLong(int[] nums, int k, int t) {
        long[] nums_L = Arrays.stream(nums).mapToLong(Long::valueOf).toArray();
        TreeSet<Long> tset = new TreeSet<>();
        int left = 0, right = 0, n = nums.length;

        while (right < n) {
            Long num = nums_L[right++];
            Long upper = num + t, bottom = num - t;
            Long num_j = tset.ceiling(bottom);// ❤返回tset中 ≥ bottom的[最小]元素
            if (num_j != null && num_j <= upper) {
                return true;
            }
            tset.add(num);

            while (right - left > k) {
                tset.remove(nums_L[left++]);
            }
        }
        return false;
    }

    // 法1-2：滑动窗口(长度k) + 有序集合（TreeSet）
    // 遍历x∈nums，查找:
    // 1) y∈[x-t, y-t] 等价于 满足 y=(x-t)min <= (x+t);
    // 2)下标 i-j <= k
    public boolean containsNearbyAlmostDuplicate1_2(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k < 0) return false;
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long bottom = (long)nums[i] - (long)t,
                  upper = (long)nums[i] + (long)t;
            Long y = set.ceiling(bottom); // ❤返回set中 ≥ num的[最小]元素
            if (y != null && y <= upper) return true;

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
