package String;

import java.util.*;

public class HJ19_EasyErrorLog {
    public static void main(String[] args) {
        /**
         list存放键（键包括文件名+行码），list的作用是存放所有键，不包括此键，
         新建map，包括此键，更新map。map存放键（同list的键），值（出现次数），每次新建或迭代更新键值，并放入list。
         */
        Map<String, Integer> map = new HashMap<>(); // <fname+"_"+rowIdx, cnt>
        List<String> keys = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] strs = line.split(" "); // path, rowIdx
            // 行号
            int rowIdx = Integer.valueOf(strs[1]);
            // 获取文件名
            String[] dirs = strs[0].split("\\\\"); // ?分割路径'\'（win:'\\'），需要4个'\'?'
            String fname = dirs[dirs.length-1];
            int len = fname.length();
            if (len > 16) fname = fname.substring(len-16);

            String key = fname + " " + rowIdx;
            if (keys.contains(key)) {
                map.put(key, map.getOrDefault(key, 0)+1);
            } else {
                map.put(key, 1);
                keys.add(key); // todo: 改为循环日志（覆盖写：list.set(idx%8, key);）
            }
        } // while到此终止！?

        int n_keys = keys.size();
        int start = n_keys-8>=0? n_keys-8: 0;
        for(int j = start; j < n_keys;j++){
            String curKey = keys.get(j);
            System.out.println(curKey+" "+map.get(curKey));
        }

    }
}