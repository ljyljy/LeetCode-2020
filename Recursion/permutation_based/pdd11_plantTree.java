package Recursion.permutation_based;

// https://www.nowcoder.com/practice/52f25c8a8d414f8f8fe46d4e62ef732c?tpId=158&tqId=34023&companyId=732&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F732&qru=%2Fta%2Fexam-pdd%2Fquestion-ranking&tab=answerKey

import java.util.*;

public class pdd11_plantTree {
    public Deque<Integer> res = new ArrayDeque<>();
    private boolean dfs(int[] tree, int idx, int totalCnt) {
        if (idx == totalCnt) return true;
        // 剪枝：若剩余坑位 < 某种树的剩余数目*2(注意奇偶)
        //       如果还剩下remain=2k+1个坑位, 最多有k+1个树属于同一个种类(cnt_i(<=))
        //              --> k = cnt-1; 2*(cnt-1) + 1 <= remain  --> 2*cnt <= remain + 1
        //       如果还剩下remain=2k个坑位, 最多有k个树属于同一个种类(cnt_i(<=))
        //              --> cnt_i*2 <= remain
        // （∵这样必相邻，不符题意 ↑）
        for (int cnt_i: tree) {
            int remain = totalCnt - idx + 1;
            if (cnt_i * 2 > remain)  // 存在，cnt*2 >{remain, remain+1}min
                return false;
        }
        // 横向遍历（某一数层）：所有类
        for (int i = 0; i < tree.length; i++) {
            // 当前树种还有剩余可种 && 相邻/前者 与自己不同类
            if (res.isEmpty() || (tree[i] > 0 && i != res.peekLast())){
                res.addLast(i);
                tree[i]--;
                // 排列问题：1) for从0起 2) 下探idx+1
                boolean flag = dfs(tree, idx+1, totalCnt);
                if (flag) return true;
                tree[i]++;
                res.removeLast();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] tree = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            tree[i] = sc.nextInt();
            sum += tree[i];
        }
        pdd11_plantTree sol = new pdd11_plantTree();
        boolean flag = sol.dfs(tree, 0, sum);

        if (flag) {
            for (Integer idx: sol.res) {
                System.out.print(idx+1 + " ");
            }
            System.out.println();
        }else {
            System.out.println("-");
        }
    }
}
