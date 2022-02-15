import java.util.*;

public class q20_valid_parentheses {
    // 法2：不用map，只用双端队列（still栈）
    public boolean isValid(String s) {
        if (s.isEmpty()) return true;
        Deque<Character> stack = new ArrayDeque<>();
        for (char c: s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    // 法1
    private static final Map<Character, Character> map = new HashMap<Character, Character>(){{
        put('(',')'); put('{', '}'); put('[', ']'); put('?','?'); // 为避免pop出现异常，放首个元素'？'
    }}; // 定义时添加元素，要用两个{{}}！！！(一个{}中可定义class)
    public boolean isValid2(String s) {
        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>(){{add('?');}}; // 双端队列
        for (Character c : s.toCharArray()) {
            if (map.containsKey(c))
                stack.addLast(c);
            else if (c != map.get(stack.removeLast())) { //
                // c为 1.')]}',则pop对应的栈顶元素↑  或  2.其他非括号, 则↓
                return false;
            }
        }
        return stack.size() == 1;
    }



}
