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

    // ˫�˶��еĲ���add/removeFirst��ջ��/��β - ��q402
    private boolean dfs(Deque<Double> cards) {
        if (cards.size() == 1) {
            return Math.abs(cards.peekLast() - TARGET) < EPISLON;
        }
        for (int i = 0; i < cards.size(); i++) {
            double a = cards.removeLast(); // removeXX������-?��ͷ/ջ��a=Last
            for (int j = 0; j < cards.size(); j++) {
                double b = cards.removeLast(); // ?��ͷ/ջ��b
                Set<Double> results = getResults(a, b);
                for (double res: results) {
                    cards.addLast(res);
                    if (dfs(cards)) return true;
                    cards.removeLast(); //
                }
                cards.addFirst(b); // �ŵ�����β/ջ��b����������addLast��!!!!
                // ���ڴ�ǰ������㣬����ʱ��Ҫ��a/b�ŵ����棬�ȴ�����������㣨���򲻻��ٱ�������
            }
            cards.addFirst(a);  // ?�ŵ���β/ջ��a������addLast!!!! �����˳��ߵ���Ѱ���������
        }
        return false;
    }

    private Set<Double> getResults(double a, double b) {
        Set<Double> res = new HashSet<>();
        res.add(a + b);
        res.add(a - b);
//        res.add(b - a); // ����
        res.add(a * b);
        if (Math.abs(b) >= EPISLON) res.add(a / b); // ������Ϊ0
//        if (Math.abs(a) >= EPISLON) res.add(b / a); // ������Ϊ0
        return res;
    }
}