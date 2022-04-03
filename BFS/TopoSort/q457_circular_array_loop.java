package BFS.TopoSort;

import java.util.*;

public class q457_circular_array_loop {
    private int n;
    private int[] nums, inDeg;
    private List<List<Integer>> graph;
    public boolean circularArrayLoop(int[] nums) {
        n = nums.length; this.nums = nums;
        // 先处理(flag正)正向边 nums[i] > 0 的情况
        setGraph(true);
        boolean res = topoSort();
        if (res) return true;

        // 再处理(flag负) 负向边 nums[i] < 0 的情况
        setGraph(false);
        res = topoSort();
        if (res) return true;

        return false;
    }

    private void setGraph(boolean flag) {
        graph = new ArrayList<>();// 每次需要【重置】！
        inDeg = new int[n];// 每次需要【重置】！
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
//            ❤：取余（负数求补）-- end=((i+nums[i])%n+n)%n 得到终点（负余修正，类比q974、457）
            int dst = ((i + nums[i]) % n + n) % n; // 负数求补
            if (flag) {// 先处理(flag正) 正向边 nums[i] > 0 的情况
                if (nums[i] <= 0 || dst == i) continue; // 排除自环：dst == i(src)
            } else{    // 再处理(flag负) 负向边 nums[i] < 0 的情况
                if (nums[i] >= 0 || dst == i) continue;
            }
            graph.get(i).add(dst); // 图<i_src, <dst>>
            inDeg[dst]++;
        }
    }

    private boolean topoSort() {
        Deque<Integer> deq = new ArrayDeque<>();

        // 1. 起点 -- 入度为0, 加入队列
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) deq.offer(i);
        }

        while (!deq.isEmpty()) {
            int src = deq.poll();
            for(int dst: graph.get(src)) {// 图<src, <dst>>
                inDeg[dst]--;
                if (inDeg[dst] == 0)
                    deq.offer(dst);
            }
        }

        // BFS结束后，若仍有入度非0的点，说明有向图该结点【存在环（非自环）】
        for (int i = 0; i < n; i++) {
            if (inDeg[i] != 0)
                return true; // 存在环
        }
        return false;
    }
}
