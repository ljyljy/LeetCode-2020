import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class q20_valid_parentheses {
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

    // 法2：不用map，只用双端队列（still栈）
    public boolean isValid(String s) {
        if (s.length() == 0) return true;
        LinkedList<Character> stack = new LinkedList<>();
        for (Character c : s.toCharArray()) {
            if (c == '[') stack.addLast(']');
            else if (c == '{') stack.addLast('}');
            else if (c == '(') stack.addLast(')');
            else if (stack.isEmpty()|| c != stack.removeLast())
                return false;
        }
        return stack.isEmpty();
    }

}
