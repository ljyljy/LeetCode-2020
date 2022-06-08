package Binary_Search;

public class q275_hIndex {
    public int hIndex(int[] citations) {
        int n = citations.length;
        if (citations[n-1] == 0) return 0; // 若全为0
        int start = 0, end = n-1;
        // [L, mid(ret)] & [mid+1, R]
        while (start < end) {
            int mid = start + end >> 1;
            // ∵升序 ∴有(n-1)-mid+1 = n-mid篇至少被引用citations[mid]次
            if (citations[mid] >= n - mid) { // 引用次数↓: 左区间
                end = mid; // 相等时，应继续往左边找(确保hIdx=n-mid最大)
                // 因此mid与左边界必须在一个区间, 即[L, mid] ↑
            } else {
                start = mid + 1;
            }
        }
        return n - start; // h指数=n-mid
    }

}
