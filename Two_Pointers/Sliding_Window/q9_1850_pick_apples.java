package Two_Pointers.Sliding_Window;

public class q9_1850_pick_apples {
    // ��1��O(n^2) ˫ָ��-���巨
    public int pickApples(int[] nums, int lenA, int lenB) {
        int n = nums.length;
        if (n < lenA + lenB) return -1;
        int maxCnt = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) { // ��������[1, n)
            int maxB_L = findMax(nums, lenB, 0, i);
            int maxB_R = findMax(nums, lenB, i, n); // ����ҿ�
            int maxA_L = findMax(nums, lenA, 0, i);
            int maxA_R = findMax(nums, lenA, i, n); // ����ҿ�
            // ���ӡ� �� WA: ����ֵ����Ϊ-1������ֱ����ӣ�
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

    // ����O(n): ��nums[start, end)�䣬�ҵ�����Ϊwnd�Ĵ��ڣ���֤�����ڲ��ܺ�max
    private int findMax(int[] nums, int wnd, int start, int end) {
        if (wnd > end - start) return -1; // ����ҿ�
        int n = nums.length;
        int left = start, right = start; // ���ӡ����Ǵ�0��
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

    // todo  ��2��ǰ׺��

    public static void main(String[] args) {
        int[] nums = {4,5,4,5,6,4,7,10,9,1};
        int lenA = 1, lenB = 4;
        q9_1850_pick_apples sol = new q9_1850_pick_apples();
        int maxCnt = sol.pickApples(nums, lenA, lenB);
        System.out.println(maxCnt);
    }
}
