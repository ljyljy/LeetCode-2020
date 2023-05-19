#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
#include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q207v2. 课程表

// 法2：DFS
/* 知识点：
1) dfs带返回值
  - visited用作返回值判断，需放在dfs内部。
  - `if (visited[cur] == -1) return true;`
    - 理解：剪枝 【当前'选修路径'回溯过(cur的前置课程dfs时，下探过cur)，已知cur~所有邻边不成环, 无需再从cur出发下探】
2) visited数组有三个状态：
  - 0: 未访问/初始化
  - 1: 正在访问/dfs下探
  - -1: 已访问/回溯
*/
int** nxts; // 领接表<pre前置课程, nxtCouses列表>
int* n_cols; // nxts列数
int* visited;
int basicSize, curCnt;

bool dfs(int cur);

bool canFinish(int numCourses, int** prerequisites, int prerequisitesSize, int* prerequisitesColSize) {
    basicSize = numCourses, curCnt = 0; // 动态扩容TLE，不采用
    nxts = (int**)calloc(numCourses, sizeof(int*));
    n_cols = (int*)calloc(numCourses, sizeof(int));
    visited = (int*)calloc(numCourses, sizeof(int)); // 初始化为0

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

    }

    for (int i = 0; i < numCourses; i++) {
        // visited[i] = 1; // 需放入dfs判断返回值
        if (!dfs(i)) return false;
        // visited[i] = -1;
    }

    return true;
}

bool dfs(int cur) {
    if (visited[cur] == 1) return false; // 成环【回溯过，已知i~邻边成环】
    if (visited[cur] == -1) return true; // 剪枝 【当前'选修路径'回溯过(cur的前置课程dfs时，下探过cur)，已知cur~所有邻边不成环, 无需再从cur出发下探】

    visited[cur] = 1; // 正在访问/dfs下探，搜索cur的邻边(后置课程)
    for (int i = 0; i < n_cols[cur]; i++) {
        // nxts[cur]: cur的后置课程列表
        if (!dfs(nxts[cur][i])) return false;
    }
    visited[cur] = -1;// 已访问/回溯，dfs尚未返回F，说明cur未成环(后置课程)
    return true;
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