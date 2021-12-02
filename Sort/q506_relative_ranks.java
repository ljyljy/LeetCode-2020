package Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class q506_relative_ranks {
    String[] medals = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] ans = new String[n];
        int[] sortedScore = score.clone();
        Arrays.sort(sortedScore); // Ĭ�����򣬳���תΪ��װ�� + Collections.reverseOrder()
        Map<Integer, Integer> map = new HashMap<>(); // <����, ����>
        for (int i = 0; i < n; i++)
            map.put(sortedScore[i], n-1-i); // ��������������sortedScore[0]==���ε�����һ

        for (int i = 0; i < n; i++) {
            int num = score[i];
            int rank = map.get(num);
            ans[i] = rank < 3? medals[rank] : String.valueOf(rank+1);
        }
        return ans;
    }
}
