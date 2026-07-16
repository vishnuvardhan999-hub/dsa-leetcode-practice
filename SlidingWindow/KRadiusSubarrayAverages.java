/**
 * LeetCode Problem #2090: K Radius Subarray Averages
 * Difficulty : Medium
 * Link       : https://leetcode.com/problems/k-radius-subarray-averages/
 * Pattern    : Sliding Window (Fixed Size)
 *
 * PROBLEM STATEMENT:
 * ------------------
 * You are given a 0-indexed array nums of n integers, and an integer k.
 *
 * The k-radius average for a subarray of nums centered at index i with radius k
 * is the average of all elements between indices i-k and i+k (inclusive).
 *
 * If there are fewer than k elements before or after index i, the k-radius
 * average is -1. Averages are truncated (floor division), not rounded.
 *
 * Build and return an array avgs of length n where avgs[i] is the k-radius
 * average for the subarray centered at index i.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : nums = [7,4,3,9,1,8,5,2,6], k = 3
 *   Output: [-1,-1,-1,5,4,4,-1,-1,-1]
 *   Explanation:
 *     Index 3: avg of [7,4,3,9,1,8,5] = 37/7 = 5 (indices 0..6)
 *     Index 4: avg of [4,3,9,1,8,5,2] = 32/7 = 4 (indices 1..7)
 *     Index 5: avg of [3,9,1,8,5,2,6] = 34/7 = 4 (indices 2..8)
 *     All others: fewer than k elements on one side → -1
 *
 * Example 2:
 *   Input : nums = [100000], k = 0
 *   Output: [100000]
 *
 * Example 3:
 *   Input : nums = [8], k = 100000
 *   Output: [-1]
 *
 * CONSTRAINTS:
 * ------------
 * - n == nums.length
 * - 1 <= n <= 10^5
 * - 0 <= nums[i], k <= 10^5
 */

class Solution {
    public int[] getAverages(int[] nums, int k) {
        int arr[] = new int[nums.length];
        // Fill all positions with -1 by default
        for (int i = 0; i < nums.length; i++) {
            arr[i] = -1;
        }
        // If window size (2k+1) exceeds array length, no valid averages exist
        if (2 * k + 1 > nums.length) {
            return arr;
        }
        int size = 2 * k + 1;
        long sum = 0;
        // Build initial window sum for the first window [0 .. 2k]
        for (int i = 0; i < size; i++) {
            sum = sum + nums[i];
        }
        int n = nums.length;
        int i = 0;
        int j = 2 * k;
        int mid = 0;
        while (j < n) {
            mid = i + (j - i) / 2;   // center index of current window
            arr[mid] = (int)(sum / size);
            if (j + 1 < n) {
                sum = sum - nums[i];  // remove leftmost element
                i++;
                j++;
                sum = sum + nums[j];  // add new rightmost element
            } else {
                break;
            }
        }
        return arr;
    }
}

/*
 * APPROACH: Fixed-Size Sliding Window (size = 2k+1)
 * ---------------------------------------------------
 *
 * KEY INSIGHT:
 * ------------
 * Every valid center index needs exactly k elements to its left and k to its right.
 * That means the window always has a fixed size of 2k+1.
 * We slide this fixed window across the array, computing the average for
 * the center element each time — O(1) per step after the initial build.
 *
 * HOW IT WORKS:
 * -------------
 * 1. Fill arr[] with -1 (default for invalid positions).
 * 2. If 2k+1 > n, no center can have k elements on both sides → return arr.
 * 3. Build the sum of the first window [0 .. 2k].
 * 4. i = left pointer, j = right pointer (j starts at 2k).
 * 5. While j < n:
 *    - mid = (i + j) / 2 → the center of the current window
 *    - arr[mid] = sum / (2k+1)  (integer division = floor)
 *    - Slide: remove nums[i], advance i and j, add nums[j]
 *    - Stop when j reaches the end
 *
 * STEP-BY-STEP with nums = [7,4,3,9,1,8,5,2,6], k = 3 (size=7):
 * ----------------------------------------------------------------
 *   Initial sum = 7+4+3+9+1+8+5 = 37  (window [0..6])
 *
 *   i=0, j=6: mid=3 → arr[3] = 37/7 = 5
 *             slide: sum = 37-7+2 = 32, i=1, j=7
 *   i=1, j=7: mid=4 → arr[4] = 32/7 = 4
 *             slide: sum = 32-4+6 = 34, i=2, j=8
 *   i=2, j=8: mid=5 → arr[5] = 34/7 = 4
 *             j+1=9 >= n=9 → break
 *
 *   Result: [-1,-1,-1, 5, 4, 4, -1,-1,-1] ✓
 *
 * WHY long for sum?
 * ------------------
 * nums[i] can be up to 10^5 and window size up to 2*10^5+1.
 * Max sum = 10^5 * (2*10^5+1) ≈ 2*10^10 — overflows int (max ~2.1*10^9).
 * Using long prevents overflow.
 *
 * WHY i + (j - i) / 2 instead of (i + j) / 2?
 * -----------------------------------------------
 * Both give the same result here since there's no overflow risk for indices,
 * but i + (j-i)/2 is the standard overflow-safe midpoint formula.
 *
 * Time Complexity : O(n) — initial build O(k) + sliding O(n), overall O(n)
 * Space Complexity: O(1) — only a few variables (output array doesn't count)
 */
