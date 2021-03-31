package Array;

class qo_04_q90_findNumberIn2DArray {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            int curVal = matrix[row][col];
            if(curVal < target) row++;
            else if(curVal > target) col--;
            else return true; // ==
        }
        return false;
    }
}