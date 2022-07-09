package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 栈方法的push ->内部实现是addFirst(); pop  removeFirst();
 * 【栈顶在双端队列中就是队列头部，每次都在头部添加/删除】
 * 【助记：将双端队列Deque看做可双向操作的栈！】
 */
class q946_validateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        if (n != popped.length) return false;
        Deque<Integer> deque = new ArrayDeque<>();

        int i = 0;
        for (int x: pushed) {
            deque.offerFirst(x); // addFirst 或 stack.push()
            while (!deque.isEmpty() && i < n && deque.peekFirst() == popped[i]) { // peekFirst: 栈顶
                deque.pollFirst(); // removeFirst 或 stack.pop()
                i++;
            }
        }
        return deque.isEmpty();  // 或 i == n
    }
}