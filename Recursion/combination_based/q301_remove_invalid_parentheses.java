package Recursion.combination_based;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class q301_remove_invalid_parentheses {
     Set<String> set = new HashSet<>();
     int n, maxScore, ansLen;
     String s;

    // 法1：dfs+剪枝预处理--[两重剪枝]（1-可匹配的最大score，2-最终答案长度）
    // 时间：O(n * 2^n), 空间：O(n)
    // 时间复杂度:(1) 预处理max和len 的复杂度为O(n);
    // (2) 不考虑剪枝效果，最坏情况下，每个位置都有两种选择，搜索所有方案的复杂度为O(2^n);
    // (3) 同时搜索过程中会产生的新字符串（最终递归树中叶子节点的字符串长度最大为n，使用StringBuilder 也是同理)，复杂度为O(n)。
    public List<String> removeInvalidParentheses1(String _s) {
         s = _s; n = s.length();
         int cntL = 0, cntR = 0;
         for (char c: s.toCharArray()) { // O(n)
             if (c == '(') cntL++;
             else if (c == ')') cntR++;
         }
         // 剪枝1：可匹配的左/右括号的数量，dfs时，有效解的maxScore
         maxScore = Math.min(cntL, cntR);

         int invalidL = 0, invalidR = 0;
         for (char c: s.toCharArray()) { // O(n)
             if (c == '(') invalidL++;
             else if (c == ')') {
                 if (invalidL > 0) invalidL--;
                 else invalidR++;
             }
         }
         ansLen = n - invalidL - invalidR; // 剪枝2：最终有效答案的长度
         dfs(0, "", invalidL, invalidR, 0);
         return new ArrayList<>(set);
     }

    private void dfs(int idx, String cur, int invalidL, int invalidR, int score) {
        if (invalidL < 0 || invalidR < 0  // 当前串非法（括号不匹配）
                || score < 0 || score > maxScore) return;
        if (invalidL == 0 && invalidR == 0) {
            if (cur.length() == ansLen) {
                set.add(cur);
            }
        }
        if (idx == n) return;

        char c = s.charAt(idx);
        if (c == '(') {
            dfs(idx+1, cur+c, invalidL, invalidR, score+1);
            dfs(idx+1, cur, invalidL-1, invalidR, score); // 算作非法')', 不加入结果cur
        } else if (c == ')') {
            dfs(idx+1, cur+c, invalidL, invalidR, score-1);
            dfs(idx+1, cur, invalidL, invalidR-1, score); // 算作非法')', 不加入结果cur
        } else {
            dfs(idx+1, cur+c, invalidL, invalidR, score);
        }
    }

    // 法2：dfs+剪枝预处理--[一重剪枝]（1-可匹配的最大score）
    // 时间：O(n * 2^n), 空间：O(n)
    public List<String> removeInvalidParentheses(String _s) {
        s = _s; n = s.length();
        int cntL = 0, cntR = 0;
        for (char c: s.toCharArray()) { // O(n)
            if (c == '(') cntL++;
            else if (c == ')') cntR++;
        }
        // 剪枝1：可匹配的左/右括号的数量，dfs时，有效解的maxScore
        maxScore = Math.min(cntL, cntR);
        dfs0(0, "", 0);
        return new ArrayList<>(set);
    }

    private void dfs0(int idx, String cur, int score) {
        if (score < 0 || score > maxScore) return; // 当前串非法（括号不匹配）
        if (idx == n) {
            if (score == 0 && cur.length() >= ansLen) {
                if (cur.length() > ansLen) {
                    set.clear(); // 先前的答案都不是最长长度，清空
                }
                ansLen = cur.length(); // 更新最长长度
                set.add(cur);
            }
            return;
        }

        char c = s.charAt(idx);
        if (c == '(') {
            dfs0(idx+1, cur+c, score+1);
            dfs0(idx+1, cur, score); // 不考虑c，score不变
        } else if (c == ')') {
            dfs0(idx+1, cur+c, score-1);
            dfs0(idx+1, cur, score); // 不考虑c，score不变
        } else { // 其他字符，保留至答案cur，score不更新
            dfs0(idx+1, cur+c, score);
        }
    }
}
