package Recursion.permutation_based;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class q1723_find_min_time_to_finish_all_jobs {
    // 法1【必会+理解set, 推荐】：set版去重 - 同一父亲的同一树层结点，若【某工时】已遍历，则跳过
    // 时间 O(k^n) 空间O(k)
    private int[] jobs;
    private int n, k;
    private int ans = Integer.MAX_VALUE; // min{maxWorkTime} // Arrays.stream(jobs).cnt();
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
        // ∵ 同一层中，若当前工时之前遍历过(如:员工2工时10 - workerTime=
        //[2,8,10]，同层员工1又计算出10 - workerTime=[2,10,8])，则后续可跳过。
        // ❤（∵排列问题，∴该情况属于：workerTime内部元素顺序对调，算作重复）
        Set<Integer> used = new HashSet<>(); // 同一树层去重(工时相同，说明[工时排列]仅互换了顺序)
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
    public int minimumTimeRequired2(int[] jobs, int k) {
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

    // 法3：二分答案
    public int minimumTimeRequired(int[] jobs, int k) {
        this.jobs = jobs;
        this.k = k;
        Arrays.sort(jobs);
        int start = Arrays.stream(jobs).max().getAsInt();
        int end = Arrays.stream(jobs).sum();
        while (start < end) { // [L, mid], [mid+1, R]
            int mid = start + end >> 1;
            if (check(mid)) // 说明找到可行解，∵但需要min{可行解/最大工作时间}
                end = mid; // ∴ 继续向左区间搜索
            else start = mid + 1; // 无法分配，尝试增大mid={最大工作时间}(右区间)
        }
        return start;
    }

    private boolean check(int tryMaxTime) {
        int[] workTime = new int[k];
        return dfs3(0, workTime, tryMaxTime);
    }

    private boolean dfs3(int idx, int[] workTime, int tryMaxTime) {
        if (idx == jobs.length) return true; // 找到可行解
        for (int i = 0; i < k; i++) { // 横向遍历：员工i
            if (workTime[i] + jobs[idx] <= tryMaxTime) {
                workTime[i] += jobs[idx];
                //如果返回值为true，表示该组合满足条件，直接返回true。
                //我们只需要找到一组符合条件的组合，证明上限mid是可以的就行了。
                //不需要把所有的组合都递归出来。
                if (dfs3(idx+1, workTime, tryMaxTime))
                    return true; // 纵向遍历：工作idx
                workTime[i] -= jobs[idx];
            }
            // (1)如果当前工人 i 和 i+1 都没有被分配工作[回溯完毕，下探false]，那么我们将工作先分配给任何一个人都没有区别，
            // 如果分配给工人 i 不能成功完成分配任务(❤被回溯为0)，那么分配给工人 i+1 也一样无法完成。
            // (2) [不剪枝(2)也可以过]如果第i个工人承担当前工作达到上限，且返回值为false(程序执行到这里默认是false了)
            // 说明后续工作无法分配？-- 就出现了与先前定义分配原则矛盾的情况。具体解释看问题2第3个。
            if (workTime[i] == 0 || workTime[i] + jobs[idx] == tryMaxTime)
                break;

        }
        return false;
    }

}
