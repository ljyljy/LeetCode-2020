package String;

import java.util.*;

public class HJ19_EasyErrorLog {
    public static void main(String[] args) {
        /**
         list��ż����������ļ���+���룩��list�������Ǵ�����м����������˼���
         �½�map�������˼�������map��map��ż���ͬlist�ļ�����ֵ�����ִ�������ÿ���½���������¼�ֵ��������list��
         */
        Map<String, Integer> map = new HashMap<>(); // <fname+"_"+rowIdx, cnt>
        List<String> keys = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] strs = line.split(" "); // path, rowIdx
            // �к�
            int rowIdx = Integer.valueOf(strs[1]);
            // ��ȡ�ļ���
            String[] dirs = strs[0].split("\\\\"); // ?�ָ�·��'\'��win:'\\'������Ҫ4��'\'?'
            String fname = dirs[dirs.length-1];
            int len = fname.length();
            if (len > 16) fname = fname.substring(len-16);

            String key = fname + " " + rowIdx;
            if (keys.contains(key)) {
                map.put(key, map.getOrDefault(key, 0)+1);
            } else {
                map.put(key, 1);
                keys.add(key); // todo: ��Ϊѭ����־������д��list.set(idx%8, key);��
            }
        } // while������ֹ��?

        int n_keys = keys.size();
        int start = n_keys-8>=0? n_keys-8: 0;
        for(int j = start; j < n_keys;j++){
            String curKey = keys.get(j);
            System.out.println(curKey+" "+map.get(curKey));
        }

    }
}