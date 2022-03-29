package DataStructure.Deque;

import java.util.*;

public class q402_remove_k_digits {
    // 单调栈
    public String removeKdigits(String num, int k) {
        int n = num.length();
        int remain = n - k;
        if (remain == 0) return "0";

        Deque<Integer> stack = new ArrayDeque<>();
        for (char ch: num.toCharArray()) {
            int curNum = Integer.valueOf(ch + "");
            while (k > 0 && !stack.isEmpty() && stack.peek() > curNum) {
                // 新来的数更小，说明可以把栈顶pop，放入更小的数字 ->> 维护单调递增栈
                stack.pop();
                k--;
            }
            stack.push(curNum);
        }
        while (!stack.isEmpty() && k-- > 0) stack.pop(); // ?勿漏！法1) 还需pop，直至k为0!!!!
        while (!stack.isEmpty() && stack.peekLast() == 0) stack.removeLast(); //法1） ?队头/栈底=Last：前导0去除, 不可pop！
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        // 法2）先：若本就递增，则stack无法pop，需在此截取前remain个↓ (避免先lstrip导致截取到后面的大数)
        // if (sb.length() > remain) sb = new StringBuilder(sb.substring(0, remain));
        // 法2）后：lstrip('0')
        // while (sb.length() > 0 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.length() == 0? "0" : sb.toString();
    }
}
