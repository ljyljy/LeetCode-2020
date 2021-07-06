package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class qo_32_1_levelOrder {
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                res.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        // 问题：return res.toArray(new int[res.size()]); // (指定类型 如new String[n], 但无int[]!)
        // 解决：List<Integer> 转 int[]
        int n = res.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = res.get(i);
        return arr;
    }
}
