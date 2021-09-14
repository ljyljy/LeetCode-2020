package Sort.Quick_Sort.Quick_Select;

public class q9_49_sort_letters_by_case {
    private int n;
    public void sortLetters(char[] chars) {
        n = chars.length;
        int cnt_small = partition(chars, 0, n-1);
        System.out.println("cnt_small = " + cnt_small);
    }

    private int partition(char[] chars, int start, int end) {
        int i = start, j = end;
        while (i <= j) {
            while (i <= j && isSmall(chars[i])) i++; // 小写在前
            while (i <= j && !isSmall(chars[j])) j--;// 大写在后
            if (i <= j) {
                swap(chars, i, j);
                i++; j--;
            }
        }// 退出后, i > j(idx_L)
        return i; // 或j+1(ret右侧，即 cnt_L = idx_L + 1)
    }

    private boolean isSmall(char ch) {
        return 'a' <= ch && ch <= 'z';
    }

    private void swap(char[] A, int left, int right) {
        char tmp = A[left];
        A[left] = A[right];
        A[right] = tmp;
    }

    public static void main(String[] args) {
        char[] chars = {'a', 'B', 'C', 'd', 'E', 'f'};
        q9_49_sort_letters_by_case sol = new q9_49_sort_letters_by_case();
        sol.sortLetters(chars);
        for (char ch: chars)
            System.out.print(ch + " ");
    }
}
