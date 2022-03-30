package String;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class HJ94_count_tickets {
    private static final String Invalid = "Invalid";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = Integer.valueOf(sc.nextLine());
            String[] names = sc.nextLine().split("\\s");
            int k = Integer.valueOf(sc.nextLine());
            String[] ticks = sc.nextLine().split("\\s");
            countTickets(names, ticks);
        }
    }

    private static void countTickets(String[] names, String[] ticks) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String name: names) map.put(name, 0);
        map.put(Invalid, 0);

        for (String tick: ticks) {
            if (map.containsKey(tick)) {
                map.put(tick, map.get(tick)+1);
            } else {
                map.put(Invalid, map.get(Invalid)+1);
            }
        }

        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
