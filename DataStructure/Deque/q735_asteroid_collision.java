package DataStructure.Deque;

import java.util.*;

public class q735_asteroid_collision {
    /** �����⣡������
     [-2,-1,1,2] --> [-2,-1,1,2] ������ײ����
     [-2,1,1,-1] --> [-2,1]
     �� ֻ��ջ��������ֵ�� �Ż���ײ������

     // �������������߹��ӣ���������ݵ��ˣ�������ݣ��������߹ݵ��ˣ�ר�����߹���
     // ��ջ��Ϊ��ݣ�����������������棨������ջ���������������ˣ���Ҫ����Ӧս
     // ��������ֻ�аѹ�������˶���Ӯ�ˣ����ܽ����
     */
    public int[] asteroidCollision(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            boolean flag = true;
            // ֻ��ջ��������ֵ�� �Ż���ײ������ ��while�У�ջ�����ܻ�䣬��numһֱ���䡿��
            while (!stack.isEmpty() && nums[stack.peek()] > 0 && nums[i] < 0) {
                int val_peek = Math.abs(nums[stack.peek()]);
                int val_cur = Math.abs(nums[i]);
                if (val_cur > val_peek) { // stack��ջ��curѹջ������while������ջ����
                    stack.pop();
                    // flag = true;  // ��д�ɲ�д
                } else if (val_cur < val_peek) { // cur��ѹջ��stack���仯, �˳�while
                    flag = false;
                    break;
                } else if (val_cur == val_peek) { // cur��ѹջ��stack��ջ, ���˳�while,���ܿ��¸�num��
                    stack.pop();
                    flag = false;
                    break; // ����©break����
                }
            }
            if (flag)  stack.push(i);
        }

        int m = stack.size();
        int[] res = new int[m];
        for (int i = m-1; i >= 0; i--) {
            res[i] = nums[stack.pop()];
        }
        return res;
    }
}
