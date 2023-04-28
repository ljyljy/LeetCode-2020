package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q232_implement_queue_using_stacks {
    class MyQueue {
        Deque<Integer> stack1, stack2;

        public MyQueue() { //
            stack1 = new ArrayDeque<>(); // fifo
            stack2 = new ArrayDeque<>(); // filo
        }

        public void push(int x) {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            stack1.push(x);
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }

        }

        public int pop() {
            return stack2.pop();
        }

        public int peek() {
            return stack2.peek();
        }

        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }
}
