package DataStructure.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class q71_simplify_path {
    public String simplifyPath(String path) {
        // "//xx"会分割出2个空字符串""与xx; ?forEach也会遍历到！
        String[] dirs = path.split("/");
        // for (String dir: dirs) System.out.print(dir + ",");
        Deque<String> deque = new ArrayDeque<>();
        for (String dir : dirs) {
            if (dir.equals("..")) { // 回退上级目录(pop)
                if (!deque.isEmpty()) deque.pop();
            } else {
                if (dir.equals("") || dir.equals("."))
                    continue; // 跳过"//"分割的""(?无效,勿漏)和本级目录标记(冗余)
                deque.push(dir); // 常规dir
            }
        }
        if (deque.isEmpty()) return "/";
        // 栈是倒序输出！path需要从队头（栈底）往后（栈顶）输出！
        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()) {
            sb.insert(0, "/" + deque.pop());
        }
        return sb.toString();
        // 写法2
//        StringBuilder res = new StringBuilder();
//        if (deque.isEmpty()) {
//            res.append("/");
//        } else {
//            while (!deque.isEmpty()) {
//                res.append("/");
//                res.append(deque.removeLast()); // 栈底/队头开始
//            }
//        }
//        return res.toString();
    }
}
