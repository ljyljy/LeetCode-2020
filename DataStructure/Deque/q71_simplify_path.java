package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q71_simplify_path {
    public String simplifyPath(String path) {
        String[] dirs = path.split("/"); // "//xx"会分割出2个空字符串""与xx;
        // for (String dir: dirs) System.out.print(dir + ",");
        Deque<String> deque = new ArrayDeque<>();
        for (String dir : dirs) {
            if (dir.equals("..")) { // 回退上级目录(pop)
                if (!deque.isEmpty()) deque.pop();
            } else {
                if (dir.equals("") || dir.equals("."))
                    continue; // 跳过"//"分割的""(无效)和本级目录标记(冗余)
                deque.push(dir); // 常规dir
            }
        }
        StringBuilder res = new StringBuilder();
        if (deque.isEmpty()) {
            res.append("/");
        } else {
            while (!deque.isEmpty()) {
                res.append("/");
                res.append(deque.removeLast()); // 栈底/队头开始
            }
        }
        return res.toString();
    }
}
