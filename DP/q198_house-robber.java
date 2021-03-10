//// 主函数
//public int rob(int[] nums) {
//    return dp(nums, 0);
//}
//// 返回 nums[start..] 能抢到的最大值
//private int dp(int[] nums, int start) {
//    if (start >= nums.length) {
//        return 0;
//    }
//
//    int res = Math.max(
//            // 不抢，去下家
//            dp(nums, start + 1),
//            // 抢，去下下家
//            nums[start] + dp(nums, start + 2)
//        );
//    return res;
//}