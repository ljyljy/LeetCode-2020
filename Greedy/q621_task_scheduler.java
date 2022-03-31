package Greedy;

import java.util.Arrays;

public class q621_task_scheduler {
    public int leastInterval(char[] tasks, int n) {
        int bucketSize = n + 1; // 同类型A的idx=0,n+1,...
        int[] cnts = new int[26]; // 与任务的字母
        for(char task: tasks) {
            cnts[task - 'A']++;
        }
        Arrays.sort(cnts); // 降序，取最后的即为maxFreq
        int maxFreq = cnts[25];
        int maxTypesCnt = 0; // 最大频数maxFreq所对应的不同任务数
        for (int cnt: cnts) {
            if (cnt == maxFreq) {
                maxTypesCnt++;
            }
        }
        int res1 = (maxFreq - 1) * bucketSize + maxTypesCnt; // res1:任务稀疏时（存在冷却时间）
        return Math.max(res1, tasks.length); // max(res1, 任务密集/冷却时间全被占满抵消)
    }
}
