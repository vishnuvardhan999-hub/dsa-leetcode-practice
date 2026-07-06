/**
 * LeetCode Problem #11: Container With Most Water
 * Difficulty : Medium
 * Link       : https://leetcode.com/problems/container-with-most-water/
 * Pattern    : Two Pointers
 *
 * PROBLEM STATEMENT:
 * ------------------
 * You are given an integer array height of length n. There are n vertical lines
 * drawn such that the two endpoints of the i-th line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the
 * container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 * Note: You may not slant the container.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : height = [1,8,6,2,5,4,8,3,7]
 *   Output: 49
 *   Explanation: Lines at index 1 (height=8) and index 8 (height=7).
 *                Width = 8-1 = 7, height = min(8,7) = 7 → area = 7 * 7 = 49.
 *
 * Example 2:
 *   Input : height = [1,1]
 *   Output: 1
 *   Explanation: Width = 1, height = min(1,1) = 1 → area = 1.
 *
 * CONSTRAINTS:
 * ------------
 * - n == height.length
 * - 2 <= n <= 10^5
 * - 0 <= height[i] <= 10^4
 */

class Solution {
    public int maxArea(int[] height) {
        int val = 0;
        int len = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int l = 0;
            if (height[left] > height[right]) {
                l = right - left;
                val = Math.max(val, height[right] * l);
                right--;
            } else {
                if (height[left] < height[right]) {
                    l = right - left;
                    val = Math.max(val, height[left] * l);
                    left++;
                } else {
                    if (height[left] == height[right]) {
                        l = right - left;
                        val = Math.max(val, height[right] * l);
                        left++;
                        right--;
                    }
                }
            }
        }
        return val;
    }
}

/*
 * APPROACH: Two Pointers (Greedy Shrink)
 * ----------------------------------------
 *
 * KEY INSIGHT:
 * ------------
 * The area of a container is determined by:
 *   area = width × min(height[left], height[right])
 *   where width = right - left
 *
 * To maximize area, we want both wide AND tall containers.
 * Starting with the widest possible (left=0, right=n-1), we then
 * greedily shrink from the shorter side — because keeping the shorter
 * side can never produce a larger area (width decreases, height stays
 * bounded by the shorter side).
 *
 * THREE CASES at each step:
 * --------------------------
 *   1. height[left] > height[right]:
 *      Water level = height[right] (shorter side limits it).
 *      Moving left inward can't help — it can only decrease width
 *      while height is already capped at height[right].
 *      → Move right inward (right--), hoping for a taller right wall.
 *
 *   2. height[left] < height[right]:
 *      Water level = height[left].
 *      → Move left inward (left++), hoping for a taller left wall.
 *
 *   3. height[left] == height[right]:
 *      Either move works — move both inward (left++, right--).
 *
 * STEP-BY-STEP with height = [1,8,6,2,5,4,8,3,7]:
 * --------------------------------------------------
 *   left=0(1), right=8(7) → min=1, area=1×8=8,  val=8  → left++
 *   left=1(8), right=8(7) → min=7, area=7×7=49, val=49 → right--
 *   left=1(8), right=7(3) → min=3, area=3×6=18, val=49 → right--
 *   left=1(8), right=6(8) → equal, area=8×5=40, val=49 → left++,right--
 *   left=2(6), right=5(4) → min=4, area=4×3=12, val=49 → right--
 *   left=2(6), right=4(5) → min=5, area=5×2=10, val=49 → right--
 *   left=2(6), right=3(2) → min=2, area=2×1=2,  val=49 → right--
 *   left=2, right=2 → left==right → loop ends
 *   Return 49 ✓
 *
 * WHY WE MOVE THE SHORTER SIDE:
 * ------------------------------
 * If we move the taller side instead, the width decreases AND the height
 * can only stay the same or get smaller (bounded by the shorter side).
 * So moving the taller side can never increase the area.
 * Moving the shorter side at least gives a CHANCE of finding a taller wall
 * that compensates for the reduced width.
 *
 * NOTE: The variable 'len' is declared but unused — it can be removed.
 *
 * Time Complexity : O(n) — each pointer moves at most n steps total
 * Space Complexity: O(1) — only two pointers and a max tracker
 */
