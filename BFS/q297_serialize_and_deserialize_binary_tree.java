package BFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class q297_serialize_and_deserialize_binary_tree {
    class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) {this.val = val; left = right = null;}
    }

    private final int INF = Integer.MIN_VALUE;
    private TreeNode nullNode = new TreeNode(INF);
    // 法2：BFS(层序), 分割","
    public String serialize(TreeNode root) {
        if (root == null) return "";
        // 法1：前序DFS - O(n)
        // return serialize_dfs(root, ""); // 输出：1,2,null,null,3,4,null,null,5,null,null

        // 法2：层序BFS
        return serialize_BFS(root);  // 输出：1,2,3,null,null,4,5,null,null,null,null,
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.equals("")) return null; // data.isEmpty()
        // 统一处理字符串：分解nodes(via","), 转化为deque<>(方便poll/peek)
        String[] nodesArr = data.split(","); // ❤数组转化集合，集合初始化(集合)↓
        Deque<String> nodesDeq = new ArrayDeque<>(Arrays.asList(nodesArr));
        // 法1：前序DFS - O(n)

        // return deserialize_dfs(nodesDeq);

        // 法2：层序BFS
        return deserialize_BFS(nodesDeq);
    }

    // 法2：序列化 - 层序BFS （❤自定义nullNode(nullptr) & INF值）
    private String serialize_BFS(TreeNode root) {
        if (root == null) return "";//（可不写 serialize已特判，此处一定不是头结点为空）
        Deque<TreeNode> deq = new ArrayDeque<>();
        deq.offer(root);
        String res = "";
        while (!deq.isEmpty()) {
            TreeNode node = deq.poll();
            // if (node.val == INF) {
            //     res += INF + ",";
            //     continue;
            // } // 可合并到下句↓
            res += node.val + ",";
            if (!node.equals(nullNode)) { // ❤
                deq.offer(node.left != null? node.left : nullNode);
                deq.offer(node.right != null? node.right : nullNode);
            }
        }
        return res;
    }

    // BFS解码：层序特点-首位root, 后续成对pair<左、右孩子>
    private TreeNode deserialize_BFS(Deque<String> nodesDeq) {
        if (nodesDeq == null || nodesDeq.isEmpty()) return null;
        String rootVal = nodesDeq.poll();
        TreeNode root = new TreeNode(Integer.valueOf(rootVal));

        Deque<TreeNode> queue = new ArrayDeque<>(); // ❤辅助层序遍历解码
        queue.offer(root);

        while (!queue.isEmpty() && !nodesDeq.isEmpty()) {
            TreeNode cur = queue.poll();// ↓合法数据一定是成对<左、右孩子>的
            int val_L = Integer.parseInt(nodesDeq.poll());
            int val_R = Integer.parseInt(nodesDeq.poll());
            if (val_L != INF) {
                cur.left = new TreeNode(val_L);
                queue.offer(cur.left);
            }
            if (val_R != INF) {
                cur.right = new TreeNode(val_R);
                queue.offer(cur.right);
            }
        }
        return root;
    }


    // 法1：DFS(前序)
    // DFS 前序, 空结点"null", 分割","
    private String serialize_dfs(TreeNode root, String str) {
        if (root == null) // serialize已特判，此处一定不是头结点为空
            str += INF + ",";
        else { // 前序：根左右
            str += root.val + ",";
            str = serialize_dfs(root.left, str); // ↓函数内嵌sb.append
            str = serialize_dfs(root.right, str);// 不可'+=', 直接'='即可
            // str += str_L + str_R;
        }
        return str;
    }

    private TreeNode deserialize_dfs(Deque<String> nodesDeq) {// ❤
        if (nodesDeq == null || nodesDeq.isEmpty()) return null;
        int val = Integer.valueOf(nodesDeq.poll());
        if (val == INF) return null;

        TreeNode node = new TreeNode(val);
        node.left = deserialize_dfs(nodesDeq);
        node.right = deserialize_dfs(nodesDeq);
        return node;
    }
}
