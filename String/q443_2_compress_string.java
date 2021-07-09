package String;

public class q443_2_compress_string {
    public int compress(char[] chars) {
        int n = chars.length;
        int alpha = 0, write = 0, cnt = 0;
        for (int read = 0; read < n; read++) {
            // 后一个判断前提：(read+1<n，即i < n-1)↓
            if (read == n-1 || chars[read] != chars[read+1]) {
                chars[write++] = chars[alpha];

                String cntStr = (read - alpha + 1) + "";
                // 若字母不重复(alpha == read时)，则无需将cnt写入chars (如: ["a"], ["a","b"])
                if (!cntStr.equals("1")) {
                    for (char ch : cntStr.toCharArray()) {
                        chars[write++] = ch;
                    }
                }
                alpha = read + 1; // 下一个字母idx ∵if(不再有连续字母)
            }
        }
        return write;
    }
}
