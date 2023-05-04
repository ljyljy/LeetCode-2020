#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q79.c
/* 知识点：
1) 二维转一维，方便传参
- visited[i][j] --> visited[i * g_n + j]
  -否则还需传入g_n, (bool*) visited[g_n] 或 同时传入g_m & g_n：bool visited[g_m][g_n] (见法2，q79v2.c)

2）法1：带全局变量isFound vs 法2：有返回值的dfs
*/
int g_m, g_n;
char** g_board;
bool isFound;
const int _x[4] = { 0,  1, 0, -1 };
const int _y[4] = { -1, 0, 1,  0 };

// 法1：dfs + 全局变量isFound
void dfs(int i, int j, int idx, char* word, bool* visited); // <i,j>: 当前位置; idx：匹配word的下标进度(搜索进度)
bool isValid(int i, int j, bool* visited);

bool exist(char** board, int m, int* boardColSize, char* word) {
    isFound = false;
    g_m = m; g_n = boardColSize[0]; g_board = board;
    bool visited[g_m * g_n + 1];
    memset(visited, 0, sizeof(visited));

    for (int i = 0; i < g_m; i++) {
        for (int j = 0; j < g_n; j++) {
            // if (visited[i * g_n + j]) continue; // 冗余，起点处必未访问过
            // if (g_board[i][j] == word[0]) { // 可不加此if：遍历合法起点
            visited[i * g_n + j] = true;
            dfs(i, j, 0, word, visited);
            visited[i * g_n + j] = false;
            // }
        }
    }
    return isFound;
}

void dfs(int i, int j, int idx, char* word, bool* visited) {
    if (isFound) return; // 找到一个可行解，即返回
    int n = strlen(word);

    if (idx <= n - 1 && word[idx] != g_board[i][j]) return; // 匹配失败
    if (idx >= n - 1) { // 最后一个字符 也匹配成功
        isFound = true;
        return;
    }
    // printf("idx = %d, board[%d][%d] = %c\n", idx, i, j, g_board[i][j]);

    for (int dir = 0; dir < 4; dir++) {
        int newI = i + _x[dir], newJ = j + _y[dir];
        if (!isValid(newI, newJ, visited)) continue;
        visited[newI * g_n + newJ] = true; // 冗余
        dfs(newI, newJ, idx + 1, word, visited);
        visited[newI * g_n + newJ] = false;
    }
}

bool isValid(int i, int j, bool* visited) {
    return 0 <= i && i < g_m && 0 <= j && j < g_n && !visited[i * g_n + j];
}

int main() {
    char* board[] = { "ABCE", "SFCS", "ADEE" };
    int m = 3, boardColSize[] = { 4, 4, 4 };
    char* word = "ABCCED";
    printf("%d\n", exist(board, m, boardColSize, word));

    char* word2 = "SEE";
    printf("%d\n", exist(board, m, boardColSize, word2));

    char* word3 = "ABCB";
    printf("%d\n", exist(board, m, boardColSize, word3));
    return 0;
}