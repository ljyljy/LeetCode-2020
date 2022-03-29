package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q316_removeDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        int[] cnts = new int[256]; // ����ASCII�ַ���[0,256]����
        for (char ch: s.toCharArray()) cnts[ch]++;

        Deque<Character> stack = new ArrayDeque<>();
        for (char ch: s.toCharArray()) { // ά�������ݡ�����ջ - �ֵ�������
            if (!stack.contains(ch)) { // ???��©�˾䣡
                // �磺ac[b], b<c,��b����c, ��pop-c, ջ��ֻ��ab�ˣ�����������c��������?
                while (!stack.isEmpty() && ch < stack.peek() && cnts[stack.peek()] > 0) {
                    stack.pop();                              // ����ǰ��С��ch��󣬻���ջ��Ԫ�صĸ���
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
