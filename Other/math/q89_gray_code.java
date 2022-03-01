package Other.math;

import java.util.ArrayList;
import java.util.List;

public class q89_gray_code {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>(){{add(0);}};
        int head = 1; // 每轮<<1(乘2)，加到res每个元素的最首。
        // 模拟：i=1~n
        // 保序：res每轮逆序遍历（j--）
        for (int i = 0; i < n; i++) {
            for (int j = res.size()-1; j >= 0; j--) {
                res.add(head + res.get(j));
            }
            head <<= 1;
        }
        return res;
    }
}
