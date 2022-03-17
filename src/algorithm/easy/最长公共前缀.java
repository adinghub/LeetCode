package algorithm.easy;

/**
 * @author Qidong Ding
 * @description TODO： 14. 最长公共前缀
 * @date 2022-03-04-14:40
 * @since JDK 1.8
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class 最长公共前缀 {
    public static void main(String[] args) {
        System.out.println(new Solution().longestCommonPrefix(new String[]{"ab","a"}));
    }
    static class Solution {
        public String longestCommonPrefix(String[] strs) {
            // 当长度为0 时，为空，直接返回空串
            if (strs.length == 0) {
                return "";
            }
            // 公共长度，随便拿一个字符串来做比较，这里拿第一个
            String str = strs[0];
            // 循环整个字符数组
            for (int i = 1; i < strs.length; i++) {
                int j = 0;
                // 循环数组里的每个字符，当j 小于数组里第i个元素的长度和小于str 的长度的时候 才进入
                for (; j < strs[i].length() && j < str.length(); j++) {
                    if (str.charAt(j) != strs[i].charAt(j)) {
                        break;
                    }
                }
                // 每两个字符串循环完记录前缀
                str = str.substring(0, j);
            }
            return str;
        }
    }
}
