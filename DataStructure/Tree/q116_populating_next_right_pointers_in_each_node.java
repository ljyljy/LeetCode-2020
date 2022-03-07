package DataStructure.Tree;

import java.util.ArrayDeque;
import java.util.Deque;


public class q116_populating_next_right_pointers_in_each_node {
    public Node connect(Node root) {
        if (root == null) return null;
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Node last = null;
            for (int i = 0; i < size; i++) {
                // ¸ù
                Node cur = queue.poll();
                if (i == 0) {
                    last = cur;
                } else {
                    last.next = cur;
                    last = cur;
                }
                // ×ó¡¢ÓÒ
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return root;
    }



    // ÌâÄ¿
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

}
