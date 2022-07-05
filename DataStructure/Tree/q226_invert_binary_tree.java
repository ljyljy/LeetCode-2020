package DataStructure.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class q226_invert_binary_tree {
    // 法1、递归（前序、后序都可以（推荐），
    //         但递归の中序必须-左中左！因为'中'后，‘原右’已经被翻转为‘新左’了）
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return null;
        swapNode2(root); // 前序
        invertTree1(root.left);
        invertTree1(root.right);
        return root;
    }

    private void swapNode2(TreeNode root) {
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }

    // ↓ 无效swap！需要传入引用！
    private void swapNode(TreeNode lf, TreeNode rt) {
        TreeNode tmp = lf;
        lf = rt;
        rt = tmp;
    }


    // 法2-1、迭代(层序) - queue[中左右]
    public TreeNode invertTree_levelOrder(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                swapNode2(node);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return root;
    }

    // 法2-2、迭代(前序) - stack[中右左]
    public TreeNode invertTree0(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = stack.pop();
                swapNode2(node);
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }
        }
        return root;
    }

    // 法2-3、迭代(中序) 【大一统模板】- stack[右中左]
    TreeNode nullNode = new TreeNode(Integer.MIN_VALUE);
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (!node.equals(nullNode)) {
                if (node.right != null)
                    stack.push(node.right); // 右

                stack.push(node); // 中
                stack.push(nullNode);

                if (node.left != null)
                    stack.push(node.left); // 左

            } else { // 根
                swapNode2(stack.pop());
            }
//            int size = stack.size();
//            for (int i = 0; i < size; i++) {
//                if (node.right != null) stack.push(node.right);
//                TreeNode node = stack.pop();
//                swapNode2(node);
//                if (node.left != null) stack.push(node.left);
//            }
        }
        return root;
    }


}
