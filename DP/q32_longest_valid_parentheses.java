package DP;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class q32_longest_valid_parentheses {
    // 2.������-ջ�����q20
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        char[] ss = s.toCharArray();
        int maxLen = 0;

        Deque<Integer> deque = new ArrayDeque<>();
        deque.push(-1); // �ڱ�
        for (int i = 0; i < n; i++) {
            if (ss[i] == '(') {
                deque.push(i);
            } else { // ss[i] == ')'
                deque.pop(); // ��ǰ"()"������ջ, ��¶����ջ���ڱ�
                if (deque.isEmpty()) {
                    deque.push(i); // ����push(-1): �ڱ�
                }
                // '('.idx=pop&peek; i=')'.idx, ����=i-��һ��"("���±�
                maxLen = Math.max(maxLen, i - deque.peek()); // ��peekFirst

            }
        }
        return maxLen;
    }

    // 1.DP - �״��ػᣡ
    public int longestValidParentheses_DP(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        char[] ss = s.toCharArray();
        int[] dp = new int[n]; // ��ʼ��dp[0]=0����������Ч=0
        for(int i = 1; i < n; i++) {
            // if (ss[i] == '(')  dp[i] = 0;
            if (ss[i] == ')') {
                if (ss[i-1] == '(') { // "(..)+()"
                    dp[i] = i-2>=0? dp[i-2] + 2: 2;
                } else { // ss[i-1] == ')'�� ��"))"
                    if (i-1>= 0 && i-dp[i-1]-1 >= 0 &&
                            ss[i-dp[i-1]-1] == '(') {
                        dp[i] = dp[i-1] + 2; // " ( (...))"
                        if (i-1 >= 0 && i-dp[i-1]-2 >= 0) { // "(.. ) &((...))"
                            dp[i] += dp[i-dp[i-1]-2];
                        }
                    }
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
