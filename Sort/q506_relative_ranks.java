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
        Arrays.sort(sortedScore); // 默认升序，除非转为包装类 + Collections.reverseOrder()
        Map<Integer, Integer> map = new HashMap<>(); // <分数, 名次>
        for (int i = 0; i < n; i++)
            map.put(sortedScore[i], n-1-i); // 分数降序排名！sortedScore[0]==名次倒数第一

        for (int i = 0; i < n; i++) {
            int num = score[i];
            int rank = map.get(num);
            ans[i] = rank < 3? medals[rank] : String.valueOf(rank+1);
        }
        return ans;
    }
}
