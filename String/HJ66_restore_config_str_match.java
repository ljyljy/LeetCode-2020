package String;

import java.util.*;

//字符串匹配：
// 1.先看匹配串是一个字符还是两个字符，一个字符时，看匹配的字符是单字符还是双字符，单字符可以，双字符不行
//2.匹配串是两个字符时，看匹配了几个，只能匹配一个的话，算成功。大于一种，算不成功
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
                if (cnt == 1) { // 只有一个单词
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
             * 关键点：
             * 1.先判断输入字符串的长度，长度为1时，只匹配关键词为1的字符串，比如输入r只能匹配reset，同理长度为2的只能匹配关键词长度为2的字符串，比如输入r b，只能匹配reset board和reboot backplane。
             * 2.如果匹配出的结果不唯一，也就是匹配到两个，则匹配不成功。比如输入r b，匹配reset board和reboot backplane，用计数器可以解决，此时输出 unknown command
             */
        }
    }
}
