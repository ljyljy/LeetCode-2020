package Array.induction;

public class q48_rotate_image {
    // 原地旋转
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // [2,0]->[0,0], [0,1]->[1,2]
        // [i,j] -> [J, N-1-I] -> [n-1-i, n-1-j]  -> [n-1-j, i] -> 循环： [i, j]...
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n+1) / 2; j++) {
                int tmp = matrix[i][j]; // 暂存1
                matrix[i][j] = matrix[n-1-j][i]; // 7 -> 原"1"
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];// 9 -> 原"7"
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];// 3 -> 原"9"
                matrix[j][n-1-i] = tmp;// 1 -> 原"3"
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
