package Array.Interval_Intersection;

import java.util.ArrayList;
import java.util.List;

public class q763_partition_labels {
    // 贪心
    public List<Integer> partitionLabels(String s) {
        List<Integer> lens = new ArrayList<>();
        int[] last = new int[26]; // 每个字母最后一次出现的idx
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            last[ch - 'a'] = i;
        }

        int start = 0, end = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            end = Math.max(end, last[ch - 'a']);
            if (i == end) {
                lens.add(end - start + 1);
                start = end + 1;
            }
        }
        return lens;
    }
}
