package DataStructure.Tree;

public class q114_Flatten_Binary_Tree_to_LinkedList {
    //    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val; this.left = left; this.right = right;
        }
    }
    // 法1：递归
    private TreeNode lastNode = null;
    private void flatten1(TreeNode root) {
        if (root == null) return;
        // 1. 根 - 前序遍历
        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }
        lastNode = root;
        // ↓不设置可能栈溢出/RunTimeError！！！
        TreeNode right = lastNode.right;
        // 2. 左
        flatten1(root.left);
        // 3. 右 ❤ 不可直接root.right!!!
        // ∵2.左回溯结束后，root.right(新)已变为先前的root.left(旧)！
        flatten1(right);
    }

    // 法2：分治 以2-3-4为例
    private void flatten(TreeNode root) {
        if (root == null) return ;
        divConq(root);
    }

    private TreeNode divConq(TreeNode root) {
        if (root == null) return null; // 2
        TreeNode leftLast = divConq(root.left);// 3
        TreeNode rightLast = divConq(root.right);// 4

        if (leftLast != null) {  // 链表传递 -- 类似swap
            leftLast.right = root.right;// 3-4
            root.right = root.left; // 2-3 （右边不可写leftLast!!！）
            root.left = null; // 左子树保持空
        }
        if (rightLast != null)
            return rightLast;
        else if (leftLast != null)  // rightLast为空
            return leftLast;
        else
            return root;// 不是null！
    }
}
