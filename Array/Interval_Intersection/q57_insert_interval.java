package Array.Interval_Intersection;

import java.util.Arrays;

public class q57_insert_interval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if (n == 0) return new int[][]{newInterval}; // ���һά��
        // ����n+1�����䣬��������������䶼���غ�
        int[][] res = new int[n+1][2];
        // ��������Ԫ��[0]���������� - ��Ŀ�Ѿ��ź�?
        // Arrays.sort(intervals, (o1, o2)->(o1[0]-o2[0]));

        // ���������б�
        // 1. �����������������������������
        int idx = 0, i = 0;
        while (i < n && intervals[i][1] < newInterval[0]) {
            res[idx++] = intervals[i++];
        }
        // 2. �ص�����ϲ�, ����ΪnewInterval
        // intervals[i][1] >= newInterval[0] && intervals[i][0] < newInterval[1]
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res[idx++] = newInterval;

        // 3.���������ұ�������������������
        while (i < n) {
            res[idx++] = intervals[i++];
        }
        return Arrays.copyOf(res, idx); // ��ȡres��ǰidx��
    }
}
