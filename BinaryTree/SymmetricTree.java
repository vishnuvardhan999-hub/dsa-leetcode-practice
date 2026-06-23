/**
 * LeetCode Problem #101: Symmetric Tree
 * Difficulty : Easy
 * Link       : https://leetcode.com/problems/symmetric-tree/
 *
 * PROBLEM STATEMENT:
 * ------------------
 * Given the root of a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : root = [1,2,2,3,4,4,3]
 *   Output: true
 *   Explanation:
 *         1
 *        / \
 *       2   2
 *      / \ / \
 *     3  4 4  3
 *   The tree is symmetric.
 *
 * Example 2:
 *   Input : root = [1,2,2,null,3,null,3]
 *   Output: false
 *   Explanation:
 *         1
 *        / \
 *       2   2
 *        \   \
 *         3   3
 *   Not symmetric — left subtree has right child, right subtree has right child.
 *
 * CONSTRAINTS:
 * ------------
 * - The number of nodes in the tree is in the range [1, 1000].
 * - -100 <= Node.val <= 100
 *
 * Follow up: Could you solve it both recursively and iteratively?
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
    public boolean isSymmetric(TreeNode root) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        if (root == null) {
            return true;
        }
        tra1(root.left, list1);
        tra2(root.right, list2);
        // Collections.reverse(list2);
        return list1.equals(list2);
    }

    // BFS on left subtree: enqueue left child first, then right
    ArrayList<Integer> tra1(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return list;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == null) {
                list.add(null);
                continue;
            }
            list.add(curr.val);
            q.add(curr.left);
            q.add(curr.right);
        }
        return list;
    }

    // BFS on right subtree: enqueue RIGHT child first, then left (mirrored order)
    ArrayList<Integer> tra2(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return list;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == null) {
                list.add(null);
                continue;
            }
            list.add(curr.val);
            q.add(curr.right);  // <-- right first (mirror of tra1)
            q.add(curr.left);
        }
        return list;
    }
}

/*
 * APPROACH: BFS Mirror Serialization + List Comparison
 * ------------------------------------------------------
 *
 * A tree is symmetric if the left subtree is a mirror image of the right subtree.
 * To check this, serialize both subtrees using BFS but in mirrored order,
 * then compare the two lists.
 *
 * KEY INSIGHT:
 * ------------
 * tra1 (left subtree)  → enqueues children as: left → right  (normal order)
 * tra2 (right subtree) → enqueues children as: right → left  (mirrored order)
 *
 * This means if the tree IS symmetric, both traversals will visit nodes in the
 * same sequence — producing identical lists.
 *
 * HOW IT WORKS:
 * -------------
 * 1. If root is null → return true (empty tree is symmetric).
 * 2. Serialize root.left  using tra1 (left-first BFS).
 * 3. Serialize root.right using tra2 (right-first BFS, i.e., mirrored).
 * 4. If list1 == list2 → tree is symmetric.
 *
 * STEP-BY-STEP with root = [1,2,2,3,4,4,3]:
 * -------------------------------------------
 *   Left subtree (root.left = 2):
 *   tra1: poll 2 → [2], enqueue 3,4
 *         poll 3 → [2,3], enqueue null,null
 *         poll 4 → [2,3,4], enqueue null,null
 *         poll null×4 → [2,3,4,null,null,null,null]
 *   list1 = [2, 3, 4, null, null, null, null]
 *
 *   Right subtree (root.right = 2):
 *   tra2: poll 2 → [2], enqueue 3,4  (right=3, left=4 → enqueue right first)
 *         poll 3 → [2,3], enqueue null,null
 *         poll 4 → [2,3,4], enqueue null,null
 *         poll null×4 → [2,3,4,null,null,null,null]
 *   list2 = [2, 3, 4, null, null, null, null]
 *
 *   list1.equals(list2) → true ✓
 *
 * STEP-BY-STEP with root = [1,2,2,null,3,null,3]:
 * -------------------------------------------------
 *   list1 (left,  tra1): [2, null, 3, null, null]
 *   list2 (right, tra2): [2, 3, null, null, null]
 *   list1.equals(list2) → false ✓
 *
 * WHY NULLS MATTER:
 * -----------------
 * Including nulls preserves the structural positions. Without them,
 * structurally different trees with same values would be incorrectly equal.
 *
 * NOTE: The commented-out Collections.reverse(list2) is an earlier attempt
 * that would NOT have worked correctly — reversing a flat BFS list doesn't
 * produce a mirrored traversal. The correct fix is traversing right-first
 * in tra2, which is exactly what the final code does.
 *
 * Time Complexity : O(n) — each node visited once per subtree BFS
 * Space Complexity: O(n) — queue and lists each hold up to n elements
 */
