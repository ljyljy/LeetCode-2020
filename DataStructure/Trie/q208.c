#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <limits.h>
#include <math.h>
#include <ctype.h>

// 208. ʵ�� Trie (ǰ׺��)
typedef struct Trie {
    // �� Trie*���飬ÿ��Ԫ��ָ��һ��Trie�ṹ��
    struct Trie* children[26] /*���ɳ�ʼ�� = { NULL }*/;
    bool isWord /*���ɳ�ʼ�� = false*/;
} Trie;


Trie* trieCreate() {
    // ����д���������ڲ�����Ľṹ�����������������ᱻ�ͷ�
    // Trie trie = { NULL, false }; // ��ջ�������ڶ���ġ��ֲ�������
    // Trie* pTrie = &trie; /*������ (Trie*)malloc(sizeof(Trie));*/

    // ��ȷд�����ڡ��ѡ��������ڴ� (�Ժ�һ��Ҫ�ǵ�free)
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
        // ���״�if����ʡ�ԣ�����Ḳ��ԭ����ָ�루���ܵ���ԭ����isWord=True�����ǣ�
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
    printf("%d ", trieSearch(pTrie, "apple"));   // ���� true
    printf("%d ", trieSearch(pTrie, "app"));     // ���� false
    printf("%d ", trieStartsWith(pTrie, "app")); // ���� true
    trieInsert(pTrie, "app");
    printf("%d ", trieSearch(pTrie, "app"));     // ���� true
    trieFree(pTrie);
    return 0;
}