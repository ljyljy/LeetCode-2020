package Recursion.partition_based;

import java.util.*;

public class q140_word_break_ii {
    boolean[] dp;
    int n;
    List<String> res = new ArrayList<>();
    Deque<String> path = new ArrayDeque<>();
    Set<String> wordSet;
    public List<String> wordBreak(String s, List<String> wordDict) {
        n = s.length();  wordSet = new HashSet<>(wordDict);
        getDP(s);
        if (dp[n]) dfs(s, n);
        return res;

//        // ��2�����ַ����ָ����dp�Ż�
//        return dfs2(s);
    }

    private void getDP (String s) {
        dp = new boolean[n+1];
        dp[0] = true;
        int len = n;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j <= n; j++) {
                String subStr = s.substring(i, j);
                if (dp[i] && wordSet.contains(subStr)) {
                    dp[j] = true;
                }
            }
        }
    }

    private void dfs(String s, int idx) {
        if (idx == 0) {
            res.add(String.join(" ", path)); // Deque<String> path�ĺϲ�
            return ;
        }

        // д��2�����Բ�ֵ���߽�� len - 1 ����ö�ٵ� 0?
        // dp[i]�Ǵ�0~i-1ƥ�䣬��Ϊdp[i] & s[i:idx];
        for (int i = idx-1; i >= 0; i--) {
            String suffix = s.substring(i, idx);
            if (dp[i] && wordSet.contains(suffix)) {
                path.push(suffix); // addFirst�� ����FILO��ջ��
                dfs(s, i); // ����i-1����[i, idx]�����ظ��ָ�������(���q39�������ظ�ʹ��??)
                path.pop(); // removeFirst����ջ
            }
        }

        // // д��1(WA! ·��˳���ˣ�)
        // for (int i = idx+1; i <= n; i++) {
        //     String suffix = s.substring(idx, i);
        //     if (dp[idx] && wordSet.contains(suffix)) {
        //         path.push(suffix); // addFirst�� ����FILO��ջ��
        //         dfs(s, i);
        //         path.pop(); // removeFirst��
        //     }
        // }
    }


    // ��2�����ַ����ָ����dp�Ż�����̡�
    Map<String, List<String>> memo = new HashMap<>();
    private List<String> dfs2(String s) {
        if (memo.containsKey(s)) return memo.get(s);
        if (s.equals("")) {
            // res.add(String.join(" ", path));
            return new ArrayList<>();
        }
        List<String> curList = new ArrayList<>();
        // д��2�����Բ�ֵ���߽�� len - 1 ����ö�ٵ� 0?
        // dp[i]�Ǵ�0~i-1ƥ�䣬��Ϊdp[i] & s[i:idx];
        for (int i = 1; i <= s.length(); i++) {
            String pre = s.substring(0, i), nxt = s.substring(i);
            if (wordSet.contains(pre)) {
                List<String> nxtList = dfs2(nxt);
                for (String nxtStr: nxtList) {
                    // ?���Σ������и��Str�õ��Ľ����ȫ����Ҫ���ϱ�����������curList
                    curList.add(pre + " " + nxtStr);
                }
            }
        }
        // ��©�����ĵ��ʣ�nxtΪ�գ�pre==sʱ����: s=dog, nxt=""��
        if (wordSet.contains(s)) curList.add(s);
        memo.put(s, curList);
        return curList;
    }
}
