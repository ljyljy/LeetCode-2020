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

    // ��ȣ�q543����+��+�Լ���vs ���⣺�+�γ�+�Լ�
    int g_maxCnt = 1;
    
    public int diameter(Node root) {
        if (root == null) return 0;
        dfs(root);
        return g_maxCnt - 1; // ���ֱ��/����=���ڵ���-1
    }

    private int dfs(Node root) {
        if (root == null) return 0;
        int maxCnt1 = 0, maxCnt2 = 0; // ���ߣ�����γ�
        for (Node node: root.children) {
            int curDepth = dfs(node);
            if (curDepth >= maxCnt1) {
                maxCnt2 = maxCnt1; // �γ�
                maxCnt1 = curDepth; // �
            } else if (curDepth > maxCnt2) {
                maxCnt2 = curDepth; // ���´γ�����δ�����
            }
        }
        // ȫ�����ڵ���=˫�߽ڵ���=�����+�γ�����+�Լ�
        g_maxCnt = Math.max(g_maxCnt, maxCnt1 + maxCnt2 + 1);
        return maxCnt1 + 1;
    }
}
