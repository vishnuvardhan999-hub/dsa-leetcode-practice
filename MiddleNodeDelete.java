/**
 * LeetCode Daily Problem - June 15, 2026
 * ----------------------------------------
 * Problem #2095: Delete the Middle Node of a Linked List
 * Difficulty : Medium
 * Link       : https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 *
 * PROBLEM STATEMENT:
 * ------------------
 * You are given the head of a linked list. Delete the middle node,
 * and return the head of the modified linked list.
 *
 * The middle node of a linked list of size n is the ⌊n / 2⌋th node
 * from the start using 0-based indexing, where ⌊x⌋ denotes the floor of x.
 *
 * For n = 1, 2, 3, 4, and 5, the middle nodes are at index 0, 1, 1, 2, and 2 respectively.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : head = [1, 3, 4, 7, 1, 2, 6]
 *   Output: [1, 3, 4, 1, 2, 6]
 *
 * Example 2:
 *   Input : head = [1, 2, 3, 4]
 *   Output: [1, 2, 4]
 *
 * Example 3:
 *   Input : head = [2, 1]
 *   Output: [2]
 *
 * CONSTRAINTS:
 * ------------
 * - The number of nodes in the list is in the range [1, 10^5].
 * - 1 <= Node.val <= 10^5
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteMiddle(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while(fast != null && fast.next != null) {
           prev = slow;
           slow = slow.next;
           fast = fast.next.next;
        }    
         prev.next = slow.next;
    return head;
    }
   
}
