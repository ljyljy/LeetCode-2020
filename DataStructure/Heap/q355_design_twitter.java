package DataStructure.Heap;

import java.util.*;

public class q355_design_twitter {
//    class Twitter{
        private Map<Integer, Set<Integer>> followings; // <usrId, ��ע�б�>
        private Map<Integer, Tweet> idTweetMap; // <usrId, �����б�>
        private int timestamp = 0; // ȫ��ʹ�õ�ʱ����ֶΣ��û�ÿ����һ������֮ǰ + 1
        private PriorityQueue<Tweet> maxHeap; // �ϲ� k ������(��time����)

        public q355_design_twitter() { // Twitter
            followings = new HashMap<>();
            idTweetMap = new HashMap<>();
            maxHeap = new PriorityQueue<>((o1, o2) -> o2.time-o1.time);
        }

        // ������
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

        // ��ȡ10�����������
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
                // ��·�鲢�����q23, 786 etc.
                if (head.nxt != null) {
                    maxHeap.offer(head.nxt);
                }
                cnt++;
            }
            return res;
        }

        // followerId�����ע-> ����עfolloweeId
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

            Set<Integer> followSet = followings.get(followerId); // ����Ϊnull��
            if (followSet != null && followSet.contains(followeeId)) {
                // followings��<erId, fSet>�����ò��䣬fSet���иı伴�ɣ�
                // �����衿�ٽ�followings������put(erId, ���º��followSet)
                followSet.remove(followeeId);
            }
        }
//    }

    // �����࣬��һ������������ӽǣ�
    private class Tweet {
        private int id;
        private int time;
        private Tweet nxt; // ������

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
