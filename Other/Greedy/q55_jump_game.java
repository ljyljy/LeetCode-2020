package Other.Greedy;

public class q55_jump_game {
    // 法1：贪心
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int farthest = nums[0];
        for (int i = 0; i < n; i++) {
            if (i <= farthest) { // ?大前提：必须可达！
                farthest = Math.max(farthest, i + nums[i]);
                if (farthest >= n-1) {
                    return true;
                }
            }
        }
        return false;
    }
}
