#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

/**
 * 知识点
 * 1）队列, q934
 *  【队列空】head(++,poll) == tail(++,offer)
    【队列size】tail - head，无需+1（左闭右开）！！！
   2) 扩展 - 双端队列，q862
     【栈顶】 [tail-1], 左闭右开！！！
     【栈底】 [head]
     【栈空】 head == tail
     【pop/弹栈】tail--
     【出队列poll/removeLast】head++
 * 3）C语言 - 搜索
    坐标2D转1D：idx = i * col + j
    i = idx / col; j = idx % col;
 */
const int _x[] = { -1, 1, 0, 0 };
const int _y[] = { 0, 0, -1, 1 };

// int*** g_grid;
int g_m, g_n;
int g_head, g_tail;
void BFS1(int** grid, int* queue, int* island1, int* islandSize);
int BFS2(int** grid, int* queue); // 求最短路，按层遍历(需for-size)
bool isValid(int x, int y);
int getID(int i, int j);
void FREE(void* p);

// 法2：双BFS
int shortestBridge2(int** grid, int gridSize, int* gridColSize) {
    g_m = gridSize; g_n = gridColSize[0]; // g_grid = &grid;
    g_head = g_tail = 0;
    int* queue = (int*)calloc(g_m * g_n, sizeof(int)); // 队列, BFS用
    int* island1 = (int*)calloc(g_m * g_n, sizeof(int)); // Set<int>, 坐标2D转1D
    int islandSize = 0;

    // 【队列空】head(++,poll) == tail(++,offer)
    // 【队列size】tail - head，无需+1（左闭右开）！！！
    for (int i = 0; i < g_m; i++) {
        for (int j = 0; j < g_n; j++) {
            if (grid[i][j] != 1) continue;
            grid[i][j] = -1; // visited
            queue[g_tail++] = getID(i, j); // offer() - tail++
            islandSize = 0; // 每次找到新岛1，重置！
            BFS1(grid, queue, island1, &islandSize);
            // BFS1结束, queue为空，重新初始化为[岛1的所有点]
            // 准备进行BFS2：求island1 & 2的最短距离
            g_head = g_tail = 0; // island1
            for (int k = 0; k < islandSize; k++) {
                queue[g_tail++] = island1[k];
            }
            int step = BFS2(grid, queue);
            FREE(queue);
            FREE(island1);
            return step;
        }
    }
    FREE(queue);
    FREE(island1);
    return 0;
}


// int*指针类型，值传递中，若有下标访问&修改，则修改成功，无需返回值。
void BFS1(int** grid, int* queue, int* island1, int* islandSize) {
    while (g_head != g_tail) {
        // 此处无需按层遍历(只需要找到岛1所有点即可)，【size可写可不写】！
        int curIdx = queue[g_head++]; // poll() - head++
        int x = curIdx / g_n, y = curIdx % g_n;
        // BFS过程中，记录下所有queue中的结点([当前岛1]的所有点)
        island1[(*islandSize)++] = curIdx;
        for (int dir = 0; dir < 4; dir++) {
            int newX = x + _x[dir], newY = y + _y[dir];
            //   只探索unvisited && 陆地 ↓
            if (isValid(newX, newY) && grid[newX][newY] == 1) {
                grid[newX][newY] = -1;
                queue[g_tail++] = getID(newX, newY);
            }
        }
    }
}

int BFS2(int** grid, int* queue) {
    int step = 0;
    while (g_head != g_tail) {
        // 【勿漏！求最短路，必须按层遍历，需要for-size！】
        int size = g_tail - g_head; // 无需+1（左闭右开）！！！
        for (int i = 0; i < size; i++) {
            int curIdx = queue[g_head++];
            int x = curIdx / g_n, y = curIdx % g_n;
            for (int dir = 0; dir < 4; dir++) {
                int newX = x + _x[dir], newY = y + _y[dir];
                // 只探索unvisited && 岛2陆地 ↓
                if (!isValid(newX, newY)) continue;
                if (grid[newX][newY] == 1) {
                    return step;
                }
                else if (grid[newX][newY] == 0) {
                    grid[newX][newY] = -1;
                    queue[g_tail++] = getID(newX, newY);
                }
            }
        }
        step++;// 写在for后（而非for前），避免return step-1
    }
    return step;
}

bool isValid(int x, int y) {
    return 0 <= x && x < g_m && 0 <= y && y < g_n;
}

int getID(int i, int j) {
    return i * g_n + j;
}

void FREE(void* p) {
    free(p);
    p = NULL;
}

// 法1：DFS+BFS

// int g_m, g_n;
// int g_head, g_tail;
// bool isValid(int x, int y);
// int getID(int i, int j);
// void FREE(void* p);
void dfs(int** grid, int i, int j, int* queue);
int bfs(int** grid, int* queue);

int shortestBridge(int** grid, int gridSize, int* gridColSize) {
    g_m = gridSize; g_n = gridColSize[0]; // g_grid = &grid;
    g_head = g_tail = 0;

    // 【队列空】head(++,poll) == tail(++,offer)
    // 【队列size】tail - head，无需+1（左闭右开）！！！
    for (int i = 0; i < g_m; i++) {
        for (int j = 0; j < g_n; j++) {
            if (grid[i][j] != 1) continue;
            int* queue = (int*)calloc(g_m * g_n, sizeof(int));
            // 1) DFS: 找到一个可行解（[当前岛屿1]的所有点）, break
            dfs(grid, i, j, queue);
            // 2) BFS: 从queue(岛1)中所有点('-1')，向四周广搜（将遇到的'0'变成【-1】, step++），直到碰到‘1’
            int step = bfs(grid, queue);
            FREE(queue);
            return step;
        }
    }
    return 0;
}

void dfs(int** grid, int i, int j, int* queue) {
    grid[i][j] = -1; // visited
    queue[g_tail++] = getID(i, j); // offer()

    for (int dir = 0; dir < 4; dir++) {
        int newX = i + _x[dir], newY = j + _y[dir];
        // 只探索unvisited && 岛2陆地 ↓
        if (isValid(newX, newY) && grid[newX][newY] == 1) {
            grid[newX][newY] = -1;
            dfs(grid, newX, newY, queue);
        }
    }
}

int bfs(int** grid, int* queue) {
    int step = 0;
    while (g_head != g_tail) {
        // 【勿漏！求最短路，必须按层遍历，需要for-size！】
        int size = g_tail - g_head; // 无需+1（左闭右开）！！！
        for (int i = 0; i < size; i++) {
            int curIdx = queue[g_head++]; // poll()
            int x = curIdx / g_n, y = curIdx % g_n;
            for (int dir = 0; dir < 4; dir++) {
                int newX = x + _x[dir], newY = y + _y[dir];
                // 只探索unvisited && 岛2陆地 ↓
                if (!isValid(newX, newY)) continue;
                if (grid[newX][newY] == 1) {
                    return step;
                }
                else if (grid[newX][newY] == 0) {
                    grid[newX][newY] = -1;
                    queue[g_tail++] = getID(newX, newY);
                }
            }
        }
        step++;
    }
    return step;
}