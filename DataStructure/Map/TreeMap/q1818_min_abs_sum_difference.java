package DataStructure.Map.TreeMap;

import java.util.*;


public class q1818_min_abs_sum_difference {
    final int MOD = (int)1e9 + 7;
    // 法3：二分
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        long diffSum = 0, maxChange = 0;
        int[] sorted1 = nums1.clone(); //或 System.arraycopy(nums1, 0, sorted1, 0, n);
        Arrays.sort(sorted1);

        for (int i = 0; i < n; i++) {
            if (nums1[i] == nums2[i]) continue;
            int curDiff = Math.abs(nums1[i] - nums2[i]);
            diffSum += curDiff;
            long newDiff = binSearch_getMinDiff(sorted1, nums2[i]);
            if (newDiff < curDiff)
                maxChange = Math.max(maxChange, curDiff - newDiff);
            // ↑ 最大化前后差值 = max{|nums[i]-nums[i]|} - min{|nums[j]-nums[i]|}
        }
        return (int)((diffSum - maxChange) % MOD);
    }

    private long binSearch_getMinDiff(int[] arr, int x) {
        int start = 0, end = arr.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == x) return 0;
            else if (arr[mid] > x) end = mid;
            else start = mid;
        }
        long diff1 = Math.abs(arr[start] - x);
        long diff2 = Math.abs(arr[end] - x);
        // System.out.println(diff1 + ", " + diff2);
        return diff1 <= diff2? diff1: diff2;
    }


    // 法2：用一个大根堆(TreeSet)
    public int minAbsoluteSumDiff_TreeSet(int[] nums1, int[] nums2) {
        int n = nums1.length;
        long diffSum = 0;
        // 大根堆 <nums1>
        TreeSet<Integer> tset = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            long curDiff = Math.abs(nums1[i] - nums2[i]);
            diffSum += curDiff;
            tset.add(nums1[i]);
        }

        //遍历nums2，从TreeSet:nums1中找离他(nums2[i])最近的两个数
        // 通过ceiling (>=x)和 floor(<=x)方法
        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long srcDiff = Math.abs(nums1[i] - nums2[i]);
            if (srcDiff == 0) continue;
            int rep1 = Integer.MAX_VALUE, rep2 = Integer.MAX_VALUE;
            if (tset.ceiling(nums2[i]) != null)
                rep1 = tset.ceiling(nums2[i]);
            if (tset.floor(nums2[i]) != null)
                rep2 = tset.floor(nums2[i]);

            long newDiff = Math.min(Math.abs(rep1 - nums2[i]), Math.abs(nums2[i]-rep2));
            res = Math.min(res, diffSum - srcDiff + newDiff);
        }
        return (int)(res % MOD);
    }

    // 法1：用俩大根堆 - TLE
    public int minAbsoluteSumDiff_TLE(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int diffSum = 0;
        // 大根堆 <差值, List<idx>或直接覆盖为最新idx>
        TreeMap<Integer, Integer> tmap = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            int curDiff = Math.abs(nums1[i] - nums2[i]);
            diffSum += curDiff;
            tmap.put(curDiff, i);
        }
        if (diffSum == 0) return 0;

        int maxChange = Integer.MIN_VALUE; // 最大化前后差值res = max{|nums[i]-nums[i]|} - min{|nums[j]-nums[i]|}
        while (!tmap.isEmpty()) {
            // 最大差值 & 对应下标
            Map.Entry<Integer, Integer> entry = tmap.pollFirstEntry();
            // System.out.println(entry.getKey() + "--" + entry.getValue());
            int maxDiff = entry.getKey(), maxIdx = entry.getValue();

            TreeMap<Integer, Integer> tmap2 = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                int diff = Math.abs(nums1[i] - nums2[maxIdx]);
                tmap2.put(diff, i);
            }

            int minDiff = tmap2.firstKey(), minIdx = tmap2.get(minDiff);
            // System.out.println(minDiff + "-- idx=" + tmap2.get(minDiff));
            maxChange = Math.max(maxChange, maxDiff - minDiff);
        }

        return (diffSum - maxChange)%MOD;
    }
}
