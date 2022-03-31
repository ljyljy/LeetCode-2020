package DP.G1_01BagPack.optimize;

import java.util.*;

public class q45_LCP9_minJump {
    // ��1��dp
    // ���⣺i��ֻ����Ծ��1)��[0, i-1]��2)��i+jump[i]��
    // �������� ��Ծ��ϷQ45��55����
    public int minJump_dp(int[] jump) {
        int n = jump.length;
        int[] dp = new int[n]; // ���١��١���x�Σ�����(�Ӻ���ǰ)
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[n-1] = 1; // �߽��ٵ�1�Σ���������
        for (int i = n-2; i >= 0; i--) {
            // ��Խ�磬���ٵ�һ�μ��ɣ�������Ҫ��1�ε�i + jump[i]��������dp[i + jump[i]]��
            dp[i] = i + jump[i] >= n? 1: dp[i + jump[i]] + 1;
            // ��Ϊ���������ǰ���������������һ�ΰѺ������������滻��
            for(int j = i + 1; j < n; j++){
                if (dp[j] <= dp[i]) break; // ��֦���˺�Ӧ��j����������
                dp[j] = dp[i] + 1;// Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[0];
    }


    // ��2��BFS
    public int minJump(int[] jump) {
        int n = jump.length;
        boolean[] used = new boolean[n+1];
        Deque<int[]> queue = new ArrayDeque<>(); // [idx, step]
        queue.offer(new int[]{0, 1});
        used[0] = true;
        int farthestL = 1; // ?
        while (!queue.isEmpty()) {
            int size = queue.size();
            // for (int i = 0; i < size; i++) {
            int[] cur = queue.poll();
            int idx = cur[0], step = cur[1];
            int farIdx = idx + jump[idx];
            if (farIdx >= n) return step;
            if (!used[farIdx]) {
                queue.offer(new int[]{farIdx, step+1});
                used[farIdx] = true;
            }
            for (int j = farthestL; j < idx; j++) {
                if (!used[j]) {
                    queue.offer(new int[]{j, step+1});
                    used[j] = true;
                }
            }
            // }
            farthestL = Math.max(farthestL, idx+1);
        }
        return -1;
    }
}
