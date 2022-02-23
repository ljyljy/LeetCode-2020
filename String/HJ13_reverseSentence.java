package String;

import java.util.*;

public class HJ13_reverseSentence {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> strs = new LinkedList<>();
//         while (sc.hasNext()) { // 只有一行句子，无需循环
        String str = sc.nextLine();
        String[] words = str.split(" ");
        int n = words.length;
//        // 法1: 利用Collections.reverse
//        List<String> wordsList = Arrays.asList(words);
//        Collections.reverse(wordsList);
//        for (String s: wordsList) {
//            System.out.print(s+" ");
//        }
        // 法2：常规 倒序遍历
        for (int i = n-1; i >= 0; i--) {
            System.out.print(words[i] + " ");
        }

//         }
    }
}
