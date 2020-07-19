package Tree;

import java.util.*;

public class q590_n_ary_tree_postorder_traversal {

//    Definition for a N tree node.
    public class Node {
        int val;
        List<Node> children;
        Node(int x) { val = x; }
    }

    // 1. 递归
    public List<Integer> postorder1(Node root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(Node root, List<Integer> res) {
        if (root != null) {
            if (root.children != null)
                for (Node children : root.children)
                    helper(children, res);
            res.add(root.val);
        }
    }

    // 2.迭代（栈）-- 逆（child, self）+ ∴【反向链表保存】 <-- ∵(self, child)
    public List<Integer> postorder(Node root) {
        LinkedList<Integer> res = new LinkedList<>();
        Deque<Node> stack = new ArrayDeque<>();
        if (root == null) return res;

        Node curr = root;
        stack.push(curr);
        while (!stack.isEmpty()) {
            curr = stack.pop();
            res.addFirst(curr.val);
            for (Node children : curr.children) {
//                if (curr.children != null)
                stack.push(children);
            }
        }
        return res;
    }

}
