#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// ������
// UserMap<userId, Follows��followerIds��(����curIdx/������ʽ)> -- ��������ϣ��
// TweetMap<User&tweetId, Tweets��tweetIds��(����curIdx/������ʽ)> -- ��������Ҫ����qsort��/����ͷ�巨��

// ��Ŀ��1 <= userId, followerId, followeeId <= 500
#define MAX_NUM 501

typedef struct {
    int id; // userId �� [1, 500]
    bool followList[MAX_NUM]; // ���q146����ע��񡣾���followerId��Ӧ��������±�
} User;

typedef struct Tweet_s {
    int userId;
    int tweetId; // tweetIds
    struct Tweet_s* nxt; // ������ͷ�巨��������������λ
} Tweet;

typedef struct {
    User* user; // ��2��User user[MAX_NUM]; ����Ҫfree
    Tweet* tweet;
} Twitter;


Twitter* twitterCreate() {
    Twitter* obj = (Twitter*)calloc(1, sizeof(Twitter));
    // memset(obj->user, 0, MAX_NUM * sizeof(User)); // ��2��user[MAX_NUM]
    obj->user = (User*)calloc(MAX_NUM, sizeof(User)); // ��1��User*
    obj->tweet = (Tweet*)calloc(1, sizeof(Tweet)); // dummy
    obj->tweet->nxt = NULL;
    return obj;
}

void twitterPostTweet(Twitter* obj, int userId, int tweetId) {
    // ��������tweet
    Tweet* tweet = (Tweet*)calloc(1, sizeof(Tweet));
    tweet->userId = userId;
    tweet->tweetId = tweetId;
    // ����������tweet��ͷ�巨������Twitter.tweet�����ײ�
    tweet->nxt = obj->tweet->nxt;
    obj->tweet->nxt = tweet;
}

// �����Լ�&��ע���б�����10�����ģ�ʱ��ӽ���Զ����
int* twitterGetNewsFeed(Twitter* obj, int myUserId, int* retSize) {
    int* res = (int*)calloc(10, sizeof(int));
    int curCnt = 0; // ��ǰ������
    Tweet* curTweet = obj->tweet->nxt; // ������������
    while (curTweet != NULL && curCnt < 10) {
        bool isMyFollower = obj->user[myUserId].followList[curTweet->userId];
        // ��ǰ����by�Լ� || by��ע��
        if (myUserId == curTweet->userId || isMyFollower == true) {
            res[curCnt++] = curTweet->tweetId;
        }
        curTweet = curTweet->nxt;
    }
    *retSize = curCnt;
    return res;
}

void twitterFollow(Twitter* obj, int followerId, int followeeId) {
    obj->user[followerId].followList[followeeId] = true;
}

void twitterUnfollow(Twitter* obj, int followerId, int followeeId) {
    obj->user[followerId].followList[followeeId] = false;
}

void twitterFree(Twitter* obj) {
    if (obj && obj->user) free(obj->user); // ��2��user[MAX_NUM]����Ҫ
    if (obj && obj->tweet) free(obj->tweet);
    if (obj) free(obj);
}

/**
 * Your Twitter struct will be instantiated and called as such:
 * Twitter* obj = twitterCreate();
 * twitterPostTweet(obj, userId, tweetId);

 * int* param_2 = twitterGetNewsFeed(obj, userId, retSize);

 * twitterFollow(obj, followerId, followeeId);

 * twitterUnfollow(obj, followerId, followeeId);

 * twitterFree(obj);
*/

int main() {
    Twitter* obj = twitterCreate();
    twitterPostTweet(obj, 1, 5);
    int retSize = 0;
    int* res = twitterGetNewsFeed(obj, 1, &retSize);
    for (int i = 0; i < retSize; i++) {
        printf("%d ", res[i]);
    }
    printf("\n");

    twitterFollow(obj, 1, 2);
    twitterPostTweet(obj, 2, 6);
    res = twitterGetNewsFeed(obj, 1, &retSize);
    for (int i = 0; i < retSize; i++) {
        printf("%d ", res[i]);
    }
    printf("\n");

    twitterUnfollow(obj, 1, 2);
    res = twitterGetNewsFeed(obj, 1, &retSize);
    for (int i = 0; i < retSize; i++) {
        printf("%d ", res[i]);
    }
    printf("\n");
    return 0;

}