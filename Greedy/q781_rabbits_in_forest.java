package Greedy;

import java.util.Arrays;

public class q781_rabbits_in_forest {
    // 法1：贪心: 模拟解法
    // 先升序answers，遍历cnt时，将其对答案的影响应用到ans中（ans += cnt + 1），并将后面的 cnt 个 cnt 进行忽略。
    public int numRabbits(int[] answers) {
        Arrays.sort(answers); // 升序排列
        int n = answers.length;
        int total = 0;
        for (int i = 0; i < n; i++) {
            int cnt = answers[i];
            total += cnt + 1; // 回答者自己+其他兔
            // 跳过其他(cnt)个ans[i], 将下标i后移cnt次，假设是同颜色的兔子(已更新到total)
            int k = cnt;
            while (k-- > 0 && i+1 < n && answers[i] == answers[i+1]) i++;
        }
        return total;
    }
}
