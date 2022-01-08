package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class q739_daily_temperatures {
    /**
     * 单调栈，栈内顺序要么从大到小 要么从小到大,
     *      本题【栈底(start)->栈顶(遍历)】从大到小，降序
     * 入站元素要和当前栈内栈首元素进行比较
     * 若大于栈顶则 则与元素下标做差
     * 若大于等于则放入
     * https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0739.%E6%AF%8F%E6%97%A5%E6%B8%A9%E5%BA%A6.md
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Arrays.fill(res, 0); // 全部初始化为0（最后留在栈中的，即为右边无最大的元素（个数=0））
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int cur = temperatures[i];
            while (!deque.isEmpty() && // ↓ 必须放进while！因为需要反复判断(可能多次弹栈)
                    temperatures[deque.peekFirst()] < cur) {// 栈顶是peek/peekFirst()!!!??
                int prevIdx = deque.pop();
                res[prevIdx] = i - prevIdx;
            }
            deque.push(i); // 存放下标
        }
        return res;
    }
    /*
    栈方法的push ->内部实现是addFirst(); pop ? removeFirst();
    【栈顶在双端队列中就是队列头部，每次都在头部添加/删除】
    【助记：将双端队列Deque看做可双向操作的栈！】
     */
}
