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

/*
 * APPROACH: Two Pointers (Slow & Fast) + Prev Pointer
 * ----------------------------------------------------
 *
 * The idea is to find the middle node in a single pass without
 * knowing the total length of the list.
 *
 * HOW IT WORKS:
 * -------------
 * Three pointers are used:
 *   - slow  : moves one step at a time
 *   - fast  : moves two steps at a time
 *   - prev  : always stays one step behind slow
 *
 * Because fast moves twice as fast as slow, when fast reaches
 * the end of the list, slow will be exactly at the middle node.
 * And prev will be just before that middle node.
 *
 * STEP-BY-STEP with Example [1, 3, 4, 7, 1, 2, 6]:
 * --------------------------------------------------
 * Start : slow=1, fast=1, prev=null
 * Step 1: prev=1,  slow=3,  fast=4
 * Step 2: prev=3,  slow=4,  fast=1
 * Step 3: prev=4,  slow=7,  fast=null  → loop ends
 *
 * Now slow is at node 7 (the middle), prev is at node 4.
 * Do: prev.next = slow.next  →  4 → 1 (skips 7)
 * Result: [1, 3, 4, 1, 2, 6] ✓
 *
 * EDGE CASE:
 * ----------
 * If the list has only 1 node (head.next == null), there is
 * no middle to delete — just return null directly.
 *
 * Time Complexity : O(n) — single pass through the list
 * Space Complexity: O(1) — only 3 extra pointers used
 */
