package String;

import java.util.*;

public class HJ13_reverseSentence {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> strs = new LinkedList<>();
//         while (sc.hasNext()) { // ֻ��һ�о��ӣ�����ѭ��
        String str = sc.nextLine();
        String[] words = str.split(" ");
        int n = words.length;
//        // ��1: ����Collections.reverse
//        List<String> wordsList = Arrays.asList(words);
//        Collections.reverse(wordsList);
//        for (String s: wordsList) {
//            System.out.print(s+" ");
//        }
        // ��2������ �������
        for (int i = n-1; i >= 0; i--) {
            System.out.print(words[i] + " ");
        }

//         }
    }
}
