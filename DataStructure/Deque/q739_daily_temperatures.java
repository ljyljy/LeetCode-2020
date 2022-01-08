package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class q739_daily_temperatures {
    /**
     * ����ջ��ջ��˳��Ҫô�Ӵ�С Ҫô��С����,
     *      ���⡾ջ��(start)->ջ��(����)���Ӵ�С������
     * ��վԪ��Ҫ�͵�ǰջ��ջ��Ԫ�ؽ��бȽ�
     * ������ջ���� ����Ԫ���±�����
     * �����ڵ��������
     * https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0739.%E6%AF%8F%E6%97%A5%E6%B8%A9%E5%BA%A6.md
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Arrays.fill(res, 0); // ȫ����ʼ��Ϊ0���������ջ�еģ���Ϊ�ұ�������Ԫ�أ�����=0����
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int cur = temperatures[i];
            while (!deque.isEmpty() && // �� ����Ž�while����Ϊ��Ҫ�����ж�(���ܶ�ε�ջ)
                    temperatures[deque.peekFirst()] < cur) {// ջ����peek/peekFirst()!!!??
                int prevIdx = deque.pop();
                res[prevIdx] = i - prevIdx;
            }
            deque.push(i); // ����±�
        }
        return res;
    }
    /*
    ջ������push ->�ڲ�ʵ����addFirst(); pop ? removeFirst();
    ��ջ����˫�˶����о��Ƕ���ͷ����ÿ�ζ���ͷ�����/ɾ����
    �����ǣ���˫�˶���Deque������˫�������ջ����
     */
}
