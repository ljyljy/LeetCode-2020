#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
// #include <string.h>
// #include <math.h>
// #include <limits.h>

// 分析：
// UserMap<userId, Follows【followerIds】(数组curIdx/链表形式)> -- 数组代替哈希，
// TweetMap<User&tweetId, Tweets【tweetIds】(数组curIdx/链表形式)> -- 数组则需要排序（qsort）/链表头插法√

// 题目：1 <= userId, followerId, followeeId <= 500
#define MAX_NUM 501

typedef struct {
    int id; // userId ∈ [1, 500]
    bool followList[MAX_NUM]; // 类比q146：关注与否。具体followerId对应该数组的下标
} User;

typedef struct Tweet_s {
    int userId;
    int tweetId; // tweetIds
    struct Tweet_s* nxt; // 单链表，头插法：最新推文在首位
} Tweet;

typedef struct {
    User* user; // 法2：User user[MAX_NUM]; 不需要free
    Tweet* tweet;
} Twitter;


Twitter* twitterCreate() {
    Twitter* obj = (Twitter*)calloc(1, sizeof(Twitter));
    // memset(obj->user, 0, MAX_NUM * sizeof(User)); // 法2：user[MAX_NUM]
    obj->user = (User*)calloc(MAX_NUM, sizeof(User)); // 法1：User*
    obj->tweet = (Tweet*)calloc(1, sizeof(Tweet)); // dummy
    obj->tweet->nxt = NULL;
    return obj;
}

void twitterPostTweet(Twitter* obj, int userId, int tweetId) {
    // 创建推文tweet
    Tweet* tweet = (Tweet*)calloc(1, sizeof(Tweet));
    tweet->userId = userId;
    tweet->tweetId = tweetId;
    // 将最新推文tweet，头插法：插入Twitter.tweet链表首部
    tweet->nxt = obj->tweet->nxt;
    obj->tweet->nxt = tweet;
}

// 检索自己&关注人列表的最近10条推文，时间从近到远排序
int* twitterGetNewsFeed(Twitter* obj, int myUserId, int* retSize) {
    int* res = (int*)calloc(10, sizeof(int));
    int curCnt = 0; // 当前推文数
    Tweet* curTweet = obj->tweet->nxt; // 遍历链表，暴力
    while (curTweet != NULL && curCnt < 10) {
        bool isMyFollower = obj->user[myUserId].followList[curTweet->userId];
        // 当前推文by自己 || by关注人
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
    if (obj && obj->user) free(obj->user); // 法2：user[MAX_NUM]不需要
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