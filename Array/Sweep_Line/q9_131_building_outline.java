package Array.Sweep_Line;

import java.util.*;

public class q9_131_building_outline {
    // https://leetcode-cn.com/problems/the-skyline-problem/solution/gong-shui-san-xie-sao-miao-xian-suan-fa-0z6xc/
    // https://www.lintcode.com/problem/131/
    // TEST CASE: [[1,2,3],[1,2,4],[1,2,3],[1,2,4]]
    class Node { // 返回值为三维 - Java:自定义类(成员变量*3)/Py: list([_s,_e,_h])
        private int time, height;

        public Node() {
        }

        public Node(int t, int h) {
            time = t;
            height = h;
        }
    }

    private Comparator<Node> comparator = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.time == o2.time)
                return o2.height - o1.height;
            return o1.time - o2.time; // 按<time升序, 高度降序 ❤ > 排列
        }
    };

    public List<List<Integer>> buildingOutline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0)
            return res;

        List<Node> nodes = new ArrayList<>();
        for (int[] building : buildings) {
            nodes.add(new Node(building[0], building[2])); // start
            nodes.add(new Node(building[1], -building[2])); // end
        }
        Collections.sort(nodes, comparator); // 或 nodes.sort(comparator);

        // 下面使用heap(PriorityQueue - 大根堆 / TreeMap / TreeSet)
        // Map<height楼高, 频数>
        TreeMap<Integer, Integer> tmap = new TreeMap<>(Collections.reverseOrder()); // 类似大根堆(默认小根堆，需逆序)
        tmap.put(0, 1); // 哨兵<楼高0, 频数1>

        int prevH = 0;
        int startTime = nodes.get(0).time;
        for (Node node : nodes) {
            if (node.height >= 0) { // 楼の起点，加入TreeMap
                tmap.put(node.height, tmap.getOrDefault(node.height, 0) + 1);
            } else { // 终点(height为负，需要再取负) - map中去除
                int cnt = tmap.get(-node.height);
                if (cnt == 1)
                    tmap.remove(-node.height);
                else
                    tmap.put(-node.height, cnt - 1);
            }

            int curH = tmap.firstKey(); // 当前最高的楼高
//            System.out.println("curH = "+ curH + "; 依次遍历↓<H, cnt>");
//            for (Map.Entry<Integer, Integer> entry: tmap.entrySet()) {
//                System.out.println(entry.getKey() + " -- " + entry.getValue());
//            }

            if (curH != prevH) { // 还防止有同高的高楼但不重叠 - 需要合并起止点
                if (prevH != 0) {// 当前的起点 node.time == 上一轮的终点
                    res.add(Arrays.asList(startTime, node.time, prevH));
                }
                prevH = curH;
                startTime = node.time;
            }
        }
        return res;
    }
    // public List<List<Integer>> buildingOutline(int[][] buildings) {
    //     // write your code here
    //     List<List<Integer>> res = new ArrayList<>();
    //     if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
    //         return res;
    //     }
    //     List<int[]> points = new ArrayList<>();
    //     TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
    //     map.put(0, 1);
    //     for (int[] building : buildings) {
    //         points.add(new int[]{building[0], -building[2]});
    //         points.add(new int[]{building[1], building[2]});
    //     }

    //     Collections.sort(points, new Comparator<int[]>() {
    //         public int compare(int[] a, int[] b) {
    //             return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
    //         }
    //     });

    //     int prev = 0;
    //     int start = points.get(0)[0];
    //     for (int[] point : points) {
    //         if (point[1] < 0) {
    //             map.put(-point[1], map.getOrDefault(-point[1], 0) + 1);
    //         } else {
    //             int cnt = map.get(point[1]);
    //             if (cnt == 1) {
    //                 map.remove(point[1]);
    //             } else {
    //                 map.put(point[1], cnt - 1);
    //             }
    //         }
    //         int cur = map.firstKey();
    //         if (cur != prev) {
    //             if (prev != 0) {
    //                 res.add(Arrays.asList(start, point[0], prev));
    //             }
    //             start = point[0];
    //             prev = cur;
    //         }
    //     }
    //     return res;
    // }

}
