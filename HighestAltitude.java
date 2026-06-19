/**
 * LeetCode Daily Problem - June 19, 2026
 * ----------------------------------------
 * Problem #1732: Find the Highest Altitude
 * Difficulty : Easy
 * Link       : https://leetcode.com/problems/find-the-highest-altitude/
 *
 * PROBLEM STATEMENT:
 * ------------------
 * There is a biker going on a road trip. The road trip consists of n + 1 points
 * at different altitudes. The biker starts at point 0 with altitude 0.
 *
 * You are given an integer array gain of length n where gain[i] is the net gain
 * in altitude between points i and i + 1 for all (0 <= i < n).
 *
 * Return the highest altitude of a point.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : gain = [-5, 1, 5, 0, -7]
 *   Output: 1
 *   Explanation:
 *     Altitudes = [0, -5, -4, 1, 1, -6]
 *     The highest is 1.
 *
 * Example 2:
 *   Input : gain = [-4, -3, -2, -1, 4, 3, 2]
 *   Output: 0
 *   Explanation:
 *     Altitudes = [0, -4, -7, -9, -10, -6, -3, -1]
 *     The highest is 0 (the starting point).
 *
 * CONSTRAINTS:
 * ------------
 * - n == gain.length
 * - 1 <= n <= 100
 * - -100 <= gain[i] <= 100
 */

class Solution {
    public int largestAltitude(int[] gain) {
        int n = gain.length + 1;
        int arr[] = new int[n];
        int max = Integer.MIN_VALUE;
        arr[0] = 0;
        for (int i = 1; i < n; i++) {
            arr[i] = arr[i - 1] + gain[i - 1];
        }
        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }
}

/*
 * APPROACH: Prefix Sum + Max Tracking
 * -------------------------------------
 *
 * The altitude at each point is a running total (prefix sum) of the gains.
 * We just need to find the maximum value in that prefix sum array.
 *
 * HOW IT WORKS:
 * -------------
 * 1. The biker starts at altitude 0, so arr[0] = 0.
 * 2. For every subsequent point i, the altitude is:
 *      arr[i] = arr[i-1] + gain[i-1]
 *    This builds the full altitude array from the gain differences.
 * 3. Loop through the altitude array and track the maximum.
 *
 * STEP-BY-STEP with Example gain = [-5, 1, 5, 0, -7]:
 * -----------------------------------------------------
 *   arr[0] = 0
 *   arr[1] = 0 + (-5) = -5
 *   arr[2] = -5 + 1   = -4
 *   arr[3] = -4 + 5   =  1
 *   arr[4] =  1 + 0   =  1
 *   arr[5] =  1 + (-7)= -6
 *
 *   Altitude array = [0, -5, -4, 1, 1, -6]
 *   max = 1 ✓
 *
 * STEP-BY-STEP with Example gain = [-4, -3, -2, -1, 4, 3, 2]:
 * -------------------------------------------------------------
 *   Altitude array = [0, -4, -7, -9, -10, -6, -3, -1]
 *   max = 0 ✓  (the starting altitude is the highest here)
 *
 * WHY Integer.MIN_VALUE for initial max?
 *   All altitudes could be negative, so initializing max to 0 would be wrong.
 *   Using Integer.MIN_VALUE ensures any real altitude will be picked up.
 *
 * Time Complexity : O(n) — one pass to build altitude array + one pass to find max
 * Space Complexity: O(n) — stores the full altitude array
 *
 * NOTE: This can be optimized to O(1) space by tracking max during the
 * prefix sum build itself, but this version is clear and easy to follow.
 */
