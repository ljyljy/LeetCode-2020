package BFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class q297_twice {
    private final int INF = Integer.MIN_VALUE;
    private TreeNode nullNode = new TreeNode(INF);

    public TreeNode deserialize(String data) {
        String[] strs = data.split(",");
        if (strs == null || strs.length == 0 || strs[0].isEmpty()) return null;
        Deque<String> strDeq = new ArrayDeque<>(Arrays.asList(strs));
        // return deserialize_dfs(strDeq);// 法1：DFS
        return deserialize_bfs(strDeq);// 法2：BFS
    }

    public String serialize(TreeNode root) {
        if (root == null) return "";
        // return serialize_dfs(root); // 法1：DFS
        return serialize_bfs(root);  // 法2：BFS
    }

    // BFS版
    private String serialize_bfs(TreeNode root) { // null结点转为 nullNode(INF)
        if (root == null) return INF + ",";
        Deque<TreeNode> nodesDeq = new ArrayDeque<>();
        nodesDeq.offer(root);
        StringBuilder sb = new StringBuilder();
        while (!nodesDeq.isEmpty()) {
            TreeNode cur = nodesDeq.poll();
            sb.append(cur.val + ",");
            if (cur.val != INF) {
                nodesDeq.offer(cur.left != null? cur.left: nullNode);
                nodesDeq.offer(cur.right != null? cur.right: nullNode);
            }
        }
        return sb.toString();
    }

    // BFS版
    // ❤遇到【"INF"设置为null/忽略自动置null】，而非【显式置nullNode！！错❤！】（会打印出INT_MAX）！
    private TreeNode deserialize_bfs(Deque<String> strDeq) { // 遇到"INF"设置为null，而非nullNode（会打印出INT_MAX）！
        if (strDeq == null || strDeq.isEmpty() || strDeq.peek().isEmpty()) return null;
        Deque<TreeNode> nodesDeq = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.valueOf(strDeq.poll()));
        nodesDeq.offer(root);

        while (!nodesDeq.isEmpty() && !strDeq.isEmpty()) {
            TreeNode cur = nodesDeq.poll();
            int valL = Integer.valueOf(strDeq.poll());
            int valR = Integer.valueOf(strDeq.poll());
            if (valL != INF) {
                cur.left = new TreeNode(valL);
                nodesDeq.offer(cur.left);
            }
            if (valR != INF) {
                cur.right = new TreeNode(valR);
                nodesDeq.offer(cur.right);
            }
            // ↓ 【WA】不可对"INF"设为【nullNode ×】，会打印出INT_MAX, 应设为【null√】
//            if (cur.val == INF) continue;
//            cur.left = valL != INF? new TreeNode(valL): nullNode;
//            nodesDeq.offer(cur.left);
//            cur.right = valR != INF? new TreeNode(valR): nullNode;
//            nodesDeq.offer(cur.right);
        }
        return root;
    }


    // DFS版
    private TreeNode deserialize_dfs(Deque<String> strDeq) {
        if (strDeq == null || strDeq.isEmpty()) return null;
        String valStr = strDeq.poll();
        // if (valStr.isEmpty()) return null;
        int val = Integer.valueOf(valStr);
        if (val == INF) return null;

        TreeNode root = new TreeNode(val);
        root.left = deserialize_dfs(strDeq);
        root.right = deserialize_dfs(strDeq);
        return root;
    }

    private String serialize_dfs(TreeNode root) {
        if (root == null) return INF + ",";
        String res = root.val + ",";
        res += serialize_dfs(root.left);
        res += serialize_dfs(root.right);
        return res;
    }




    class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode() {}

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
