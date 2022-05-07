package Binary_Search.Sweep_Line;

import java.util.*;

public class q218_the_skyline_problem {
    // 扫描线 + TreeMap / heap - PriorityQueue (大根堆)
    class Node {
        private int time, height;
        public Node(){}
        public Node(int t, int h) {time = t; height = h;}
    }

//    private Comparator<Node> comparator = new Comparator<Node>(){
//        public int compare(Node o1, Node o2) { // 按<time升序, 楼高降序❤>排列
//            return o1.time == o2.time? o2.height - o1.height : o1.time - o2.time;
//        }
//    };

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return res;
        }
        List<Node> nodes = new ArrayList<>();
        for (int[] building : buildings) {
            nodes.add(new Node(building[0], building[2])); // 起点
            nodes.add(new Node(building[1], -building[2])); // 终点
        } // (∵高度降序 ∴【终点】始终不会被peek()/firstKey()) ↑
        Collections.sort(nodes,
                ((o1, o2) -> o1.time == o2.time? o2.height - o1.height: o1.time - o2.time));

//        for (Node node: nodes) {
//            System.out.println(node.time + ", " + node.height);
//        }

        // 类似大根堆(需逆序) - map<楼高，cnt频数>
        TreeMap<Integer, Integer> tmap = new TreeMap<>(Collections.reverseOrder());
        tmap.put(-1, 1); // 哨兵❤ <楼高=-1, cnt=1>
        int prevH = -1;  // 哨兵❤
        int prevTime = nodes.get(0).time;
        for (Node node: nodes) {
            int curH = node.height, curTime = node.time;
            if (curH >= 0) { // 起点，加入tmap（频数+1）
                tmap.put(curH, tmap.getOrDefault(curH, 0)+1);
            } else { // 终点，移除tmap（根据频数≥1 分情况）
                curH = -node.height;
                tmap.put(curH, tmap.get(curH)-1);
                if (tmap.get(curH) < 1) {
                    tmap.remove(curH);
                }
            }
            // 更新max_heap/tmap完毕，找peek() / firstKey()
            int curMaxH = tmap.firstKey();
//            System.out.println("time=" + node.time + ", " + "curMaxH = "+ curMaxH + "; 依次遍历↓time, <H, cnt>");
//            if (curMaxH == -1 && prevH != -1) { // 走到重楼中间的"路冲"
//             System.out.println("路冲：" + node.time);
//            }
//            for (Map.Entry<Integer, Integer> entry: tmap.entrySet()) {
//             System.out.println(entry.getKey() + " -- " + entry.getValue());
//            }

            if (prevH != curMaxH) { // 防止一段时间内，最高楼是一致的（这些区间应该合并）
                if (prevH != -1) { // 非哨兵
                    // 当前新高楼curTime == 上一轮的终点(大根堆中被掩盖)
                    // res.add(Arrays.asList(startTime, curTime, prevH));
                    res.add(Arrays.asList(prevTime, prevH));// Arrays.asList(new int[]{xxx})
                    // 紧接着，若走到重楼中间的"路冲" - tmap中只有哨兵(key=curMaxH=-1)
                    if (curMaxH == -1) { // curMaxH == -1 && prevH != -1
                        res.add(Arrays.asList(curTime, 0));
                    }
                }
                prevH = curMaxH;
                prevTime = curTime;
            }

        }
        return res;
    }

    public static void main(String[] args) {
        q218_the_skyline_problem sol = new q218_the_skyline_problem();
        int[][] buildings = {{2,9,10}, {3,7,15}, {5,12,12}, {15,20,10}, {19,24,8}};
        System.out.println(sol.getSkyline(buildings));
    }
}
