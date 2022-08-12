package Recursion.combination_based;

import java.util.*;

public class q679_24_game {
    private Deque<Double> nextCards = new ArrayDeque<>();
    private final double TARGET = 24;
    private final double EPISLON = 1e-6;

    public boolean judgePoint24(int[] cards) {
        for (double card: cards) {
            nextCards.add((double)card);
        }
        return dfs(nextCards);
    }

    // 双端队列的操作add/removeFirst：栈顶/队尾 - 见q402
    private boolean dfs(Deque<Double> cards) {
        if (cards.size() == 1) {
            return Math.abs(cards.peekLast() - TARGET) < EPISLON;
        }

        for (int i = 0; i < cards.size(); i++) {
            double a = cards.removeLast(); // 【队头】/栈底
            for (int j = 0; j < cards.size(); j++) {
                double b = cards.removeLast(); // 【队头】/栈底
                Set<Double> results = getResults(a, b);
                for (double res: results) {
                    // 算好的重新放在【队头】（a、b计算好，被【替换】为结果nxt）
                    cards.addLast(res);
                    if (dfs(cards)) return true;
                    cards.removeLast(); //
                }
                cards.addFirst(b);  // 队尾/栈顶，【假“回溯”】：放到队【尾】，更换顺序，看有无可行解
                // 由于从前往后计算，回溯时需要将a/b放到后面，等待后续加入计算（否则不会再遍历到）
            }
            cards.addFirst(a);  // 放到队尾/栈顶a，而非addLast!!!! 【将相对顺序颠倒，寻找其他结果】
        }
        return false;
    }

    private Set<Double> getResults(double a, double b) {
        Set<Double> res = new HashSet<>();
        // 【队列】，因此【a前 b后】
        res.add(a + b);
        res.add(a - b);
//        res.add(b - a); // 随意
        res.add(a * b);
        if (Math.abs(b) >= EPISLON) res.add(a / b); // 除数不为0
//        if (Math.abs(a) >= EPISLON) res.add(b / a); // 除数不为0
        return res;
    }
}
