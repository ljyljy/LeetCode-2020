#include <stdio.h>
#include <stdlib.h>
// #include <string.h>
#include <stdbool.h>
// #include <math.h>
// #include <limits.h>

// q207v2. �γ̱�

// ��2��DFS
/* ֪ʶ�㣺
1) dfs������ֵ
  - visited��������ֵ�жϣ������dfs�ڲ���
  - `if (visited[cur] == -1) return true;`
    - ��⣺��֦ ����ǰ'ѡ��·��'���ݹ�(cur��ǰ�ÿγ�dfsʱ����̽��cur)����֪cur~�����ڱ߲��ɻ�, �����ٴ�cur������̽��
2) visited����������״̬��
  - 0: δ����/��ʼ��
  - 1: ���ڷ���/dfs��̽
  - -1: �ѷ���/����
*/
int** nxts; // ��ӱ�<preǰ�ÿγ�, nxtCouses�б�>
int* n_cols; // nxts����
int* visited;
int basicSize, curCnt;

bool dfs(int cur);

bool canFinish(int numCourses, int** prerequisites, int prerequisitesSize, int* prerequisitesColSize) {
    basicSize = numCourses, curCnt = 0; // ��̬����TLE��������
    nxts = (int**)calloc(numCourses, sizeof(int*));
    n_cols = (int*)calloc(numCourses, sizeof(int));
    visited = (int*)calloc(numCourses, sizeof(int)); // ��ʼ��Ϊ0

    for (int i = 0; i < numCourses; i++) { // ��� nxts[pre]�ĺ�̿γ��б��ɶ�̬���ݣ���TLE��
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
        // visited[i] = 1; // �����dfs�жϷ���ֵ
        if (!dfs(i)) return false;
        // visited[i] = -1;
    }

    return true;
}

bool dfs(int cur) {
    if (visited[cur] == 1) return false; // �ɻ������ݹ�����֪i~�ڱ߳ɻ���
    if (visited[cur] == -1) return true; // ��֦ ����ǰ'ѡ��·��'���ݹ�(cur��ǰ�ÿγ�dfsʱ����̽��cur)����֪cur~�����ڱ߲��ɻ�, �����ٴ�cur������̽��

    visited[cur] = 1; // ���ڷ���/dfs��̽������cur���ڱ�(���ÿγ�)
    for (int i = 0; i < n_cols[cur]; i++) {
        // nxts[cur]: cur�ĺ��ÿγ��б�
        if (!dfs(nxts[cur][i])) return false;
    }
    visited[cur] = -1;// �ѷ���/���ݣ�dfs��δ����F��˵��curδ�ɻ�(���ÿγ�)
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