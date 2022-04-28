package Binary_Search.bin_Answer;

import java.util.Arrays;


/**
 * 排序 heaters，O(m * logm)
 * 遍历每一个房屋 house，O(n)
 * 二分 house 在 heaters 中的插入位置，O(logm)
 * 总时间复杂度：O((n + m) * logm)
 */
public class q475_heaters {
    // 法1：二分答案
    public int findRadius0(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int start = 0, end = (int)1e9;
        while (start < end) { // [L, mid], [mid+1, R]
            int mid = start + end >> 1;
            if (check(houses, heaters, mid)) end = mid;
            else start = mid+1;
        }
        return start;
    }

    private boolean check(int[] houses, int[] heaters, int tryR) {
        int n1 = houses.length, n2 = heaters.length;
        for (int i = 0, j = 0; i < n1; i++) {
            while (j < n2 && houses[i] > heaters[j] + tryR) j++; // 找到合适的右边界(见例2)
            if (j < n2 && heaters[j] - tryR <= houses[i] && houses[i] <= heaters[j] + tryR) continue; // 换下一个house
            return false;
        }
        return true;
    }

//    解法二：同向双指针
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int n1 = houses.length, n2 = heaters.length;
        int i = 0, j = 0, radius = 0;

        while (i < n1 && j < n2) {
            int curR = Math.abs(houses[i] - heaters[j]);
            int nxtR = Integer.MAX_VALUE;
            if (j + 1 < n2) {
                nxtR = Math.abs(houses[i] - heaters[j+1]);
            }
            if (curR < nxtR) { // max{可行解的下限}
                radius = Math.max(radius, curR);
                i++;
            } else j++;
        }
        return radius;
    }

}
