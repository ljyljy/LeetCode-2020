package Tree;

//    Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class q246_inorder_successor_in_bst {
    // 法1：
    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        // 退出：未找到p/root为空  || 找到p
        while (root != null && root.val != p.val) {
            if (root.val > p.val) { // 左子树中找
                // 0.[左 [根] 右], 找到p:
                // 1.若successor无右子树，即可直接返回
                // 2.若有右子树，则返回右子树的最左孩子
                successor = root;
                root = root.left;
            } else { // if root.val < p.val[不可能等于 因为等于时会跳出]
                root = root.right;
            }
        }

        if (root == null) return null;
        if (root.right == null) return successor; // 1.
        // 2. 找到右子树的最左孩子
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

        // 法2：【荐！】
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while (root != null) {
            if (root.val > p.val) {
                res = root; // 1.左子树的根 2.右子树的最左孩子
                root = root.left;
            } else // <= 在右子树中找
                root = root.right;
        }
        return res;
    }

        // 法3：
    public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;
        if (root.val <= p.val) { // <=  相等时找右子树，∵左根[右]
            // 若右子树中（下面的else语句）：1.无左子树 直接返回右子树的根 2.否则返回右子树的最左孩子
            return inorderSuccessor3(root.right, p);
        } else { // >  0. 左[根]右，若[根]无右孩子 直接返回，否则继续找右子树中的最左孩子(上面的if语句)
            TreeNode leftSuc = inorderSuccessor3(root.left, p);
            return (leftSuc != null)? leftSuc : root;
        }
    }
}
