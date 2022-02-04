package Two_Pointers;//Given n non-negative integers a1, a2, ..., an , where each represents a point
//at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of
// line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis for
//ms a container, such that the container contains the most water.
//
// Note: You may not slant the container and n is at least 2.
//
//
//
//
//
// The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In thi
//s case, the max area of water (blue section) the container can contain is 49.
//
//
//
// Example:
//
//
//Input: [1,8,6,2,5,4,8,3,7]
//Output: 49 Related Topics 数组 双指针

// 一维数组的坐标变换！ for i,j

// 1.枚举 O(n^2)：左x，右j，(j-x)*min(height[i], height[j])
// 2. O(n)：左右边界 同时向中间收敛 左右夹逼



public class q11_container_with_most_water {
    // 2. O(n)：左右边界 同时向中间收敛 【左右夹逼】
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0,  j = height.length-1; i < j; ) {
            // 挪矮的lowH，让高的highH保持不动，这样求得面积可以尽量大
            int minH = height[i] < height[j]? height[i++] : height[j--];
            int area = (j - i + 1) * minH;
            max = Math.max(area, max);
        }
        return max;
    }


    // 1.枚举--TLE
    public int maxArea01(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; ++i) {
            for (int j = i + 1; j < height.length; ++j) {
                int area = (j - i) * Math.min(height[i], height[j]);
                max = Math.max(area, max);
            }
        }
        return max;
    }
}
