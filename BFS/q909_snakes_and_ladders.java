package BFS;

import java.util.*;

public class q909_snakes_and_ladders {
    // 最新版本
    private Map<Integer, Integer> map = new HashMap<>(); // visitedMap<idx, step>
    private int n;
    private int[] board1D;
    public int snakesAndLadders(int[][] board) {
        n = board.length;
        if (board[0][0] != -1) return -1;
        boolean isR = true;
        board1D = new int[n*n+1];
        int idx = 1;
        for (int i = n-1; i >= 0; i--) {
            for (int j = isR? 0: n-1; (isR? j < n: j >= 0); j += isR? 1: -1) {
                board1D[idx++] = board[i][j];
            }
            isR = !isR;
        }

        return bfs();
    }

    private int bfs() {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1); // idx=1起始
        // ↓ 其实不需要，可以直接使用visitedMap.contains（见old版本），类比q815，q909
        boolean[] visited = new boolean[n*n+1];
        visited[1] = true;
        map.put(1, 0);

        while (!queue.isEmpty()) {
            int curIdx = queue.poll();
            int step = map.get(curIdx);
            if (curIdx == n*n) return step;
            for (int nxtIdx : getNextIdx(curIdx)) {
                if (!isValid(nxtIdx) || visited[nxtIdx]) continue;
                visited[nxtIdx] = true;
                queue.offer(nxtIdx);
                map.put(nxtIdx, step + 1);
            }
        }
        return -1;
    }

    private Set<Integer> getNextIdx(int curIdx) {
        Set<Integer> nxtSet = new HashSet<>();
        for (int dir = 1; dir <= 6; dir++) {
            int nxtIdx = curIdx + dir;
            if (!isValid(nxtIdx)) continue;
            if (board1D[nxtIdx] != -1) {
                nxtIdx = board1D[nxtIdx];
            }
            nxtSet.add(nxtIdx);
        }
        return nxtSet;
    }

    private boolean isValid(int idx) {
        return 0 <= idx && idx <= n*n;
    }

    // old
    public int snakesAndLadders_old(int[][] board) {
        int n = board.length;
        // 若最终目的地还有所指向，则无法停留在最终目的地
        if (board[0][0] != -1) return -1;
        int[] board1D = new int[n*n+1];// idx从1起！[0]处空缺
        boolean isL = true; // 是否从L左侧开始向右侧遍历
        int idx = 1; // 方格下标从1起！
        for (int i = n-1; i >= 0; i--) { // 行
            for (int j = (isL? 0:n-1); (isL? j<n: j>=0); j += isL? 1:-1) {
                board1D[idx++] = board[i][j];
            }
            isL = !isL;
        }
        // for(int num:board1D) System.out.println(num);

        Deque<Integer> queue = new ArrayDeque<>();// idx（从1起）
        Map<Integer, Integer> map = new HashMap<>();// <idx, step>
        queue.offer(1);
        map.put(1, 0);

        while (!queue.isEmpty()) {
            int curIdx = queue.poll();
            int step = map.get(curIdx);
            if (curIdx == n*n) return step;
            for (int i = 1; i <= 6; i++) {
                int nxtIdx = curIdx + i;
                if (nxtIdx < 0 || nxtIdx > n*n) continue;
                if (board1D[nxtIdx] != -1) // 若非-1，则必须爬向指定目的地(该步骤内)
                    nxtIdx = board1D[nxtIdx];
                if (!map.containsKey(nxtIdx)) {
                    queue.offer(nxtIdx);
                    map.put(nxtIdx, step+1);
                }
                // if (nxtIdx == n*n) return map.get(nxtIdx);
            }
        }
        return -1;
    }
}
