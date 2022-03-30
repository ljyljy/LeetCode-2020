package String;

import java.util.*;

//�ַ���ƥ�䣺
// 1.�ȿ�ƥ�䴮��һ���ַ����������ַ���һ���ַ�ʱ����ƥ����ַ��ǵ��ַ�����˫�ַ������ַ����ԣ�˫�ַ�����
//2.ƥ�䴮�������ַ�ʱ����ƥ���˼�����ֻ��ƥ��һ���Ļ�����ɹ�������һ�֣��㲻�ɹ�
public class HJ66_restore_config_str_match {
    private static final String noMatch = "noMatch";
    private static final Map<String, String> map = new HashMap<String, String>(){{
        put("reset","reset what");
        put("reset board","board fault");
        put("board add","where to add");
        put("reboot backplane","impossible");
        put("backplane abort","install first");
        put("board delete","no board at all");
        put(noMatch,"unknown command");
    }};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] cmd = sc.nextLine().split("\\s");
            int cnt = cmd.length;
            String resKey = noMatch;
            int confuseCnt = 0;

            Set<String[]> prefixSet = new HashSet<>(); // [[reset], [reset, board], ...]
            for (String key: map.keySet()) {
                prefixSet.add(key.split("\\s"));
            }

            for (String[] prefix: prefixSet) {
                int cnt_p = prefix.length;
                if (cnt_p != cnt) continue;
                if (cnt == 1) { // ֻ��һ������
                    if (prefix[0].startsWith(cmd[0])) {
                        resKey = prefix[0];
                    }
                }
                if (cnt == 2) {
                    if (prefix[0].startsWith(cmd[0]) && prefix[1].startsWith(cmd[1])) {
                        resKey = prefix[0] + " " + prefix[1];
                        confuseCnt++;
                    }
                }
            }
            System.out.println(confuseCnt > 1? "unknown command": map.get(resKey));

            /**
             * �ؼ��㣺
             * 1.���ж������ַ����ĳ��ȣ�����Ϊ1ʱ��ֻƥ��ؼ���Ϊ1���ַ�������������rֻ��ƥ��reset��ͬ����Ϊ2��ֻ��ƥ��ؼ��ʳ���Ϊ2���ַ�������������r b��ֻ��ƥ��reset board��reboot backplane��
             * 2.���ƥ����Ľ����Ψһ��Ҳ����ƥ�䵽��������ƥ�䲻�ɹ�����������r b��ƥ��reset board��reboot backplane���ü��������Խ������ʱ��� unknown command
             */
        }
    }
}
