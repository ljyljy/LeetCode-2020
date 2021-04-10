package Recursion.partition_based;

import java.util.*;

public class q131_palindrome_partitioning {
    // 法1：未优化版 - 普通dfs（切割）
    List<List<String>> res = new ArrayList<>();
    Deque<String> path = new ArrayDeque<>();
    public List<List<String>> partition_v1(String s) {
        if (s.isEmpty() || s == null) return res;
        char[] chars = s.toCharArray();
//        Arrays.sort(chars); // ×❤不可排序！因为要求切割顺序是“保序”的!
        dfs(chars, 0);
        return res;
    }

    private void dfs(char[] chars, int idx) {
        if (idx == chars.length) { // idx即为切割下标(转化❤)
            res.add(new ArrayList<>(path));
            return;
        }
        // 切割子串s[idx:i] = s.substring(idx, i+1) -- 左闭右开
        for (int i = idx; i < chars.length; i++) {
            if (!checkPalindrome(chars, idx, i)) continue;
            //        或  ↓ subStr = s.substring(idx, i+1) -- 左闭右开
            path.addLast(new String(chars, idx, i - idx + 1));
            dfs(chars, i + 1);
            path.removeLast();
        }
    }

    // 截取字符串substr消耗性能，故采用[传下标]的方式判断↓
    // - 时间复杂度是 O(N)，
    // 待优化: 先采用动态规划，把回文子串的结果记录在一个表格里 / Manacher算法
    private boolean checkPalindrome(char[] chars, int start, int end) {
        for (int i = start, j = end; i <= j; i++,j--) {
            if (chars[i] != chars[j]) return false;
        }
        return true;
    }

    // 法2：【优化版 - 推荐】 - dp[i][j] + dfs（切割）
    public List<List<String>> partition_v2(String s) {
        int n = s.length();
        if (n == 0) return res;
        char[] chars = s.toCharArray();
        boolean[][] dp = get_dp(chars, n); // 预处理dp：回文判断-优化
        dfs_v2(chars, 0, dp, path);
        return res;
    }

    private boolean[][] get_dp(char[] chars, int n) {
        boolean[][] dp = new boolean[n][n];// 状态：dp[i][j] 表示 s[i][j] 是否是回文
        // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
        for (int j = 0; j < n; j++) { // 按列递归（纵向）
            // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
            for (int i = 0; i <= j; i++) { // ∵按列递归 ∴dp[:][j-1]合法↓
                if (chars[i] == chars[j] && (j - i <= 2 || dp[i+1][j-1]))
                    dp[i][j] = true;
                // (1) j==i: 单字符, 形如"a"√
                // (2) j-i==1: 双字符且相同, 形如"aa"√
                // (3) j-i==2: 形如"aba"√
                // (4) j-i>2 但去除首尾ij后的子串是回文√,形如"a(回文)a"
            }
        }
        return dp;
    }

    private void dfs_v2(char[] chars, int idx, boolean[][] dp, Deque<String> path) {
        if (idx == chars.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i < chars.length; i++) {
            if (!dp[idx][i]) continue; // 回文判断，否则子串s[idx, i]是回文↓
            path.addLast(new String(chars, idx, i-idx+1));
            dfs_v2(chars, i+1, dp, path); // ∵不可重复分割 ∴i+1
            path.removeLast();
        }
    }

    // 法3：【进一步优化版 - 不推荐 有疑问】 - memo[] + dp[][] + dfs（切割）
    // 法2+记忆化搜索， 如a, b, a, xxxx 和 aba, xxxx -> xxxx部分重复，可以用memo优化。
    public List<List<String>> partition_v3(String s) {
        int n = s.length();
        if (n == 0) return res;
        char[] chars = s.toCharArray();
        boolean[][] dp = get_dp(chars, n);
        // Map<分割idx, 子串s[idx:]的分割列表>
        Map<Integer, List<List<String>>> memo = new HashMap<>();
        return dfs_v3(chars, 0, dp, memo);
    }

    private List<List<String>> dfs_v3(char[] chars, int idx, boolean[][] dp,
                                      Map<Integer, List<List<String>>> memo) {
        if (idx == chars.length) {
//            List<List<String>> res = new ArrayList<>();
//            res.add(new ArrayList<>());
//            return res;// WHY??? 写成return new ArrayList<>()结果为[]?
            /* TODO: TEST
            for (int i: memo.keySet()) {
                for (List<String> path : memo.get(i)){
                    System.out.println("key=" + i + ", path=" + path);
                    // ↑ 打印：idx=s.length(), memo=[]
//                    memo.getOrDefault(i, new ArrayList<>());
                }
            }

            return new ArrayList<>(){{
                add(new ArrayList<>(){{add("ljy");}});
                }};
             */
            return new ArrayList<>(){{add(new ArrayList<>());}};
            //↑ memo.get(idx)首次递归到最下层，分割idx已到末尾，path后缀空集即可
        }
        if (memo.containsKey(idx))
            return memo.get(idx);
        else memo.put(idx, new ArrayList<>()); // 接着for-横向遍历 & dfs-纵向遍历

        for (int i = idx; i < chars.length; i++) {
            if (! dp[idx][i]) continue; // 否则，是回文, dfs下探↓
            List<List<String>> res_next = dfs_v3(chars, i+1, dp, memo); // 下层分割的所有子方案
            for (List<String> path: res_next) {
                // path: 某一条完整的可行路径，如["aa", "b"] 或 ["a","a","b"]
                List<String> list = new ArrayList<>();
                list.add(new String(chars, idx, i-idx+1)); // 本层结果，或s.substring(idx, i+1);
                list.addAll(path); // 下层结果，类似于extend; ↓ list: 某一种情况List<String>
                memo.get(idx).add(list); // 每多一种情况，就向new ArrayList<~<Str>>()嵌套一个list
                //       ↑ 不是i！是idx！ （不是memo.get(i)!）
                // ❤ ↑ 理解：将回文子串s[idx, i+1](下探)的答案 扩充至 s[idx, i]的答案中
            }
        }
        return memo.get(idx);
    }
}
