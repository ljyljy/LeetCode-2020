package DataStructure.Tree;

public class q450_delete_node_in_bst {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            else { // 左、右子树均不为空：
                // 将待删结点.左子树(∵min) => 右子树.最左孩子 下面(∵min)
                TreeNode subTreeL = root.left;
                // 找右子树.最左孩子
                TreeNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                cur.left = subTreeL;
                root = root.right; // 删除当前结点root, 用更新后的右子树覆盖；
                return root;
            }
        } else if (root.val > key) { // 找左子树
            root.left = deleteNode(root.left, key);
        } else { // <  找右子树
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    // 普通二叉树的删除
    // 1. swap(待删结点.val, 右子树.最左孩子.val)
    // 2. 二次遍历时，被NULL覆盖(∵swap后，变叶子)
    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            // 若待删结点.右子树为空，直接返回左子树, 替换当前节点
            if (root.right == null) return root.left;
            // 右子树非空: 1. swap(待删结点.val, 右子树.最左孩子.val)
            //      2. 二次遍历时，被NULL覆盖(∵swap后，变叶子)
            // ↓ 找右子树.最左孩子
            TreeNode prev = root;
            TreeNode cur = root.right;
            while (cur.left != null) {
                prev = cur;
                cur = cur.left;
            }
            swap(root, cur); // 注意一定要传入引用！值替换！
        }
        root.left = deleteNode2(root.left, key);
        root.right = deleteNode2(root.right, key);
        return root;
    }

    private void swap(TreeNode n1, TreeNode n2) {
        int tmp = n1.val;
        n1.val = n2.val;
        n2.val = tmp;
    }
}
