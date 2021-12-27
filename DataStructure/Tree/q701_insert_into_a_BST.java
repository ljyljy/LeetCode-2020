package DataStructure.Tree;

public class q701_insert_into_a_BST {
    // 1.递归(有返回值)[荐]
    public TreeNode insertIntoBST_1(TreeNode root, int val) {
        if (root == null) {
            TreeNode node = new TreeNode(val);
            return node;
        } // ↓ 插入左子树
        if (val < root.val) root.left = insertIntoBST(root.left, val);
        else root.right = insertIntoBST(root.right, val);
        return root;
    }

    // 1-2.递归(无返回值-需记录parent)
    TreeNode parent = new TreeNode(Integer.MAX_VALUE);

    private void traversal(TreeNode root, int val) {
        if (root == null) {
            TreeNode node = new TreeNode(val);
            if (val < parent.val) parent.left = node;
            else parent.right = node;
            return ;
        }
        parent = root;
        if (val < root.val) traversal(root.left, val);
        else traversal(root.right, val);
    }

    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) {
            TreeNode node = new TreeNode(val);
            return node;
        }
        traversal(root, val);
        return root;
    }

    // 2.迭代（也需要parent）
    TreeNode parent2 = new TreeNode(Integer.MAX_VALUE);
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        TreeNode cur = root;
        if (root == null) return node;
        while (cur != null) {
            parent = cur;
            if (val < cur.val) cur = cur.left;
            else cur = cur.right;
        }
        if (val < parent.val) parent.left = node;
        else  parent.right = node;
        return root;
    }
}
