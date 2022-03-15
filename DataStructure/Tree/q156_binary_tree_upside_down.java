package DataStructure.Tree;

public class q156_binary_tree_upside_down {
    TreeNode newRoot;
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) return null;
        dfs(root, null); // ��ǰ����+��(?���������!)
        return newRoot;
    }

    private void dfs(TreeNode root, TreeNode pre) {
        if (root == null) return ;
        dfs(root.left, root);
        // �� �ݹ鷵��ʱ��rootΪ�������������newRoot��
        if (newRoot == null) newRoot = root;
        if (pre != null) {
            root.left = pre.right;
            root.right = pre;
            // �������ϱ��������ÿ�
            pre.right = null;
            pre.left = null;
        }
    }
}
