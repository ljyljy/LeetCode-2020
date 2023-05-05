// ��1��1) ��������ϣ 2)����������max
int hardestWorker1(int n, int** logs, int logsSize, int* logsColSize){
    int totalTime[logsSize]; // ��������ϣ��Ա����<=500, idx=����i
    memset(totalTime, 0, sizeof(totalTime));
    int taskIdMap[logsSize]; // <idx=����i, val=Ա��ID>
    memset(taskIdMap, 0, sizeof(taskIdMap));

    int startTime = 0;
    int maxTime = INT_MIN, minID = -1;
    for (int i = 0; i < logsSize; i++) {
        int id = logs[i][0]; // Ա��id
        int leaveTime = logs[i][1];
        totalTime[i] += leaveTime - startTime;
        taskIdMap[i] = id;

        startTime = leaveTime;
        if (totalTime[i] > maxTime) {
            maxTime = totalTime[i];
            minID = id;
        } else if (totalTime[i] == maxTime) {
            minID = fmin(minID, id); // ����ʱ����ͬ��ֻ��¼minID
        }

    }
    return minID;
}

// ��2: �ṹ������
typedef struct taskIdTime_s {
    int taskID; // taskIdTime�����±꼴Ϊ����i
    int employerID;
    int totalTime;
} taskIdTime;

// �Աȳ�Ա��taskIdTime���͵�map[i], map��������ΪtaskIdTime*����ǿת & ������
static inline int cmp(const void *pa, const void *pb) {
    taskIdTime a = *(taskIdTime*)pa, b = *(taskIdTime*)pb;
    if (a.totalTime != b.totalTime) {
        return b.totalTime - a.totalTime; // ����i.ִ��ʱ�� ����
    } else return a.employerID - b.employerID; // ���򣬰�Ա��ID����
}

int hardestWorker(int n, int** logs, int logsSize, int* logsColSize){
    taskIdTime map[logsSize]; // �ṹ������ϣ��Ա���� & Ա����<=500
    memset(map, 0, sizeof(map));

    int startTime = 0;
    for (int i = 0; i < logsSize; i++) {
        int leaveTime = logs[i][1];
        map[i].taskID = i;
        map[i].employerID = logs[i][0]; // Ա��id
        map[i].totalTime = leaveTime - startTime;
        startTime = leaveTime;
    }

    qsort(map, logsSize, sizeof(*map), cmp);
    // for (int i = 0; i < logsSize; i++) {
    //     printf("totalTime = %d, employerID = %d, taskID = %d\n", map[i].totalTime, map[i].employerID, map[i].taskID);
    // }
    return map[0].employerID;
}