package Divide_Conquer;

public class q236_lowest_common_ancestor_of_a_binary_tree {
    //    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val || root.val == q.val) return root; // 自底向上找到p/q的祖先(包括自己)

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // p、q分别位于本层root的左、右子树中
        if (left != null && right != null) return root;
        // p、q分别位于本层root的左子树中，则左孩子是更近的祖先
        if (left != null) return left;
        if (right != null) return right;
        return null;
    }
}
