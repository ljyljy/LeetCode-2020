package Two_Pointers;

// 求子串数量，类比：q9_1870, Q9_1375
public class q9_1870_count_substr_with_all_zeros {
    public int stringCount(String str) {
        // 隐式双指针，统计cnt
        int cnt = 0, sum = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '0') {
                cnt++;
            } else {
                sum += countSubstr_allZeros(cnt);
                cnt = 0;
            }
        }
        if (cnt > 0) {
            sum += countSubstr_allZeros(cnt);
        }
        return sum;
    }

    // 解题思路：计算出每段连续的0的个数，然后根据等差数列求和求得子串个数。
    //（如：’000’中, 0个数为cnt=3，共3个‘0’，2个‘00’，1个‘000’，子串个数=3+2+1=等差数列(1+3)*3/2=6）
    private int countSubstr_allZeros(int cnt) {
        return (1 + cnt) * cnt / 2;
    }
}
