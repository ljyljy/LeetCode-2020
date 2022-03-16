package DP;

public class q44_wildcard_matching {
    /*
    思路：动态规划， 定义二维dp数组，其中dp[i][j]表示s的前i个字符和p的前j个字符是否匹配，
        为了方便初始化，我们将s和p的长度均+1
        考虑到P中可能出现三种字符：普通字母(a-z)、'*'或者是'.', 则其动态转移方程分别是：
        1) 如果p[j]为普通字母，dp[i][j]==dp[i-1][j-1] and s[i]==p[j]
        2) 如果p[j]为通配符'./?'（s=”ab”, p=”a?”）, dp[i][j]==dp[i-1][j-1]
        3) 如果p[j]为'*', 则情况比较复杂, 分以下两种情况讨论：
           A. 以s="c", p="c*"为例，此时'*'匹配0次(j不保留,j-1)，dp[i][j]==dp[i][j-1]
           B. 以s="c[abc]", p="c[*]"为例，此时'*'匹配多次(j保留,i-1)，dp[i][j]==dp[i-1][j]
        ∴ A+B. 若p[j]==’*’:
              dp[i][j] = dp[i][j-1] or dp[i-1][j];
     */
    char[] ss, pp;
    public boolean isMatch(String s, String p) {
        // 为了解决s="a", p="[*]a"中*组合在p开头0次匹配的问题，需要额外初始化dp[0][:]。
        // 为此，在s前加“ ”，以便于s=" "与p="*"的匹配;
        // i>0, j=0时，dp[i][j]=false(∵'空p'不可能与'非空s'匹配,如s="a", p=" ")
        s = " " + s; p = " " + p; // ???
        ss = s.toCharArray(); pp = p.toCharArray();
        int nS = ss.length, nP = pp.length;
        boolean[][] dp = new boolean[nS][nP];// ???
        dp[0][0] = true; // 假定s和p都从空字符开始
        for (int i = 0; i < nS; i++) {
            for (int j = 1; j < nP; j++) { // dp[>0][j=0]一定是false，无需遍历
                if (pp[j] != '*') {
                    dp[i][j] = (i-1 >= 0 && dp[i-1][j-1]) && match(i, j);
                } else {
                    dp[i][j] = dp[i][j-1] || (i-1 >= 0 && dp[i-1][j]);
                }
            }
        }
        return dp[nS-1][nP-1];// ???
    }

    private boolean match(int i, int j) {
        // s与p对应字符相同 || p对应位置为通配符'./?'(必须匹配一个字符)
        return ss[i] == pp[j] || pp[j] == '?';
    }
}
