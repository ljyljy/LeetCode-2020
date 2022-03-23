package Bit;

public class q171_excel_sheet_column_number {
    public int titleToNumber(String columnTitle) {
        int n = columnTitle.length();
        int ans = 0;
        for (int i = 0; i < n; i++) { // ÕýÐò±éÀú
            int mod = ((columnTitle.charAt(i) - 'A') + 1) % 27;
            ans = ans * 26 + mod;
        }
        return ans;
    }
}
