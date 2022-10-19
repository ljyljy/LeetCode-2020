#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

int diff[10] = {
        [0] = 0 ,[1] = 0,[8] = 0, // rotate_same={0,1,8}, rotateState=0
        [2] = 1,[5] = 1,[6] = 1,[9] = 1,// rotate_diff={2,5,6,9}, rotateState=1
        [3] = -1,[4] = -1,[7] = -1 };// 其余, rotateState=-1(invalid)

int dfs(char* numStr, int m, int(*memo)[m], int idx, int rotateState, bool isLimit);

int rotatedDigits(int n) {
    char* numStr = (char*)malloc(sizeof(char) * 11);
    sprintf(numStr, "%d", n);
    int m = strlen(numStr);
    int memo[m][m];
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < m; j++) {
            memo[i][j] = -1;
        }
    }
    return dfs(numStr, m, memo, 0, 0, true);
}

int dfs(char* numStr, int m, int(*memo)[m], int idx, int rotateState, bool isLimit) {
    if (rotateState == -1) return 0;
    if (idx == m) return rotateState;
    if (!isLimit && memo[idx][rotateState] != -1) return memo[idx][rotateState];
    int cnt = 0;
    int top = isLimit ? numStr[idx] - '0' : 9;
    for (int bit = 0; bit <= top; bit++) {
        cnt += dfs(numStr, m, memo, idx + 1,
            rotateState | diff[bit], isLimit && bit == top);
    }
    // 若有limit，则不记录memo！！！
    if (!isLimit) memo[idx][rotateState] = cnt;
    return cnt;
}

int main() {
    for (int i = 0; i < 10; i++) {
        printf("%d ", diff[i]);
    }

    int n = 10;
    int ans = rotatedDigits(10);
    printf("\nans = %d\n", ans);

    printf("-1 | 0 = %d, -1 || 0 = %d\n", -1 | 0, -1 || 0);
}