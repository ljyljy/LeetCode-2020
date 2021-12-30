package DataStructure.Tree;

public class q669_trim_a_BST {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        if (root.val < low) { // ɾ���Լ� & ��
            return trimBST(root.right, low, high);
        } else if (root.val > high) {// ɾ���Լ� & ��
            return trimBST(root.left, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
