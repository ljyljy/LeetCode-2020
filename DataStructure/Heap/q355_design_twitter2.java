package DataStructure.Heap;

import java.util.*;

public class q355_design_twitter2 {
    private Map<Integer, Set<Integer>> followMap;
    private Map<Integer, List<Tweet>> usrTweetMap;
    private int timestamp;
    private PriorityQueue<Tweet> maxQ; // time top10С

    //    class Twitter{
    public q355_design_twitter2() { // Twitter
        followMap = new HashMap<>();
        usrTweetMap = new HashMap<>();
        timestamp = 0;
        maxQ = new PriorityQueue<>((o1, o2) -> o2.time - o1.time);
    }

    // ������
    public void postTweet(int userId, int tweetId) {
        List<Tweet> tweets = usrTweetMap.getOrDefault(userId, new ArrayList<>());
        Tweet newTweet = new Tweet(userId, tweetId, timestamp++);
        tweets.add(0, newTweet); // ͷ�巨
        usrTweetMap.put(userId, tweets); // ��/ע�⣺��������put��
    }

    // ��ȡ10�����������
    public List<Integer> getNewsFeed(int userId) {
        maxQ.clear(); // �ӣ� ��©��
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

    // followerId�����ע-> ����עfolloweeId
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }

        Set<Integer> followSet = followMap.getOrDefault(followerId, new HashSet<>());
        followSet.add(followeeId);
        followMap.put(followerId, followSet); // ��/ע�⣺��������put��
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

    // �����࣬��һ������������ӽǣ�
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
