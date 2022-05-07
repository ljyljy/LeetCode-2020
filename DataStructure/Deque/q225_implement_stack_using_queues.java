package DataStructure.Deque;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class q225_implement_stack_using_queues {
    class MyStack {
        Queue<Integer> queue1;
        Queue<Integer> queue2;

        public MyStack() {
            //q1 存储栈内的元素，q2 作为入栈操作的辅助/缓存队列。
            queue1 = new LinkedList<>(); // 队头：新 -> 队尾：旧
            queue2 = new LinkedList<>();
        }

        // x: 队列尾 -> 栈顶（队列头）
        public void push(int x) {
            queue2.offer(x);
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
            // swap<q1(空), q2> -> q2空，q1: 新->旧
            Queue<Integer> tmp = queue1;
            queue1 = queue2;
            queue2 = tmp;
            // 完毕后，q1：最新,...,最老;   q2: 空
        }

        public int pop() { // pop最新
            return queue1.poll();
        }

        public int top() {
            return queue1.peek();
        }

        public boolean empty() {
            return queue1.isEmpty();
        }
    }
}
