package Array;

import java.util.ArrayList;
import java.util.List;

public class q6_zigzag_conversion {
    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++)
            rows.add(new StringBuilder()); // numRows个sb
        int row_i = 0, flag = -1; // 遍历行:0,1,2,3,2,1,...

        for (char c: s.toCharArray()) { // 遍历s
            rows.get(row_i).append(c);
            if (row_i == 0|| row_i == numRows-1)
                flag = -flag; // ±1交替（row=边界时）
            row_i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb: rows) res.append(sb);
        return res.toString();
    }
}
