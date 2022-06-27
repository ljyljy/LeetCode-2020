package DataStructure.Map.TreeMap;

//import javafx.scene.layout.Priority;

import java.util.*;

public class q451_sort_characters_by_frequency {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>(); //   Ƶ
        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> entryMaxHeap =
                new PriorityQueue<>((o1, o2) -> (o2.getValue() - o1.getValue()));
        entryMaxHeap.addAll(map.entrySet());

        StringBuilder sb = new StringBuilder();
        while (!entryMaxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = entryMaxHeap.poll();
            sb.append((entry.getKey()+"").repeat(entry.getValue()));
        }
        return sb.toString();

    }
}
