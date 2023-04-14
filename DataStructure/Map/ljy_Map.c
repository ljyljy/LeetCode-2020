#include "uthash.h"
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <limits.h>
#include <stdbool.h>

typedef struct Map {
    int id;                    /* key */
    char name[10];
    UT_hash_handle hh;         /* makes this structure hashable */
} Map;

/*声明哈希为NULL指针*/
// ljy注意：
//  刷题时，最好将定义的Map* map放到main中，再把每个map相关的操作函数入参1设为Map** map, 调用时使用& map
Map* map = NULL;    /* important! initialize to NULL */


// 2.1.0 添加 - HASH_ADD_INT(map, keyField/key.字段名, pEntry);
void map_add(int user_id, char* name) {
    Map* pEntry;
    /*重复性检查，当把两个相同key值的结构体添加到哈希表中时会报错*/
    HASH_FIND_INT(map, &user_id, pEntry);  /* id already in the hash? */
    /*只有在哈希中不存在ID的情况下，我们才创建该项目并将其添加。否则，我们只修改已经存在的结构。*/
    if (pEntry == NULL) {
        pEntry = (Map*)malloc(sizeof * pEntry);
        pEntry->id = user_id;
        HASH_ADD_INT(map, id, pEntry);  /* id: name of key field */
    }
    strcpy(pEntry->name, name);
}

// // 2.1.0 添加或更新（+、-）
// //  完整代码，见q1094
// #define ADD 1
// #define MINUS 2

//typedef struct TreeMap {
//    int time; // key
//    int cnt; // val
//    UT_hash_handle hh;
//} TreeMap;

//void map_putOrUpdate(TreeMap** map, int key, int val, int OPS) {
//    TreeMap* pEntry = NULL;
//    HASH_FIND_INT(*map, &key, pEntry);// 【入参key.地址】
//    if (pEntry == NULL) { // put
//        pEntry = (TreeMap*)malloc(sizeof(TreeMap));
//        pEntry->time = key;
//        if (OPS == ADD) pEntry->cnt = val;
//        else pEntry->cnt = -val; // 后续用于前缀和
//        HASH_ADD_INT(*map, time, pEntry);// 【Map.key字段名】
//    }
//    else { // Update: 加/减
//        if (OPS == ADD) {
//            pEntry->cnt += val;
//        }
//        else {
//            pEntry->cnt -= val;
//        }
//    }
//}


// 2.3 查找 - HASH_FIND_INT(map, &key_val, pEntry)
Map* find_user(int user_id) {
    Map* pEntry;
    pEntry = (Map*)malloc(sizeof(Map)); // 或sizeof(*pEntry)
    HASH_FIND_INT(map, &user_id, pEntry);  /* s: output pointer */
    return pEntry;
}

/*
// 2.4 替换 - HASH_REPLACE
void replace_user(HashHead* head, HashNode* newNode) {
    HashNode* oldNode = find_user(*head, newNode->id);
    if (oldNode)
        HASH_REPLACE_INT(*head, id, newNode, oldNode);
}
*/



// 2.5 删除某个entry - HASH_DEL
void delete_user(Map* pEntry) {
    HASH_DEL(map, pEntry);  /* user: pointer to deletee */
    free(pEntry);             /* optional; it's up to you! */
}

// 2.6 循环删除
void map_free() {
    Map* cur, * tmp;
    HASH_ITER(hh, map, cur, tmp) {
        HASH_DEL(map, cur);  /* delete; users advances to next */
        free(cur);            /* optional- if you want to free  */
    }
}
/*
// 2.7 删除哈希表所有元素
HASH_CLEAR(hh, map);
*/

// 2.9 遍历哈希表 - v1：使用for; v2: HASH_ITER
void print_users() {
    for (Map* cur = map; cur != NULL; cur = cur->hh.next) {
        printf("user id %d: name %s\n", cur->id, cur->name);
    }
}

// 2.10 排序哈希表
// HASH_SORT(users, name_sort);

int sort_function(void* a, void* b) {
    /* compare a to b (cast a and b appropriately)
     * return (int) -1 if (a < b)
     * return (int)  0 if (a == b)
     * return (int)  1 if (a > b)
     */
}

int name_sort(Map* a, Map* b) {
    return strcmp(a->name, b->name); // 对val字典序
}

int id_sort(Map* a, Map* b) {
    return (a->id - b->id); // key升序
}

void sort_by_name() {
    HASH_SORT(map, name_sort);
}

void sort_by_id() {
    HASH_SORT(map, id_sort);
}

int main(int argc, char* argv[]) {
    char in[10];
    int id = 1, running = 1;
    Map* s;
    unsigned num_users;

    while (running) {
        printf(" 1. add user\n");
        printf(" 2. add/rename user by id\n");
        printf(" 3. find user\n");
        printf(" 4. delete user\n");
        printf(" 5. delete all users\n");
        printf(" 6. sort items by name\n");
        printf(" 7. sort items by id\n");
        printf(" 8. print users\n");
        printf(" 9. count users\n");
        printf("10. quit\n");
        gets(in);
        switch (atoi(in)) {
        case 1:
            printf("name?\n");
            map_add(id++, gets(in));
            break;
        case 2:
            printf("id?\n");
            gets(in); id = atoi(in);
            printf("name?\n");
            map_add(id, gets(in));
            break;
        case 3:
            printf("id?\n");
            s = find_user(atoi(gets(in)));
            printf("user: %s\n", s ? s->name : "unknown");
            break;
        case 4:
            printf("id?\n");
            s = find_user(atoi(gets(in)));
            if (s) delete_user(s);
            else printf("id unknown\n");
            break;
        case 5:
            delete_all();
            break;
        case 6:
            sort_by_name();
            break;
        case 7:
            sort_by_id();
            break;
        case 8:
            print_users();
            break;
        case 9:
            // 2.8 计算哈希表元素个数
            num_users = HASH_COUNT(map);
            printf("there are %u users\n", num_users);
            break;
        case 10:
            running = 0;
            break;
        }
    }

    delete_all();  /* free any structures */
    return 0;
}
