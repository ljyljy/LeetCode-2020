package DataStructure.Map.TreeMap;

import java.util.*;

public class HJ8_mergeTables{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int key = sc.nextInt();
            int val = sc.nextInt();
            map.put(key, map.getOrDefault(key, 0) + val);
        }

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}