package DataStructure.Tree;

import java.util.*;

public class q652_q297_HJ22_2_3 {
    static Map<String, Integer> cntMap = new HashMap();
    static List<TreeNode> ans = new ArrayList();
    static Set<String> serials = new TreeSet<>(((o1, o2) -> o2.length()-o1.length())); // 序列化子树.长度降序
    static final String NULL = "null";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            String[] nodeStr = sc.nextLine().split("\\s");
        String line1 = "1,2,3,1,null,2,null,null,null,null,null,1,null"; // WA
        String line2 = "1,2,3,4,null,2,4,null,null,4,null"; // pass
        String[] nodeStr = line2.split(",");
        Deque<String> nodes = new ArrayDeque<>(Arrays.asList(nodeStr));

        TreeNode root = deserialize_BFS(nodes);

        dfs(root);
//        }

        System.out.println("ALL ANS: ");
        for (String serial: serials)
            System.out.println(serial);
        System.out.println("-".repeat(20));


        if (serials.isEmpty()) {
            System.out.println(-1);
        } else {
//            List<TreeNode> nodes = new ArrayList<>();// 所有可行解 -> TODO: 序列化 & 计算深度，输出最深的树
            // TODO: 序列化输出的是完全二叉？ -- 直接返回最长的
            boolean first = true;
            for (String serial: serials) {
                if (!first) break;
                String[] nodes0 = serial.split(",");
                if (nodes0.length == 3 && NULL.equals(nodes0[1]) && NULL.equals(nodes0[2])) {
                    System.out.println(-1); // 【子树不可以只有一个结点】
                }else System.out.println(serial);
                first = false;
            }
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
