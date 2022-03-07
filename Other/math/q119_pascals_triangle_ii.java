package Other.math;

import java.util.ArrayList;
import java.util.List;

public class q119_pascals_triangle_ii {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>(){{add(1);}};
        for (int i = 0; i < rowIndex; i++) {
            List<Integer> tmp = new ArrayList<>(){{add(1);}};
            for (int j = 0; j+1 < res.size(); j++) {
                tmp.add(res.get(j) + res.get(j+1));
            }
            tmp.add(1);
            res = tmp;
        }
        return res;
    }

}
