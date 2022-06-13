package Binary_Search.bin_Answer;

import java.util.Arrays;

public class q9_437_copy_books {
    private int[] pages;
    private int k, n_book;
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0 || k <= 0) return 0;
        this.pages = pages; this.k = k; this.n_book = pages.length;

        int left = Arrays.stream(pages).max().getAsInt();
        int right = Arrays.stream(pages).sum();
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            // 说明单机时间mid还可以减小(左区间)，从而增加工人k
            if (check(mid) <= k) { // 法2：[L, mid] & [mid+1, R](确保mid与L在一个区间)
                right = mid; // ❤优先左区间(∵求至少)
            } else left = mid;
        }
        // ∵至少 ∴优先左区间
        if (check(left) <= k) return left;
        return right;

        // 法2
//        while (left < right) { // [L, mid] & [mid+1, R](确保mid与L在一个区间)
//            int mid = left + right >> 1; // left + (right - left) >> 1;
//            if (check(mid) <= k) right = mid;
//            else left = mid + 1;
//        }
//        if (check(left) <= k) return left;
//        return -1;
    }

    private int check(int tryTimeLimit){
        int tryK = 1, total_time = 0;
        for (int page: pages) {
            if (page + total_time > tryTimeLimit) {
                tryK++; // 工人数++
                total_time = 0; // 当前工人的工作时间，初始化为 0
            } // ❤置零后，↓ 还需加上当前page，算作当前工人的工作
            total_time += page;
        }
        return tryK;
    }

    private int check2(int tryTimeLimit){
        int tryCnt = 1, total_time = 0;
        for (int i = 0; i < n_book; i++) {
            if (total_time + pages[i] <= tryTimeLimit) {
                total_time += pages[i];
            } else {
                tryCnt++; // 工人数++
                total_time = 0 + pages[i];
                // ❤置零后，↑ 还需加上当前pages[i]，算作当前工人的工作
            }
        }
        return tryCnt; // 在tryTimeLimit下，至少所需工人数
    }


}
