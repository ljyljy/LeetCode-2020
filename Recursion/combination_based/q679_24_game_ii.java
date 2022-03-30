package Repathsion.combination_based;

import java.util.*;

public class q679_24_game_ii {
//    TODO: 返回结果，并打印出合法的表达式
    private static final int TARGET = 24;
    private static final double EPS = 1e-6;

    public boolean judgePoint24(int[] cards) {
        if (cards == null || cards.length != 4) return false;
        double[] nxtCards = new double[4];
        List<String> path = new ArrayList<>(); // current expression
        List<String> ans = new ArrayList<>();
        int i = 0;
        for (int num : cards) {
            nxtCards[i++] = num;
            path.add(num + "");
        }
        return solve(nxtCards, path, ans);
    }

    private boolean solve(double[] cards, List<String> path, List<String> ans) {
        int n = cards.length;
        if (n == 1) {
            if (Math.abs(TARGET - cards[0]) <= EPS) {
                ans.add(path.get(0));
                System.out.println("expression = " + ans.get(0));
                return true;
            }
            return false;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double[] nxtCards = new double[n - 1]; // pop倆数a&b，运算->结果替换, 故少了一位
                List<String> path2 = new ArrayList<>();
                int kk = 0;
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) { // 跳过选择的倆数a,b
                        nxtCards[kk++] = cards[k];
                        path2.add(path.get(k));
                    }
                }
                for (Pair p : getNextRes(cards, i, j, path)) {
                    nxtCards[kk] = p.val;
                    path2.add(p.expr);
                    if (solve(nxtCards, path2, ans)) return true;
                    path2.remove(path2.size() - 1); //backtrack reset state
                }
            }
        }
        return false;
    }

    private List<Pair> getNextRes(double[] cards, int i, int j, List<String> path) {
        double x = cards[i], y = cards[j];
        List<Pair> res = new ArrayList<>();
        res.add(new Pair(x + y,  "(" + path.get(i) + "+" + path.get(j) + ")"));
        res.add(new Pair(x - y,  "(" + path.get(i) + "-" + path.get(j) + ")"));
        res.add(new Pair(y - x,  "(" + path.get(j) + "-" + path.get(i) + ")"));
        res.add(new Pair(x * y,   path.get(i) + "*" + path.get(j)));
        if (y != 0) {
            res.add(new Pair(x / y,  "(" + path.get(i) + "/" + path.get(j) + ")"));
        }
        if (x != 0) {
            res.add(new Pair(y / x, "(" + path.get(j) + "/" + path.get(i) + ")"));
        }
        return res;
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
