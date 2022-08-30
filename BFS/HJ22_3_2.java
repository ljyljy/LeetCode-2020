package BFS;

import java.util.*;

public class HJ22_3_2 {
    // 类比q127
//    作者：starky
//    链接：https://www.nowcoder.com/discuss/924503?type=post&order=recall&pos=&page=2&ncTraceId=&channel=-1&source_id=search_post_nctrack
//    来源：牛客网

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();
        List<List<Integer>> graph = new ArrayList<>(N);
        for (int i = 0; i < N; ++i) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N; ++i) {
            String nextLine = sc.nextLine();
            String[] split = nextLine.split(",");
            for (int j = 1; j < split.length; ++j) {
                graph.get(i).add(Integer.parseInt(split[j]));
            }
        }
        sc.close();

        // bfs
        Deque<Integer> queue = new LinkedList<>();
        queue.add(M);
        Set<Integer> visited = new HashSet<>();
        visited.add(M);
        Set<Integer> ans = new HashSet<>();

        while (!queue.isEmpty()) {
            Integer cur = queue.pollFirst();
            visited.add(M);
            for (int next : graph.get(cur)) {
                if (visited.contains(next)) { // BFS成环
                    System.out.println(-1); // 【BFS避免成环】，类比q127、HJ22_3_2, 133
                    return;
                }
                if (ans.contains(next)) {
                    continue;
                }
                ans.add(next);
                queue.add(next);
            }
        }
        Integer[] ansArr = ans.toArray(new Integer[ans.size()]);
        Arrays.sort(ansArr);
        for (int i = 0; i < ansArr.length - 1; ++i) {
            System.out.print(ansArr[i] + ",");
        }
        if (ansArr.length == 0) {
            System.out.println("null");
        } else {
            System.out.println(ansArr[ansArr.length - 1]);
        }
    }
}
