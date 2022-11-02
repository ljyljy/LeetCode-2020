package DP.P2_Composition;

import java.util.Arrays;

public class q1012_numbers_with_repeated_digits {
    char[] chs;
    int[][] memo1;
    int n;

    // 法2：间接答案，求【不连续数字】的方案数，最后用N-dfs1, 即 num - dfs1
    // https://leetcode.cn/problems/numbers-at-most-n-given-digit-set/solution/by-lfool-epqy/
    // https://leetcode.cn/problems/numbers-with-repeated-digits/solution/by-endlesscheng-c5vg/
    public int numDupDigitsAtMostN(int num) {
        chs = String.valueOf(num).toCharArray();
        n = chs.length;
        memo1 = new int[n][1024]; // <idx, mask, 是否与上一位重复0/1>
        // ↑ mask/bitmap:‘0~9’是否已选择, 上界=11位bin(1024)
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo1[i], -1);
        } // 结果取反 ↓
        return num - dfs1(0, 0, true, false);
    }

    private int dfs1(int idx, int mask, boolean isLimit, boolean isNum) {
        // 结束条件：如果 isNum 为 false，表示一直没有选择，直接返回 0；否则返回 1
        // 如果到结束条件时，isNum 仍为 false，还有一种表示含义，即表示 0
        if (idx == n) return isNum ? 1 : 0;
        if (!isLimit && isNum && memo1[idx][mask] != -1) {
            return memo1[idx][mask];
        }
        // 不能以0打头，若尚未isNum，必须从1起！（去除前导0）
        int bottom = isNum ? 0 : 1;
        int up = isLimit ? chs[idx] - '0' : 9;
        // 1) 若尚未isNum，则可以不选当前idx，下探
        //     ps.若isNum，则必须对idx处进行特判，cnt先置0
        int cnt = isNum ? 0 : dfs1(idx + 1, mask, false, isNum);
        for (int bit = bottom; bit <= up; bit++) {
            if ((mask >> bit & 1) != 1) { // 没有被选择过，未重复过
                // 2）选当前数，则isNum一定为true, 继续下探
                cnt += dfs1(idx + 1, mask | (1 << bit),
                        isLimit && bit == up, true);
            }
        }
        if (!isLimit && isNum) memo1[idx][mask] = cnt;
        return cnt;
    }


    // 法1：直接求方案数
    int[][][] memo;

    public int numDupDigitsAtMostN1(int num) {
        chs = String.valueOf(num).toCharArray();
        n = chs.length;
        memo = new int[n][1024][2]; // <idx, mask, 是否与上一位重复0/1>
        // ↑ mask/bitmap:‘0~9’是否已选择, 上界=11位bin(1024)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 1024; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return dfs(0, 0, 0, true, false);
    }

    private int dfs(int idx, int mask, int repeat, boolean isLimit, boolean isNum) {
        if (idx == n) return isNum && (repeat == 1) ? 1 : 0;
        if (!isLimit && isNum && memo[idx][mask][repeat] != -1) {
            return memo[idx][mask][repeat];
        }
        // 不能以0打头，若尚未isNum，必须从1起！（去除前导0）
        int bottom = isNum ? 0 : 1;
        int up = isLimit ? chs[idx] - '0' : 9;
        int cnt = 0;
        if (!isNum) { // 1）尚未isNum，可不选当前数，直接下探
            // isLimit为false！！因为当前bit位不选，一定比原数n小，不需要limit了！
            cnt += dfs(idx + 1, mask, repeat, false, false);
        }
        for (int bit = bottom; bit <= up; bit++) {
            // 2）选当前数，则isNum一定为true, 继续下探
            cnt += dfs(idx + 1, mask | (1 << bit),
                    repeat | mask >> bit & 1,
                    isLimit && bit == up, true);
        }
        if (!isLimit && isNum) memo[idx][mask][repeat] = cnt;
        return cnt;
    }

}
