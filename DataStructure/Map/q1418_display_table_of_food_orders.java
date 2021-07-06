package DataStructure.Map;

import java.util.*;

public class q1418_display_table_of_food_orders {
    public List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> res = new ArrayList<>();
        if (orders == null || orders.size() == 0) return res;
        Map<Integer, Map<String, Integer>> tableFoodMap = new HashMap<>(); // <"Table", 《foodItem, cnt》>
        Set<String> foodItemSet = new HashSet<>();
        Set<Integer> tableSet = new HashSet<>();
        for (List<String> order: orders) {
            int table = Integer.valueOf(order.get(1));
            String foodItem = order.get(2);
            Map<String, Integer> foodCnt = tableFoodMap.getOrDefault(table, new HashMap<>());
            foodCnt.put(foodItem, foodCnt.getOrDefault(foodItem, 0) + 1);
            tableFoodMap.put(table, foodCnt);
            foodItemSet.add(foodItem);
            tableSet.add(table);
        }

        // for (Map.Entry<Integer, Map<String, Integer>> tableFood: tableFoodMap.entrySet()) {
        //     int table = tableFood.getKey();
        //     Map<String, Integer> foodCnt = tableFood.getValue();
        //     for (Map.Entry<String, Integer> entry : foodCnt.entrySet())
        //         System.out.println(table + ", "+ entry.getKey() + ": " + entry.getValue());
        // }
        // 1.将set中的物品名按字典序升序排列
        List<String> foodItemList = new ArrayList<>(foodItemSet);
        Collections.sort(foodItemList); // 默认字典序

        List<String> title = new ArrayList<>();
        title.add("Table");
        title.addAll(foodItemList);
        // for (String str: title) System.out.println(str);

        // 2.将桌号升序排列
        List<Integer> tableList = new ArrayList<>(tableSet);
        Collections.sort(tableList); // 默认字典序
        // for (Integer num: tableList) System.out.println(num);

        // 3. 填入res
        res.add(title);
        int n_table = tableSet.size();
        for (int i = 0; i < n_table; i++) {
            List<String> row = new ArrayList<>();
            int table = tableList.get(i);
            row.add("" + table);
            Map<String, Integer> foodCnt = tableFoodMap.getOrDefault(table, new HashMap<>());
            for (int j = 0; j < foodItemList.size(); j++) {
                String food = foodItemList.get(j);
                int cnt = foodCnt.getOrDefault(food, 0);
                row.add("" + cnt);
            }
            res.add(row);
        }
        return res;
    }
}
