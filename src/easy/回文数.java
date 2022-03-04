package easy;

/**
 * @author Qidong Ding
 * @description TODO： 第九题回文数
 * @date 2022-03-04-9:08
 * @since JDK 1.8
 *
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 例如，121 是回文，而 123 不是。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */

public class 回文数 {
    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome(121));
    }

}

class Solution {
    public boolean isPalindrome(int x) {
        // x 为负数，直接返回
        if (x < 0) {
            return false;
        }
        // 定义一个变量存储 倒序的数字
        int count = 0;
        // 将 x 赋给 num 防止影响 x 不好判断
        int num = x;
        while (num != 0) {
            // 将得到的 最小值 增加10倍 加上 num 的 最小值
            count = count * 10 + num % 10;
            // 取 num 的 各十百千 位
            num /= 10;
        }
        return count == x;
    }
}
