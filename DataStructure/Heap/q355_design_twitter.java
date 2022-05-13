package DataStructure.Heap;

import java.util.*;

public class q355_design_twitter {
//    class Twitter{
        private Map<Integer, Set<Integer>> followings; // <usrId, 关注列表>
        private Map<Integer, Tweet> idTweetMap; // <usrId, 推文列表>
        private int timestamp = 0; // 全局使用的时间戳字段，用户每发布一条推文之前 + 1
        private PriorityQueue<Tweet> maxHeap; // 合并 k 组推文(按time升序)

        public q355_design_twitter() { // Twitter
            followings = new HashMap<>();
            idTweetMap = new HashMap<>();
            maxHeap = new PriorityQueue<>((o1, o2) -> o2.time-o1.time);
        }

        // 发推文
        public void postTweet(int userId, int tweetId) {
            timestamp++;
            if (idTweetMap.containsKey(userId)) {
                Tweet oldHead = idTweetMap.get(userId);
                Tweet newHead = new Tweet(tweetId, timestamp);
                newHead.nxt = oldHead;
                idTweetMap.put(userId, newHead);
            } else {
                Tweet newHead = new Tweet(tweetId, timestamp);
                idTweetMap.put(userId, newHead);
            }
        }

        // 获取10个最近的推文
        public List<Integer> getNewsFeed(int userId) {
            maxHeap.clear();

            if (idTweetMap.containsKey(userId)) {
                maxHeap.offer(idTweetMap.get(userId));
            }

            Set<Integer> followingSet = followings.getOrDefault(userId, null);
            if (followingSet != null && !followingSet.isEmpty()) {
                for (int following: followingSet) {
                    if (idTweetMap.containsKey(following)) {
                        maxHeap.offer(idTweetMap.get(following));
                    }
                }
            }

            List<Integer> res = new ArrayList<>(10);
            int cnt = 0;
            while (!maxHeap.isEmpty() && cnt < 10) {
                Tweet head = maxHeap.poll();
                res.add(head.id);
                // 多路归并，类比q23, 786 etc.
                if (head.nxt != null) {
                    maxHeap.offer(head.nxt);
                }
                cnt++;
            }
            return res;
        }

        // followerId发起关注-> 被关注followeeId
        public void follow(int followerId, int followeeId) {
            if (followerId == followeeId) return;

            Set<Integer> followingSet = followings.get(followerId);
            if (followingSet == null) {
                followings.put(followerId, new HashSet<>(){{add(followeeId);}});
            } else {
                followingSet.add(followeeId);
                followings.put(followerId, followingSet);
            }
        }

        public void unfollow(int followerId, int followeeId) {
            if (followerId == followeeId) return;

            Set<Integer> followSet = followings.get(followerId); // 可能为null！
            if (followSet != null && followSet.contains(followeeId)) {
                // followings对<erId, fSet>的引用不变，fSet自行改变即可！
                // 【无需】再将followings中重新put(erId, 更新后的followSet)
                followSet.remove(followeeId);
            }
        }
//    }

    // 推文类，是一个单链表（结点视角）
    private class Tweet {
        private int id;
        private int time;
        private Tweet nxt; // 单链表

        public Tweet(int id, int timestamp) {
            this.id = id;
            this.time = timestamp;
        }
    }

    public static void main(String[] args) {
        q355_design_twitter sol = new q355_design_twitter();
        sol.postTweet(1, 1);
        List<Integer> newsFeed = sol.getNewsFeed(1);
        System.out.println(newsFeed);

        sol.follow(2, 1);

        List<Integer> res2 = sol.getNewsFeed(2);
        System.out.println(res2);

        sol.unfollow(2, 1);

        List<Integer> res3 = sol.getNewsFeed(2);
        System.out.println(res3);
    }
}
