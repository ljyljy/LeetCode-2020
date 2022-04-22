package Greedy;

public class q134_gas_station {
//    д��2���������ο�LC_˼·2��
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; ) {
            int curLeft = 0, curCost = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n; // ���븳��ʱֵj������Ḳ��i��ֵ???
                curLeft += gas[j];
                curCost += cost[j];
                if (curLeft < curCost) {
                    break;
                }
                cnt++;
                // i = (i+cnt) % n; // ö�٣������ܷ�������һȦ��ѭ����ȡ�ࣩ
            }
            if (cnt == n) return i;
            i = i + cnt + 1; // ˼·���Ż�2
// ���α�����ʼ��(���޽��i+cnt��һλ��ʼretry)������ȡ��
            // �� (i+cnt)�������޽⣬˵���ô�cost�ܴ󣬴���һλ��ʼ��������(i+cnt)���ں�λ(�������ܸ����ʣ������curLeft)
        }
        return -1;
    }

    // д��2
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int remain = gas[i], j = i;
            while (remain >= cost[j]) {
                int newJ = (j + 1) % n;
                remain = remain - cost[j] + gas[newJ];
                j = newJ;

                if (j == i) return i;
            }
            if (j < i) return -1;
            i = j;
        }
        return -1;
    }
}
