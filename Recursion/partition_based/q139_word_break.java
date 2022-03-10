package Recursion.partition_based;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class q139_word_break {
    // ��2: DP����ȫ����, ����(��)/��Ͼ��ɡ�
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {// ������������s
            for (int j = i+1; j <= n; j++) {// ��Ʒ���Ӵ�[i, j)
                String subStr = s.substring(i, j);
                if (dp[i] && wordDict.contains(subStr)) {
                    dp[j] = true;
                }
            }
        }
        return dp[n];
    }


    private Map<String, Boolean> memo = new HashMap<>();
    // ��1-2: DFS
    public boolean wordBreak_dfs2(String s, List<String> wordDict) {
        return dfs2(s, wordDict, 0, memo);
    }

    private boolean dfs2(String s, List<String> wordDict, int idx,
                         Map<String, Boolean> memo) {
        String key = idx + "";
        if (idx >= s.length()) {
            memo.put(key, true);
            return true;
        }
        if (memo.containsKey(key)) return memo.get(key);

        for (int i = idx; i < s.length(); i++) {
            String prev = s.substring(idx, i+1);
            if (!wordDict.contains(prev)) continue;
            if (dfs2(s, wordDict, i+1, memo)) {
                memo.put(key, true);
                return true;
            }
        }
        memo.put(key, false);
        return false;
    }

    // ��1-1: DFS+memo
    public boolean wordBreak_dfs1(String s, List<String> wordDict) {
        return dfs(s, wordDict, memo);
    }

    private boolean dfs(String s, List<String> wordDict,
                        Map<String, Boolean> memo) {
        if (s.equals("")) return true;
        String key = s;
        if (memo.containsKey(key)) return memo.get(key);
        for (int i = 1; i <= s.length(); i++) {
            String prev = s.substring(0, i), nxt = s.substring(i, s.length());
            // System.out.println("prev="+prev + ", nxt=" + nxt);
            if (!wordDict.contains(prev)) continue;
            if (dfs(nxt, wordDict, memo)) {
                memo.put(nxt, true);
                return true;
            }
        }
        memo.put(key, false);
        return false;
    }
}
