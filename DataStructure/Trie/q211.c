#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
// #include <string.h>
// #include <limits.h>
// #include <math.h>
// #include <ctype.h>

// q211.c
typedef struct WordDictionary_s {
    // �� WordDictionary*���飬ÿ��Ԫ��ָ��һ��WordDictionary�ṹ��
    struct WordDictionary_s* children[26] /*���ɳ�ʼ�� = { NULL }*/;
    bool isWord /*���ɳ�ʼ�� = false*/;
} WordDictionary;


WordDictionary* wordDictionaryCreate() {
    WordDictionary* res = (WordDictionary*)calloc(1, sizeof(WordDictionary));
    res->isWord = false;
    memset(res->children, 0, 26 * sizeof(WordDictionary*)); // ���״�����sizeof(res->children)
    return res;
}

void wordDictionaryAddWord(WordDictionary* obj, char* word) {
    int n = strlen(word);
    for (int i = 0; i < n; i++) {
        int idx = word[i] - 'a';
        if (obj->children[idx] == NULL) {
            obj->children[idx] = wordDictionaryCreate();
        }
        obj = obj->children[idx];
    }
    obj->isWord = true;
}

bool wordDictionarySearch(WordDictionary* obj, char* word) {
    int n = strlen(word);
    for (int i = 0; i < n; i++) {
        if (word[i] == '.') {
            for (int j = 0; j < 26; j++) {
                if (obj->children[j] == NULL) continue;
                if (wordDictionarySearch(obj->children[j], word + i + 1)) {
                    return true;
                }
            }
            return false; // ����©��
        }
        else {
            int idx = word[i] - 'a';
            if (obj->children[idx] == NULL) {
                return false;
            }
            obj = obj->children[idx];
        }
    }
    return obj->isWord;
}

void wordDictionaryFree(WordDictionary* obj) {
    for (int i = 0; i < 26; i++) {
        if (obj->children[i] != NULL) {
            wordDictionaryFree(obj->children[i]);
        }
    }
    free(obj);
}

int main() {
    WordDictionary* obj = wordDictionaryCreate();
    wordDictionaryAddWord(obj, "bad");
    wordDictionaryAddWord(obj, "dad");
    wordDictionaryAddWord(obj, "mad");
    printf("%d\n", wordDictionarySearch(obj, "pad")); // 0
    printf("%d\n", wordDictionarySearch(obj, "bad")); // 1
    printf("%d\n", wordDictionarySearch(obj, ".ad")); // 1
    printf("%d\n", wordDictionarySearch(obj, "b..")); // 1
    wordDictionaryFree(obj);
    return 0;
}