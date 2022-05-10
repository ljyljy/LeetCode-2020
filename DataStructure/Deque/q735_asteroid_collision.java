package DataStructure.Deque;

import java.util.*;

public class q735_asteroid_collision {
    /** 【审题！！！】
     [-2,-1,1,2] --> [-2,-1,1,2] 不会碰撞！？
     [-2,1,1,-1] --> [-2,1]
     ∴ 只有栈顶正，新值负 才会碰撞！！！

     // 将这道题比喻成踢馆子，正方是武馆的人，坐镇武馆，负方是踢馆的人，专门来踢馆子
     // 将栈作为武馆，正方的人在武馆里面（正方进栈），遇到负方的人，就要出来应战
     // 负方的人只有把馆子里的人都踢赢了，才能进武馆
     */
    public int[] asteroidCollision(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            boolean flag = true;
            // 只有栈顶正，新值负 才会碰撞！！！
            while (!stack.isEmpty() && nums[stack.peek()] > 0 && nums[i] < 0) {
                int val_peek = Math.abs(nums[stack.peek()]);
                int val_cur = Math.abs(nums[i]);
                if (val_cur > val_peek) { // stack弹栈，cur压栈
                    stack.pop();
                } else if (val_cur < val_peek) { // cur不压栈，stack不变化
                    flag = false;
                    break;
                } else if (val_cur == val_peek) { // cur不压栈，stack弹栈
                    stack.pop();
                    flag = false;
                    break; // 【勿漏break！】
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
