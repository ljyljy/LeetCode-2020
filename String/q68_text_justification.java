package String;

import java.util.*;

public class q68_text_justification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; ) { // ?技巧：i++放入循环体内控制。
            List<String> curRow = new ArrayList<>(); // 当前行的所有 word
            curRow.add(words[i]);
            int curLen = words[i++].length();
            // 多个|cur+" "+nextWord|<=maxWidth(则加入当前行)
            while (i < n && curLen+1+words[i].length() <= maxWidth) {
                curLen += 1 + words[i].length(); // 单词间先暂时间隔1个空格(至少)
                curRow.add(words[i++]);
            }
            int wordCnt = curRow.size();

            // 如果当前为最后一行(遍历到最后一个单词)，特殊处理为左对齐
            if (i == n) { // ?if条件！易错！
                StringBuilder sb = new StringBuilder(curRow.get(0));
                for(int j = 1; j < wordCnt; j++) {
                    sb.append(" " + curRow.get(j));
                }
                res.add(fillBlanks(sb.toString(), maxWidth));
                break;
            }

            // 如果当前行只有一个 word，特殊处理为左对齐
            if (wordCnt == 1) {
                String word = curRow.get(0);
                // while(word.length() < maxWidth) word += " ";
                res.add(fillBlanks(word, maxWidth));
                continue;
            }

            /**
             * 其余为一般情况
             * totalWordLen : 当前行单词总长度;
             * spaceWidth : 当前行空格总长度;
             * spaceItem : 往下取整后的单位空格长度
             */
            // int totalWordLen = 0;
            // for (int j = 0; j < curRowWordCnt; j++) {
            //     totalWordLen += curRow.get(j).length();
            // }
            int totalWordLen = curLen - (wordCnt-1); // 当前总长度-间隙数
            int spaceWidth = maxWidth - totalWordLen;
            int spaceItemLen = spaceWidth / (wordCnt-1);
            int fillCnt = spaceWidth % (wordCnt-1); // 空格无法均分，靠左间隙多补充一个" "，直到余数=0
            String spaceItem = " ".repeat(spaceItemLen);
            StringBuilder sb = new StringBuilder();
            int fillIdx = 0;
            for (int j = 0; j < wordCnt; j++) {
                String word = curRow.get(j);
                sb.append(word);
                if (j == wordCnt-1) break; // 两端对齐，末尾单词end
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
