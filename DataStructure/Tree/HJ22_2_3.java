package DataStructure.Tree;

import java.util.*;

public class HJ22_2_3 {
    static Map<String, Integer> cntMap = new HashMap();
    static List<TreeNode> ans = new ArrayList();
    static Set<String> serials = new HashSet<>();
    static final String NULL = "null";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            String[] nodeStr = sc.nextLine().split("\\s");
            String line = "1,2,3,1,null,2,null,null,null,null,null,1,null,null,null";
            String line2 = "1,2,3,4,null,2,4,null,null,4,null";
            String[] nodeStr = line2.split(",");
            Deque<String> nodes = new ArrayDeque<>(Arrays.asList(nodeStr));

            TreeNode root = deserialize_BFS(nodes);

            dfs(root);
//        }

        if (serials.isEmpty()) {
            System.out.println(-1);
        } else {
//            for (String str: serials) {
//                System.out.println(str);
//            }
            Deque<String> res0 = new ArrayDeque<>(serials); // 所有可行解 -> TODO: 序列化 & 计算深度，输出最深的树

        }
    }

    // BFS解码：层序特点-首位root, 后续成对pair<左、右孩子>
    private static TreeNode deserialize_BFS(Deque<String> nodesDeq) {
        if (nodesDeq == null || nodesDeq.isEmpty() || nodesDeq.peek().isEmpty()) return null;
        TreeNode root = new TreeNode(Integer.valueOf(nodesDeq.poll()));

        Deque<TreeNode> queue = new ArrayDeque<>(); // ?辅助层序遍历解码
        queue.offer(root);

        while (!queue.isEmpty() && !nodesDeq.isEmpty()) {
            TreeNode cur = queue.poll();// ↓合法数据一定是成对<左、右孩子>的
            String valL = nodesDeq.poll(), valR = nodesDeq.poll();
//            System.out.println(valL + ", " + valR);
            if (!NULL.equals(valL)) {
                cur.left = new TreeNode(Integer.parseInt(valL));
                queue.offer(cur.left);
            }
            if (!NULL.equals(valR)) {
                cur.right = new TreeNode(Integer.parseInt(valR));
                queue.offer(cur.right);
            }
        }
        return root;
    }

    public static String dfs(TreeNode node) {
        if (node == null) return "null";
        String serial = node.val + "," + dfs(node.left) + "," + dfs(node.right);
        cntMap.put(serial, cntMap.getOrDefault(serial, 0) + 1);
        if (cntMap.get(serial) == 2) {
//            if (serial.split(",").length >= 2) {
                ans.add(node);
                serials.add(serial);
//            }
        }
        return serial;
    }

//    // 法2：序列化 - 层序BFS （?自定义nullNode(nullptr) & INF值）
//    private static String serialize_BFS(TreeNode root) {
//        if (root == null) return "";//（可不写 serialize已特判，此处一定不是头结点为空）
//        Deque<TreeNode> deq = new ArrayDeque<>();
//        deq.offer(root);
//        String res = "";
//        while (!deq.isEmpty()) {
//            TreeNode node = deq.poll();
//            // if (node.val == INF) {
//            //     res += INF + ",";
//            //     continue;
//            // } // 可合并到下句↓
//            res += node.val + ",";
//            if (!node.equals(nullNode)) { // ?
//                deq.offer(node.left != null? node.left : nullNode);
//                deq.offer(node.right != null? node.right : nullNode);
//            }
//        }
//        return res;
//    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode() {}
        public TreeNode(int val) {this.val = val;}
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val; this.left = left; this.right = right;
        }
    }
}
