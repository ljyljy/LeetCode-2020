package Other.math;

public class q168_excel_sheet_column_title {
    // 法1：减一via (--num)
    public String convertToTitle1(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber != 0) {
            int mod = (--columnNumber) % 26; // --num，而非计算时'A'+mod-1
            char alpha = (char)('A'+mod);
            sb.append(alpha);
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }

    // 法2：减一via (--mod & 三目 & num--)
    // 当mod==0时，无法得到“Z”，此时用三目运算符解决，并且将（columnNumber/26）--
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber != 0) {
            int mod = columnNumber % 26;
            char alpha = mod != 0? (char)('A'+mod-1) : 'Z'; // ❤
            sb.append(alpha);
            columnNumber /= 26;
            if (mod == 0) columnNumber--;// ❤
        }
        return sb.reverse().toString();
    }

}
