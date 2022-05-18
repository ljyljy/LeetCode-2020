package DataStructure.Tree;

public class q538_convert_bst_to_greater_tree {
    // 根据题意&规律，反向中序（右-中-左）
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }
}
