package DataStructure.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class q173_binary_search_tree_iterator {
    Deque<TreeNode> stack = new ArrayDeque<>();

    public q173_binary_search_tree_iterator(TreeNode root) {
        inorderLeftMostTraverse(root); // 【1. 左】
    }

    // 将左孩子依次压栈 【中序 - 左根右】
    private void inorderLeftMostTraverse(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode p = stack.pop(); // 【2. 根】
        if (p.right != null)
            inorderLeftMostTraverse(p.right); // 【3. 右 - 继续中序遍历，压栈】
        return p.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
