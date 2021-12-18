package DataStructure.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class q222_count_complete_tree_nodes {
    // 1. [普通] dfs
    public int countNodes_1(TreeNode root) {
        if (root == null) return 0;
        int cnt_L = countNodes(root.left);
        int cnt_R = countNodes(root.right);
        return 1 + cnt_L + cnt_R;
    }

    // 2. [普通] bfs (层次遍历)
    public int countNodes_2(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            cnt += size; // 法1 [荐，减少add操作数]
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // cnt++; // 法2
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return cnt;
    }

    // 3.【完全二叉树-性质】 - dfs求子树(until满足满二叉树)高/深h, 节点数=2^h-1
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        TreeNode left = root.left, right = root.right;
        int depthL = 1, depthR = 1;
        while (left != null) {
            depthL++;
            left = left.left;
        }
        while (right != null) {
            depthR++;
            right = right.right;
        }
        if (depthL == depthR) // 满二叉(子)树【最左孩子深度==最右孩子深度】
            return (2 << (depthL-1)) - 1; // 节点数=2^h-1
        // 否则，下探 直到满二叉子树 // ↓ 不可写left和right（被更新了）
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // 4.(变题-only针对满二叉树). 【满二叉树-性质】 - dfs/bfs求树高h, 节点数=2^h-1
    private int maxH = 0;
    public int countNodes_WA(TreeNode root) {
        if (root == null) return 0;
        getHeight(root, 0);
        return 2 << (maxH-1) + 1; // 2^h = Math.pow(2, h) = 2 << (h-1)
        // ↑ 注意: 【2 ^ 2】 = 4 = 2*2 = 【2 << 1】
    }

    private void getHeight(TreeNode root, int height) {
        maxH = height > maxH? height : maxH;
        if (root == null) return ;

        getHeight(root.left, height+1); // 回溯
        getHeight(root.right, height+1);
    }

}
