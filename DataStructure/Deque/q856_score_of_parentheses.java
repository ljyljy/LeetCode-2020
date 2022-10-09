package DataStructure.Deque;


import java.util.ArrayDeque;
import java.util.Deque;

public class q856_score_of_parentheses {
    // v2: ջ��O(n)�����q1190,��394��856
    public int scoreOfParentheses2(String s) {
        int n = s.length();
        if (n == 2) return 1; // ����Ŀ��֤ƽ��
        Deque<Integer> deque = new ArrayDeque<>();
        deque.push(0); // �ڱ������������������(A)�Ľ���ۼ�

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

    // v1: ���η�, ʱ����O(n^2), ���q224��227��856
    public int scoreOfParentheses(String s) {
        int n = s.length();
        if (n == 2) return 1; // ����Ŀ��֤ƽ��
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
