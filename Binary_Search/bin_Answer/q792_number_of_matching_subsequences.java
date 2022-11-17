package Binary_Search.bin_Answer;

import java.util.*;

public class q792_number_of_matching_subsequences {
    public int numMatchingSubseq(String s, String[] words) {
        int n = s.length(), cnt = 0;
        Map<Character, List<Integer>> map = new HashMap<>(); // <c, s�ж�Ӧ�±�ļ��ϣ�������>
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            List<Integer> idxes = map.getOrDefault(c, new ArrayList<>());
            idxes.add(i);
            map.put(c, idxes);
        }
        for (String word : words) {
            boolean ok = true;
            int len = word.length(), curIdx = -1;
            // ��map�����α�������c��Ӧ��curIdx��������
            for (int i = 0; i < len && ok; i++) {
                char c = word.charAt(i);
                List<Integer> idxes = map.getOrDefault(c, new ArrayList<>());
                // �����֡� ��idxes�е�Ԫ��(c�±�)������[����]
                // �ҵ���һ��[����]curIdx���±꣬�����Ǹ���curIdx
                int start = 0, end = idxes.size() - 1;
                while (start < end) { // [L, mid], [mid+1, R]
                    int mid = start + end >> 1; // ������+1��midһ��Ҫ�������䡿��
                    if (idxes.get(mid) > curIdx) end = mid; // mid��������С
                    else start = mid + 1; // �������ʱ������������
                }
                if (end < 0 || idxes.get(start) <= curIdx) {
                    ok = false; // ��idxesΪ�գ���end<0��û�ҵ���Ӧ��c
                } else curIdx = idxes.get(start);
            }
            if (ok) cnt++;
        }
        return cnt;
    }
}
