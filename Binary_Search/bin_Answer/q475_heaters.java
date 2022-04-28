package Binary_Search.bin_Answer;

import java.util.Arrays;


/**
 * ���� heaters��O(m * logm)
 * ����ÿһ������ house��O(n)
 * ���� house �� heaters �еĲ���λ�ã�O(logm)
 * ��ʱ�临�Ӷȣ�O((n + m) * logm)
 */
public class q475_heaters {
    // ��1�����ִ�
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
            while (j < n2 && houses[i] > heaters[j] + tryR) j++; // �ҵ����ʵ��ұ߽�(����2)
            if (j < n2 && heaters[j] - tryR <= houses[i] && houses[i] <= heaters[j] + tryR) continue; // ����һ��house
            return false;
        }
        return true;
    }

//    �ⷨ����ͬ��˫ָ��
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
            if (curR < nxtR) { // max{���н������}
                radius = Math.max(radius, curR);
                i++;
            } else j++;
        }
        return radius;
    }

}
