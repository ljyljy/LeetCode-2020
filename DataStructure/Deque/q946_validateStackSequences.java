package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

class q946_validateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        if (n != popped.length) return false;
        Deque<Integer> deque = new ArrayDeque<>();

        int i = 0;
        for (int x: pushed) {
            deque.offerFirst(x); // stack.push()
            while (!deque.isEmpty() && i < n && deque.peekFirst() == popped[i]) {
                deque.pollFirst(); // stack.pop()
                i++;
            }
        }
        return deque.isEmpty();  // 或 i == n
    }
}