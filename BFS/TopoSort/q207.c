#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
#include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q207. 课程表

/* 知识点：
1) 拓扑排序
  - 领接表<pre前置课程, nxtCouses列表>
  - 入度
2）队列
  - int* queue = (int*)calloc(N, sizeof(int)), lf = 0, rt = 0;
    - queue[rt++] = i; // 入队列
    - int cur = queue[lf++]; // 出队列
    - 队列不空： `while (lf < rt) { ... }`
    - ```C
        while (lf < rt) { // 队列不空
            int cur = queue[lf++]; // 出队列
            queue[rt++] = nxt[i]; // 入队列
        }
    ```
*/
// 法1：BFS 拓扑排序，queue
int** nxts; // 领接表<pre前置课程, nxtCouses列表>
int* n_cols; // nxts列数
int* indegree; // 拓扑排序，入度
int basicSize, curCnt;

bool canFinish(int numCourses, int** prerequisites, int prerequisitesSize, int* prerequisitesColSize) {
    basicSize = numCourses, curCnt = 0; // 动态扩容TLE，不采用
    nxts = (int**)calloc(numCourses, sizeof(int*));
    n_cols = (int*)calloc(numCourses, sizeof(int));
    indegree = (int*)calloc(numCourses, sizeof(int));
    for (int i = 0; i < numCourses; i++) { // 针对 nxts[pre]的后继课程列表，可动态扩容（会TLE）
        nxts[i] = (int*)calloc(basicSize, sizeof(int));
    }
    for (int i = 0; i < prerequisitesSize; i++) {
        int cur = prerequisites[i][0], pre = prerequisites[i][1];
        nxts[pre][n_cols[pre]++] = cur;
        if (n_cols[pre] == basicSize) {
            basicSize *= 2;
            nxts[pre] = (int*)realloc(nxts[pre], basicSize * sizeof(int));
        }
        indegree[cur]++;
    }

    // 队列，需要首尾idx, 类比滑动窗口（vs 栈stk 只需top）
    int* queue = (int*)calloc(numCourses, sizeof(int)), lf = 0, rt = 0;
    for (int i = 0; i < numCourses; i++) {
        if (indegree[i] == 0) {
            queue[rt++] = i; // 入队列
        }
    }

    int visited = 0;
    // 队列不空
    while (lf < rt) {
        visited++;
        int cur = queue[lf++];
        int* nxt = nxts[cur];
        // int nxtCnt = n_cols[cur];
        for (int i = 0; i < n_cols[cur]; i++) {
            if (--indegree[nxt[i]] == 0) {
                queue[rt++] = nxt[i];
            }
        }
    }
    free(queue);
    return visited == numCourses;
}

int main()
{
    int numCourses = 2;
    int prerequisites[][2] = { {1,0} };
    int* p_prerequisites[] = { prerequisites[0] };
    int prerequisitesSize = sizeof(prerequisites) / sizeof(prerequisites[0]);
    int prerequisitesColSize[] = { 2 };
    printf("%d\n", canFinish(numCourses, p_prerequisites, prerequisitesSize, prerequisitesColSize)); // true

    int numCourses2 = 2;
    int prerequisites2[][2] = { {1,0},{0,1} };
    int* p_prerequisites2[] = { prerequisites2[0], prerequisites2[1] };
    int prerequisitesSize2 = sizeof(prerequisites2) / sizeof(prerequisites2[0]);
    int prerequisitesColSize2[] = { 2, 2 };
    printf("%d\n", canFinish(numCourses2, p_prerequisites2, prerequisitesSize2, prerequisitesColSize2)); // false
    return 0;
}