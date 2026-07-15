/**
 * LeetCode Problem #643: Maximum Average Subarray I
 * Difficulty : Easy
 * Link       : https://leetcode.com/problems/maximum-average-subarray-i/
 * Pattern    : Sliding Window (Fixed Size)
 *
 * PROBLEM STATEMENT:
 * ------------------
 * You are given an integer array nums consisting of n elements, and an integer k.
 *
 * Find a contiguous subarray whose length is equal to k that has the maximum
 * average value and return this value.
 *
 * Any answer with a calculation error less than 10^-5 will be accepted.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : nums = [1,12,-5,-6,50,3], k = 4
 *   Output: 12.75
 *   Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 *
 * Example 2:
 *   Input : nums = [5], k = 1
 *   Output: 5.0
 *
 * CONSTRAINTS:
 * ------------
 * - n == nums.length
 * - 1 <= k <= n <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 */

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double sum = 0;
        int i = 0;
        int j = i;
        int n = nums.length;
        double avg = Double.NEGATIVE_INFINITY;
        while (j < n) {
            sum = sum + nums[j];
            if (j - i + 1 == k) {
                avg = Math.max(avg, sum / k);
                i++;
                sum = sum - nums[i - 1];
            }
            j++;
        }
        return avg;
    }
}

/*
 * APPROACH: Fixed-Size Sliding Window
 * -------------------------------------
 *
 * Since k is fixed, we don't need to check every possible subarray from scratch.
 * Instead, slide a window of size k across the array — when the window moves
 * one step right, add the new element and drop the leftmost element.
 * This gives us the sum of every k-length subarray in O(1) per step.
 *
 * HOW IT WORKS:
 * -------------
 * Two pointers i (left) and j (right) define the current window.
 *   - Expand j to the right, adding nums[j] to sum.
 *   - When window size reaches k (j - i + 1 == k):
 *       1. Compute average = sum / k, update max avg.
 *       2. Slide window: remove nums[i] from sum, advance i.
 *   - Continue until j reaches the end.
 *
 * STEP-BY-STEP with nums = [1,12,-5,-6,50,3], k = 4:
 * -----------------------------------------------------
 *   j=0: sum=1,   window=1  (size < k)
 *   j=1: sum=13,  window=2  (size < k)
 *   j=2: sum=8,   window=3  (size < k)
 *   j=3: sum=2,   window=4  == k → avg=2/4=0.5,  slide: sum=2-1=1,   i=1
 *   j=4: sum=51,  window=4  == k → avg=51/4=12.75, slide: sum=51-12=39, i=2
 *   j=5: sum=42,  window=4  == k → avg=42/4=10.5, slide: sum=42-(-5)=47, i=3
 *   Return 12.75 ✓
 *
 * WHY Double.NEGATIVE_INFINITY?
 * ------------------------------
 * All elements can be negative (range: -10^4 to 10^4), so initializing
 * avg to 0 could give a wrong answer. NEGATIVE_INFINITY ensures any
 * real average will be picked up on the first window.
 *
 * NOTE on the subtraction order:
 *   After computing avg, the code does:
 *     i++;
 *     sum = sum - nums[i - 1];
 *   This is equivalent to: sum -= nums[i] before i++ — just written in
 *   a slightly different order. Both achieve the same result correctly.
 *
 * Time Complexity : O(n) — single pass, each element added and removed once
 * Space Complexity: O(1) — only a few variables, no extra arrays
 */
