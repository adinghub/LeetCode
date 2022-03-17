package algorithm.easy;

/**
 * @author Qidong Ding
 * @description TODO： 21. 合并两个有序链表
 * @date 2022-03-04-17:09
 * @since JDK 1.8
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class 合并两个有序链表 {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next =  new ListNode(2);
        list1.next.next = new ListNode(4);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);
        System.out.println(new 合并两个有序链表.Solution().mergeTwoLists(list1, list2));
    }

    static class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            // 当有 一个链表为 null 的时候 返回 另一个链表
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }
            // 判断 如果 链表1 小于 链表2 那么 链表1 的下一个节点指向的是 链表1的下一个节点的值 和 链表2 的值进行比较后 小的值，反之 则链表2的下一个节点指向 链表2的下一个节点的值 和 链表1 的值进行比较后 小的值， 重复此过程
            if (list1.val < list2.val) {
                list1.next = mergeTwoLists(list1.next, list2);
                return list1;
            }else {
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            }
        }
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) {
         this.val = val;
     }
     ListNode(int val, ListNode next) {
         this.val = val;
         this.next = next;
     }
}