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
 *   Explanation: n = 7, middle index = ⌊7/2⌋ = 3 → node with value 7 is removed.
 *
 * Example 2:
 *   Input : head = [1, 2, 3, 4]
 *   Output: [1, 2, 4]
 *   Explanation: n = 4, middle index = ⌊4/2⌋ = 2 → node with value 3 is removed.
 *
 * Example 3:
 *   Input : head = [2, 1]
 *   Output: [2]
 *   Explanation: n = 2, middle index = ⌊2/2⌋ = 1 → node with value 1 is removed.
 *
 * CONSTRAINTS:
 * ------------
 * - The number of nodes in the list is in the range [1, 10^5].
 * - 1 <= Node.val <= 10^5
 *
 * APPROACH: Slow & Fast Pointer (Floyd's Tortoise and Hare)
 * ---------------------------------------------------------
 * - Use a dummy node pointing to head to handle edge cases cleanly.
 * - slow pointer moves one step at a time.
 * - fast pointer moves two steps at a time.
 * - When fast reaches the end, slow is just before the middle node.
 * - Skip the middle node by: slow.next = slow.next.next
 *
 * Time Complexity : O(n)  — single pass through the list
 * Space Complexity: O(1)  — no extra space used
 */

public class MiddleNodeDelete {

    // Definition for singly-linked list node
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode deleteMiddle(ListNode head) {
        // Edge case: single node list, nothing to delete
        if (head == null || head.next == null) return null;

        // Dummy node to simplify pointer manipulation
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = head;

        // Advance fast by 2 and slow by 1 each iteration
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // slow is now just before the middle node — skip it
        slow.next = slow.next.next;

        return dummy.next;
    }

    // Helper: build a linked list from an array
    private static ListNode buildList(int[] vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    // Helper: print the linked list
    private static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(", ");
            head = head.next;
        }
        sb.append("]");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        MiddleNodeDelete sol = new MiddleNodeDelete();

        // Example 1 → expected: [1, 3, 4, 1, 2, 6]
        printList(sol.deleteMiddle(buildList(new int[]{1, 3, 4, 7, 1, 2, 6})));

        // Example 2 → expected: [1, 2, 4]
        printList(sol.deleteMiddle(buildList(new int[]{1, 2, 3, 4})));

        // Example 3 → expected: [2]
        printList(sol.deleteMiddle(buildList(new int[]{2, 1})));
    }
}
