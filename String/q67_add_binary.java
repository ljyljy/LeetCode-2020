package String;

public class q67_add_binary {
    // 类比q415,q66，q2，q43
    public String addBinary(String a, String b) {
        int n1 = a.length(), n2 = b.length();
        StringBuilder sb = new StringBuilder();
        int carry = 0; // 从后往前，末位相加
        for (int i = n1-1, j = n2-1; (i >= 0 || j >= 0 || carry != 0); i--, j--) {
            int x = i >= 0? a.charAt(i) - '0': 0;
            int y = j >= 0? b.charAt(j) - '0': 0;
            int sum = (x + y + carry) % 2;
            carry = (x + y + carry) / 2;
            sb.append(sum); // 或头插 sb.insert(0, sum)
        }
        return sb.reverse().toString();
    }
}
