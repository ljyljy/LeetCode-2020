package Array.induction;

public class q48_rotate_image {
    // 原地旋转
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 循环：[i][j] -> [n-j-1][i] -> [n-i-1][n-j-1] -> matrix[j][n-i-1]
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n+1) / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = tmp;// matrix[i][j];
                // 这是目标，但循环应该是首尾相连！（以下首首相连 错误！）
                // matrix[i][j] = matrix[j][n-i-1];
                // matrix[j][n-i-1] = matrix[n-i-1][n-j-1];
                // matrix[n-i-1][n-j-1] = matrix[n-j-1][i];
                // matrix[n-j-1][i] = tmp;// matrix[i][j];
            }
        }
        // return matrix;
    }
}
