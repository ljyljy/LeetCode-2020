package Recursion.combination_based;

import java.util.*;

public class q332_reconstruct_itinerary {
    // 法1：对临接点排序- List.forEach(sort - String::CompareTo)
    private List<String> res = new LinkedList<>();// ∵逆序插入，∴用链表
    public List<String> findItinerary1(List<List<String>> tickets) {
        if (tickets == null || tickets.size() == 0) return res;
        Map<String, List<String>> graph = getGraph1(tickets);
        // 按目的顶点排序， List.forEach(lambda/sort函数)
        graph.values().forEach(x -> x.sort(String::compareTo));
        dfs(graph, "JFK");
        return res;
    }

    private Map<String, List<String>> getGraph1(List<List<String>> tickets) {
        Map<String, List<String>> graph = new HashMap<>(); // <src, dsts邻接点>
        for (List<String> pair : tickets) {
            // ∵涉及删除操作，∴用链表
            String src = pair.get(0), dst = pair.get(1);
            // 若key(src)未存入graph, 则新建空列表作为val
            List<String> nbr = graph.computeIfAbsent(src, key -> new LinkedList<>()); // get(key若空)增强
            nbr.add(dst);
        }
        return graph;
    }


    private void dfs(Map<String, List<String>> graph, String src) {
        List<String> nbr = graph.get(src);
        while (nbr != null && nbr.size() > 0) { // !nbr.isEmpty()
            String dst = nbr.remove(0); // 字典序最小
            dfs(graph, dst);
        }
        res.add(0, src); // 逆序插入
    }

    // 法2：优先级队列(小顶堆) - 对临接点排序【graph不能作为类成员！DFS用！】
//    private Map<String, PriorityQueue<String>> graph = new HashMap<>();
    private List<String> res1 = new LinkedList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        if (tickets == null || tickets.size() == 0) return res1;
        Map<String, PriorityQueue<String>> graph = getGraph2(tickets);
        visit(graph, "JFK"); // dfs2
        return res1;
    }

    // DFS方式遍历图，当走到不能走为止，再将节点加入到答案
    private Map<String, PriorityQueue<String>> getGraph2 (List<List<String>> tickets) { // <src, dsts邻接点>
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> pair : tickets) {
            // ∵涉及删除操作，∴用链表
            String src = pair.get(0), dst = pair.get(1);
            // 若key(src)未存入graph, 则新建空列表作为val
            PriorityQueue<String> nbr = graph.computeIfAbsent(src,
                    key -> new PriorityQueue<>()); // get(key若空)增强
            nbr.add(dst);
        }
        return graph;
    }

    private void dfs2(Map<String, PriorityQueue<String>> graph,
                      String src) {
        PriorityQueue<String> nbr = graph.get(src);
        while (nbr != null && !nbr.isEmpty()) {
            String dst = nbr.poll();
            dfs2(graph, dst);
        }
        res1.add(0, src);
    }

    // 法3：递归 -> 迭代{????}
    private void visit(Map<String, PriorityQueue<String>> graph,
                       String src) {
        Stack<String> stack = new Stack<>();
        stack.push(src);
        while (!stack.isEmpty()) {
            PriorityQueue<String> nbr;
            while ( (nbr = graph.get(stack.peek())) != null
                    && !nbr.isEmpty())
                stack.push(nbr.poll());
            res1.add(0, stack.pop());
        }
    }
}
