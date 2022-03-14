package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q150_evaluate_reverse_polish_notation {
    public int evalRPN(String[] tokens) {
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
