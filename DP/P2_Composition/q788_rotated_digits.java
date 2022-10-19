package DP.P2_Composition;

import java.util.Arrays;

public class q788_rotated_digits {
    char[] numStr;
    int[][] memo;
    int[] diff;
    int m;

    public int rotatedDigits(int n) {
        numStr = String.valueOf(n).toCharArray();
        m = numStr.length;
        memo = new int[m][2]; // <idx, rotateState>
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        generateDiff();
        // �ӵ�0λ=0������0��ת����Ϊ�Լ����ʳ�ʼrotateState=0
        return dfs(0,0,true);
    }

    private int dfs(int idx, int rotateState, boolean isLimit) {
        // ��ĳһλΪ3/4/7����ת��invalid������������嶼��Ч����0
        if (rotateState == -1) return 0; // v1, ��֤rotateState��{0,1}
        if (idx == m) return rotateState;
        if (!isLimit && memo[idx][rotateState] != -1) return memo[idx][rotateState];
        int cnt = 0;
        int top = isLimit? numStr[idx] - '0' : 9;
        for (int bit = 0; bit <= top; bit++) {
//            if (rotateState == -1) continue; // v2, ��֤rotateState��{0,1}
            cnt += dfs(idx+1, rotateState | diff[bit],
                    isLimit && bit == top); // �� ��λ�򣡣���
            // -1 | 0 = -1, -1 || 0 = 1
        }
        if (!isLimit) memo[idx][rotateState] = cnt;
        return cnt;
    }

    // ��ֱ�ӣ�int[] diff = new int[]{0, 0, 1, -1, -1, 1, 1, -1, 0, 1};
    private void generateDiff() {
        // ����0~9����ת180�Ⱥ����� rotateState��{-1,0,1}
        diff = new int[10]; // diff=1,same=0,invalid=-1
        int[] rotate_diff = {2, 5, 6, 9};
        int[] rotate_same = {0, 1, 8};
        int[] rotate_invalid = {3, 4, 7};
        for (int i: rotate_diff)  diff[i] = 1;
        for (int i: rotate_same)  diff[i] = 0;
        for (int i: rotate_invalid)  diff[i] = -1;
    }
}
