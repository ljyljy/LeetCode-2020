package Array.sort_def;

import java.util.Arrays;

public class q1893_check_if_all_range_covered {
    // 法1-2：排序
    public boolean isCovered(int[][] ranges, int left, int right) {
        Arrays.sort(ranges, (o1, o2) -> (o1[0] != o2[0]? o1[0] - o2[0] : o1[1] - o2[1]));
        for (int[] range : ranges) {
            int L = range[0], R = range[1];
            if (L <= left && left <= R)
                left = R+1; // left右移
        }
        return left > right;
    }

    // 法0：排序（想复杂了）
    // private Comparator<int[]> comparator = new Comparator<>(){
    //     public int compare(int[] o1, int[] o2) {
    //         return o1[0] != o2[0]? o1[0] - o2[0] : o1[1] - o2[1];
    //     }
    // };

    public boolean isCovered0(int[][] ranges, int left, int right) {
        Arrays.sort(ranges, (o1, o2) -> (o1[0] != o2[0]? o1[0] - o2[0] : o1[1] - o2[1]));
        int m = ranges.length, n = ranges[0].length;
        if (n == 0) return false;
        // ↓错误！只能保证ranges[0][0]是最小，最大数(range[x][1])有可能第一维不是最大!!
        // if (right > ranges[m-1][1]) return false; // maxNum都比range最大数要大（溢出了）
        if (left < ranges[0][0])  return false;// minNum都比range最小数要小（溢出了）

        int lf = left, rt = right;
        int k = 0, tickCnt = 0;

        for (int i = lf; i <= rt; ) {
            int status = check(ranges[k], i);
            // System.out.println(i+ "-> status: "+status);
            if (status == 0) {
                tickCnt++;
                i++;// 接着匹配下一个num
                if (tickCnt == right - left + 1)
                    return true;
                // System.out.println(i + "-> tickCnt:" + tickCnt);
            } else if (status == 1) {
                k++;
                // System.out.println(i + "-> k++: [" + ranges[k][0] + ", " + ranges[k][1] + "]");
                if (k >= m) return false;
            } else { // status == -1
                return false;
            }
        }
        return tickCnt == right - left + 1;

    }

    private int check (int[] range, int num) {
        if (range[0] <= num && num <= range[1])
            return 0; // 正好满足
        else if (range[0] > num) // 不用往后遍历range了，
            return -1; // num偏小了
        else return 1; // num偏大 > range[1], 需要range[k++]
    }
}
