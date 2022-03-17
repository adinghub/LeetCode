package algorithm.easy;

/**
 * @author Qidong Ding
 * @description TODO： 26. 删除有序数组中的重复项
 * @date 2022-03-17-11:07
 * @since JDK 1.8
 *
 * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
 * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么nums的前 k 个元素应该保存最终结果。
 * 将最终结果插入nums 的前 k 个位置后返回 k 。
 * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 判题标准:
 * 系统会用下面的代码来测试你的题解:
 *
 * int[] nums = [...]; // 输入数组
 * int[] expectedNums = [...]; // 长度正确的期望答案
 *
 * int k = removeDuplicates(nums); // 调用
 *
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 *     assert nums[i] == expectedNums[i];
 * }
 * 如果所有断言都通过，那么您的题解将被 通过。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2,_]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 *
 * 提示：
 *
 * 0 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按 升序 排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class 删除有序数组中的重复项 {
    public static void main(String[] args) {
        int resoult = new Solution().removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4});
        System.out.println(resoult);
    }

    static class Solution {
        /**
         * 如果 target 位置的元素 与 i 位置的元素 不等，那么 target++ 操作后，将 i 位置的元素赋值到 target 位置
         * 这么比较绕，因为只有不等的时候 target保留当前的元素，然后 target++ 操作后，将 i 位置的元素 赋值到 target的下一个位置，这样就保留了一个重复元素
         * target 位置的元素 与 i 位置的元素 相等，那么将不做操作，target 还是第一个重复的元素，直到 遇到 target 位置的元素 与 i 位置的元素不等的时候，
         * target 保留当前第一个重复的元素，然后 ++ 到下一个重复的元素，然后把 i 位置的不同的元素赋值到第二个重复元素上这样就去除了重复的元素
         *
         * 之所以要返回 target +1 是因为 Test程序 需要根据返回的不重复长度来进行遍历操作，也就是说 到 target+1 的位置是不重复的，后面还有其他的元素是不需要的。
         *
         * @param nums
         * @return
         */
        public int removeDuplicates(int[] nums) {
            // 定义一个 指针 默认第0个索引开始
            int target = 0;
            // 循环 i 从 第二个元素开始比较
            for (int i = 1; i < nums.length; i++) {
                if (nums[target] != nums[i]) {
                    target = target+1;
                    nums[target] = nums[i];
                }
            }
            return target+1;
        }
    }
}
