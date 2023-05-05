// 法1：1) 数组代替哈希 2)遍历排序，找max
int hardestWorker1(int n, int** logs, int logsSize, int* logsColSize){
    int totalTime[logsSize]; // 数组代替哈希，员工数<=500, idx=任务i
    memset(totalTime, 0, sizeof(totalTime));
    int taskIdMap[logsSize]; // <idx=任务i, val=员工ID>
    memset(taskIdMap, 0, sizeof(taskIdMap));

    int startTime = 0;
    int maxTime = INT_MIN, minID = -1;
    for (int i = 0; i < logsSize; i++) {
        int id = logs[i][0]; // 员工id
        int leaveTime = logs[i][1];
        totalTime[i] += leaveTime - startTime;
        taskIdMap[i] = id;

        startTime = leaveTime;
        if (totalTime[i] > maxTime) {
            maxTime = totalTime[i];
            minID = id;
        } else if (totalTime[i] == maxTime) {
            minID = fmin(minID, id); // 任务时长相同，只记录minID
        }

    }
    return minID;
}

// 法2: 结构体排序
typedef struct taskIdTime_s {
    int taskID; // taskIdTime数组下标即为任务i
    int employerID;
    int totalTime;
} taskIdTime;

// 对比成员：taskIdTime类型的map[i], map传参类型为taskIdTime*，需强转 & 解引用
static inline int cmp(const void *pa, const void *pb) {
    taskIdTime a = *(taskIdTime*)pa, b = *(taskIdTime*)pb;
    if (a.totalTime != b.totalTime) {
        return b.totalTime - a.totalTime; // 任务i.执行时间 降序
    } else return a.employerID - b.employerID; // 否则，按员工ID升序
}

int hardestWorker(int n, int** logs, int logsSize, int* logsColSize){
    taskIdTime map[logsSize]; // 结构体代替哈希，员工数 & 员工数<=500
    memset(map, 0, sizeof(map));

    int startTime = 0;
    for (int i = 0; i < logsSize; i++) {
        int leaveTime = logs[i][1];
        map[i].taskID = i;
        map[i].employerID = logs[i][0]; // 员工id
        map[i].totalTime = leaveTime - startTime;
        startTime = leaveTime;
    }

    qsort(map, logsSize, sizeof(*map), cmp);
    // for (int i = 0; i < logsSize; i++) {
    //     printf("totalTime = %d, employerID = %d, taskID = %d\n", map[i].totalTime, map[i].employerID, map[i].taskID);
    // }
    return map[0].employerID;
}