package DataStructure.Tree;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class q559_maximum_depth_of_n_ary_tree {
    // 1.�ݹ� dfs������
    public int maxDepth_dfs(Node root) {
        if (root == null) return 0;
        int maxDepthChild = 0;
        for (Node child: root.children) {
            maxDepthChild = Math.max(maxDepthChild, maxDepth_dfs(child));
        }
        return 1 + maxDepthChild;
    }

    // 2.������ջģ��/����
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

    // 2.������ջģ��(������)-ǰ��-�����ң�
    Node nullNode = new Node(Integer.MIN_VALUE);
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int depth = 0;  // ģ�����-����depth�����0
        int maxDepth = 0; // �������¼�����ֵ
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            if (!cur.equals(nullNode)) {
                // ��
                stack.push(cur);
                stack.push(nullNode);
                depth++;
                // ��('����')
                for (Node child: cur.children) {
                    stack.push(child);
                }
            } else {
                Node root_ = stack.pop();
                depth--; // ģ�����-����depth�����0
            }
            maxDepth = Math.max(maxDepth, depth);
        }
        return maxDepth;
    }
}
