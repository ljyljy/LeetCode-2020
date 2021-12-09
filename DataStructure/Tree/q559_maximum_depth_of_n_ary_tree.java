package DataStructure.Tree;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class q559_maximum_depth_of_n_ary_tree {
    // 1.递归 dfs【荐】
    public int maxDepth_dfs(Node root) {
        if (root == null) return 0;
        int maxDepthChild = 0;
        for (Node child: root.children) {
            maxDepthChild = Math.max(maxDepthChild, maxDepth_dfs(child));
        }
        return 1 + maxDepthChild;
    }

    // 2.迭代（栈模拟/层序）
    public int maxDepth_levelOrder(Node root) {
        if (root == null) return 0;
        int depth = 0;
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                for (Node child: cur.children) {
                    queue.offer(child);
                }
            }
        }
        return depth;
    }

    // 2.迭代（栈模拟(根右左)-前序-根左右）
    Node nullNode = new Node(Integer.MIN_VALUE);
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int depth = 0;  // 模拟回溯-最终depth会归于0
        int maxDepth = 0; // 故另需记录其最大值
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            if (!cur.equals(nullNode)) {
                // 根
                stack.push(cur);
                stack.push(nullNode);
                depth++;
                // 子('右左')
                for (Node child: cur.children) {
                    stack.push(child);
                }
            } else {
                Node root_ = stack.pop();
                depth--; // 模拟回溯-最终depth会归于0
            }
            maxDepth = Math.max(maxDepth, depth);
        }
        return maxDepth;
    }
}
