package easy;

/**
 * @author Qidong Ding
 * @description TODO：
 * @date 2022-01-20-21:52
 * @since JDK 1.8
 *
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和 为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 */

public class 两数之和 {
    public static void main(String[] args) {
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{j,i};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}
