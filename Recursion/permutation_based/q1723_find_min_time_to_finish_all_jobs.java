package Recursion.permutation_based;

import java.util.HashSet;
import java.util.Set;

public class q1723_find_min_time_to_finish_all_jobs {
    // 法1【必会+理解set】：set版去重 - 同一父亲的同一树层结点，若workerTotalTime[员工i]已遍历，则跳过
    // 时间 O(k^n) 空间O(k)
    private int[] jobs;
    private int n, k;
    private int ans = Integer.MAX_VALUE; // min{maxWorkTime} // Arrays.stream(jobs).sum();
    public int minimumTimeRequired1(int[] jobs, int k) {
        this.jobs = jobs;
        this.k = k;
        this.n = jobs.length;
        int[] workerTime = new int[k];// 记录工人i的总工时(如1+2+8、4+7)
        dfs(0, workerTime, 0);
        return ans;
    }

    private void dfs(int idx, int[] workerTime, int maxTime) {
        // 剪枝: 若递归maxWT>=当前记录的ans，肯定不是答案
        if (maxTime >= ans) return;
        if (idx == n)  {
            ans = Math.min(ans, maxTime);
            return; // ans=min{maxTime}, 最小可行解
        }
        // ∵ 同一层中，若同一员工当前工时之前遍历过(重复)，则后续可跳过。
        // （就算之前路径不同，后面的路径是排列问题，minAns已经算过了，无需重复计算一遍后面的排列）
        Set<Integer> used = new HashSet<>(); // 同一树层去重(同一员工&&工时相同)
        for (int i = 0; i < k; i++) {  // 横向遍历: k个员工i
            if (used.contains(workerTime[i])) continue;   // 不加会TLE!!!

            used.add(workerTime[i]);
            workerTime[i] += jobs[idx];  // 当前员工i分配工作jobs[idx]
            dfs(idx+1, workerTime, Math.max(maxTime, workerTime[i]));
            // 或 dfs(idx+1, workerTime, Arrays.stream(workerTime).max().getAsInt());
            workerTime[i] -= jobs[idx]; // 撤销选择 (↑ maxWT实则为数组workerTT的最大值)
        }
    }

    // 法2：【理解、掌握 - 技巧 & 同一棵搜索树，调换搜索顺序の"剪枝"】
    // 【同一树枝"去重"-优先不重复结点】技巧版剪枝 - 优先分配给「空闲工人」
    public int minimumTimeRequired(int[] jobs, int k) {
        this.jobs = jobs;
        this.k = k;
        this.n = jobs.length;
        int[] workerTime = new int[k];// 记录工人i的总工时(如1+2+8、4+7)
        // ↓ 错误！因为不是严格的去重，只是优先考虑同一树枝的非重复员工，但允许重复
        // boolean[] used = new boolean[k];
        dfs2(0, 0, workerTime, 0);
        return ans;
    }

    private void dfs2(int idx, int used, int[] workerTime, int maxTime) {
        if (maxTime >= ans) return;// 剪枝: 若>=当前最小的可行解，跳过
        if (idx == n) {
            ans = Math.min(ans, maxTime);
            return;// ans=min{maxTime}, 最小可行解
        }
        // 剪枝：优先分配给「空闲工人」, used: 从员工0开始依次分配，直到员工k-1为止(例1: 依次分配给员工0,1,2;即遍历完[0,1]后，优先遍历[2])
        if (used < k) {
            workerTime[used] += jobs[idx];
            dfs2(idx+1, used+1, workerTime, Math.max(maxTime, workerTime[used]));
            workerTime[used] -= jobs[idx];
        }
        // 正规全排列，注意i<k仍会TLE ! (例1: 上面回溯后, 假设used=1, 说明[员工2已遍历过并回溯至此]，接下来应该搜索used，为员工[0,1]分配工作)
        for(int i = 0; i < used; i++) {
            workerTime[i] += jobs[idx];  // 当前员工i分配工作jobs[idx]
            dfs2(idx+1, used, workerTime, Math.max(maxTime, workerTime[i]));
            // 或 dfs(idx+1, used, workerTime, Arrays.stream(workerTime).max().getAsInt());
            workerTime[i] -= jobs[idx]; // 撤销选择 (↑ maxWT实则为数组workerTT的最大值)
        }
    }

}
