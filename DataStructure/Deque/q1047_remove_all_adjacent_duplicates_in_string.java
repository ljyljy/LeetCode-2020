package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q1047_remove_all_adjacent_duplicates_in_string {
    public String removeDuplicates(String s) {
        int n = s.length();
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (!stack.isEmpty() && ch == stack.peek()) { // ÓÃ¡¾if¡¿£¡¶ø·Çwhile£¡
                stack.pop();
            } else stack.push(ch);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.insert(0, stack.pop());
        return sb.toString();
    }
}
