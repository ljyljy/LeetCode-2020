package DataStructure.Deque;

import java.util.*;

public class q402_remove_k_digits {
    // ����ջ
    public String removeKdigits(String num, int k) {
        int n = num.length();
        int remain = n - k;
        if (remain == 0) return "0";

        Deque<Integer> stack = new ArrayDeque<>();
        for (char ch: num.toCharArray()) {
            int curNum = Integer.valueOf(ch + "");
            while (k > 0 && !stack.isEmpty() && stack.peek() > curNum) {
                // ����������С��˵�����԰�ջ��pop�������С������ ->> ά����������ջ
                stack.pop();
                k--;
            }
            stack.push(curNum);
        }
        while (!stack.isEmpty() && k-- > 0) stack.pop(); // ?��©����1) ����pop��ֱ��kΪ0!!!!
        while (!stack.isEmpty() && stack.peekLast() == 0) stack.removeLast(); //��1�� ?��ͷ/ջ��=Last��ǰ��0ȥ��, ����pop��
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        // ��2���ȣ������͵�������stack�޷�pop�����ڴ˽�ȡǰremain���� (������lstrip���½�ȡ������Ĵ���)
        // if (sb.length() > remain) sb = new StringBuilder(sb.substring(0, remain));
        // ��2����lstrip('0')
        // while (sb.length() > 0 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.length() == 0? "0" : sb.toString();
    }
}
