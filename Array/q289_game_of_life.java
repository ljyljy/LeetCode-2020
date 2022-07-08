package Array;

public class q289_game_of_life {
    // ����-ģ��
    // ����1��ԭ���޸ģ�board[i][j]��ֵ����ʮλ����ֵ0/1���ڸ�λ������ʱ>>1���ɸ��Ǿ�ֵ��
    public void gameOfLife(int[][] board) {
        if (board == null) return;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveAround = 0;
                // �߽磡��for (x = i-1; x <= i+1; x++) Ȼ��check x�Ƿ�Խ��
                for (int x = Math.max(i-1, 0); x <= Math.min(i+1, m-1); x++) {
                    for (int y = Math.max(j-1, 0); y <= Math.min(j+1, n-1); y++) {
                        if (x == i && y == j) continue;
                        if ((board[x][y] & 1) == 1) { // ���������ţ�'&'�����ȼ��ͣ���
                            liveAround++;
                        }
                    }
                }
                int cur = board[i][j] & 1, nxt;
                if (cur == 1) { // ��ǰ��
                    if (liveAround < 2 || liveAround > 3) {
                        nxt = 0;
                    } else nxt = 1; // liveAround == 2��3
                } else { // ��ǰ��
                    if (liveAround == 3) nxt = 1;
                    else nxt = 0;
                }
                board[i][j] |= (nxt << 1); // ԭ���޸�: ��ֵnxt����ʮλ
            }
        }

        // ԭ���޸�2����ֵ��ʮλ>>1�����Ǿ�ֵ����λ��
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
    }
}
