package DataStructure.Heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// ���q264, q17_09
public class q17_09_get_kth_magic_number_lcci {
    public int getKthMagicNumber(int k) {  // ��k�� - ��С��
        PriorityQueue<Long> heap = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        heap.offer(1L);
        set.add(1L);

        long curNum = 1L;
        int[] factors = { 3, 5, 7 }; // ��ʼ������
        for (int i = 0; i < k; i++) {
            curNum = heap.poll(); // ��poll��k�Σ���k�μ�Ϊ����
            for (int fac: factors) {
                // nxtNum������ֻ����factors�е�Ԫ�ع���
                long nxtNum = curNum * fac;
                if (!set.contains(nxtNum)) {
                    set.add(nxtNum);
                    heap.offer(nxtNum);
                }
            }
        }
        return (int)curNum;
    }
}
