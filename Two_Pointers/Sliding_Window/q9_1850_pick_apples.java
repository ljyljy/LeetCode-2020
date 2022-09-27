package Two_Pointers.Sliding_Window;

public class q9_1850_pick_apples {
    // 法1：O(n^2) 双指针-隔板法
    public int pickApples(int[] nums, int lenA, int lenB) {
        int n = nums.length;
        if (n < lenA + lenB) return -1;
        int maxCnt = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) { // 遍历隔板[1, n)
            int maxB_L = findMax(nums, lenB, 0, i);
            int maxB_R = findMax(nums, lenB, i, n); // 左闭右开
            int maxA_L = findMax(nums, lenA, 0, i);
            int maxA_R = findMax(nums, lenA, i, n); // 左闭右开
            // 【坑】 ↓ WA: 返回值可能为-1，不能直接相加！
//            maxCnt = Math.max(maxCnt, Math.max(
//                            maxA_L + maxB_R, maxB_L + maxA_R));
            if (maxA_L != -1 && maxB_R != -1) {
                maxCnt = Math.max(maxCnt, maxA_L + maxB_R);
            }
            if (maxB_L != -1 && maxA_R != -1) {
                maxCnt = Math.max(maxCnt, maxB_L + maxA_R);
            }
        }
        return maxCnt == Integer.MIN_VALUE? -1: maxCnt;
    }

    // 滑窗O(n): 在nums[start, end)间，找到长度为wnd的窗口，保证窗口内部总和max
    private int findMax(int[] nums, int wnd, int start, int end) {
        if (wnd > end - start) return -1; // 左闭右开
        int n = nums.length;
        int left = start, right = start; // 【坑】不是从0起！
        int curCnt = 0, maxCnt = 0;

        while (right < end) {
            curCnt += nums[right++];

            while (right - left >= wnd) {
                maxCnt = Math.max(maxCnt, curCnt);
                curCnt -= nums[left++];
            }
        }
        return maxCnt;
    }

    // todo  法2：前缀和

    public static void main(String[] args) {
        int[] nums = {4,5,4,5,6,4,7,10,9,1};
        int lenA = 1, lenB = 4;
        q9_1850_pick_apples sol = new q9_1850_pick_apples();
        int maxCnt = sol.pickApples(nums, lenA, lenB);
        System.out.println(maxCnt);
    }
}
