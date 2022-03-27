package String;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HJ_msg {
    private static class Message {
//        int tagNum;
        int tagLen;
        int offset;
        String msg;
        public Message(){}
        public Message(int tagLen, int offset, String msg) {
            this.offset = offset;
            this.tagLen = tagLen;
            this.msg = msg;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Integer, Message> map = new HashMap<>();
        while (sc.hasNext()) {
//
            String msg = sc.nextLine();
            int n = sc.nextInt();
            int[] tagNums = new int[n];
//            String msg = "0F04ABABABAB1001FF";
//            int n = 2;
            int left = 0;
            for (int i = 0; i < n; i++) {
                tagNums[i] = sc.nextInt(); // 15; // sc.nextInt();

                String tag = msg.substring(left, left+2);
                int tag_len = Integer.valueOf(msg.substring(left+2, left+4));
                int start_i = left+4, end_i = left+4+tag_len*2;
                String tag_msg = msg.substring(start_i, end_i);

                int tagNum = 0;
                for (char ch: tag.toCharArray()) {
                    if (Character.isDigit(ch)) {
                        tagNum = tagNum * 10 + ch - '0';
                    } else {
                        tagNum = tagNum * 10 + ch - 'A' + 10;
                    }
                }
                map.put(tagNum, new Message(tag_len, start_i+(start_i-left)/2, tag_msg));
//                System.out.println(tagNum);
//                if (tagNum == tagNum_i) {
//                    System.out.println(tag_len + " " + (start_i-left)/2);
//                } else {
//                    System.out.println("0 0");
//                }
                left = end_i;
            }

            for (int tagNum: tagNums) {
                if (map.containsKey(tagNum)) {
                    Message msg_i = map.get(tagNum);
                    System.out.println(msg_i.tagLen + " " + msg_i.offset);
                }
            }

        }
    }
}
