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
int _y[] = { 1, 0, -1, 0 }; // 上，右，下，左

typedef struct HashSet {
    char path[MAX_LEN]; // m、n∈[1, 50]
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
                dfs(grid, i, j, path, 9, m, n);// 初始的方向可以随便写，不影响正确性

                // set.add(path); ↓
                set_add(&set, path);
            }
        }
    }
    int cnt = HASH_CNT(hh, set);
    set_free(&set);
    return cnt; // set.size();
}

// 没设置path当前的len，因为可以通过strlen获得。
void dfs(int** grid, int i, int j, char* path, int lastDir, int m, int n) {
    grid[i][j] = WATER;
    int curLen = strlen(path);
    path[curLen++] = '0' + lastDir; path[curLen++] = ',';
    for (int dir = 0; dir < 4; dir++) {
        int newX = i + _x[dir], newY = j + _y[dir];
        if (!isValid(grid, newX, newY, m, n)) continue;
        dfs(grid, newX, newY, path, dir, m, n);
    }
    curLen = strlen(path); // path回溯结束可能会更新
    path[curLen++] = '-'; path[curLen++] = '0' + lastDir; path[curLen++] = ',';
}

bool isValid(int** grid, int x, int y, int m, int n) {
    return 0 <= x && x < m && 0 <= y && y < n&& grid[x][y] == LAND;
}


int main() {
    int m = 4;
    int* n = (int*)malloc(sizeof(int) * m);
    // memset(n, 5, sizeof(int) * m); // WA! memset清零可以，但设为非0数字，会出错！
    for (int i = 0; i < m; i++) {
        n[i] = 5; // 不可memset(n, 5, ...)！
    }

    // v1: 通过rand，初始化grid
    // int** grid = (int**)malloc(sizeof(int*) * m);
    // //

    // srand(time(0)); // 保证每次rand()得到的值不同！
    // for (int i = 0; i < m; i++) {
    //     grid[i] = (int*)malloc(sizeof(int) * n[0]);
    //     for (int j = 0; j < n[0]; j++) {
    //         grid[i][j] = rand() % 2;
    //         printf("%d ", grid[i][j]);
    //     }
    //     printf("\n");
    // }

    // v2：通过int[][], 为int**赋值（不可强转为int**，grid应退化为(int*)[5]！）
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