package DataStructure.Trie;

public class q421_max_xor_of_two_in_an_array {
    class TrieNode {
        TrieNode[] child = new TrieNode[2]; // ∵二进制表示 ∴只有0/1
    }

    TrieNode root = new TrieNode();
    private void insert(int num) {
        TrieNode p = root; // 从根遍历
        // 逆序: 从高位依次插入Trie
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1; // 获取num第i位(0/1)
            if (p.child[bit] == null)
                p.child[bit] = new TrieNode();
            p = p.child[bit]; // 指针下移
        }
    }

    private int getNum2_with_max_XOR(int num1) {
        int num2 = 0; // 找到Trie树中与num最大异或的数num2
        TrieNode p = root;
        // 逆序: 从高位依次遍历num
        for (int i = 31; i >= 0; i--) {
            int bit1 = (num1 >> i) & 1, bit2 = 1-bit1; // 0/1
            if (p.child[bit2] != null) { // 优先找 bit2(∵相异的位 ∴该位XOR=1), 即~bit1
                num2 |= (bit2 << i); // 1)或运算: 还原为num2的第i位
                p = p.child[bit2]; // 指针下移
            } else { //
                num2 |= (bit1 << i);
                p = p.child[bit1];
            }
        }
        return num2;
    }

    public int findMaximumXOR(int[] nums) {
        int ans = 0;
        for (int num1: nums) {
            insert(num1);
            int num2 = getNum2_with_max_XOR(num1);
            ans = Math.max(ans, num1 ^ num2);
        }
        return ans;
    }
}
