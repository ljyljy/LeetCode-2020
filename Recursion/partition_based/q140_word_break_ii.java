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

//        // 法2，纯字符串分割，不加dp优化
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
            res.add(String.join(" ", path)); // Deque<String> path的合并
            return ;
        }

        // 写法2：可以拆分的左边界从 len - 1 依次枚举到 0?
        // dp[i]是从0~i-1匹配，分为dp[i] & s[i:idx];
        for (int i = idx-1; i >= 0; i--) {
            String suffix = s.substring(i, idx);
            if (dp[i] && wordSet.contains(suffix)) {
                path.push(suffix); // addFirst： 必须FILO！栈！
                dfs(s, i); // 不是i-1！对[i, idx]可以重复分割多种情况(类比q39：可以重复使用??)
                path.pop(); // removeFirst：弹栈
            }
        }

        // // 写法1(WA! 路径顺序反了！)
        // for (int i = idx+1; i <= n; i++) {
        //     String suffix = s.substring(idx, i);
        //     if (dp[idx] && wordSet.contains(suffix)) {
        //         path.push(suffix); // addFirst： 必须FILO！栈！
        //         dfs(s, i);
        //         path.pop(); // removeFirst：
        //     }
        // }
    }


    // 法2：纯字符串分割，不加dp优化【简短】
    Map<String, List<String>> memo = new HashMap<>();
    private List<String> dfs2(String s) {
        if (memo.containsKey(s)) return memo.get(s);
        if (s.equals("")) {
            // res.add(String.join(" ", path));
            return new ArrayList<>();
        }
        List<String> curList = new ArrayList<>();
        // 写法2：可以拆分的左边界从 len - 1 依次枚举到 0?
        // dp[i]是从0~i-1匹配，分为dp[i] & s[i:idx];
        for (int i = 1; i <= s.length(); i++) {
            String pre = s.substring(0, i), nxt = s.substring(i);
            if (wordSet.contains(pre)) {
                List<String> nxtList = dfs2(nxt);
                for (String nxtStr: nxtList) {
                    // ?分治：后面切割的Str得到的结果不全！需要加上本层结果，构造curList
                    curList.add(pre + " " + nxtStr);
                }
            }
        }
        // 勿漏！最后的单词：nxt为空，pre==s时（如: s=dog, nxt=""）
        if (wordSet.contains(s)) curList.add(s);
        memo.put(s, curList);
        return curList;
    }
}
