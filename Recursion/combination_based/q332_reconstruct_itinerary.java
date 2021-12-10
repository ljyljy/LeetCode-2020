package Recursion.combination_based;

import java.util.*;

public class q332_reconstruct_itinerary {
    /* 示例：
    JFK-SFO; JFK-ATL; SFO-ATL; ATL-JFK; ATL-SFO;
    after sort:
    ATL-JFK; ATL-SFO; JFK-ATL; JFK-SFO; SFO-ATL;
     */
    List<String> res = new ArrayList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        int ticketsCnt = tickets.size();
        boolean[] used = new boolean[ticketsCnt];
        // printList(tickets);
        Collections.sort(tickets, new Comparator<>(){
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return o1.get(0).equals(o2.get(0))? o1.get(1).compareTo(o2.get(1))
                        : o1.get(0).compareTo(o2.get(0));
                // o1.get(1)-o2.get(1): o1.get(0)-o2.get(0);
            }
        });
        // System.out.println("\nafter sort: ");
        // printList(tickets);

        // 遍历sorted tickets
        for (int i = 0; i < ticketsCnt; i++) {
            String src = tickets.get(i).get(0);
            String nxt = tickets.get(i).get(1);
            if (!src.equals("JFK")) continue;
            Deque<String> path = new ArrayDeque<>(); // ❤必须在for内定义！否则WA！
            path.addLast(src); // ❤ path首个是JFK(固定)，接着才是回溯。src不可removeLast()

            path.addLast(nxt);
            used[i] = true;
            dfs(tickets, ticketsCnt, nxt, used, path);
            used[i] = false;
            path.removeLast();
            if (res.size() == ticketsCnt+1)
                break;
        }
        return res;
    }


    private void dfs (List<List<String>> tickets, int ticketsCnt,
                      String nxt, boolean[] used, Deque<String> path) {
        //剪枝：已找到第一个合理行程直接返回
        if (path.size() == ticketsCnt+1) {
            // res.add(new ArrayList<String>(path)); // res设为List<List<String>>
            res = new ArrayList<String>(path);
            return;
        }

        // 遍历找到当前结点(nxt)的下一个结点
        for (int i = 0; i < ticketsCnt; i++) {
            //剪枝：已找到第一个合理行程(不是path.size！)直接返回
            if (res.size() == ticketsCnt + 1) return;

            String src = tickets.get(i).get(0);
            String nxtnxt = tickets.get(i).get(1);
            if (used[i] || !src.equals(nxt)) continue;

            path.addLast(nxtnxt);
            used[i] = true;
            dfs(tickets, ticketsCnt, nxtnxt, used, path);
            used[i] = false;
            path.removeLast();
        }
    }

    private void printList(List<List<String>> tickets) {
        for (List<String> ticket: tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            System.out.print(src + "-" + dst + "; ");
        }
    }


    // 法1：对临接点排序- List.forEach(sort - String::CompareTo)
    private List<String> res0 = new LinkedList<>();// ∵逆序插入，∴用链表
    public List<String> findItinerary1(List<List<String>> tickets) {
        if (tickets == null || tickets.size() == 0) return res0;
        Map<String, List<String>> graph = getGraph1(tickets);
        // 按目的顶点排序， List.forEach(lambda/sort函数)
        graph.values().forEach(x -> x.sort(String::compareTo));
        dfs(graph, "JFK");
        return res0;
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
        res0.add(0, src); // 逆序插入
    }

    // 法2：优先级队列(小顶堆) - 对临接点排序【graph不能作为类成员！DFS用！】
//    private Map<String, PriorityQueue<String>> graph = new HashMap<>();
    private List<String> res1 = new LinkedList<>();
    public List<String> findItinerary2(List<List<String>> tickets) {
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
