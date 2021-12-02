package Recursion.partition_based;

import java.util.*;

public class q93_restore_ip_addresses {
    private List<String> res = new ArrayList<>();
    private Deque<String> path = new ArrayDeque<>(4); // ����Ϊ4��[0,255]������

    // д��2
    public List<String> restoreIpAddresses_v2(String s) {
        int n = s.length();
        if (n < 4 || n > 12) return res;
        dfs2(s, n, 0, 0, path);
        return res;
    }

    private void dfs2(String s, int len, int idx, int validSplitNum, Deque<String> path) {
        if (idx == len) {
            if (validSplitNum == 4)
                res.add(String.join(".", path));
            return;
        }

        int remainLen = len - idx;
        int remainSplit = 4 - validSplitNum;
        if (remainLen < remainSplit || remainLen > remainSplit * 3)
            return;

        for (int i = idx; i < s.length(); i++) {
            String curSeg = s.substring(idx, i+1);
            if (isValidIP(curSeg)) {
                path.addLast(curSeg);
                dfs2(s, len, i+1, validSplitNum+1, path);
                path.removeLast();
            } else continue;
        }
    }

    private boolean isValidIP(String curSeg) {
        if (curSeg.length() < 1 || curSeg.length() > 3) return false;
        if (curSeg.charAt(0) == '0' && curSeg.length() != 1) return false;

        int val = Integer.valueOf(curSeg);
        return 0 <= val && val <= 255;
    }

    // д��1
    public List<String> restoreIpAddresses(String s) {
        int n = s.length();
        if (n < 4 || n > 12) return res;
        dfs(s, n, 0, 0, path);
        return res;
    }

    private void dfs(String s, int n, int idx, int validSplitNum, Deque<String> path) {
        if (idx == n) { // ���ָ�idx==n
            if (validSplitNum == 4) // �Ϸ��ֶ���=4
                res.add(String.join(".", path)); // py: '.'.join(path)
            return;
        }
        // ��֦1��ʣ�����ָ���/���� ? (�Ϸ�)���������ָ�������[remainSplit, 3*remainSplit]
        int remainLen = n - idx; // ʣ������������ָ���/����
        int remainSplit = 4 - validSplitNum;    // �� �Ӷ�[0, 255], ���ȡ�[1, 3]
        if (remainLen < remainSplit || remainLen > 3 * remainSplit)
            return;

        // �� �������� �� i��idx��ʼ
        for (int i = idx; i < n; i++) {
            int curSeg = check(s, idx, i);
            if (curSeg == -1) continue; // ��֦2���Ӷ�s[idx, i]�Ƿ�����
            path.addLast(curSeg + ""); // ��String.valueOf(curSeg)
            dfs(s, n, i+1, validSplitNum+1, path); // �߲����ظ�ȡ�� ��i+1
            path.removeLast();
        }
    }

    private int check(String s, int start, int end) {
        int len = end - start + 1;
        // 1) ���ȡ� [1, 3]
        if (len < 1 || len > 3) return -1;
        // 2) ������'0'�ֶ�, ������>1ʱ ��������"ǰ��0"(����"011" ��)
        if (s.charAt(start) == '0' && len != 1) return -1;
        // 3) �Ӷ� ����res��[0, 255]
        /*// ��2: ��res
        res = 0;
        for (int i = start; i <= end; i++)
            res = res * 10 + (s.charAt(i) - '0');
        */
        int res = Integer.valueOf(s.substring(start, end+1));
        if (res > 255 || res < 0) return -1;
        return res;
    }

    public static void main(String[] args) {
        q93_restore_ip_addresses sol = new q93_restore_ip_addresses();
        String s1 = "25525511135", s3="256", s4="011", s2="0";
        String s5 = "010010";
        System.out.println(sol.check(s4, 0, s2.length()-1));
        List<String> res = sol.restoreIpAddresses(s5);
        System.out.println(res);
    }
}
