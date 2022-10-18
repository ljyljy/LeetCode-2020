#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>
#include "uthash.h"
#include <time.h>

#define LAND 1
#define WATER 0
#define MAX_LEN 2600
int _x[] = { 0, 1, 0, -1 };
int _y[] = { 1, 0, -1, 0 }; // �ϣ��ң��£���

typedef struct HashSet {
    char path[MAX_LEN]; // m��n��[1, 50]
    UT_hash_handle hh;
} HashSet;

bool set_add(HashSet** set, const char* path) {
    HashSet* pKey = NULL;
    HASH_FIND_STR(*set, path, pKey);
    if (pKey == NULL) {
        pKey = (HashSet*)malloc(sizeof(HashSet));
        memset(pKey->path, 0, sizeof(pKey->path));
        strcpy(pKey->path, path);
        HASH_ADD_STR(*set, path, pKey);
    }
    return true;
}

void FREE(void* p) {
    free(p);
    p = NULL;
}

void set_free(HashSet** map) {
    HashSet* cur, * tmp;
    HASH_ITER(hh, *map, cur, tmp) {
        HASH_DEL(*map, cur);
        FREE((HashSet*)cur);
    }
}

void dfs(int** grid, int i, int j, char* path, int lastDir, int m, int n);
bool isValid(int** grid, int x, int y, int m, int n);

int numDistinctIslands(int** grid, int gridSize, int* gridColSize) {
    int m = gridSize, n = gridColSize[0];
    HashSet* set = NULL;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == LAND) {
                char path[MAX_LEN] = { 0 };
                dfs(grid, i, j, path, 9, m, n);// ��ʼ�ķ���������д����Ӱ����ȷ��

                // set.add(path); ��
                set_add(&set, path);
            }
        }
    }
    int cnt = HASH_CNT(hh, set);
    set_free(&set);
    return cnt; // set.size();
}

// û����path��ǰ��len����Ϊ����ͨ��strlen��á�
void dfs(int** grid, int i, int j, char* path, int lastDir, int m, int n) {
    grid[i][j] = WATER;
    int curLen = strlen(path);
    path[curLen++] = '0' + lastDir; path[curLen++] = ',';
    for (int dir = 0; dir < 4; dir++) {
        int newX = i + _x[dir], newY = j + _y[dir];
        if (!isValid(grid, newX, newY, m, n)) continue;
        dfs(grid, newX, newY, path, dir, m, n);
    }
    curLen = strlen(path); // path���ݽ������ܻ����
    path[curLen++] = '-'; path[curLen++] = '0' + lastDir; path[curLen++] = ',';
}

bool isValid(int** grid, int x, int y, int m, int n) {
    return 0 <= x && x < m && 0 <= y && y < n&& grid[x][y] == LAND;
}


int main() {
    int m = 4;
    int* n = (int*)malloc(sizeof(int) * m);
    // memset(n, 5, sizeof(int) * m); // WA! memset������ԣ�����Ϊ��0���֣������
    for (int i = 0; i < m; i++) {
        n[i] = 5; // ����memset(n, 5, ...)��
    }

    // v1: ͨ��rand����ʼ��grid
    // int** grid = (int**)malloc(sizeof(int*) * m);
    // //

    // srand(time(0)); // ��֤ÿ��rand()�õ���ֵ��ͬ��
    // for (int i = 0; i < m; i++) {
    //     grid[i] = (int*)malloc(sizeof(int) * n[0]);
    //     for (int j = 0; j < n[0]; j++) {
    //         grid[i][j] = rand() % 2;
    //         printf("%d ", grid[i][j]);
    //     }
    //     printf("\n");
    // }

    // v2��ͨ��int[][], Ϊint**��ֵ������ǿתΪint**��gridӦ�˻�Ϊ(int*)[5]����
    int fill[4][5] = { {1, 1, 0, 1, 1}, { 1,0,0,0,0 }, { 0,0,0,0,1 }, { 1,1,0,1,1 } };
    int** grid = (int**)malloc(sizeof(int*) * m);
    for (int i = 0; i < m; i++) {
        grid[i] = (int*)malloc(sizeof(int) * n[0]);
    }
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n[0]; j++) {
            grid[i][j] = fill[i][j];
        }
    }
    int cnt = numDistinctIslands(grid, m, n);
    printf("cnt = %d\n", cnt);
    return 0;
}