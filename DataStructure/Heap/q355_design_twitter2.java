package DataStructure.Heap;

import java.util.*;

public class q355_design_twitter2 {
    private Map<Integer, Set<Integer>> followMap;
    private Map<Integer, List<Tweet>> usrTweetMap;
    private int timestamp;
    private PriorityQueue<Tweet> maxQ; // time top10小

    //    class Twitter{
    public q355_design_twitter2() { // Twitter
        followMap = new HashMap<>();
        usrTweetMap = new HashMap<>();
        timestamp = 0;
        maxQ = new PriorityQueue<>((o1, o2) -> o2.time - o1.time);
    }

    // 发推文
    public void postTweet(int userId, int tweetId) {
        List<Tweet> tweets = usrTweetMap.getOrDefault(userId, new ArrayList<>());
        Tweet newTweet = new Tweet(userId, tweetId, timestamp++);
        tweets.add(0, newTweet); // 头插法
        usrTweetMap.put(userId, tweets); // 坑/注意：必须重新put！
    }

    // 获取10个最近的推文
    public List<Integer> getNewsFeed(int userId) {
        maxQ.clear(); // 坑： 勿漏！
        List<Tweet> tweets = usrTweetMap.getOrDefault(userId, new ArrayList<>());
        maxQ.addAll(tweets);

        Set<Integer> follows = followMap.getOrDefault(userId, new HashSet<>());
        for (Integer follow : follows) {
            List<Tweet> tweets_follow = usrTweetMap.getOrDefault(follow, new ArrayList<>());
            maxQ.addAll(tweets_follow);
        }
        List<Integer> res = new ArrayList<>();
        while (!maxQ.isEmpty() && res.size() < 10) {
            Tweet tweet = maxQ.poll();
            res.add(tweet.tweetId);
        }
        return res;
    }

    // followerId发起关注-> 被关注followeeId
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }

        Set<Integer> followSet = followMap.getOrDefault(followerId, new HashSet<>());
        followSet.add(followeeId);
        followMap.put(followerId, followSet); // 坑/注意：还需重新put！
    }

    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        Set<Integer> followSet = followMap.getOrDefault(followerId, new HashSet<>());
        followSet.remove(followeeId);
        followMap.put(followerId, followSet);
    }
//    }

    // 推文类，是一个单链表（结点视角）
    class Tweet {
        int userId;
        int tweetId;
        int time;

        public Tweet(int userId, int tweetId, int time) {
            this.userId = userId;
            this.tweetId = tweetId;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Tweet{" +
                    "userId=" + userId +
                    ", tweetId=" + tweetId +
                    ", time=" + time +
                    '}';
        }
    }

    public static void main(String[] args) {
        q355_design_twitter2 sol = new q355_design_twitter2();
        sol.postTweet(1, 5);
        List<Integer> newsFeed = sol.getNewsFeed(1);
        System.out.println(newsFeed);

        sol.follow(1, 2);
        sol.postTweet(2, 6);

        List<Integer> res2 = sol.getNewsFeed(1);
        System.out.println(res2);

        sol.unfollow(1, 2);

        List<Integer> res3 = sol.getNewsFeed(1);
        System.out.println(res3);
    }
}
