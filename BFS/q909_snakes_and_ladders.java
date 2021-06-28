package BFS;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class q909_snakes_and_ladders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        // 若最终目的地还有所指向，则无法停留在最终目的地
        if (board[0][0] != -1) return -1;
        int[] board0 = new int[n*n+1];// idx从1起！[0]处空缺
        boolean isL = true; // 是否从L左侧开始向右侧遍历
        int idx = 1; // 方格下标从1起！
        for (int i = n-1; i >= 0; i--) { // 行
            for (int j = (isL? 0:n-1); (isL? j<n: j>=0); j += isL? 1:-1) {
                board0[idx++] = board[i][j];
            }
            isL = !isL;
        }
        // for(int num:board0) System.out.println(num);

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
                if (board0[nxtIdx] != -1) // 若非-1，则必须爬向指定目的地(该步骤内)
                    nxtIdx = board0[nxtIdx];
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
