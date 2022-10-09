package DataStructure.Deque;


import java.util.ArrayDeque;
import java.util.Deque;

public class q856_score_of_parentheses {
    // v2: 栈，O(n)，类比q1190,、394、856
    public int scoreOfParentheses2(String s) {
        int n = s.length();
        if (n == 2) return 1; // ∵题目保证平衡
        Deque<Integer> deque = new ArrayDeque<>();
        deque.push(0); // 哨兵，用于最后计算大括号(A)的结果累加

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                deque.push(0);
            } else {
                int last = deque.pop();
                int newTop = deque.pop() + Math.max(2*last, 1);
                deque.push(newTop);
            }
        }
        return deque.peek();
    }

    // v1: 分治法, 时、空O(n^2), 类比q224、227、856
    public int scoreOfParentheses(String s) {
        int n = s.length();
        if (n == 2) return 1; // ∵题目保证平衡
        int cnt = 0, splitIdx = -1;
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i);
            cnt += (ch == '(') ? 1: -1;
            if (cnt == 0) {
                splitIdx = i;
                break;
            }
        }
        if (splitIdx == n-1) {
            return 2 * scoreOfParentheses(s.substring(1, n-1));
        } else {
            String lf = s.substring(0, splitIdx+1), rt = s.substring(splitIdx+1);
            return scoreOfParentheses(lf) + scoreOfParentheses(rt);
        }
    }
}
