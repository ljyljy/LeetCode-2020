package Recursion.combination_based;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class q301_remove_invalid_parentheses {
     Set<String> set = new HashSet<>();
     int n, maxScore, ansLen;
     String s;

    // ��1��dfs+��֦Ԥ����--[���ؼ�֦]��1-��ƥ������score��2-���մ𰸳��ȣ�
    // ʱ�䣺O(n * 2^n), �ռ䣺O(n)
    // ʱ�临�Ӷ�:(1) Ԥ����max��len �ĸ��Ӷ�ΪO(n);
    // (2) �����Ǽ�֦Ч���������£�ÿ��λ�ö�������ѡ���������з����ĸ��Ӷ�ΪO(2^n);
    // (3) ͬʱ���������л���������ַ��������յݹ�����Ҷ�ӽڵ���ַ����������Ϊn��ʹ��StringBuilder Ҳ��ͬ��)�����Ӷ�ΪO(n)��
    public List<String> removeInvalidParentheses1(String _s) {
         s = _s; n = s.length();
         int cntL = 0, cntR = 0;
         for (char c: s.toCharArray()) { // O(n)
             if (c == '(') cntL++;
             else if (c == ')') cntR++;
         }
         // ��֦1����ƥ�����/�����ŵ�������dfsʱ����Ч���maxScore
         maxScore = Math.min(cntL, cntR);

         int invalidL = 0, invalidR = 0;
         for (char c: s.toCharArray()) { // O(n)
             if (c == '(') invalidL++;
             else if (c == ')') {
                 if (invalidL > 0) invalidL--;
                 else invalidR++;
             }
         }
         ansLen = n - invalidL - invalidR; // ��֦2��������Ч�𰸵ĳ���
         dfs(0, "", invalidL, invalidR, 0);
         return new ArrayList<>(set);
     }

    private void dfs(int idx, String cur, int invalidL, int invalidR, int score) {
        if (invalidL < 0 || invalidR < 0  // ��ǰ���Ƿ������Ų�ƥ�䣩
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
            dfs(idx+1, cur, invalidL-1, invalidR, score); // �����Ƿ�')', ��������cur
        } else if (c == ')') {
            dfs(idx+1, cur+c, invalidL, invalidR, score-1);
            dfs(idx+1, cur, invalidL, invalidR-1, score); // �����Ƿ�')', ��������cur
        } else {
            dfs(idx+1, cur+c, invalidL, invalidR, score);
        }
    }

    // ��2��dfs+��֦Ԥ����--[һ�ؼ�֦]��1-��ƥ������score��
    // ʱ�䣺O(n * 2^n), �ռ䣺O(n)
    public List<String> removeInvalidParentheses(String _s) {
        s = _s; n = s.length();
        int cntL = 0, cntR = 0;
        for (char c: s.toCharArray()) { // O(n)
            if (c == '(') cntL++;
            else if (c == ')') cntR++;
        }
        // ��֦1����ƥ�����/�����ŵ�������dfsʱ����Ч���maxScore
        maxScore = Math.min(cntL, cntR);
        dfs0(0, "", 0);
        return new ArrayList<>(set);
    }

    private void dfs0(int idx, String cur, int score) {
        if (score < 0 || score > maxScore) return; // ��ǰ���Ƿ������Ų�ƥ�䣩
        if (idx == n) {
            if (score == 0 && cur.length() >= ansLen) {
                if (cur.length() > ansLen) {
                    set.clear(); // ��ǰ�Ĵ𰸶���������ȣ����
                }
                ansLen = cur.length(); // ���������
                set.add(cur);
            }
            return;
        }

        char c = s.charAt(idx);
        if (c == '(') {
            dfs0(idx+1, cur+c, score+1);
            dfs0(idx+1, cur, score); // ������c��score����
        } else if (c == ')') {
            dfs0(idx+1, cur+c, score-1);
            dfs0(idx+1, cur, score); // ������c��score����
        } else { // �����ַ�����������cur��score������
            dfs0(idx+1, cur+c, score);
        }
    }
}
