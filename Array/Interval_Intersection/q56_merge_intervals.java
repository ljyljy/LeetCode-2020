package Array.Interval_Intersection;

import java.util.Arrays;

public class q56_merge_intervals {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        // 按start升序? 勿漏！易错！
        Arrays.sort(intervals, (o1, o2)->(o1[0]-o2[0]));
        int[][] res = new int[n][2]; // 最后Arrays.copyOf(arr, idx);
        int[] last = intervals[0];
        int idx = 0;
        for (int i = 1; i < n; i++) {
            if (isOverlap(last, intervals[i])) {
                last = mergeInterval(last, intervals[i]);
            } else {
                res[idx++] = last;
                last = intervals[i];
            }
        }
        // 最后无论走if还是else，都没有加入结果集↓
        res[idx++] = last;
        return Arrays.copyOf(res, idx);
    }

    private boolean isOverlap(int[] inv1, int[] inv2) {
        return Math.max(inv1[0], inv2[0]) <= Math.min(inv1[1], inv2[1]); // qsort后，一定是inv2[0] <= inv1[1]
    }

    private int[] mergeInterval(int[] inv1, int[] inv2) {
        int[] newInv = new int[2];
        newInv[0] = Math.min(inv1[0], inv2[0]);
        newInv[1] = Math.max(inv1[1], inv2[1]);
        return newInv;
    }
}
