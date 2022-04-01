package BFS;

import java.util.*;

public class q815_bus_routes {
    // 预处理领接表等：类比HJ22_1_3, Q815
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        int n = routes.length;
        boolean[][] edge = new boolean[n][n]; // <路线x, 路线y>可否换乘(有共同子集)
        Map<Integer, Set<Integer>> siteBusMap = new HashMap<>();// 与routes相反，<站点x,list(公交路线1 2 3...)>, 如<站点7, 路线[0, 1]>
        for (int bus = 0; bus < n; bus++) { // 遍历路线：bus-当前route // 如：idx=0,1
            for (int site : routes[bus]) { // 遍历该路线所有站点 // 如[1,2,7]或[3,6,7]
                // 更新包含站点site的所有路线（siteBusMap）
                Set<Integer> busSet = siteBusMap.getOrDefault(site, new HashSet<>());
                busSet.add(bus);
                siteBusMap.put(site, busSet);
                // 更新'换乘'信息（edge）
                for (int bus2 : busSet) {
                    if (bus == bus2) continue;
                    edge[bus][bus2] = true;
                    edge[bus2][bus] = true;
                }
            }
        }
        // 若终点站没有任何路线可通行
        if (!siteBusMap.containsKey(target)) return -1;

        // ↓【visitedMap变种】：类比q815，q909
        int[] transferCnt = new int[n]; // visitedMap: 记录从起点站所在路线r0到其余各个路线r2的乘坐次数（累加）
        Arrays.fill(transferCnt, -1);
        Deque<Integer> queue = new LinkedList<>();
        // 初始化：queue<可通起点的路线bus-∵需计算换乘数>，而非site！！(类比q207，815)
        for (int bus : siteBusMap.get(source)) {
            transferCnt[bus] = 1; // 起点站所在路线，尚未换乘
            queue.offer(bus);
        }

        while (!queue.isEmpty()) {
            int bus = queue.poll();
            for (int nxtBus = 0; nxtBus < n; nxtBus++) {
                if (edge[bus][nxtBus] && transferCnt[nxtBus] == -1) { // -1：表示尚未遍历过（避免重复乘坐）
                    transferCnt[nxtBus] = transferCnt[bus] + 1; // transferCnt <=> visitedMap变种，类比q909
                    queue.offer(nxtBus);
                }
            }
        }
        // 遍历终点所在的所有线路busEnd，找到transferCnt[busEnd]最小者
        int res = Integer.MAX_VALUE;
        for (int busEnd : siteBusMap.get(target)) {
            if (transferCnt[busEnd] != -1)
                res = Math.min(res, transferCnt[busEnd]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
