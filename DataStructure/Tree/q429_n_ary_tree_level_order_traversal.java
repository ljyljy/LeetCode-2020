package DataStructure.Tree;

import java.util.*;

public class q429_n_ary_tree_level_order_traversal {
    //    Definition for a N tree node.
    public class Node {
        int val;
        List<Node> children;

        Node(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        List<Integer> level = new ArrayList<>(){{add(root.val);}};
        res.add(level);

        while (!queue.isEmpty()) {
            int size = queue.size();
            level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                // 每层节点依次出队列，加入level，孩子入队列(下层)
                Node node = queue.poll();
                level.add(node.val);
                queue.addAll(node.children); // 或下句↓
                // for (Node child : node.children) queue.offer(child);
            }
            res.add(level);
        }
        return res;
    }
}