package Binary_Search.bin_Answer;

import java.util.*;

public class q981_time_based_key_value_store {
    // 法1：自定义Node(k, v, t) & 二分搜索给定timestamp
    class Node {
        private String key, value;
        private int timestamp;
        public Node (String k, String v, int t) {key = k; value = v; timestamp = t;};
    }

    private Map<String, List<Node>> map = new HashMap<>();
//    public TimeMap() {
//        map = new HashMap<>();
//    }

    public void set(String k, String v, int t) {
        List<Node> nodes = map.getOrDefault(k, new ArrayList<>());
        nodes.add(new Node(k, v, t));
        map.put(k, nodes);
    }

    public String get(String k, int t) {
        List<Node> nodes = map.getOrDefault(k, new ArrayList<>());
        if (nodes.isEmpty()) return "";
        // 1. time升序排列 - 无需(默认升序)
        // Collections.sort(nodes, (o1, o2) -> (o1.timestamp - o2.timestamp));
        int n = nodes.size();
        // 2. 二分查找<=t的最大值  [L, mid-1(ret返回值)] [mid(t), R]
        int L = 0, R = n-1;
        while (L < R) {
            int mid = L + R + 1 >> 1;
            if (nodes.get(mid).timestamp <= t)
                L = mid; // 在右区间找更大的
            else R = mid - 1; // 在左区间找更小的
        }
        return nodes.get(L).timestamp <= t? nodes.get(L).value: "";
    }


//    法0：Map嵌套（Map<Integer, String>会超时！可能是因为案例timestamp >> value个数！）
    private Map<String, Map<String, Integer>> map0 = new HashMap<>();

    public void set0(String key, String value, int timestamp) {
        Map<String, Integer> valMap_new = map0.getOrDefault(key, new HashMap<>());

        valMap_new.put(value, timestamp);
        map0.put(key, valMap_new);
    }

    public String get0(String key, int timestamp) {
        // if (key == null || key.equals("")) return null;
        Map<String, Integer> valMap_prev = map0.getOrDefault(key, new HashMap<>());
        if (valMap_prev.isEmpty()) return "";

        int maxTime_pre = -1;
        String maxVal_pre = "";
        for (Map.Entry<String, Integer> entry : valMap_prev.entrySet()) {
            String curVal = entry.getKey();
            int curTime = entry.getValue();
            if (curTime == timestamp)
                return curVal;
            if (maxTime_pre <= timestamp) {
                if (timestamp > curTime && curTime >= maxTime_pre) {
                    maxTime_pre = curTime;
                    maxVal_pre = curVal;
                }
            } else return "";
        }
        return maxVal_pre;
    }
}
