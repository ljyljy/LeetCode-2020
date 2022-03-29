package Recursion.combination_based;

import java.util.*;

public class q679_HJ67_24_game {
    private static double EPS = 1e-6;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
//            int[] nums = new int[4];
            Deque<Double> nums = new ArrayDeque<>();
            for (int i = 0; i < 4; i++) {
//                nums[i] = sc.nextInt();
                nums.add(sc.nextDouble());
            }
            System.out.println(dfs(nums));
        }
    }

    private static boolean dfs(Deque<Double> nums) {
        if (nums.size() == 1) {
            if (Math.abs(nums.peek() - 24.0) <= EPS) {
                return true;
            } else return false;
        }

        for (int i = 0; i < nums.size(); i++) {
            double a = nums.removeLast(); // ¶ÓÎ²/Õ»¶¥
            for (int j = 0; j < nums.size(); j++) {
                double b = nums.removeLast();// ¶ÓÎ²/Õ»¶¥
                for (double nxt: getNxts(a, b)) {
                    nums.addLast(nxt);
                    if (dfs(nums)) return true;
                    nums.removeLast();
                }
                nums.addFirst(b);  // ?¶ÓÍ·/Õ»µ×
            }
            nums.addFirst(a);  // ?¶ÓÍ·/Õ»µ×
        }
        return false;
    }

    private static Set<Double> getNxts(double a, double b) {
        Set<Double> res = new HashSet<>();
        res.add(a + b);
        res.add(a - b);
        res.add(a * b);
        if(Math.abs(b) >= EPS) res.add(a / b);
        return res;
    }
}
