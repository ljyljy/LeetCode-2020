#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q79v2.c

/* ֪ʶ�㣺
1) ��άתһά�����㴫��
- visited[i][j] --> visited[i * g_n + j]
  -�����贫��g_n, (bool*) visited[g_n] �� ͬʱ����g_m & g_n��bool visited[g_m][g_n] (����2��q79v2.c)
2����1����ȫ�ֱ���isFound vs ��2���з���ֵ��dfs
*/
int g_m, g_n, g_len;
char** g_board;
const int _x[4] = { 0,  1, 0, -1 };
const int _y[4] = { -1, 0, 1,  0 };

// ��2���з���ֵ��dfs

// <i,j>: ��ǰλ��; idx��ƥ��word���±����(��������)
bool dfs(int i, int j, int idx, char* word, bool visited[g_m][g_n]);
bool isValid(int i, int j, bool visited[g_m][g_n]);

bool exist(char** board, int m, int* boardColSize, char* word) {
    g_m = m; g_n = boardColSize[0]; g_board = board;
    g_len = strlen(word);
    bool visited[g_m][g_n];
    memset(visited, 0, sizeof(visited));

    for (int i = 0; i < g_m; i++) {
        for (int j = 0; j < g_n; j++) {
            visited[i][j] = true;
            if (dfs(i, j, 0, word, visited)) {
                return true; // �ҵ�һ�����н⣬������T
            }
            visited[i][j] = false;
        }
    }
    return false; // �޽�
}

bool dfs(int i, int j, int idx, char* word, bool visited[g_m][g_n]) {
    if (idx < g_len && g_board[i][j] != word[idx]) return false; // word[idx]ƥ��ʧ��
    if (idx >= g_len - 1) return true; // ���һ���ַ���Ҳƥ��ɹ�

    for (int dir = 0; dir < 4; dir++) {
        int newI = i + _y[dir], newJ = j + _x[dir];
        if (!isValid(newI, newJ, visited)) continue;
        visited[newI][newJ] = true;
        if (dfs(newI, newJ, idx + 1, word, visited)) {
            return true; // ��(i,j)����ƥ��word[idx]�������ҵ�һ�����н⣬������T
        }
        visited[newI][newJ] = false;
    }
    return false; // ��(i,j)����ƥ��word[idx]�������޽�
}

bool isValid(int i, int j, bool visited[g_m][g_n]) {
    return i >= 0 && i < g_m && j >= 0 && j < g_n && !visited[i][j];
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