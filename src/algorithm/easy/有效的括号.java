package algorithm.easy;

import java.util.Stack;

/**
 * @author Qidong Ding
 * @description TODO： 20. 有效的括号
 * @date 2022-03-04-15:47
 * @since JDK 1.8
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 *
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 *
 * 输入：s = "{[]}"
 * 输出：true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class 有效的括号 {
    public static void main(String[] args) {
        System.out.println(new Solution().isValid("([)]"));
    }

    static class Solution {
        public boolean isValid(String s) {
            // 创建一个栈
            Stack<Character> stack = new Stack<>();
            // 循环 字符串
            for (int i = 0; i < s.length(); i++) {
                // 字符串肯定 先是朝右的，然后比较，压栈
                if ('(' == s.charAt(i)) {
                    stack.push(')');
                } else if ('[' == s.charAt(i)) {
                    stack.push(']');
                } else if ('{' == s.charAt(i)) {
                    stack.push('}');
                    // 当不匹配 朝右 的时候，意味着压栈结束，需要 弹栈。根据规则，判断 后续字符是否与弹出字符匹配，字符不匹配或栈为空则是匹配失败
                } else if (stack.isEmpty() || !(stack.pop() == s.charAt(i))) {
                    return false;
                }
            }
            // 弹栈结束，如果 栈 不为空，则不匹配
            if (!stack.isEmpty()) {
                return false;
            }
            // 一切匹配
            return true;
        }
    }
}
