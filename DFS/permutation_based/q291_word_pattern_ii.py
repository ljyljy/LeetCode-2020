# 	291. 单词规律II（困难）
# 给定一个pattern和一个字符串str，查找str是否遵循相同的模式。
# 这里遵循的意思是一个完整的匹配，在一个字母的模式和一个非空的单词str之间有一个双向连接的模式对应。(如果a对应s，那么b不对应s。例如，给定的模式= "ab"， str = "ss"，返回false）。
# 说明： 你可以假设 pattern 只包含小写字母。
# 例1：
# 输入:
# pattern = "abab"
# str = "redblueredblue"
# 输出: true
# 说明: "a"->"red","b"->"blue"
# 样例2
# 输入:
# pattern = "aaaa"
# str = "asdasdasdasd"
# 输出: true
# 说明: "a"->"asd"
# 样例3
# 输入:
# pattern = "aabb"
# str = "xyzabcxzyabc"
# 输出: false.


"""
这里我们为什么需要一个额外的 Set<String> 呢？
pattern:    "bdpbibletwuwbvh"
str:        "aaaaaaaaaaaaaaa"

 这里第一次执行时， map中匹配了 b -> a
 递归进去以后第二次执行时，d 没有在 map 中，所以跳过了map的匹配检测，
 所以进入循环体， 这时第二个word 又是 a, 按道理 a 应该被 b 匹配并且之前应该在map.containsKey的检查中跳出， 但现在并没有跳出，而是试图绑匹配给另一个pattern的字母 d,
 很明显 b != d 重复绑定不是正确结果， 所以需要continue掉这次尝试。
 当然， 我们也可以不需要这个set,  而是直接用map.containsValue(d), 因为之前map检查中己经跳出了如果己经绑定过，现在又出现了，就意味着重复绑定， 或者说不是匹配的。 之所以单独开了这个set 因为map.containsValue需要额外的O(n)时间， 实际上就是以空间换时间或时间换空间的选择。
"""


class Solution:
    def wordPatternMatch(self, pattern, strr):
        return self.is_match(pattern, strr, {}, set())

    # dfs
    def is_match(self, pattern, strr: str, mapping, used_word):
        if not pattern: return not strr  # 初次判断 & 最终递归出口
        pat_i = pattern[0]  # 如：a、b
        if pat_i in mapping:
            word = mapping[pat_i]  # 如：red
            if not strr.startswith(word):  # 如：redbluered...
                return False  # ↓  ba... <-> bluered...
            return self.is_match(pattern[1:], strr[len(word):],
                                 mapping, used_word)
        # ch尚无映射
        for i in range(len(strr)):
            word = strr[:i + 1]  # 从头切分长度 = i+1 ∈[1, len(strr)]
            if word in used_word: continue  # 重复绑定（当前pat_i不可以与其他pat_j绑定同一个word❤❤❤）

            used_word.add(word)  # 初次绑定(当前pat_i对应的word)
            mapping[pat_i] = word  # ↓ 也可写strr[len(word):]
            if self.is_match(pattern[1:], strr[i + 1:], mapping, used_word):
                return True  # 此处不是直接return，若不匹配不是直接返回False，而是取消绑定，继续尝试❤
            del mapping[pat_i]  # 去除绑定
            used_word.remove(word)

        return False
