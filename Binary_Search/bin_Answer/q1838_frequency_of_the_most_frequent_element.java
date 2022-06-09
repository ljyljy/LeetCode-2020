package Binary_Search.bin_Answer;

import java.util.*;

public class q1838_frequency_of_the_most_frequent_element {
    // 【荐】  法2： 二分 O(nlogn)
    private int[] nums, sum;
    private int n, k;
    public int maxFrequency(int[] nums, int k) {
        n = nums.length; this.nums = nums; this.k = k;
        this.sum = new int[n+1];
        Arrays.sort(nums);

        for (int i = 1; i <= n; i++)
            sum[i] = sum[i-1] + nums[i-1];

        // 二分前提：保持升序(∴前缀和↑)
        int start = 0, end = n; // 二分答案 ans频数∈[0,n]
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (check(mid))  // 说明满足要求，mid可以再加(右区间)
                start = mid;
            else end = mid;
        }
        if (check(end)) return end;
        if (check(start)) return start;

//        // 写法2
//        while (start < end) { // [L, mid-1] & [mid, R]
//            int mid = start + end + 1 >> 1;
//            // 相等时，往右找有没有更大的频数（但mid，R需在一个区间）
//            if (check(mid)) start = mid;
//            else end = mid-1;
//        }
//        if (check(start)) return start;
        return 1;
    }

    // 二分答案 = tryCnt（最高频数）
    private boolean check(int tryCnt) {
        // 滑动窗口，窗口大小=tryCnt
        // for (int i = 0; i < n - tryCnt + 1; i++) {
        for (int i = 0; i+tryCnt-1 < n; i++) {
            // num_idx = [i, i+tryCnt-1]; ->sum[j+1]-sum[i]
            int j = i+tryCnt-1;
            int sum_window = sum[j+1] - sum[i];
            int sum_ifDone = nums[j] * tryCnt;// 前提：nums有序
            int tryK = sum_ifDone - sum_window;
            // System.out.println("sum_window:" + sum_window + ", sum_ifDone:" + sum_ifDone+", tryK=" + tryK);
            if (tryK <= k) return true;
        }
        return false;
    }

    // 法1： 朴素 O(n^2)
    public int maxFrequency1(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) map.put(num, map.getOrDefault(num, 0)+1);
        List<Integer> nums0 = new ArrayList<>(map.keySet());
        Collections.sort(nums0);

        int maxCnt = 1; // 答案频数>=1
        for (int i = 0; i < nums0.size(); i++) {
            int x = nums0.get(i), cnt_x = map.get(x);
            int k0 = k;
            if (i > 0) {
                for (int j = i-1; j >= 0; j--) {
                    int y = nums0.get(j), cnt_y = map.get(y);
                    int diff = x - y;
                    // if (diff == 0) continue;
                    if (k0 >= diff) {
                        int curCnt = Math.min(cnt_y, k0 / diff);
                        k0 -= curCnt * diff; // ↑ 保证k0>=0
                        cnt_x += curCnt;
                    } else break;
                }
            }
            maxCnt = Math.max(maxCnt, cnt_x);
        }
        return maxCnt;
    }
}
