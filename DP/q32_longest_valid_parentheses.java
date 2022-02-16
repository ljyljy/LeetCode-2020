package DP;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class q32_longest_valid_parentheses {
    // 2.括号类-栈，类比q20
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        char[] ss = s.toCharArray();
        int maxLen = 0;

        Deque<Integer> deque = new ArrayDeque<>();
        deque.push(-1); // 哨兵
        for (int i = 0; i < n; i++) {
            if (ss[i] == '(') {
                deque.push(i);
            } else { // ss[i] == ')'
                deque.pop(); // 当前"()"都不入栈, 暴露后面栈顶哨兵
                if (deque.isEmpty()) {
                    deque.push(i); // 类似push(-1): 哨兵
                }
                // '('.idx=pop&peek; i=')'.idx, 长度=i-上一个"("的下标
                maxLen = Math.max(maxLen, i - deque.peek()); // 或peekFirst

            }
        }
        return maxLen;
    }

    // 1.DP - 易错，必会！
    public int longestValidParentheses_DP(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        char[] ss = s.toCharArray();
        int[] dp = new int[n]; // 初始化dp[0]=0，单括号无效=0
        for(int i = 1; i < n; i++) {
            // if (ss[i] == '(')  dp[i] = 0;
            if (ss[i] == ')') {
                if (ss[i-1] == '(') { // "(..)+()"
                    dp[i] = i-2>=0? dp[i-2] + 2: 2;
                } else { // ss[i-1] == ')'， 即"))"
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
