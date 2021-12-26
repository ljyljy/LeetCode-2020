package Divide_Conquer;

public class q235_lowestCommonAncestor_BST {
    // 1.�ݹ飨���BST��������.val��[p,q]��[BST��������н⣬��������--�������н⼴��]
    public TreeNode lowestCommonAncestor_DFS(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val > p.val && root.val > q.val) { // ���н��(������)
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            if (left != null) return left; // �ҵ����н⣬�������أ����򣬲�����!!!��
        } else if (root.val < p.val && root.val < q.val) { // ���н��(������)
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (right != null) return right; // �ҵ����н⣬�������أ����򣬲�����!!!��
        } else {
            return root; // root.val��[p,q]����Ϊ��
        }
        return null;
    }

    // 2.����
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val > p.val && root.val > q.val) root = root.left;
            else if (root.val < p.val && root.val < q.val) root = root.right;
            else return root;
        }
        return null;
    }
}
