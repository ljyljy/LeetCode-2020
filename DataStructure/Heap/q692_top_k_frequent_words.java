package DataStructure.Heap;

import java.util.*;

public class q692_top_k_frequent_words {
    // 类比q347,692
    public List<String> topKFrequent(String[] words, int k) {
        // 1.先用哈希表统计单词出现的频率
        Map<String, Integer> map = new HashMap<>(); // <word, cnt>
        for (String word: words) {
            map.put(word, map.getOrDefault(word, 0)+1);
        }
        // 2.小根堆
        // 前k大--最小堆，pop小，保留最后k大の升序; 又∵需降序输出(词频↓, 字典↑) - ∴翻转
        PriorityQueue<String> minHeap = new PriorityQueue<>(k, (w1, w2) -> {
            if (map.get(w1) == map.get(w2)) // 1) 词频相同
                return w2.compareTo(w1); // 则比较单词, 按字典排序(降序！∵最后需翻转为升序)
            else return map.get(w1) - map.get(w2); // 2) 否则，按词频升序 -- minHeap
        });
        // 3.依次向堆加入元素
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            minHeap.offer(entry.getKey());
            if (minHeap.size() > k)
                minHeap.poll();
        }
        // 4.依次弹出堆中的 K 个元素，放入结果集合中
        List<String> res = new ArrayList<>(k); // LinkedList不可指定大小为k！
        while (!minHeap.isEmpty()) res.add(minHeap.poll());
        // 5.注意最后需要反转元素的顺序
        Collections.reverse(res); // 法2：不翻转，直接头插法：res.add(0, minHeap.poll());
        return res;
    }
}
