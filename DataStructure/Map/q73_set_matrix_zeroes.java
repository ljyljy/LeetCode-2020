package DataStructure.Map;

public class q73_set_matrix_zeroes {
    // ��1���ռ�O(m+n)
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] rows = new boolean[m]; // ���������ϣ
        boolean[] cols = new boolean[n];
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] || cols[j])
                    matrix[i][j] = 0;
            }
        }
    }

    // ��2���ռ�O(1) - ��
//    https://leetcode-cn.com/problems/set-matrix-zeroes/solution/xiang-jie-fen-san-bu-de-o1-kong-jian-jie-dbxd/
}
