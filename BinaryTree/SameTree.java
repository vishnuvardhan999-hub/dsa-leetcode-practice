/**
 * LeetCode Problem #100: Same Tree
 * Difficulty : Easy
 * Link       : https://leetcode.com/problems/same-tree/
 *
 * PROBLEM STATEMENT:
 * ------------------
 * Given the roots of two binary trees p and q, write a function to check
 * if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical,
 * and the nodes have the same value.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : p = [1,2,3], q = [1,2,3]
 *   Output: true
 *   Explanation: Both trees have the same structure and values.
 *
 * Example 2:
 *   Input : p = [1,2], q = [1,null,2]
 *   Output: false
 *   Explanation: Same values but different structure (2 is left child vs right child).
 *
 * Example 3:
 *   Input : p = [1,2,1], q = [1,1,2]
 *   Output: false
 *   Explanation: Same structure but different values at child nodes.
 *
 * CONSTRAINTS:
 * ------------
 * - The number of nodes in both trees is in the range [0, 100].
 * - -10^4 <= Node.val <= 10^4
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

import java.util.*;

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        tra1(p, list1);
        tra2(q, list2);
        System.out.println(list1);
        System.out.println(list2);
        return list1.equals(list2);
    }

    ArrayList<Integer> tra1(TreeNode p, ArrayList<Integer> list) {
        if (p == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                list.add(null);
                continue;
            }
            list.add(curr.val);
            queue.add(curr.left);
            queue.add(curr.right);
        }
        return list;
    }

    ArrayList<Integer> tra2(TreeNode q, ArrayList<Integer> list) {
        if (q == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(q);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                list.add(null);
                continue;
            }
            list.add(curr.val);
            queue.add(curr.left);
            queue.add(curr.right);
        }
        return list;
    }
}

/*
 * APPROACH: BFS Level-Order Serialization + List Comparison
 * -----------------------------------------------------------
 *
 * The idea is to serialize both trees into lists using BFS (level-order
 * traversal), including nulls to capture the tree structure, then compare
 * the two lists for equality.
 *
 * HOW IT WORKS:
 * -------------
 * 1. For each tree, perform a BFS using a Queue.
 * 2. For every node dequeued:
 *    - If it's null  → add null to the list (preserves structure gaps)
 *    - If it's valid → add its value, then enqueue its left and right children
 *      (even if they're null, so null positions are recorded)
 * 3. After both trees are serialized, compare list1.equals(list2).
 *    If both lists are identical (same values in same positions including nulls),
 *    the trees are the same.
 *
 * STEP-BY-STEP with p=[1,2,3], q=[1,2,3]:
 * -----------------------------------------
 *   BFS on p: poll 1 → add 1, enqueue 2,3
 *             poll 2 → add 2, enqueue null,null
 *             poll 3 → add 3, enqueue null,null
 *             poll null → add null (×4)
 *   list1 = [1, 2, 3, null, null, null, null]
 *
 *   Same for q → list2 = [1, 2, 3, null, null, null, null]
 *   list1.equals(list2) → true ✓
 *
 * STEP-BY-STEP with p=[1,2], q=[1,null,2]:
 * ------------------------------------------
 *   list1 = [1, 2, null, null, null]      (2 is left child)
 *   list2 = [1, null, 2, null, null]      (2 is right child)
 *   list1.equals(list2) → false ✓
 *
 * WHY NULLS MATTER:
 * -----------------
 * Including nulls in the list is crucial — without them, trees with the
 * same values but different structures (like Example 2) would incorrectly
 * be considered equal.
 *
 * NOTE: tra1 and tra2 do exactly the same thing — they can be merged into
 * a single helper method to avoid code duplication. Both are kept here
 * as-is to match the original solution.
 *
 * Time Complexity : O(n) — BFS visits every node once in each tree
 * Space Complexity: O(n) — queue and list each hold up to n+1 elements
 */
