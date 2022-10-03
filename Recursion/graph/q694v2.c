#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>
#include "uthash.h"
#include <time.h>
/**
 * 坑：
 * 1）HASH_key为结构体指针（HASH_ADD_KEYPTR、HASH_FIND）
 * 2）防止char[2600]导致栈溢出，key为指针类型，并定义长度("动态数组")
 * 3）HASH_COUNT：等价于map.size()
 */
#define LAND 1
#define WATER 0
#define MAX_LEN 2600

int _x[] = { 0, 1, 0, -1 };
int _y[] = { 1, 0, -1, 0 }; // 上，右，下，左


typedef struct Point {
    int x;
    int y;
} Point;

typedef struct HashMap {
    // char path[MAX_LEN]; // m、n∈[1, 50]
    Point* path; // key: 指针类型（防止char[2600]导致栈溢出！）
    // int pathLen; // 无需
    UT_hash_handle hh;
} HashMap;


int** g_grid = NULL;
int m = 0, n = 0;
Point g_start;

void FREE(void* p) {
    free(p); p = NULL;
}

void map_add(HashMap** map, Point* path, int pathLen) {
    HashMap* pEntry = NULL;
    HASH_FIND(hh, *map, path, sizeof(Point) * pathLen, pEntry);// 【传参3rd & 4th】
    if (pEntry != NULL) {
        FREE(path);
        return;
    }
    pEntry = (HashMap*)malloc(sizeof(HashMap));
    pEntry->path = path;
    // pEntry->pathLen = pathLen;
    HASH_ADD_KEYPTR(hh, *map, path, sizeof(Point) * pathLen, pEntry); // 【传参3rd & 4th】
}

void map_free(HashMap** map) {
    HashMap* cur, * tmp;
    HASH_ITER(hh, *map, cur, tmp) {
        HASH_DEL(*map, cur);
        FREE(cur->path);
        FREE(cur);
    }
}

bool isValid(int x, int y);
void dfs(Point* path, int* pathLen, int x, int y);

int numDistinctIslands(int** grid, int gridSize, int* gridColSize) {
    g_grid = grid;
    m = gridSize; n = gridColSize[0];
    HashMap* map = NULL;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == LAND) {
                // 每轮重新计算g_start、以其为起点的path
                g_start.x = i, g_start.y = j;
                Point* path = (Point*)malloc(sizeof(Point) * m * n); // path：预分配m*n个Point
                int pathLen = 0;
                dfs(path, &pathLen, i, j);

                // set.add(path); ↓
                map_add(&map, path, pathLen);
            }
        }
    }

    int cnt = HASH_COUNT(map);
    map_free(&map);
    return cnt;
}

void dfs(Point* path, int* pathLen, int x, int y) {
    int curIdx = *pathLen;
    path[curIdx].x = x - g_start.x;  /* 保存相对于左上角的遍历坐标 */
    path[curIdx].y = y - g_start.y;
    *pathLen = ++curIdx;

    g_grid[x][y] = WATER;

    for (int dir = 0; dir < 4; dir++) {
        int newX = x + _x[dir], newY = y + _y[dir];
        if (!isValid(newX, newY)) continue;
        dfs(path, pathLen, newX, newY);
    }

}

bool isValid(int x, int y) {
    return 0 <= x && x < m && 0 <= y && y < n&& g_grid[x][y] == LAND;
}
