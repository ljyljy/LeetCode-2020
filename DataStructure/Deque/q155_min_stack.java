package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q155_min_stack {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;
    /** initialize your data structure here. */
    public q155_min_stack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek())
            minStack.push(x);
    }

    public void pop() {
        int x = stack.pop();
        if (x == minStack.peek())
            minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
