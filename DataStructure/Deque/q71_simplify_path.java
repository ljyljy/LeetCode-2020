package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q71_simplify_path {
    public String simplifyPath(String path) {
        String[] dirs = path.split("/"); // "//xx"��ָ��2�����ַ���""��xx;
        // for (String dir: dirs) System.out.print(dir + ",");
        Deque<String> deque = new ArrayDeque<>();
        for (String dir : dirs) {
            if (dir.equals("..")) { // �����ϼ�Ŀ¼(pop)
                if (!deque.isEmpty()) deque.pop();
            } else {
                if (dir.equals("") || dir.equals("."))
                    continue; // ����"//"�ָ��""(��Ч)�ͱ���Ŀ¼���(����)
                deque.push(dir); // ����dir
            }
        }
        StringBuilder res = new StringBuilder();
        if (deque.isEmpty()) {
            res.append("/");
        } else {
            while (!deque.isEmpty()) {
                res.append("/");
                res.append(deque.removeLast()); // ջ��/��ͷ��ʼ
            }
        }
        return res.toString();
    }
}
