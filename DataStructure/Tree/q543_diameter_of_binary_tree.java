package DataStructure.Tree;

public class q543_diameter_of_binary_tree {
    private int maxLen = 1;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return maxLen-1; // ���ֱ��=��󳤶�-1���Լ���
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int len_L = dfs(root.left);
        int len_R = dfs(root.right);
        // ȫ����󳤶�
        maxLen = Math.max(maxLen, len_L + len_R + 1);
        return Math.max(len_L, len_R) + 1;  //���ؽڵ㳤��
    }
}
