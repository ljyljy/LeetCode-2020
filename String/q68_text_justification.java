package String;

import java.util.*;

public class q68_text_justification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; ) { // ?���ɣ�i++����ѭ�����ڿ��ơ�
            List<String> curRow = new ArrayList<>(); // ��ǰ�е����� word
            curRow.add(words[i]);
            int curLen = words[i++].length();
            // ���|cur+" "+nextWord|<=maxWidth(����뵱ǰ��)
            while (i < n && curLen+1+words[i].length() <= maxWidth) {
                curLen += 1 + words[i].length(); // ���ʼ�����ʱ���1���ո�(����)
                curRow.add(words[i++]);
            }
            int wordCnt = curRow.size();

            // �����ǰΪ���һ��(���������һ������)�����⴦��Ϊ�����
            if (i == n) { // ?if�������״�
                StringBuilder sb = new StringBuilder(curRow.get(0));
                for(int j = 1; j < wordCnt; j++) {
                    sb.append(" " + curRow.get(j));
                }
                res.add(fillBlanks(sb.toString(), maxWidth));
                break;
            }

            // �����ǰ��ֻ��һ�� word�����⴦��Ϊ�����
            if (wordCnt == 1) {
                String word = curRow.get(0);
                // while(word.length() < maxWidth) word += " ";
                res.add(fillBlanks(word, maxWidth));
                continue;
            }

            /**
             * ����Ϊһ�����
             * totalWordLen : ��ǰ�е����ܳ���;
             * spaceWidth : ��ǰ�пո��ܳ���;
             * spaceItem : ����ȡ����ĵ�λ�ո񳤶�
             */
            // int totalWordLen = 0;
            // for (int j = 0; j < curRowWordCnt; j++) {
            //     totalWordLen += curRow.get(j).length();
            // }
            int totalWordLen = curLen - (wordCnt-1); // ��ǰ�ܳ���-��϶��
            int spaceWidth = maxWidth - totalWordLen;
            int spaceItemLen = spaceWidth / (wordCnt-1);
            int fillCnt = spaceWidth % (wordCnt-1); // �ո��޷����֣������϶�ಹ��һ��" "��ֱ������=0
            String spaceItem = " ".repeat(spaceItemLen);
            StringBuilder sb = new StringBuilder();
            int fillIdx = 0;
            for (int j = 0; j < wordCnt; j++) {
                String word = curRow.get(j);
                sb.append(word);
                if (j == wordCnt-1) break; // ���˶��룬ĩβ����end
                sb.append(spaceItem);
                if (fillIdx++ < fillCnt) sb.append(" ");
            }
            res.add(sb.toString());
        }
        return res;
    }

    private String fillBlanks(String str, int targetLen) {
        while (str.length() < targetLen) {
            str += " ";
        }
        return str;
    }
}
