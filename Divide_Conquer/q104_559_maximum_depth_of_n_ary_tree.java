package Divide_Conquer;

import java.util.ArrayDeque;
import java.util.Deque;

public class q104_559_maximum_depth_of_n_ary_tree {

    // 法1. 递归（根的高度==最大深度）
    public int maxDepth1(Node root) {
        if (root == null) return 0;
        int depth_child = 0;
        int n_children = root.children.size();
        for (int i = 0; i < n_children; i++) {
            depth_child = Math.max(depth_child, maxDepth(root.children.get(i)));
        }
        return 1 + depth_child;
    }

    // 法2. 迭代[层次遍历]
    public int maxDepth(Node root) {
        if (root == null) return 0;
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        int n_level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            n_level++;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (Node child : node.children) {
                    queue.offer(child);
                }
            }
        }
        return n_level;
    }

}
