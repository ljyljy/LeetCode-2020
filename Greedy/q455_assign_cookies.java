package Greedy;

import java.util.Arrays;

public class q455_assign_cookies {
    // 贪心：大尺寸s优先满足大胃口g/小尺寸s优先满足小胃口g
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g); Arrays.sort(s);
        int n = g.length;
        int maxCnt = 0;
        // i: 胃口，对应孩子i;  j: 饼干尺寸，对应饼干j
        for (int i = 0, j = 0; i < n && j < s.length; j++) {
            if (s[j] >= g[i]) {
                maxCnt++;
                i++; // 继续满足下一个孩子
            } // 每轮结束，无论是否满足当前孩子，饼干j++（若没满足，j++直到满足当前孩子）
        }
        return maxCnt;
    }

}
