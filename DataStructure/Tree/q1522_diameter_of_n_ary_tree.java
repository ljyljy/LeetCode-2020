package DataStructure.Tree;


import java.util.ArrayList;
import java.util.List;

public class q1522_diameter_of_n_ary_tree {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // 类比：q543（左+右+自己）vs 本题：最长+次长+自己
    int g_maxCnt = 1;
    
    public int diameter(Node root) {
        if (root == null) return 0;
        dfs(root);
        return g_maxCnt - 1; // 最大直径/边数=最大节点数-1
    }

    private int dfs(Node root) {
        if (root == null) return 0;
        int maxCnt1 = 0, maxCnt2 = 0; // 单边：最长、次长
        for (Node node: root.children) {
            int curDepth = dfs(node);
            if (curDepth >= maxCnt1) {
                maxCnt2 = maxCnt1; // 次长
                maxCnt1 = curDepth; // 最长
            } else if (curDepth > maxCnt2) {
                maxCnt2 = curDepth; // 更新次长，但未超过最长
            }
        }
        // 全局最大节点数=双边节点数=最长孩子+次长孩子+自己
        g_maxCnt = Math.max(g_maxCnt, maxCnt1 + maxCnt2 + 1);
        return maxCnt1 + 1;
    }
}
