package Recursion.partition_based;

import java.util.*;

public class q93_restore_ip_addresses {
    private List<String> res = new ArrayList<>();
    // 长度�?4段[0,255]的数�?  [vs qo_38字符串的全排列II]
    private Deque<String> path = new ArrayDeque<>(4);

    public List<String> restoreIpAddresses(String s) {
        int n = s.length();
        if (n < 4 || n > 12) return res;
        dfs(s, n, 0, 0, path);
        return res;
    }

    private void dfs(String s, int n, int idx, int SplitNum, Deque<String> path) {
        if (idx == n) { // 若分割idx==n
            System.out.println("idx==n");
            if (SplitNum == 4) // 合法分段�?=4
                res.add(String.join(".", path)); // py: '.'.join(path)
            return;
        }
        // 剪枝1：剩余数字个�?/长度 �? (合法)待遍历数字个数区间[remainSplit, 3*remainSplit]
        int remainLen = n - idx; // 剩余待搜索的数字个数/长度
        int remainSplit = 4 - SplitNum;    // �? 子段[0, 255], 长度∈[1, 3]
        if (remainLen < remainSplit || remainLen > 3 * remainSplit)
            return;

        // �? 不是排列 �? i从idx起始
        for (int i = idx; i < n; i++) {
            int curSeg = check(s, idx, i);
            if (curSeg == -1) continue; // 剪枝2：子段s[idx, i]非法跳过
            path.addLast(curSeg + ""); // 或String.valueOf(curSeg)
            dfs(s, n, i + 1, SplitNum + 1, path);
            path.removeLast();
        }
    }

    private int check(String s, int start, int end) {
        int len = end - start + 1;
        // 1) 长度�? [1, 3]
        if (len < 1 || len > 3) return -1;
        // 2) 允许单独'0'分段, 但长�?>1�? 不允许有"前导0"(形如"011" ×)
        if (s.charAt(start) == '0' && len != 1) return -1;
        // 3) 子段 数字res∈[0, 255]
        /*// �?2: 求res
        res = 0;
        for (int i = start; i <= end; i++)
            res = res * 10 + (s.charAt(i) - '0');
        */
        int res = Integer.valueOf(s.substring(start, end+1));
        if (res > 255 || res < 0) return -1;
        return res;
    }

    public static void main(String[] args) {
        q93_restore_ip_addresses sol = new q93_restore_ip_addresses();
        String s1 = "25525511135", s3="256", s4="011", s2="0";
        String s5 = "010010";
        System.out.println(sol.check(s4, 0, s2.length()-1));
        List<String> res = sol.restoreIpAddresses(s5);
        System.out.println(res);
    }
}
