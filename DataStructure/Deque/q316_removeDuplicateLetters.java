package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q316_removeDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        int[] cnts = new int[256]; // 所有ASCII字符∈[0,256]区间
        for (char ch: s.toCharArray()) cnts[ch]++;

        Deque<Character> stack = new ArrayDeque<>();
        for (char ch: s.toCharArray()) { // 维护单调递【增】栈 - 字典序升序
            if (!stack.contains(ch)) { // ???勿漏此句！
                // 如：ac[b], b<c,且b后还有c, 则pop-c, 栈内只有ab了，后面再碰到c可以重入?
                while (!stack.isEmpty() && ch < stack.peek() && cnts[stack.peek()] > 0) {
                    stack.pop();                              // ↑当前较小的ch身后，还有栈顶元素的个数
                }
                stack.push(ch);
            }
            cnts[ch]--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.insert(0, stack.pop());
        return sb.toString();
    }

}
