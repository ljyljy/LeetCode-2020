package Array.Interval_Intersection;

import java.util.Arrays;

public class q1288_remove_covered_intervals {
    public int removeCoveredIntervals (int[][] intervals) {
        int n = intervals.length;
        if (n < 2) return n;
        // 1) o[0]���� 2)���򣬡�o[1]����, �ӣ�������֤right�����ܴ󣬼�[1,2], [1,4], [3,4] -> [1,4]��[1,2]֮ǰ����֤o[0]��ͬʱ��right��ֵ������=4>2��
        Arrays.sort(intervals, (o1, o2)->(o1[0]!=o2[0]? o1[0] - o2[0]: o2[1] - o1[1]));

        int right = Integer.MIN_VALUE;
        int rmCnt = 0;
        for (int i = 0; i < n; i++) {
            int rt = intervals[i][1];
            if (rt <= right) {
                rmCnt++;
            }
            right = Math.max(right, rt);
        }
        return n - rmCnt;
    }

}
