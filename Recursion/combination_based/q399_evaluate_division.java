package Recursion.combination_based;

import java.util.*;

// 类比q399,q990
public class q399_evaluate_division {
    List<List<String>> equations;
    double[] values;
    List<List<String>> queries;
    int k = 0;

    // 法1：DFS
    public double[] calcEquation_DFS(List<List<String>> equations,
                                 double[] values, List<List<String>> queries) {
        this.equations = equations; this.values = values; this.queries = queries;
        //构建有向图的邻接表<a, [[b,a/b], [c, a/c],...]>
        Map<String, List<Point>> graph = buildGraph();
//        Test_printGraph(graph);
        int idx = 0;
        double[] res = new double[queries.size()];
        for (List<String> query: queries) {
            String a = query.get(0), b = query.get(1);
            if (!graph.containsKey(a) || !graph.containsKey(b)) {
                res[idx++] = -1;
                continue;
            }
            res[idx++] = dfs(graph, a, b, new HashSet<>(), 1);
        }
        return res;
    }

    private double dfs(Map<String, List<Point>> graph,
                       String cur, String end, Set<String> visited, double curRes) {
        if (visited.contains(cur)) return -1;
        if (cur.equals(end)) return curRes;

        visited.add(cur);
//        List<Point> points = graph.get(cur);
        for (Point nxtP: graph.get(cur)) {
            String nxt = nxtP.divisor;
            double curDivNxt = nxtP.divAns;
            // 已知curRes=start/cur, cur/end=curDivNxt
            //        => start/end = curRes*curDivNxt
            // 若 [cur/nxt, curRes=4.0], [nxt/end, curDivNxt=3.0]
            // 则 [cur/end, curRes*curDivNxt] ↓
            double tmp = dfs(graph, nxt, end, visited, curRes * curDivNxt);
            //如果返回结果不为-1, 说明找到结果 返回即可
            if (tmp != -1) {
                return tmp;
            }
        }
        return -1;
    }

    private Map<String, List<Point>> buildGraph() {
        Map<String, List<Point>> graph = new HashMap<>();
        k = 0;
        for (List<String> pair: equations) { // pair=[a, b]
            String a = pair.get(0), b = pair.get(1);
            if (graph.containsKey(a)) {
                graph.get(a).add(new Point(b, values[k]));
            } else {
                graph.put(a, new ArrayList<>(){{add(new Point(b, values[k]));}});
            }
            // 双向邻接表！ a/b=ans, 则构建b/a = 1.0/ans
            if (graph.containsKey(b)) {
                graph.get(b).add(new Point(a, 1.0/values[k++]));
            } else {
                graph.put(b, new ArrayList<>(){{add(new Point(a, 1.0/values[k++]));}});
            }
        }
        return graph;
    }

    // 法2：BFS
    public double[] calcEquation(List<List<String>> equations,
                                 double[] values, List<List<String>> queries) {
        this.equations = equations; this.values = values; this.queries = queries;
        //构建有向图的邻接表<a, [[b,a/b], [c, a/c],...]>
        Map<String, List<Point>> graph = buildGraph();
//        Test_printGraph(graph);
        int idx = 0;
        double[] res = new double[queries.size()];
        for (List<String> query: queries) {
            String a = query.get(0), b = query.get(1);
            if (!graph.containsKey(a) || !graph.containsKey(b)) {
                res[idx++] = -1;
                continue;
            }
            res[idx++] = bfs(graph, a, b, new HashSet<>());
        }
        return res;
    }

    private double bfs(Map<String, List<Point>> graph,
                       String start, String end, Set<String> visited) {
        Deque<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(start, 1));
        visited.add(start);

        while (!queue.isEmpty()) {
            Point curP = queue.poll();
            String cur = curP.divisor;
            double curRes = curP.divAns;
            if (cur.equals(end)) return curRes;
            // 遍历邻接点
            for (Point nxtP : graph.get(cur)) {
                String nxt = nxtP.divisor;
                double curDivNxt = nxtP.divAns;
                if (visited.contains(nxt)) continue;
                // 若 [cur/nxt, curRes=4.0], [nxt/end, curDivNxt=3.0]
                // 则 [cur/end, curRes*curDivNxt] ↓
                queue.offer(new Point(nxt, curRes * curDivNxt));
                visited.add(nxt);
            }
        }
        return -1;
    }

    // 法3：并查集
    // https://leetcode.cn/problems/evaluate-division/solution/399-chu-fa-qiu-zhi-nan-du-zhong-deng-286-w45d/


    class Point {
        String divisor; // 除数
        double divAns; // 除法结果

        public Point(String divisor, double divAns) {
            this.divisor = divisor;
            this.divAns = divAns;
        }
    }

    private void Test_printGraph(Map<String, List<Point>> graph) {
        System.out.println("Print Graph:");
        for (Map.Entry<String, List<Point>> entry: graph.entrySet()) {
            System.out.println("divided=" + entry.getKey());
            List<Point> list = entry.getValue();
            for (Point p: list) {
                System.out.println(p.divisor + " -> " + p.divAns);
            }
        }
    }

    public static void main(String[] args) {
        // equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
        List<List<String>> equations = new ArrayList<>();
        String[] equats = {"a", "b", "b", "c"};
        for (int i = 0; i < equats.length; i+=2) {
            List<String> list1 = new ArrayList<>();
            list1.add(equats[i]);
            list1.add(equats[i+1]);
            equations.add(list1);
        }

        double[] values = new double[]{2.0, 3.0};

        List<List<String>> queries = new ArrayList<>();
        String[] qs = {"a","c", "b","a", "a","e", "a","a", "x","x"};
        for (int i = 0; i < qs.length; i+=2) {
            List<String> list1 = new ArrayList<>();
            list1.add(qs[i]);
            list1.add(qs[i+1]);
            queries.add(list1);
        }

        q399_evaluate_division sol = new q399_evaluate_division();
        double[] res = sol.calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(res));
    }

}



