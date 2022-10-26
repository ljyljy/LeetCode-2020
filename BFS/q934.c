#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

/**
 * ֪ʶ��
 * 1������, q934
 *  �����пա�head(++,poll) == tail(++,offer)
    ������size��tail - head������+1������ҿ���������
   2) ��չ - ˫�˶��У�q862
     ��ջ���� [tail-1], ����ҿ�������
     ��ջ�ס� [head]
     ��ջ�ա� head == tail
     ��pop/��ջ��tail--
     ��������poll/removeLast��head++
 * 3��C���� - ����
    ����2Dת1D��idx = i * col + j
    i = idx / col; j = idx % col;
 */
const int _x[] = { -1, 1, 0, 0 };
const int _y[] = { 0, 0, -1, 1 };

// int*** g_grid;
int g_m, g_n;
int g_head, g_tail;
void BFS1(int** grid, int* queue, int* island1, int* islandSize);
int BFS2(int** grid, int* queue); // �����·���������(��for-size)
bool isValid(int x, int y);
int getID(int i, int j);
void FREE(void* p);

// ��2��˫BFS
int shortestBridge2(int** grid, int gridSize, int* gridColSize) {
    g_m = gridSize; g_n = gridColSize[0]; // g_grid = &grid;
    g_head = g_tail = 0;
    int* queue = (int*)calloc(g_m * g_n, sizeof(int)); // ����, BFS��
    int* island1 = (int*)calloc(g_m * g_n, sizeof(int)); // Set<int>, ����2Dת1D
    int islandSize = 0;

    // �����пա�head(++,poll) == tail(++,offer)
    // ������size��tail - head������+1������ҿ���������
    for (int i = 0; i < g_m; i++) {
        for (int j = 0; j < g_n; j++) {
            if (grid[i][j] != 1) continue;
            grid[i][j] = -1; // visited
            queue[g_tail++] = getID(i, j); // offer() - tail++
            islandSize = 0; // ÿ���ҵ��µ�1�����ã�
            BFS1(grid, queue, island1, &islandSize);
            // BFS1����, queueΪ�գ����³�ʼ��Ϊ[��1�����е�]
            // ׼������BFS2����island1 & 2����̾���
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


// int*ָ�����ͣ�ֵ�����У������±����&�޸ģ����޸ĳɹ������践��ֵ��
void BFS1(int** grid, int* queue, int* island1, int* islandSize) {
    while (g_head != g_tail) {
        // �˴����谴�����(ֻ��Ҫ�ҵ���1���е㼴��)����size��д�ɲ�д����
        int curIdx = queue[g_head++]; // poll() - head++
        int x = curIdx / g_n, y = curIdx % g_n;
        // BFS�����У���¼������queue�еĽ��([��ǰ��1]�����е�)
        island1[(*islandSize)++] = curIdx;
        for (int dir = 0; dir < 4; dir++) {
            int newX = x + _x[dir], newY = y + _y[dir];
            //   ֻ̽��unvisited && ½�� ��
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
        // ����©�������·�����밴���������Ҫfor-size����
        int size = g_tail - g_head; // ����+1������ҿ���������
        for (int i = 0; i < size; i++) {
            int curIdx = queue[g_head++];
            int x = curIdx / g_n, y = curIdx % g_n;
            for (int dir = 0; dir < 4; dir++) {
                int newX = x + _x[dir], newY = y + _y[dir];
                // ֻ̽��unvisited && ��2½�� ��
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
        step++;// д��for�󣨶���forǰ��������return step-1
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

// ��1��DFS+BFS

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

    // �����пա�head(++,poll) == tail(++,offer)
    // ������size��tail - head������+1������ҿ���������
    for (int i = 0; i < g_m; i++) {
        for (int j = 0; j < g_n; j++) {
            if (grid[i][j] != 1) continue;
            int* queue = (int*)calloc(g_m * g_n, sizeof(int));
            // 1) DFS: �ҵ�һ�����н⣨[��ǰ����1]�����е㣩, break
            dfs(grid, i, j, queue);
            // 2) BFS: ��queue(��1)�����е�('-1')�������ܹ��ѣ���������'0'��ɡ�-1��, step++����ֱ��������1��
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
        // ֻ̽��unvisited && ��2½�� ��
        if (isValid(newX, newY) && grid[newX][newY] == 1) {
            grid[newX][newY] = -1;
            dfs(grid, newX, newY, queue);
        }
    }
}

int bfs(int** grid, int* queue) {
    int step = 0;
    while (g_head != g_tail) {
        // ����©�������·�����밴���������Ҫfor-size����
        int size = g_tail - g_head; // ����+1������ҿ���������
        for (int i = 0; i < size; i++) {
            int curIdx = queue[g_head++]; // poll()
            int x = curIdx / g_n, y = curIdx % g_n;
            for (int dir = 0; dir < 4; dir++) {
                int newX = x + _x[dir], newY = y + _y[dir];
                // ֻ̽��unvisited && ��2½�� ��
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