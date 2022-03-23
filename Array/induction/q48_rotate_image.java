package Array.induction;

public class q48_rotate_image {
    // ԭ����ת
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // [2,0]->[0,0], [0,1]->[1,2]
        // [i,j] -> [J, N-1-I] -> [n-1-i, n-1-j]  -> [n-1-j, i] -> ѭ���� [i, j]...
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n+1) / 2; j++) {
                int tmp = matrix[i][j]; // �ݴ�1
                matrix[i][j] = matrix[n-1-j][i]; // 7 -> ԭ"1"
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];// 9 -> ԭ"7"
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];// 3 -> ԭ"9"
                matrix[j][n-1-i] = tmp;// 1 -> ԭ"3"
                // ����Ŀ�꣬��ѭ��Ӧ������β�������������������� ���󣡣�
                // matrix[i][j] = matrix[j][n-i-1];
                // matrix[j][n-i-1] = matrix[n-i-1][n-j-1];
                // matrix[n-i-1][n-j-1] = matrix[n-j-1][i];
                // matrix[n-j-1][i] = tmp;// matrix[i][j];
            }
        }
        // return matrix;
    }
}
