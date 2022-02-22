package Other.Greedy;

public class q55_jump_game {
    // ��1��̰��
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int farthest = nums[0];
        for (int i = 0; i < n; i++) {
            if (i <= farthest) { // ?��ǰ�᣺����ɴ
                farthest = Math.max(farthest, i + nums[i]);
                if (farthest >= n-1) {
                    return true;
                }
            }
        }
        return false;
    }
}
