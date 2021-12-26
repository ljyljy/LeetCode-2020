package Divide_Conquer;

public class q235_lowestCommonAncestor_BST {
    // 1.递归（最近BST公共祖先.val∈[p,q]）[BST有序，求可行解，立即返回--搜索可行解即可]
    public TreeNode lowestCommonAncestor_DFS(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val > p.val && root.val > q.val) { // 可行解↓(左子树)
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            if (left != null) return left; // 找到可行解，立即返回（否则，不返回!!!）
        } else if (root.val < p.val && root.val < q.val) { // 可行解↑(右子树)
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (right != null) return right; // 找到可行解，立即返回（否则，不返回!!!）
        } else {
            return root; // root.val∈[p,q]，即为解
        }
        return null;
    }

    // 2.迭代
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val > p.val && root.val > q.val) root = root.left;
            else if (root.val < p.val && root.val < q.val) root = root.right;
            else return root;
        }
        return null;
    }
}
