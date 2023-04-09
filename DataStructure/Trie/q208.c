#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <limits.h>
#include <math.h>
#include <ctype.h>

// 208. 实现 Trie (前缀树)
typedef struct Trie {
    // ↓ Trie*数组，每个元素指向一个Trie结构体
    struct Trie* children[26] /*不可初始化 = { NULL }*/;
    bool isWord /*不可初始化 = false*/;
} Trie;


Trie* trieCreate() {
    // 错误写法：函数内部定义的结构体变量，函数结束后会被释放
    // Trie trie = { NULL, false }; // 【栈】函数内定义的【局部变量】
    // Trie* pTrie = &trie; /*修正： (Trie*)malloc(sizeof(Trie));*/

    // 正确写法：在【堆】中申请内存 (以后一定要记得free)
    Trie* pTrie = (Trie*)malloc(sizeof(Trie));
    pTrie->isWord = false;
    for (int i = 0; i < 26; i++) {
        pTrie->children[i] = NULL;
    }
    return pTrie;
}

void trieInsert(Trie* pTrie, char* word) {
    int n = strlen(word);
    for (int i = 0; i < n; i++) {
        int idx = word[i] - 'a';
        // 【易错】if不可省略，否则会覆盖原来的指针（可能导致原来的isWord=True被覆盖）
        if (pTrie->children[idx] == NULL) {
            pTrie->children[idx] = trieCreate();
        }
        pTrie = pTrie->children[idx];
    }
    pTrie->isWord = true;
}

Trie* trieFind(Trie* pTrie, char* word) {
    int n = strlen(word);
    for (int i = 0; i < n; i++) {
        int idx = word[i] - 'a';
        if (pTrie->children[idx] == NULL) {
            return NULL;
        }
        pTrie = pTrie->children[idx];
    }
    return pTrie;
}

bool trieSearch(Trie* pTrie, char* word) {
    Trie* cur = trieFind(pTrie, word);
    return cur != NULL && cur->isWord;
}

bool trieStartsWith(Trie* pTrie, char* prefix) {
    Trie* cur = trieFind(pTrie, prefix);
    return cur != NULL;
}

void trieFree(Trie* pTrie) {
    for (int i = 0; i < 26; i++) {
        if (pTrie->children[i] != NULL) {
            free(pTrie->children[i]);
            pTrie->children[i] = NULL;
        }
    }
    free(pTrie);
    pTrie = NULL;
}

int main(int argc, char const* argv[])
{
    Trie* pTrie = trieCreate();
    trieInsert(pTrie, "apple");
    printf("%d ", trieSearch(pTrie, "apple"));   // 返回 true
    printf("%d ", trieSearch(pTrie, "app"));     // 返回 false
    printf("%d ", trieStartsWith(pTrie, "app")); // 返回 true
    trieInsert(pTrie, "app");
    printf("%d ", trieSearch(pTrie, "app"));     // 返回 true
    trieFree(pTrie);
    return 0;
}