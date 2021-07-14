package Binary_Search.Sweep_Line;

import java.util.*;

public class q218_the_skyline_problem {
    // 扫描线 + TreeMap / heap - PriorityQueue (大根堆)
    class Node {
        private int time, height;
        public Node(){}
        public Node(int t, int h) {time = t; height = h;}
    }

    private Comparator<Node> comparator = new Comparator<Node>(){
        public int compare(Node o1, Node o2) { // 按<time升序, 楼高降序❤>排列
            return o1.time == o2.time? o2.height - o1.height : o1.time - o2.time;
        }
    };

    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0)
            return res;
        List<Node> nodes = new ArrayList<>();
        for (int[] building : buildings) {
            nodes.add(new Node(building[0], building[2])); // 起点
            nodes.add(new Node(building[1], -building[2])); // 终点
        } // (∵高度降序 ∴【终点】始终不会被peek()/firstKey()) ↑
        Collections.sort(nodes, comparator);

        // 类似大根堆(需逆序) - map<楼高，cnt频数>
        TreeMap<Integer, Integer> tmap = new TreeMap<>(Collections.reverseOrder());
        tmap.put(-1, 1); // 哨兵❤ <楼高-1, cnt=1>
        int prevH = -1;  // 哨兵❤
        int startTime = nodes.get(0).time;
        for (Node node: nodes) {
            if (node.height >= 0) { // 起点，加入tmap（频数+1）
                int key = node.height;
                tmap.put(key, tmap.getOrDefault(key, 0)+1);
            } else { // 终点，移除tmap（根据频数≥1 分情况）
                int endH = -node.height;
                int end_cnt = tmap.get(endH);
                if (end_cnt == 1) {
                    tmap.remove(endH);
                } else { // 频数-1
                    tmap.put(endH, end_cnt-1);
                }
            }
            // 更新max_heap/tmap完毕，找peek() / firstKey()
            int curH = tmap.firstKey();
            // System.out.println("curH = "+ curH + "; 依次遍历↓<H, cnt>");
            // if (curH == -1 && prevH != -1) { // 走到重楼中间的"路冲"
            //     System.out.println("路冲：" + node.time);
            // }
            // for (Map.Entry<Integer, Integer> entry: tmap.entrySet()) {
            //     System.out.println(entry.getKey() + " -- " + entry.getValue());
            // }

            int curTime = node.time;
            if (prevH != curH) {// 还防止有同高的高楼但不重叠 - 需要合并起止点
                if (prevH != -1) { // 非哨兵
                    // 当前新高楼curTime == 上一轮的终点(大根堆中被掩盖)
                    // res.add(Arrays.asList(startTime, curTime, prevH));
                    res.add(Arrays.asList(startTime, prevH));// Arrays.asList(new int[]{xxx})
                    // 紧接着，若走到重楼中间的"路冲" - tmap中只有哨兵(key=curH=-1)
                    if (curH == -1) { // curH == -1 && prevH != -1
                        res.add(Arrays.asList(node.time, 0));
                    }
                }

                prevH = curH;
                startTime = curTime;
            }

        }
        return res;
    }
}
