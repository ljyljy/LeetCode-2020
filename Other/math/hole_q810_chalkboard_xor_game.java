package Other.math;

public class hole_q810_chalkboard_xor_game {
    public boolean xorGame(int[] nums) {
        int n = nums.length, x = 0;
        if(n % 2 == 0) return true;
        for(int num : nums)
            x ^= num;
        return x == 0;
    }
}
