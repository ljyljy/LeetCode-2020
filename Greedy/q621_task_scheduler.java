package Greedy;

import java.util.Arrays;

public class q621_task_scheduler {
    public int leastInterval(char[] tasks, int n) {
        int bucketSize = n + 1; // ͬ����A��idx=0,n+1,...
        int[] cnts = new int[26]; // ���������ĸ
        for(char task: tasks) {
            cnts[task - 'A']++;
        }
        Arrays.sort(cnts); // ����ȡ���ļ�ΪmaxFreq
        int maxFreq = cnts[25];
        int maxTypesCnt = 0; // ���Ƶ��maxFreq����Ӧ�Ĳ�ͬ������
        for (int cnt: cnts) {
            if (cnt == maxFreq) {
                maxTypesCnt++;
            }
        }
        int res1 = (maxFreq - 1) * bucketSize + maxTypesCnt; // res1:����ϡ��ʱ��������ȴʱ�䣩
        return Math.max(res1, tasks.length); // max(res1, �����ܼ�/��ȴʱ��ȫ��ռ������)
    }
}
