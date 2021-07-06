package Tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class q117_populating_next_right_pointers_in_each_node {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    public Node connect(Node root) {
        if (root == null) return null;
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        // 层序 - queue根左右
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node pre = new Node();
            Node cur = new Node();
            for (int i = 0; i < size; i++) {
                // 1. 根
                Node node = queue.poll();
                if (i == 0) {
                    cur = node;
                    pre.next = cur;
                    // cur.next = null; // 可不写
                } else { // 勿忘else!!!
                    cur.next = node;
                    cur = cur.next; // 即node
                }
                // 2. 左、右
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return root;
    }
}
