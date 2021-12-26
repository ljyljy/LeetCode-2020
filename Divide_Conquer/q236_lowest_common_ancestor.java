package Divide_Conquer;

public class q236_lowest_common_ancestor {
    //    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // 法1：通用解法(适用于所有二叉树)
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val || root.val == q.val) return root; // 自底向上找到p/q的祖先(包括自己)

        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        // p、q分别位于本层root的左、右子树中
        if (left != null && right != null) return root;
        // p、q分别位于本层root的左子树中，则左孩子是更近的祖先
        if (left != null) return left;
        if (right != null) return right;
        return null;
    }
    // 法2：针对【二叉搜索树】的性质
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else { // split point, i.e. the LCA node.
            return root;
        }
    }
}
