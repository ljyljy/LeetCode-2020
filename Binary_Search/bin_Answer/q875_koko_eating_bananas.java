package Binary_Search.bin_Answer;

public class q875_koko_eating_bananas {
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        // int sum = Arrays.stream(piles).sum(); // 坑：非end！
        int start = 0, end = (int)1e9;
        while (start < end) { // [L, m], [m+1, R]
            int mid = start + end >> 1;
            if (check(piles, mid) <= h) {
                // 若吃完.小时数<=警察回来，则可以吃慢点
                end = mid;
            } else start = mid + 1;
        }
        return start;
    }

    private int check(int[] piles, int tryV) {
        int tryHours = 0;
        for (int num: piles) {
            tryHours += Math.ceil(num*1.0 / tryV);
        }
        return tryHours;
    }
}
