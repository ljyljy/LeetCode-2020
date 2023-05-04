#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q79.c
/* ֪ʶ�㣺
1) ��άתһά�����㴫��
- visited[i][j] --> visited[i * g_n + j]
  -�����贫��g_n, (bool*) visited[g_n] �� ͬʱ����g_m & g_n��bool visited[g_m][g_n] (����2��q79v2.c)

2����1����ȫ�ֱ���isFound vs ��2���з���ֵ��dfs
*/
int g_m, g_n;
char** g_board;
bool isFound;
const int _x[4] = { 0,  1, 0, -1 };
const int _y[4] = { -1, 0, 1,  0 };

// ��1��dfs + ȫ�ֱ���isFound
void dfs(int i, int j, int idx, char* word, bool* visited); // <i,j>: ��ǰλ��; idx��ƥ��word���±����(��������)
bool isValid(int i, int j, bool* visited);

bool exist(char** board, int m, int* boardColSize, char* word) {
    isFound = false;
    g_m = m; g_n = boardColSize[0]; g_board = board;
    bool visited[g_m * g_n + 1];
    memset(visited, 0, sizeof(visited));

    for (int i = 0; i < g_m; i++) {
        for (int j = 0; j < g_n; j++) {
            // if (visited[i * g_n + j]) continue; // ���࣬��㴦��δ���ʹ�
            // if (g_board[i][j] == word[0]) { // �ɲ��Ӵ�if�������Ϸ����
            visited[i * g_n + j] = true;
            dfs(i, j, 0, word, visited);
            visited[i * g_n + j] = false;
            // }
        }
    }
    return isFound;
}

void dfs(int i, int j, int idx, char* word, bool* visited) {
    if (isFound) return; // �ҵ�һ�����н⣬������
    int n = strlen(word);

    if (idx <= n - 1 && word[idx] != g_board[i][j]) return; // ƥ��ʧ��
    if (idx >= n - 1) { // ���һ���ַ� Ҳƥ��ɹ�
        isFound = true;
        return;
    }
    // printf("idx = %d, board[%d][%d] = %c\n", idx, i, j, g_board[i][j]);

    for (int dir = 0; dir < 4; dir++) {
        int newI = i + _x[dir], newJ = j + _y[dir];
        if (!isValid(newI, newJ, visited)) continue;
        visited[newI * g_n + newJ] = true; // ����
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