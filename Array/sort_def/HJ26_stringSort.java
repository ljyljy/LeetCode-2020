package Array.sort_def;

import java.util.*;

public class HJ26_stringSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            char[] ss = s.toCharArray();
            List<Character> letters = new ArrayList<>();
            for (char ch: ss) {
                if (Character.isLetter(ch)) { // (ch+"").matches("[\\w]") ����ĸ
                    letters.add(ch);
                }
            }
            // �����Զ�������
            Collections.sort(letters,
                    (o1, o2)->(Character.toLowerCase(o1)-Character.toLowerCase(o2)));
//             Collections.sort(list,new Comparator<Character>(){
//                 public int compare(Character o1,Character o2){
//                     return Character.toLowerCase(o1)-Character.toLowerCase(o2);
//                 }
//             });

            int idx = 0; // ԭ���޸�ss���ַ��Ĳ��֣�˳�򸲸�д��letters
            for (int i = 0; i < ss.length; i++) {
                if (Character.isLetter(ss[i])) {
                    ss[i] = letters.get(idx++);
                } // �����ַ�������
                System.out.print(ss[i]);
            }
            System.out.println();
        }
    }
}

