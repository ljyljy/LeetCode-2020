package DataStructure.Tree;

import java.util.*;

public class q590_n_ary_tree_postorder_traversal {

//    Definition for a N tree node.
    public class Node {
        int val;
        List<Node> children;
        Node(int x) { val = x; }
    }

    // 法1：递归 （左右根）- 左右：孩子顺序递归
    public List<Integer> postorder_dfs(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, res);
        return res;
    }

    private void dfs(Node root, List<Integer> res) {
        if (root == null) return;
        List<Node> children = root.children;
        for (Node child : children) {
            dfs(child, res);
        }
        res.add(root.val);
    }

    // 【大一统迭代(推荐模板1)】
    // 法0：❤迭代写法 - 后序(左右中) - 压栈:中右左(右左：即孩子逆序压栈❤)
    Node nullNode = new Node(Integer.MIN_VALUE);
    public List<Integer> postorder_stack(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) { // 压栈：根右左
            Node node = stack.pop();
            if (!node.equals(nullNode)) {
                stack.push(node);
                stack.push(nullNode);
                List<Node> children = node.children;
                int size = children.size();
                for (int i = size-1; i >= 0; i--) { // ❤孩子逆序压栈
                    stack.push(children.get(i));
                }
            } else{// 说明下一个pop的是根
                res.add(stack.pop().val);
            }
        }
        return res;
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
