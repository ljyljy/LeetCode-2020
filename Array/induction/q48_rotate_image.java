package Array.induction;

public class q48_rotate_image {
    // ԭ����ת
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // ѭ����[i][j] -> [n-j-1][i] -> [n-i-1][n-j-1] -> matrix[j][n-i-1]
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n+1) / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = tmp;// matrix[i][j];
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
