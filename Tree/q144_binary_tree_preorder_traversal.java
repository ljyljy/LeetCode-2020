package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class q144_binary_tree_preorder_traversal {
    // 法1：递归
    public List<Integer> preorderTraversal_0(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    private void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        if (root.left != null) helper(root.left, res);
        if (root.right != null) helper(root.right, res);
    }

    // 法2：非递归
    public List<Integer> preorderTraversal_2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode p = stack.pop();
            res.add(p.val);
            if (p.right != null) stack.push(p.right);
            if (p.left != null) stack.push(p.left);
        }
        return res;
    }

    // 法2：非递归【模板】
    public List<Integer> preorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root); // 【1. 根】
                res.add(root.val);
                root = root.left; // 【2. 左】
            } else { // 走到最左子树 / 叶子
                TreeNode last_pop = stack.pop(); // 回溯到父亲，然后【3.右】
                root = last_pop.right;
            }
        }
        return res;
    }

}
