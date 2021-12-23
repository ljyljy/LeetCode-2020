package DataStructure.Tree;

public class q700_search_in_a_binary_search_tree {
    // µÝ¹é
    public TreeNode searchBST0(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val < val) return searchBST(root.right, val);
        else if (root.val > val) return searchBST(root.left, val);
        else return root;
    }

    // µü´ú
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        while (root != null) {
            if (root.val > val) {
                root = root.left;
            } else if (root.val < val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }
}
