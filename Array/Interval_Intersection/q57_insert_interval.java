package Array.Interval_Intersection;

import java.util.Arrays;

public class q57_insert_interval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if (n == 0) return new int[][]{newInterval}; // 外加一维度
        // 至多n+1个区间，即插入后，所有区间都不重合
        int[][] res = new int[n+1][2];
        // 按区间首元素[0]，升序排序 - 题目已经排好?
        // Arrays.sort(intervals, (o1, o2)->(o1[0]-o2[0]));

        // 遍历区间列表：
        // 1. 将新区间左边且相离的区间加入结果集
        int idx = 0, i = 0;
        while (i < n && intervals[i][1] < newInterval[0]) {
            res[idx++] = intervals[i++];
        }
        // 2. 重叠区间合并, 覆盖为newInterval
        // intervals[i][1] >= newInterval[0] && intervals[i][0] < newInterval[1]
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res[idx++] = newInterval;

        // 3.将新区间右边且相离的区间加入结果集
        while (i < n) {
            res[idx++] = intervals[i++];
        }
        return Arrays.copyOf(res, idx); // 截取res的前idx个
    }
}
