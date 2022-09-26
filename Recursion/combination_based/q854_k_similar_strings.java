package Recursion.combination_based;

import java.util.*;

public class q854_k_similar_strings {
    // BFS���Ƽ���
    public int kSimilarity(String s1, String s2) {
        int n = s1.length();
        Queue<String> queue = new ArrayDeque<>(); // key = str_idx(str��s2�ƥ��idx)
        // <�м�����str, ���������±�idx��Ŀǰ�Եõ�str[0:idx) = s2[0:idx), ��s1->strת����step�Σ�>
        queue.offer(s1 + "_" + 0);
        Set<String> visited = new HashSet<>();
        visited.add(s1);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curKey = queue.poll();
                String[] item = curKey.split("_");
                String curStr = item[0];
                int curIdx = Integer.valueOf(item[1]); // s2��curStr�׸���һ�����ַ����±�
                if (s2.equals(curStr)) return step;
                // Ӧ��case: "bcbabc" & "bcbbca", ͷ����Ϊbcb(�����ƶ�)��curIdxӦ����
                while (curIdx < n && s2.charAt(curIdx) == curStr.charAt(curIdx)) {
                    curIdx++; // Ų��curStr��s2��һ������ȵ��±�
                }
                char pattern = s2.charAt(curIdx); // ģʽ�ַ�
                // ��s1[idx+1, n)�У����Ҳ�ѡ����s2[idx]��ͬ���ַ�������һ��
                //   ��curStr[:idx+1]=s2[:idx+1]�������м���<str, idx>, step++
                for (int j = curIdx+1; j < n; j++) {
                    // ��֦��������ͬ=û��Ҫ��������ͽ��step��������
                    if (curStr.charAt(j) == s2.charAt(j)) continue;

                    // �ҵ���pattern��ͬ���ַ���������curStr[:curIdx+1]==s2[:curIdx]�����㵱ǰnxtStr����С��������
                    if (curStr.charAt(j) == pattern) {
                        String nxtStr = swap(curStr, curIdx, j);
                        if (!visited.contains(nxtStr)) {
                            String nxtKey = nxtStr + "_" + (curIdx+1);
                            queue.offer(nxtKey);
                            visited.add(nxtStr);
                            // if (s2.equals(nxtStr)) return curIdx+1; // WA! ������ǰreturn����һ��min��
                        }
                    }
                }
            }
            // curStr[curIdx:]�У���pattern��ͬ�������ַ���������ϣ���������һϵ���µ��м��ַ���nxtStr
            step++; // ������������step++
        }
        return step;
    }

    private String swap(String s, int i, int j) {
        char[] chs = s.toCharArray();
        char c = chs[i];
        chs[i] = chs[j];
        chs[j] = c;
        return new String(chs);
    }

    // ��2��A* ��todo��https://leetcode.cn/problems/k-similar-strings/solution/by-ac_oier-w8nf/��
    int n;
    String t;
    int f(String s) {
        int ans = 0;
        for (int i = 0; i < n; i++) ans += s.charAt(i) != t.charAt(i) ? 1 : 0;
        return ans + 1 >> 1;
    }

    public int kSimilarity2(String s1, String s2) {
        if (s1.equals(s2)) return 0;
        t = s2;
        n = s1.length();
        Map<String, Integer> map = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
            int v1 = f(a), v2 = f(b), d1 = map.get(a), d2 = map.get(b);
            return (v1 + d1) - (v2 + d2);
        });
        map.put(s1, 0);
        pq.add(s1);
        while (!pq.isEmpty()) {
            String poll = pq.poll();
            int step = map.get(poll);
            char[] cs = poll.toCharArray();
            int idx = 0;
            while (idx < n && cs[idx] == t.charAt(idx)) idx++;
            for (int i = idx + 1; i < n; i++) {
                if (cs[i] != t.charAt(idx) || cs[i] == t.charAt(i)) continue;
                swap(cs, idx, i);
                String nstr = String.valueOf(cs);
                swap(cs, idx, i);
                if (map.containsKey(nstr) && map.get(nstr) <= step + 1) continue;
                if (nstr.equals(t)) return step + 1;
                map.put(nstr, step + 1);
                pq.add(nstr);
            }
        }
        return -1; // never
    }

    void swap(char[] cs, int i, int j) {
        char c = cs[i];
        cs[i] = cs[j];
        cs[j] = c;
    }


    // DFS��todo��
    int ans;
    public int kSimilarity_DFS(String s1, String s2) {
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                str1.append(s1.charAt(i));
                str2.append(s2.charAt(i));
            }
        }
        if (str1.length() == 0) {
            return 0;
        }
        ans = str1.length() - 1;
        dfs(0, 0, str1.length(), str1.toString(), str2.toString());
        return ans;
    }

    public void dfs(int pos, int cost, int len, String str1, String str2) {
        if (cost > ans) {
            return;
        }
        while (pos < str1.length() && str1.charAt(pos) == str2.charAt(pos)) {
            pos++;
        }
        if (pos == str1.length()) {
            ans = Math.min(ans, cost);
            return;
        }
        /* ��ǰ״̬�Ľ����������޴��ڵ��ڵ�ǰ����С�������� */
        if (cost + minSwap(str1, str2, pos) >= ans) {
            return;
        }
        for (int i = pos + 1; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(pos)) {
                String str1Next = swap(str1, i, pos);
                dfs(pos + 1, cost + 1, len, str1Next, str2);
            }
        }
    }

    public int minSwap(String s1, String s2, int pos) {
        int tot = 0;
        for (int i = pos; i < s1.length(); i++) {
            tot += s1.charAt(i) != s2.charAt(i) ? 1 : 0;
        }
        return (tot + 1) / 2;
    }
}
