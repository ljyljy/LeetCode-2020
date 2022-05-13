package DataStructure.Map;

import java.util.*;
//import org.junit.Test;

public class q957_prison_cells_after_n_days {
    public int[] prisonAfterNDays(int[] cells, int n) {
        n = (n - 1) % 14 + 1;  // ��ѭ������1~14��;��15��=��1��ٵ�0�죡
        while (n > 0) {
            cells = calc(cells);
            n--;
        }
        return cells;
    }

    private int[] calc(int[] cells) {
        int len = cells.length;
        int[] res = new int[len];
        for (int i = 1; i <= len-2; i++) {
            res[i] = cells[i-1] == cells[i+1]? 1: 0;
//            res[i] = cells[i-1] ^ cells[i+1]; // ���� ��λ���
        }
//        System.out.println(Arrays.toString(res));
        return res;
    }


    private int testCycle(int[] cells) {
        Map<String, Integer> visited = new HashMap<>();
        System.out.println("0: " + Arrays.toString(cells));

        int len = cells.length;
        int[] res = new int[len];
        int[] prevRes = cells;
        int days = 1;
        while (days <= 257) { // cells��8λ�����2^8��״̬��һ������ֹ
            for (int i = 1; i <= len-2; i++) {
                res[i] = prevRes[i-1] == prevRes[i+1]? 1: 0;
            }
            System.out.println(days + ": " + Arrays.toString(res));
            days++;
            if (visited.containsKey(Arrays.toString(res))) {
                System.out.println("collision: " + Arrays.toString(res));
                System.out.println("meet cycle: " + days + " & " + visited.get(Arrays.toString(res)));
                return days;
            }
            visited.put(Arrays.toString(res), days);
            prevRes = res.clone(); // ǳ����
//            prevRes = Arrays.copyOf(res, len); // �����ֻ����ֵ
        }
        return -1;
    }

    public static void main(String[] args) {
        q957_prison_cells_after_n_days sol = new q957_prison_cells_after_n_days();
        int[] cells = {0,1,0,1,1,0,0,1};
        int n = 7;
        // ����ѭ������
        int circleDays = sol.testCycle(cells);
        System.out.println("circleDays = " + circleDays);
        // output
        int[] res = sol.prisonAfterNDays(cells, n);
        System.out.println(Arrays.toString(res));
    }
}
