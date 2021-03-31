package Binary_Search;

public class q74_search_2d_matrix {
    // 法3:（BFS）
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0, col = matrix[0].length - 1;
        // 以右上角为起点root, 看作BST(查找方向: 向下row++/向左col--)
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target)  return true;
            else if (matrix[row][col] < target)
                row++; // 如果目标值大，那么下一步往下找
            else if (matrix[row][col] > target)
                col--; // 如果目标值小，那么下一步往左找
        }
        return false;
    }

    // 【荐】法2：二分*1次（先第一列-获取行号row_i，后在row_i行继续二分查找target）
    public boolean searchMatrix_v2_1(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int start = 0, end = m * n - 1;
        while (start + 1 < end) {
            int mid = start + end >> 1;
            int mid_val = matrix[mid / n][mid % n]; // row = mid / n, col = mid % n
            if (mid_val == target) return true;
            else if (mid_val < target)
                start = mid;
            else end = mid;
        }
        if (matrix[start / n][start % n] == target) return true;
        else if (matrix[end / n][end % n] == target) return true;
        return false;

        /* 二分模板3 [L, mid-1], mid, [mid+1, R]
        while (start <= end) {
            int mid = start + end >> 1;
            if (matrix[mid / n][mid % n] == target) return true;
            else if (matrix[mid / n][mid % n] < target) start = mid+1;
            else end = mid-1;
        }
         */
    }

    // 法2:（二分模板3）- [left, mid-1], mid, [mid+1, right]
    public boolean searchMatrix_v2_2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int start = 0, end = m * n - 1;
        while (start <= end) {  // [left, mid-1], mid, [mid+1, right]
            int mid = start + end >> 1;
            int mid_val = matrix[mid / n][mid % n]; // row = mid / n, col = mid % n
            if (mid_val == target) return true;
            else if (mid_val < target)
                start = mid+1;
            else end = mid-1;
        }
        return false;
    }

    // 法1：二分*2次（先第一列-获取行号row_i，后在row_i行继续二分查找target）
    public boolean searchMatrix_v1(int[][] matrix, int target) {
        int row_i = BinSearch1_col0(matrix, target);
        // System.out.println(row_i);
        if (row_i == -1) return false;
        return BinSearch2_row(matrix[row_i], target);
    }

    //  找到最后一个不大于target的matrix第一列元素
    // 二分1st - [left, mid-1], [mid, right]
    private int BinSearch1_col0(int[][] matrix, int target) {
        int start = -1, end = matrix.length - 1;
        while (start < end) {
            int mid = (start + 1) + end >> 1; //
            if (matrix[mid][0] <= target)
                start = mid;
            else end = mid - 1;
        }
        return start;
    }


    // 二分2nd - [left, mid-1], mid, [mid+1, right]
    private boolean BinSearch2_row(int[] row, int target) {
        int start = 0, end = row.length - 1;
        while (start <= end) {
            int mid = start + end >> 1;
            if (row[mid] == target)
                return true;// mid;
            else if (row[mid] < target)
                start = mid+1;
            else end = mid-1;
        }
        return false;
    }

    private boolean BinSearch2_row_v2(int[] row_i, int target) {
        int start = 0, end = row_i.length - 1;
        while (start < end) { // [L, mid], [mid+1, R]
            int mid = start + end >> 1;
            if (row_i[mid] == target) return true;
            else if (row_i[mid] < target) start = mid+1;
            else end = mid;
        }
        return (row_i[start] == target);
    }

}
