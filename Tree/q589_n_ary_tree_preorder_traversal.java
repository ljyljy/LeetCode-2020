package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class q589_n_ary_tree_preorder_traversal {
    class Node {
        int val;
        List<Node> children;

        public Node() {}
        public Node(int _val) { val = _val; }
        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    // 0.递归 - 根左右（即：孩子顺序递归）
    public List<Integer> preorder_dfs(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, res);
        return res;
    }

    private void dfs(Node root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        for (Node child : root.children) {
            dfs(child, res);
        }
    }

    // 【大一统迭代(推荐模板1)】
    // 法0：❤迭代写法 - 前序(中左右)
    // 压栈:右左[根+NULL标记] (右左：孩子逆序压栈❤)
    Node nullNode = new Node(Integer.MIN_VALUE, new ArrayList<>());
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (!node.equals(nullNode)) {
                List<Node> children = node.children;
                int size = children.size();
                for (int i = size-1; i >= 0; i--) {
                    stack.push(children.get(i));
                } // ↑ 孩子逆序压栈❤ // 1+2：右左

                stack.push(node); // 3.根
                stack.push(nullNode); // 标记根
            } else{// 说明下一个pop的是根
                res.add(stack.pop().val);
            }
        }
        return res;
    }
}
