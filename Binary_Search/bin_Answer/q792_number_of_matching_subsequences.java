package Binary_Search.bin_Answer;

import java.util.*;

public class q792_number_of_matching_subsequences {
    public int numMatchingSubseq(String s, String[] words) {
        int n = s.length(), cnt = 0;
        Map<Character, List<Integer>> map = new HashMap<>(); // <c, s中对应下标的集合（递增）>
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            List<Integer> idxes = map.getOrDefault(c, new ArrayList<>());
            idxes.add(i);
            map.put(c, idxes);
        }
        for (String word : words) {
            boolean ok = true;
            int len = word.length(), curIdx = -1;
            // 从map中依次遍历，找c对应的curIdx（递增）
            for (int i = 0; i < len && ok; i++) {
                char c = word.charAt(i);
                List<Integer> idxes = map.getOrDefault(c, new ArrayList<>());
                // 【二分】 对idxes中的元素(c下标)，进行[二分]
                // 找到第一个[大于]curIdx的下标，并覆盖更新curIdx
                int start = 0, end = idxes.size() - 1;
                while (start < end) { // [L, mid], [mid+1, R]
                    int mid = start + end >> 1; // 【不可+1，mid一定要在左区间】！
                    if (idxes.get(mid) > curIdx) end = mid; // mid还可以缩小
                    else start = mid + 1; // 包括相等时，往右区间找
                }
                if (end < 0 || idxes.get(start) <= curIdx) {
                    ok = false; // 若idxes为空，则end<0，没找到对应的c
                } else curIdx = idxes.get(start);
            }
            if (ok) cnt++;
        }
        return cnt;
    }
}
