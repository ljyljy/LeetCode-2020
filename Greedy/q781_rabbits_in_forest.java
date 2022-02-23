package Greedy;

import java.util.Arrays;

public class q781_rabbits_in_forest {
    // ��1��̰��: ģ��ⷨ
    // ������answers������cntʱ������Դ𰸵�Ӱ��Ӧ�õ�ans�У�ans += cnt + 1������������� cnt �� cnt ���к��ԡ�
    public int numRabbits(int[] answers) {
        Arrays.sort(answers); // ��������
        int n = answers.length;
        int total = 0;
        for (int i = 0; i < n; i++) {
            int cnt = answers[i];
            total += cnt + 1; // �ش����Լ�+������
            // ��������(cnt)��ans[i], ���±�i����cnt�Σ�������ͬ��ɫ������(�Ѹ��µ�total)
            int k = cnt;
            while (k-- > 0 && i+1 < n && answers[i] == answers[i+1]) i++;
        }
        return total;
    }
}
