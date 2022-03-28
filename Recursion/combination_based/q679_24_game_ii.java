package Recursion.combination_based;

import java.util.*;

public class q679_24_game_ii {
//    TODO: 返回结果，并打印出合法的表达式

    private static final int TARGET = 24;
    private static final double P = 1e-6;

    public boolean judgePoint24(int[] cards) {
        if (cards == null || cards.length != 4) return false;
        double[] a = new double[4];
        int i = 0;
        List<String> cur = new ArrayList<>(); //current expression
        List<String> ans = new ArrayList<>();
        for (int num : cards) {
            a[i++] = num;
            cur.add(String.valueOf(num));
        }
        return solve(a, cur, ans);
    }

    private boolean solve(double[] a, List<String> cur, List<String> ans) {
        int n = a.length;
        if (n == 1) {
            if (Math.abs(TARGET - a[0]) <= P) {
                ans.add(cur.get(0));
                // System.out.println("expression=" + ans.get(0));
                return true;
            }
            return false;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double[] b = new double[n - 1];
                List<String> cur2 = new ArrayList<>();
                int index = 0;
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) {
                        b[index++] = a[k];
                        cur2.add(cur.get(k));
                    }
                }
                for (Pair p : compute(a, i, j, cur)) {
                    b[index] = p.val;
                    cur2.add(p.expr);
                    if (solve(b, cur2, ans)) return true;
                    cur2.remove(cur2.size() - 1); //backtrack reset state
                }
            }
        }
        return false;
    }

    private List<Pair> compute(double[] a, int i, int j, List<String> cur) {
        double x = a[i], y = a[j];
        List<Pair> ans = new ArrayList<>();
        ans.add(new Pair(x + y,  "(" + cur.get(i) + "+" + cur.get(j) + ")"));
        ans.add(new Pair(x - y,  "(" + cur.get(i) + "-" + cur.get(j) + ")"));
        ans.add(new Pair(y - x,  "(" + cur.get(j) + "-" + cur.get(i) + ")"));
        ans.add(new Pair(x * y,   cur.get(i) + "*" + cur.get(j)));
        if (y != 0) {
            ans.add(new Pair(x / y,  "(" + cur.get(i) + "/" + cur.get(j) + ")"));
        }
        if (x != 0) {
            ans.add(new Pair(y / x, "(" + cur.get(j) + "/" + cur.get(i) + ")"));
        }
        return ans;
    }

    static class Pair {
        double val;
        String expr;
        public Pair (double val, String expr) {
            this.val = val;
            this.expr = expr;
        }
    }

}
