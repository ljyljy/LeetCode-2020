package DataStructure.Deque;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class q225_implement_stack_using_queues {
    class MyStack {
        Queue<Integer> queue1;
        Queue<Integer> queue2;

        public MyStack() {
            //q1 �洢ջ�ڵ�Ԫ�أ�q2 ��Ϊ��ջ�����ĸ���/������С�
            queue1 = new LinkedList<>(); // ��ͷ���� -> ��β����
            queue2 = new LinkedList<>();
        }

        // x: ����β -> ջ��������ͷ��
        public void push(int x) {
            queue2.offer(x);
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
            // swap<q1(��), q2> -> q2�գ�q1: ��->��
            Queue<Integer> tmp = queue1;
            queue1 = queue2;
            queue2 = tmp;
            // ��Ϻ�q1������,...,����;   q2: ��
        }

        public int pop() { // pop����
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
