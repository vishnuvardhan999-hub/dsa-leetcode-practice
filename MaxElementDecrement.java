/**
 * LeetCode Daily Problem - June 23, 2026
 * ----------------------------------------
 * Problem #1846: Maximum Element After Decreasing and Rearranging
 * Difficulty : Medium
 * Link       : https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/
 *
 * PROBLEM STATEMENT:
 * ------------------
 * You are given an array of positive integers arr.
 * Perform some operations (possibly none) on arr so that it satisfies these conditions:
 *   1. The value of the first element in arr must be 1.
 *   2. The absolute difference between any 2 adjacent elements must be <= 1.
 *      i.e., abs(arr[i] - arr[i-1]) <= 1 for each i where 1 <= i < arr.length
 *
 * You are allowed to:
 *   - Decrease the value of any element to a smaller positive integer.
 *   - Rearrange the elements in any order.
 *
 * Return the maximum possible value of an element in arr after performing
 * the operations to satisfy the conditions.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : arr = [2, 2, 1, 2, 1]
 *   Output: 2
 *   Explanation: Rearrange to [1, 2, 2, 2, 1] → satisfies conditions.
 *                Largest element = 2.
 *
 * Example 2:
 *   Input : arr = [100, 1, 1000]
 *   Output: 3
 *   Explanation: Rearrange to [1, 100, 1000] → decrease to [1, 2, 3].
 *                Largest element = 3.
 *
 * Example 3:
 *   Input : arr = [1, 2, 3, 4, 5]
 *   Output: 5
 *   Explanation: Array already satisfies conditions. Largest element = 5.
 *
 * CONSTRAINTS:
 * ------------
 * - 1 <= arr.length <= 10^5
 * - 1 <= arr[i] <= 10^9
 */

import java.util.Arrays;

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            if (Math.abs(arr[i] - arr[i - 1]) > 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        return arr[arr.length - 1];
    }
}

/*
 * APPROACH: Greedy + Sorting
 * ---------------------------
 *
 * KEY OBSERVATIONS:
 * -----------------
 * 1. We can only DECREASE values, not increase them.
 * 2. We can REARRANGE freely, so sorting is always optimal.
 * 3. After sorting, we want to maximize the last element — so we greedily
 *    push each element as high as possible while respecting the constraint
 *    that adjacent difference <= 1.
 *
 * HOW IT WORKS:
 * -------------
 * 1. Sort arr in ascending order.
 * 2. Force arr[0] = 1 (the first element must always be 1).
 * 3. For each subsequent element arr[i]:
 *    - If arr[i] - arr[i-1] > 1, the gap is too large.
 *      Decrease arr[i] to arr[i-1] + 1 (maximum it can be while satisfying the rule).
 *    - If arr[i] - arr[i-1] <= 1, the element is already fine — keep it as-is.
 *      (We never need to increase, and decreasing would only hurt the max.)
 *
 * 4. After the pass, arr[arr.length-1] holds the maximum achievable value.
 *
 * STEP-BY-STEP with arr = [100, 1, 1000]:
 * ----------------------------------------
 *   After sort : [1, 100, 1000]
 *   arr[0] = 1  → [1, 100, 1000]
 *   i=1: |100-1| = 99 > 1 → arr[1] = 1+1 = 2  → [1, 2, 1000]
 *   i=2: |1000-2| = 998 > 1 → arr[2] = 2+1 = 3 → [1, 2, 3]
 *   Return arr[2] = 3 ✓
 *
 * STEP-BY-STEP with arr = [2, 2, 1, 2, 1]:
 * ------------------------------------------
 *   After sort : [1, 1, 2, 2, 2]
 *   arr[0] = 1  → [1, 1, 2, 2, 2]
 *   i=1: |1-1| = 0 <= 1 → keep 1   → [1, 1, 2, 2, 2]
 *   i=2: |2-1| = 1 <= 1 → keep 2   → [1, 1, 2, 2, 2]
 *   i=3: |2-2| = 0 <= 1 → keep 2   → [1, 1, 2, 2, 2]
 *   i=4: |2-2| = 0 <= 1 → keep 2   → [1, 1, 2, 2, 2]
 *   Return arr[4] = 2 ✓
 *
 * WHY SORTING WORKS:
 * ------------------
 * Since we can rearrange freely, sorting ensures we build the sequence
 * from smallest to largest. Each position can be at most 1 more than the
 * previous — so the maximum achievable value is bounded by the array length
 * (if all elements are large enough). Sorting maximizes how fast we can
 * grow the sequence.
 *
 * Time Complexity : O(n log n) — dominated by sorting
 * Space Complexity: O(1)       — sort is in-place, no extra space
 */
