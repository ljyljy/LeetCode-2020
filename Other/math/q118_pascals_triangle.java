package Other.math;

import java.util.ArrayList;
import java.util.List;

public class q118_pascals_triangle {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> generate(int numRows) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        res.add(row);
        for (int i = 0; i < numRows-1; i++) {
            List<Integer> last = res.get(i);
            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            for (int j = 0; j+1 < last.size(); j++) {
                cur.add(last.get(j) + last.get(j+1));
            }
            cur.add(1);
            res.add(cur);
        }
        return res;
    }
}
