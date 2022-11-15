package Array.Sweep_Line;

import java.util.Map;
import java.util.TreeMap;

public class q1094_car_pooling {
    // ��ȣ�ɨ����q218��1109
    // ��1���������
    public boolean carPooling(int[][] trips, int capacity) {
        int[] sum = new int[1001];
        for (int i = 0; i < trips.length; i++) {
            int cnt = trips[i][0], t_start = trips[i][1], t_end = trips[i][2];
            sum[t_start] += cnt;
            sum[t_end] -= cnt;
        }
        int totalWeight = 0;
        for (int i = 0; i < 1001; i++) {
            totalWeight += sum[i];
            if (totalWeight > capacity) {
                return false;
            }
        }
        return true;
    }


    // ��2��ǰ׺�� + С����/TreeMap - TreeMap<time(����), cnt�˿���>
    //      trips[cnt, time_start(+), time_end(-)]
    public boolean carPooling2(int[][] trips, int capacity) {
        TreeMap<Integer, Integer> tmap = new TreeMap<>(); // Ĭ�ϣ���key��������
        for (int[] trip : trips) {
            int cnt = trip[0], t_start = trip[1], t_end = trip[2];
            tmap.put(t_start, tmap.getOrDefault(t_start, 0) + cnt);
            tmap.put(t_end, tmap.getOrDefault(t_end, 0) - cnt);
        }
        int totalCnt = 0;
        for (Map.Entry<Integer, Integer> entry : tmap.entrySet()) {
            totalCnt += entry.getValue(); // ��ǰ׺��
            if (totalCnt > capacity) {
                return false;
            }
        }
        return true;
    }

    // c-��1��������飡
    // C-��2��ɨ���� = TreeMap / С���� + ǰ׺�͡���ȣ�q218ɨ���ߡ�
    //      ����map����key����HASH_SORT--q451���Ȳ���
}
