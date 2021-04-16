package DP;

import java.util.HashMap;

public class q87_scramble_string {
    // 法2（推荐）：动态规划
    boolean[][][] dp;
    public boolean isScramble(String s1, String s2) {
        if (s1.isEmpty() || s2.isEmpty()) return false;
        // 1. 比较len
        int n = s1.length(), n2 = s2.length();
        if (n != n2) return false;
        // 2. 比较charCnts：每个字母出现的次数是否一致
        if (s1.equals(s2)) return true;
        int[] charCnts = getCharCnts(s1, s2);
        // ↓ 全0未必是扰动！但非全0一定不是！
        if (!AllZeros(charCnts)) return false;
        dp = new boolean[n+1][n][n];
        //遍历所有的字符串长度
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                for (int j = 0; j + len <= n; j++){
                    if (len == 1)
                        dp[len][i][j] = (s1.charAt(i) == s2.charAt(j));
                    else { //遍历切割后的左半部分长度
                        for (int q = 1; q < len; q++) {
                            dp[len][i][j] = dp[q][i][j] && dp[len-q][i+q][j+q]
                                    || dp[q][i][j+len-q] && dp[len-q][i+q][j];
                            //如果当前是 true 就 break，防止被覆盖为 false
                            if (dp[len][i][j]) break;
                        }
                    }
                }
            }
        }
        return dp[n][0][0];
    }

    private int[] getCharCnts(String s1, String s2) {
        int[] charCnts = new int[26]; // 优化：数组代替哈希
        for (int i = 0; i < s1.length(); i++) { // 或 i < n2
            charCnts[s1.charAt(i) - 'a']++;
            charCnts[s2.charAt(i) - 'a']--;
        }
        return charCnts;
    }

    private boolean AllZeros(int[] cnts) {
        for (int cnt : cnts)
            if (cnt != 0) return false;
        // 如果两个字符串的字母出现不一致直接返回 false
        return true;
    }

    // 法1：TLE（递归1）
    // https://leetcode-cn.com/problems/scramble-string/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-1-2/
    public boolean isScramble_TLE(String s1, String s2) {
        if (s1.isEmpty() || s2.isEmpty()) return false;
        // 1. 比较len
        int n1 = s1.length(), n2 = s2.length();
        if (n1 != n2) return false;
        // 2. 比较charCnts：每个字母出现的次数是否一致
        if (s1.equals(s2)) return true;
        int[] charCnts = getCharCnts(s1, s2);
        // ↓ 全0未必是扰动！但非全0一定不是！
        if (!AllZeros(charCnts)) return false;

        // 3. 遍历所有切割点
        for (int i = 0; i < n1; i++) {
            //对应情况 1 ，判断 S1 的子树能否变为 S2 相应部分 (// x + y)
            if (isScramble_TLE(s1.substring(0, i), s2.substring(0, i)) &&
                    isScramble_TLE(s1.substring(i), s2.substring(i)))
                return true;
            //对应情况 2 ，S1 两个子树先进行了交换，然后判断 S1 的子树能否变为 S2 相应部分
            // s1[0,i] -- s2[n2-i,end];  s1[i:end] -- s2[0: n2-i] ( // y + x)
            if (isScramble_TLE(s1.substring(0, i), s2.substring(n2-i)) &&
                    isScramble_TLE(s1.substring(i), s2.substring(0, n2-i)))
                return true;
        }
        return false;
    }

    // 法1-2：memo优化(TLE)
    public boolean isScramble_TLE2(String s1, String s2) {
        HashMap<String, Integer> memo = new HashMap<>();
        return dfs(s1, s2, memo);
    }

    private boolean dfs(String s1, String s2, HashMap<String, Integer> memo) {
        String keyStr = s1 + "#" + s2;
        int n1 = s1.length(), n2 = s2.length();
        //判断之前是否已经有了结果(1/0: T/F, -1: 尚未计算)
        int ret = memo.getOrDefault(keyStr, -1);
        if (ret == 1) return true;
        else if (ret == 0) return false;
        // 【ret == -1】 ↓ （计算并memo.put）
        if (n1 != n2) { // 1. 比较len
            memo.put(keyStr, 0);
            return false;
        } // 2. 比较charCnts：每个字母出现的次数是否一致
        if (s1.equals(s2)) {
            memo.put(keyStr, 1);
            return true;
        }
        int[] charCnts = getCharCnts(s1, s2);
        if (!AllZeros(charCnts)) {
            // ↓ 全0未必是扰动！但非全0一定不是！
            memo.put(keyStr, 0);
            return false;
        }
        // 3. 遍历所有切割点
        for (int i = 0; i < n1; i++) {
            //对应情况 1 ，判断 S1 的子树能否变为 S2 相应部分
            if (isScramble_TLE2(s1.substring(0, i), s2.substring(0, i)) &&
                    isScramble_TLE2(s1.substring(i), s2.substring(i))){
                memo.put(keyStr, 1);
                return true;
            }

            //对应情况 2 ，S1 两个子树先进行了交换，然后判断 S1 的子树能否变为 S2 相应部分
            // s1[0,i] -- s2[n2-i,end];  s1[i:end] -- s2[0: n2-i]
            if (isScramble_TLE2(s1.substring(0, i), s2.substring(n2-i)) &&
                    isScramble_TLE2(s1.substring(i), s2.substring(0, n2-i))){
                memo.put(keyStr, 1);
                return true;
            }
        }
        memo.put(keyStr, 0);
        return false;
    }

    //  法1-3：数组代替Hash（4维） + substring -> s.toCharArray()
    // https://leetcode-cn.com/problems/scramble-string/solution/cong-di-gui-dao-ji-yi-you-hua-di-gui-dao-pt30/
    private byte[][][][] memo1;
    public boolean isScramble_1_3(String s1, String s2) {
        int n = s1.length();
        memo1 = new byte[n][n][n][n];
        return helper(s1.toCharArray(), 0, n-1,
                      s2.toCharArray(), 0, n-1);
    }

    private boolean helper(char[] s1, int start1, int end1,
                           char[] s2, int start2, int end2) {
        if (start1 == end1) return s1[start1] == s2[start2];
        if (memo1[start1][end1][start2][end2] != 0)
            return memo1[start1][end1][start2][end2] > 0;
        for (int i = 0; i < end1 - start1; i++) {
            // 情况1：x+y
            if (helper(s1, start1, start1 + i,
                       s2, start2, start2 + i) &&
                helper(s1, start1 + i + 1, end1,
                       s2, start2 + i + 1, end2)){
                memo1[start1][end1][start2][end2] = 1;
                return true;
            }
            // 情况2：y+x(交换)
            if (helper(s1, start1, start1 + i,
                       s2, end2 - i, end2) &&
                helper(s1, start1 + i + 1, end1,
                       s2, start2, end2 - i - 1)){
                memo1[start1][end1][start2][end2] = 1;
                return true; // 情况1：x+y
            }
        }
        memo1[start1][end1][start2][end2] = -1;
        return false;
    }

    // 法1-4：三、减少一个维度
    //因为每次递归过程中start1~end1 和start2~end2的长度是相等的，则无需四个维度，三个维度即可唯一确定，即可以减少一个维度，改用end1 - start1代替
    // 略
}
