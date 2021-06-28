package BFS;

import java.util.*;

public class q815_bus_routes {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        int n = routes.length;
        boolean[][] edge = new boolean[n][n]; // <路线x, 路线y>可否换乘(有共同子集)
        Map<Integer, Set<Integer>> siteRtsMap = new HashMap<>();// <站点x,list(路线1 2 3...)>
        for (int r = 0; r < n; r++) { // 遍历路线：r-当前route
            for (int site : routes[r]) { // 遍历该路线所有站点
                // 更新站点site的所有路线（siteRtsMap）
                Set<Integer> routes_site = siteRtsMap.getOrDefault(site, new HashSet<>());
                routes_site.add(r);
                siteRtsMap.put(site, routes_site);
                // 更新'换乘'信息（edge）
                for (int r2: routes_site) {
                    if (r == r2) continue;
                    edge[r][r2] = true;
                    edge[r2][r] = true;
                }
            }
        }
        // 若终点站没有任何路线可通行
        if (!siteRtsMap.containsKey(target)) return -1;

        int[] cnt = new int[n]; // 记录从起点站所在路线r0到其余各个路线r2的乘坐次数（累加）
        Arrays.fill(cnt, -1);
        Deque<Integer> queue = new LinkedList<>(); // 初始化：queue<可通起点的路线>，❤而非site！！
        Set<Integer> routes_src = siteRtsMap.get(source);
        for (int r0: routes_src) {
            cnt[r0] = 1; // 起点站所在路线，尚未换乘
            queue.offer(r0);
        }

        while (!queue.isEmpty()) {
            int r1 = queue.poll();
            for (int r2 = 0; r2 < n; r2++) {
                if (edge[r1][r2] && cnt[r2] == -1) { // -1：表示尚未遍历过（避免重复乘坐）
                    cnt[r2] = cnt[r1] + 1;
                    queue.offer(r2);
                }
            }
        }
        // 遍历终点所在的所有线路r_end，找到cnt[r_end]最小者
        int res = Integer.MAX_VALUE;
        for (int r_end:siteRtsMap.get(target)) {
            if (cnt[r_end] != -1)
                res = Math.min(res, cnt[r_end]);
        }
        return res == Integer.MAX_VALUE? -1:res;
    }
}
