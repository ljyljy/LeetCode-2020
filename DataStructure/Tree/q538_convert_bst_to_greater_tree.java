package DataStructure.Tree;

public class q538_convert_bst_to_greater_tree {
    // ��������&���ɣ�����������-��-��
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
