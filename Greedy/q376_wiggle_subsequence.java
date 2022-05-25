package Greedy;

public class q376_wiggle_subsequence {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;
        int preDiff = 0, curDiff = 0, cnt = 1; //  边界处理！
        for (int i = 0; i+1 < n; i++) {
            curDiff = nums[i+1] - nums[i];
            //  curDiff不可为0（相邻元素不可相等）！
            if (preDiff >= 0 && curDiff < 0
                    || preDiff <= 0 && curDiff > 0){
                cnt++;
                preDiff = curDiff;
            }
        }
        return cnt;
    }
}
