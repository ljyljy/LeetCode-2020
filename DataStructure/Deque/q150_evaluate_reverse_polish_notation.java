package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q150_evaluate_reverse_polish_notation {
    public int evalRPN(String[] tokens) {
        Deque<Integer> deque = new ArrayDeque<>();
        String operators = "+-*/";
        for (String token: tokens) {
            if (!operators.contains(token)) {
                deque.offerFirst(Integer.valueOf(token));  // stack.push(); 类型转换String -> int;
                continue;  // 不进行下面的运算(iff.token为运算符时)
            }
            int a = deque.pollFirst(); // stack.pop()
            int b = deque.pollFirst();
            if (token.equals("+"))
                deque.offerFirst(b + a);
            else if (token.equals("-"))
                deque.offerFirst(b - a); // 注意b在前！！
            else if (token.equals("*"))
                deque.offerFirst(b * a);
            else // if (token.equals("/"))
                deque.offerFirst(b / a);
//            switch (token){
//                case "+":
//                    deque.offerFirst(b + a);
//                    break;
//                case "-":
//                    deque.offerFirst(b - a);
//                    break;
//                case "*":
//                    deque.offerFirst(b * a);
//                    break;
//                case "/":
//                    deque.offerFirst(b / a);
//                    break;
//            }
        }
        return deque.pollFirst();
    }
}
