package DP.P2_Composition;

import java.util.Arrays;

public class q1012_numbers_with_repeated_digits {
    // 法1：直接求方案数
    char[] chs;
    int[][][] memo1;
    int n;

    public int numDupDigitsAtMostN(int num) {
        chs = String.valueOf(num).toCharArray();
        n = chs.length;
        memo1 = new int[n][1024][2]; // <idx, mask, 是否与上一位重复0/1>
        // ↑ mask/bitmap:‘0~9’是否已选择, 上界=11位bin(1024)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 1024; j++) {
                Arrays.fill(memo1[i][j], -1);
            }
        }
        return dfs(0, 0, 0, true, false);
    }

    private int dfs(int idx, int mask, int repeat, boolean isLimit, boolean isNum) {
        if (idx == n) return isNum && (repeat == 1) ? 1 : 0;
        if (!isLimit && isNum && memo1[idx][mask][repeat] != -1) {
            return memo1[idx][mask][repeat];
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
        if (!isLimit && isNum) memo1[idx][mask][repeat] = cnt;
        return cnt;
    }

    // todo: 法2：间接答案，求不存在连续数字的方案数，最后用num-dfs2
    // https://leetcode.cn/problems/numbers-at-most-n-given-digit-set/solution/by-lfool-epqy/
    // https://leetcode.cn/problems/numbers-with-repeated-digits/solution/by-endlesscheng-c5vg/
}
