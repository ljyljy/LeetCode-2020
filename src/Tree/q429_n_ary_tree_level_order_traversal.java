package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        Queue<Node> queue = new LinkedList<>();
        if (root == null) return res;

        Node curr = root;
        queue.offer(curr);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();// 不可以直接写进for！！
            for (int i = 0; i < size; ++i) {
                curr = queue.poll();
                level.add(curr.val);
                queue.addAll(curr.children);
            }
            res.add(level);
        }
        return res;
    }



}