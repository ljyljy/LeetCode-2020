package BFS;

import java.util.*;

public class q133_clone_graph {
    class Node {
        private int val;
        private List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    // 法1：DFS
    private Map<Node, Node> visited = new HashMap<>();
    public Node cloneGraph_dfs(Node node) {
        // 1. 递归出口
        if (node == null) return null;
        // // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
        if (visited.containsKey(node))  return visited.get(node);

        // 2. 克隆自身，注意到为了深拷贝我们不会克隆它的邻居的列表
        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode); // 勿忘! 否则TLE

        // 3. 克隆邻居，遍历该节点的邻居并更新克隆节点的邻居列表
        for (Node neighbor : node.neighbors) {
            visited.get(node).neighbors.add(cloneGraph_dfs(neighbor));
        }
        return visited.get(node);
    }

    // 法2：BFS
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        // ↓ 不是DFS回溯, 本句没啥效用
        if (visited.containsKey(node))  return visited.get(node);

        Map<Node, Node> visited = new HashMap<>();
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        // ❤ 法1：首先，将node(root)放入visited
        visited.put(node, new Node(node.val));

        // BFS易错!!!
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node srcNode = queue.poll();
//                // ❤ 法2：以下3行（可替换法1）
//                Node cloneNode = new Node(srcNode.val);
//                if (!visited.containsKey(srcNode))
//                    visited.put(node, cloneNode);

                for (Node neighbor: srcNode.neighbors) {
                    // 下探: 1)neighbor入队列 2)入visited!!!
                    if (!visited.containsKey(neighbor)) {
                        visited.put(neighbor, new Node(neighbor.val));
                        queue.offer(neighbor);
                    }
                    // ❤ clone原.邻居们.add(❤clone原邻居)
                    // ❤❤❤不可写为cloneNode（与visited.get(srcNode)地址不一样！）
                    visited.get(srcNode).neighbors.add(visited.get(neighbor));
                }
            }
        }
        return visited.get(node);
    }
}
