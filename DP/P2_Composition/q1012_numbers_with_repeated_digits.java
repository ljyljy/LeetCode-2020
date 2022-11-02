package DP.P2_Composition;

import java.util.Arrays;

public class q1012_numbers_with_repeated_digits {
    char[] chs;
    int[][] memo1;
    int n;

    // ��2����Ӵ𰸣��󡾲��������֡��ķ������������N-dfs1, �� num - dfs1
    // https://leetcode.cn/problems/numbers-at-most-n-given-digit-set/solution/by-lfool-epqy/
    // https://leetcode.cn/problems/numbers-with-repeated-digits/solution/by-endlesscheng-c5vg/
    public int numDupDigitsAtMostN(int num) {
        chs = String.valueOf(num).toCharArray();
        n = chs.length;
        memo1 = new int[n][1024]; // <idx, mask, �Ƿ�����һλ�ظ�0/1>
        // �� mask/bitmap:��0~9���Ƿ���ѡ��, �Ͻ�=11λbin(1024)
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo1[i], -1);
        } // ���ȡ�� ��
        return num - dfs1(0, 0, true, false);
    }

    private int dfs1(int idx, int mask, boolean isLimit, boolean isNum) {
        // ������������� isNum Ϊ false����ʾһֱû��ѡ��ֱ�ӷ��� 0�����򷵻� 1
        // �������������ʱ��isNum ��Ϊ false������һ�ֱ�ʾ���壬����ʾ 0
        if (idx == n) return isNum ? 1 : 0;
        if (!isLimit && isNum && memo1[idx][mask] != -1) {
            return memo1[idx][mask];
        }
        // ������0��ͷ������δisNum�������1�𣡣�ȥ��ǰ��0��
        int bottom = isNum ? 0 : 1;
        int up = isLimit ? chs[idx] - '0' : 9;
        // 1) ����δisNum������Բ�ѡ��ǰidx����̽
        //     ps.��isNum��������idx���������У�cnt����0
        int cnt = isNum ? 0 : dfs1(idx + 1, mask, false, isNum);
        for (int bit = bottom; bit <= up; bit++) {
            if ((mask >> bit & 1) != 1) { // û�б�ѡ�����δ�ظ���
                // 2��ѡ��ǰ������isNumһ��Ϊtrue, ������̽
                cnt += dfs1(idx + 1, mask | (1 << bit),
                        isLimit && bit == up, true);
            }
        }
        if (!isLimit && isNum) memo1[idx][mask] = cnt;
        return cnt;
    }


    // ��1��ֱ���󷽰���
    int[][][] memo;

    public int numDupDigitsAtMostN1(int num) {
        chs = String.valueOf(num).toCharArray();
        n = chs.length;
        memo = new int[n][1024][2]; // <idx, mask, �Ƿ�����һλ�ظ�0/1>
        // �� mask/bitmap:��0~9���Ƿ���ѡ��, �Ͻ�=11λbin(1024)
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
        // ������0��ͷ������δisNum�������1�𣡣�ȥ��ǰ��0��
        int bottom = isNum ? 0 : 1;
        int up = isLimit ? chs[idx] - '0' : 9;
        int cnt = 0;
        if (!isNum) { // 1����δisNum���ɲ�ѡ��ǰ����ֱ����̽
            // isLimitΪfalse������Ϊ��ǰbitλ��ѡ��һ����ԭ��nС������Ҫlimit�ˣ�
            cnt += dfs(idx + 1, mask, repeat, false, false);
        }
        for (int bit = bottom; bit <= up; bit++) {
            // 2��ѡ��ǰ������isNumһ��Ϊtrue, ������̽
            cnt += dfs(idx + 1, mask | (1 << bit),
                    repeat | mask >> bit & 1,
                    isLimit && bit == up, true);
        }
        if (!isLimit && isNum) memo[idx][mask][repeat] = cnt;
        return cnt;
    }

}
