package String;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.regex.Pattern;

public class q468_validate_ip_address {
    /**
     * ��1�����η���Ч����ߵķ���֮һ��
     *
     * ��2��������ʽ���÷������ܲ�̫�á�
     *
     * ��3��ʹ�÷��η������õ� try/catch�����ַ���ת������������
     *      ʹ�� try/catch ����һ�ֺõķ�ʽ����Ϊ try ���еĴ��벻�ᱻ�������Ż���������ò�Ҫ��������ʹ�á�
     */
    private final String ERR = "Neither";
    private final String IPv4 = "IPv4";
    private final String IPv6 = "IPv6";
    public String validIPAddress(String IP) {
        if (IP.contains(".")) {
            String isIPv4 = checkIPv4(IP);
            return IPv4.equals(isIPv4)? IPv4: ERR;
        } else if (IP.contains(":")) {
            String isIPv6 = checkIPv6(IP);
            return IPv6.equals(isIPv6)? IPv6: ERR;
        }
        return ERR;
    }

    private String checkIPv6(String IP) {
        String[] nums = IP.split("\\:", -1);
        if (nums.length != 8) return ERR;
        for (String num: nums) {
            if (num.isEmpty() || num.length() > 4) return ERR;
            for (char ch: num.toCharArray()) {
                if (! (ch + "").matches("[0-9a-fA-F]")) {
                    return ERR;
                }
            }
        }
        return IPv6;
    }

    private String checkIPv4(String IP) {
        String[] nums = IP.split("\\.", -1);
        if (nums.length != 4) return ERR;
        for (String num: nums) {
            // 1. ����
            if (num.isEmpty() || num.length() > 3) return ERR;
            // 2. ǰ��0
            if (num.charAt(0) == '0' && num.length() > 1) return ERR;
            // 3. �����ַ�
            for (char x: num.toCharArray()) {
                if (!Character.isDigit(x)) {
                    return ERR;
                }
            }
            // 4. [0-255]
            int val = Integer.valueOf(num);
            if (!(0 <= val && val <= 255)) return ERR;
        }
        return IPv4;
    }

    public String validIPAddress_2(String IP) {
        // x1.x2.x3.  x4
        String chunkV4 = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
        Pattern patV4 = Pattern.compile("^(" + chunkV4 + "\\.){3}" + chunkV4 + "$"); // ^xxx$: ƥ�俪ͷ~ĩβ
        // x1:x2:...:x7:  x8
        String chunkV6 = "[0-9a-fA-F]{1,4}";
        Pattern patV6 = Pattern.compile("^(" + chunkV6 + "\\:){7}" + chunkV6 + "$");

        if (IP.contains(".")) {
            return (patV4.matcher(IP).matches())? IPv4: ERR;
        } else if (IP.contains(":")) {
            return (patV6.matcher(IP).matches())? IPv6: ERR;
        }
        return ERR;
    }


    public String validIPAddress_3(String IP) {
        try {
            return (InetAddress.getByName(IP) instanceof Inet6Address) ? "IPv6": "IPv4";
        } catch(Exception e) {}
        return "Neither";
    }
}
