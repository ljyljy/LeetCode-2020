package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q1190_reverse_substr_between_each_parentheses {
    // 法1：栈 -- O(n^2)
    public String reverseParentheses1(String s) {
        Deque<String> stack = new ArrayDeque<>();
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(sb.toString());
                sb.setLength(0); // sb清空
            } else if (ch == ')') {
                sb.reverse(); // u evol + i -翻转-> i love u
                sb.insert(0, stack.pop());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    // 法2：括号预处理 -- O(n)
    public String reverseParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] pair = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else if (ch == ')') {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        int idx = 0, step = 1;
        StringBuilder sb = new StringBuilder();
        while (idx < s.length()) {
            char ch = s.charAt(idx);
            if (ch == '(' || ch == ')') {
                idx = pair[idx]; // "(" 找到对应的‘)’
                step = -step;    // "(" 接着 从idx处‘)’逆序遍历，直到再次遇到‘(’
            } else {
                sb.append(ch);
            }
            idx += step;
        }
        return sb.toString();
    }
}
