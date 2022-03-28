package Recursion.combination_based;

import java.util.*;

public class q679_24_game {
    private Deque<Double> nextCards = new ArrayDeque<>();
    private final double TARGET = 24;
    private final double EPISLON = 1e-6;

    public boolean judgePoint24(int[] cards) {
        for (double card: cards) nextCards.add((double)card);
        return dfs(nextCards);
    }

    private boolean dfs(Deque<Double> cards) {
        if (cards.size() == 1) {
            return Math.abs(cards.peekLast() - TARGET) < EPISLON;
        }
        for (int i = 0; i < cards.size(); i++) {
            double a = cards.removeLast(); // 队尾/栈顶a
            for (int j = 0; j < cards.size(); j++) {
                double b = cards.removeLast(); // 队尾/栈顶b
                Set<Double> results = getResults(a, b);
                for (double res: results) {
                    cards.addLast(res);
                    if (dfs(cards)) return true;
                    cards.removeLast();
                }
                cards.addFirst(b); // ?放到队头/栈底，而非addLast!!!! 将相对顺序颠倒，寻找其他结果
            }
            cards.addFirst(a);  // ?放到队头/栈底，而非addLast!!!! 将相对顺序颠倒，寻找其他结果
        }
        return false;
    }

    private Set<Double> getResults(double a, double b) {
        Set<Double> res = new HashSet<>();
        res.add(a + b);
        res.add(a - b);
//        res.add(b - a); // 随意
        res.add(a * b);
        if (Math.abs(b) >= EPISLON) res.add(a / b); // 除数不为0
//        if (Math.abs(a) >= EPISLON) res.add(b / a); // 除数不为0
        return res;
    }
}
