/**
 * LeetCode Problem #42: Trapping Rain Water
 * Difficulty : Hard
 * Link       : https://leetcode.com/problems/trapping-rain-water/
 * Pattern    : Two Pointers
 *
 * PROBLEM STATEMENT:
 * ------------------
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it can trap after raining.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : height = [0,1,0,2,1,0,1,3,2,1,2,1]
 *   Output: 6
 *   Explanation: 6 units of rain water are trapped.
 *
 * Example 2:
 *   Input : height = [4,2,0,3,2,5]
 *   Output: 9
 *
 * CONSTRAINTS:
 * ------------
 * - n == height.length
 * - 1 <= n <= 2 * 10^4
 * - 0 <= height[i] <= 10^5
 */

class Solution {
    public int trap(int[] height) {
        // ---------------------------------------------------------------
        // APPROACH 1 (Prefix/Suffix Arrays — commented out):
        // Pre-compute leftmax[i] and rightmax[i] for every position.
        // Water at i = min(leftmax[i], rightmax[i]) - height[i]
        //
        // int sum = 0;
        // int n = height.length;
        // int leftarr[] = new int[n];
        // int rightarr[] = new int[n];
        // int leftmax = 0;
        // int rightmax = 0;
        // for (int i = 0; i < n; i++) {
        //     leftmax = Math.max(leftmax, height[i]);
        //     leftarr[i] = Math.max(leftmax, height[i]);
        // }
        // for (int i = n - 1; i >= 0; i--) {
        //     rightmax = Math.max(rightmax, height[i]);
        //     rightarr[i] = Math.max(rightmax, height[i]);
        // }
        // for (int i = 0; i < n; i++) {
        //     sum = sum + Math.min(leftarr[i], rightarr[i]) - height[i];
        // }
        // return sum;
        // ---------------------------------------------------------------

        // APPROACH 2 (Two Pointers — O(1) space, active):
        int left = 0;
        int right = height.length - 1;
        int leftmax = 0;
        int rightmax = 0;
        int sum = 0;
        while (left < right) {
            leftmax = Math.max(leftmax, height[left]);
            rightmax = Math.max(rightmax, height[right]);
            if (leftmax < rightmax) {
                sum = sum + leftmax - height[left];
                left++;
            } else {
                sum = sum + rightmax - height[right];
                right--;
            }
        }
        return sum;
    }
}

/*
 * APPROACH 1 (Prefix/Suffix Arrays — commented out):
 * ----------------------------------------------------
 * For every index i, water trapped = min(max height to the left, max height to the right) - height[i].
 * Pre-build leftarr[] and rightarr[] to store these maxes, then compute in one pass.
 * Time: O(n), Space: O(n) — needs two extra arrays.
 *
 * APPROACH 2 (Two Pointers — active):
 * -------------------------------------
 * Same logic as Approach 1, but avoids the extra arrays by maintaining
 * running leftmax and rightmax on the fly.
 *
 * KEY INSIGHT:
 * ------------
 * Water at any position is bounded by min(leftmax, rightmax).
 * We don't need to know BOTH maxes exactly — we only need the smaller one,
 * because that's what limits the water level.
 *
 * If leftmax < rightmax:
 *   We're sure the right side has a taller wall than leftmax.
 *   So the water at 'left' = leftmax - height[left].
 *   Process left, then advance left++.
 *
 * If leftmax >= rightmax (else branch):
 *   rightmax is the limiting side — process right.
 *   This also handles the equal case (leftmax == rightmax):
 *   both sides are equally tall so either can be processed; we pick right.
 *   We're sure the left side has a wall taller than rightmax.
 *   So the water at 'right' = rightmax - height[right].
 *   Process right, then retreat right--.
 *
 * HOW IT WORKS:
 * -------------
 * 1. Start left=0, right=n-1, leftmax=0, rightmax=0, sum=0.
 * 2. Each step: update leftmax and rightmax from current pointers.
 * 3. Process the side with the smaller max (guaranteed safe wall on the other side).
 * 4. Add water contribution: max - height[pointer].
 * 5. Move that pointer inward.
 *
 * STEP-BY-STEP with height = [4,2,0,3,2,5]:
 * -------------------------------------------
 *   left=0(4),right=5(5): lmax=4,rmax=5 → lmax<=rmax → sum+=4-4=0, left=1
 *   left=1(2),right=5(5): lmax=4,rmax=5 → lmax<=rmax → sum+=4-2=2, left=2
 *   left=2(0),right=5(5): lmax=4,rmax=5 → lmax<=rmax → sum+=4-0=4, left=3
 *   left=3(3),right=5(5): lmax=4,rmax=5 → lmax<=rmax → sum+=4-3=1, left=4
 *   left=4(2),right=5(5): lmax=4,rmax=5 → lmax<=rmax → sum+=4-2=2, left=5
 *   left=5 >= right=5 → loop ends
 *   Total sum = 0+2+4+1+2 = 9 ✓
 *
 * Time Complexity : O(n) — each element visited at most once
 * Space Complexity: O(1) — only 4 variables used (two pointers + two maxes)
 */
