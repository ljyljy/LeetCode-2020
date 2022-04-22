package Recursion.partition_based;

import java.util.*;

public class q132_palindrome_partitioning_ii {

    // List<List<String>> res = new ArrayList<>();
    Deque<String> path = new ArrayDeque<>();
    private int cnt = Integer.MAX_VALUE;

    // 法1：【TLS超时 不可用】：沿用q131的方法： dp + dfs
    public int minCut_TLS1(String s) {
        int n = s.length();
        if (n <= 1) return 0; // "" or "a"无需分割，自成回文
        char[] chars = s.toCharArray();
        boolean[][] dp = get_dp(chars, n);
        dfs(chars, 0, dp);
        return cnt;
    }

    private void dfs(char[] chars, int idx, boolean[][] dp) {
        if (cnt < path.size()-1) return;
        if (idx == chars.length) {
            // res.add(new ArrayList<>(path));
            if(path.size() != 0 && cnt > path.size()-1)
                cnt = path.size()-1;
            return;
        }
        for (int i = idx; i < chars.length; i++) {
            if (!dp[idx][i]) continue;
            if (cnt < path.size()+1) return;
            path.addLast(new String(chars, idx, i-idx+1));
            dfs(chars, i+1, dp);
            path.removeLast();
        }
    }

    // 法2【超时】：dfs + memo
    public int minCut_TLS2(String s) {
        int n = s.length();
        if (n <= 1) return 0; // "" or "a"无需分割，自成回文
        char[] chars = s.toCharArray();
        boolean[][] dp = get_dp(chars, n);
        Map<Integer, List<List<String>>> memo = new HashMap<>(); // map<idx, 最小分割次数>
        dfs_memo(chars, 0, dp, memo);
        for (List<String> path: memo.get(0)){ // 分割idx==0时(回溯到根，idx=n是递归最底层的叶子)
            cnt = Math.min(cnt, path.size()-1);
        }
        return cnt;
    }

    private List<List<String>> dfs_memo(char[] chars, int idx, boolean[][] dp,
                                        Map<Integer, List<List<String>> > memo) {
        if (idx == chars.length) {
            return new ArrayList<>(){{add(new ArrayList<>());}};
        }
        if (memo.containsKey(idx)) return memo.get(idx);
        else memo.put(idx, new ArrayList<>());

        for (int i = idx; i < chars.length; i++) {
            if (!dp[idx][i]) continue;
            List<List<String>> nextPaths = dfs_memo(chars, i+1, dp, memo);
            for (List<String> nextPath: nextPaths) {
                List<String> list = new ArrayList<>();
                list.add(new String(chars, idx, i-idx+1));
                list.addAll(nextPath);
                memo.get(idx).add(list);
            }
        }
        return memo.get(idx);
    }

    // 法3【推荐】：2次dp
    public int minCut(String s) {
        int n = s.length();
        if (n <= 1) return 0; // "" or "a"无需分割，自成回文
        char[] chars = s.toCharArray();
        boolean[][] dp = get_dp(chars, n);
        int[] f = new int[n]; // ↓ f[i]初始化为i（分割为单字符）/ 大数
        for (int i = 0; i < n; i++) f[i] = i; // Integer.MAX_VALUE

        // 状态递推 f[i] = min(f[j])+1, j ∈ [0, i-1]
        for (int i = 1; i < n; i++) {
            if (dp[0][i]) { // 子串s[0,i]是回文, 最小分割数=0
                f[i] = 0;
                continue;
            } // 未跳过，说明s[0,i]不是回文(如:"cabb")，
            // 则需要在其子串【分割点j】中:(1) j后-找s[j+1,i]回文("bb")
            // (2) j前-最小分割数f[j] -> ∵分割点j ∴f[j]+1
            for (int j = 0; j < i; j++) {
                // s[0,i]不是回文(如:"aab", "ababcb")，需要在其子串中找回文("aa"/"aba")+1("b"/"bcb")
                if (dp[j+1][i]) // 前提：
                    f[i] = Math.min(f[i], f[j]+1);  // 此处'+1'指代回文子串s[j+1, i]
            }
        }
        return f[n-1];
    }

    private boolean[][] get_dp(char[] chars, int n) {
        boolean[][] dp = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (chars[i] == chars[j] && (j - i <= 2 || dp[i+1][j-1]))
                    dp[i][j] = true;
            }
        }
        return dp;
    }

}
