package Recursion;

import java.util.*;

public class q690_employee_importance {
    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    // 法1：DFS -- 时、空O(n)
    Map<Integer, Employee> map = new HashMap<>(); // <id, Employee>
    public int getImportance1(List<Employee> employees, int id) {
        for (Employee employee: employees) {
            map.put(employee.id, employee);
        }
        return dfs(id);
    }

    private int dfs(int id) {
        Employee employee = map.get(id);
        List<Integer> subordinates = employee.subordinates;
        int sum = employee.importance; // 控制某结点的下一层, 首先加入自身val
        for (Integer sub_id: subordinates) { // 1. 横向遍历: 直接下属
            sum += dfs(sub_id);   // 2. 纵向遍历: 间接下属(重复的下属val会被重复计算--但符合题意)
        }
        return sum;
    }

    // 法2：BFS
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>(); // <id, Employee>
        for (Employee employee: employees) {
            map.put(employee.id, employee);
        }
        int sum = 0;
        Queue<Integer> subIds = new LinkedList<>();
        subIds.offer(id);
        while (!subIds.isEmpty()) {
            id = subIds.poll();
            Employee employee = map.get(id);
            sum += employee.importance;
            for (Integer sub_id : employee.subordinates) {
                subIds.offer(sub_id);
            }
        }
        return sum;
    }

}
