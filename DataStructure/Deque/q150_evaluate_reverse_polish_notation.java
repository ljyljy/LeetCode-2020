package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q150_evaluate_reverse_polish_notation {
    public int evalRPN2(String[] tokens) {
        String opr = "+-*/";
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token: tokens) {
            if (!opr.contains(token)) {
                stack.push(Integer.valueOf(token));
                continue;
            }
            int a = stack.pop();
            int b = stack.pop();
            switch (token) {
                case "+": stack.push(b + a); break;
                case "-": stack.push(b - a); break;
                case "*": stack.push(b * a); break;
                case "/": stack.push(b / a); break;
            }
        }
        return stack.peek();
    }

    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        String opr = "+-*/";
        for (String ch: tokens) {
            if (!opr.contains(ch)) {
                stack.addFirst(Integer.valueOf(ch)); // push
                continue;
            }
            // 逆波兰：若当前为运算符，则栈内至少有两个数！
            int a = stack.removeFirst(); // pop & 不报错
            int b = stack.removeFirst();
            if ("+".equals(ch)) {
                stack.addFirst(Integer.valueOf(b + a));
            } else if ("-".equals(ch)) {
                stack.addFirst(Integer.valueOf(b - a));
            } else if ("*".equals(ch)) {
                stack.addFirst(Integer.valueOf(b * a));
            } else if ("/".equals(ch)) {
                stack.addFirst(Integer.valueOf(b / a));
            }
        }
        return stack.removeFirst(); // pop
    }


    public int evalRPN0(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        String operators = "+-*/";
        for (String token: tokens) {
            if (!operators.contains(token)) {
                stack.offerFirst(Integer.valueOf(token));  // stack.push(); 类型转换String -> int;
                continue;  // 不进行下面的运算(iff.token为运算符时)
            }
            // 逆波兰：若当前为运算符，则栈内至少有两个数！
            int a = stack.pollFirst(); // stack.pop()
            int b = stack.pollFirst(); // 或removeFirst
            if (token.equals("+"))
                stack.offerFirst(b + a);
            else if (token.equals("-"))
                stack.offerFirst(b - a); // ❤注意b在前！！
            else if (token.equals("*"))
                stack.offerFirst(b * a);
            else // if (token.equals("/"))
                stack.offerFirst(b / a);
//            switch (token){
//                case "+":
//                    stack.offerFirst(b + a);
//                    break;
//                case "-":
//                    stack.offerFirst(b - a);
//                    break;
//                case "*":
//                    stack.offerFirst(b * a);
//                    break;
//                case "/":
//                    stack.offerFirst(b / a);
//                    break;
//            }
        }
        return stack.pollFirst();
    }
}
