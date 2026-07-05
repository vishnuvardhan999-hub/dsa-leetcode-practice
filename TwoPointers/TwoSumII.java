/**
 * LeetCode Problem #167: Two Sum II - Input Array Is Sorted
 * Difficulty : Medium
 * Link       : https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 * Pattern    : Two Pointers
 *
 * PROBLEM STATEMENT:
 * ------------------
 * Given a 1-indexed array of integers numbers that is already sorted in
 * non-decreasing order, find two numbers such that they add up to a
 * specific target number.
 *
 * Let these two numbers be numbers[index1] and numbers[index2] where
 * 1 <= index1 < index2 <= numbers.length.
 *
 * Return the indices of the two numbers, index1 and index2, added by one
 * as an integer array [index1, index2] of length 2.
 *
 * The tests are generated such that there is exactly one solution.
 * You may not use the same element twice.
 * Your solution must use only constant extra space.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : numbers = [2,7,11,15], target = 9
 *   Output: [1,2]
 *   Explanation: 2 + 7 = 9. Index1 = 1, index2 = 2.
 *
 * Example 2:
 *   Input : numbers = [2,3,4], target = 6
 *   Output: [1,3]
 *   Explanation: 2 + 4 = 6. Index1 = 1, index3 = 3.
 *
 * Example 3:
 *   Input : numbers = [-1,0], target = -1
 *   Output: [1,2]
 *   Explanation: -1 + 0 = -1. Index1 = 1, index2 = 2.
 *
 * CONSTRAINTS:
 * ------------
 * - 2 <= numbers.length <= 3 * 10^4
 * - -1000 <= numbers[i] <= 1000
 * - numbers is sorted in non-decreasing order
 * - -1000 <= target <= 1000
 * - The tests are generated such that there is exactly one solution
 */

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int arr[] = new int[2];
        int left = 0;
        int right = numbers.length - 1;
        int sum = 0;
        while (left < right) {
            sum = numbers[left] + numbers[right];
            if (sum > target) {
                sum = sum - numbers[right];
                right--;
            } else {
                if (sum < target) {
                    sum = sum - numbers[left];
                    left++;
                } else {
                    if (sum == target) {
                        arr[0] = left + 1;
                        arr[1] = right + 1;
                        return arr;
                    }
                }
            }
        }
        return arr;
    }
}

/*
 * APPROACH: Two Pointers (Converging)
 * -------------------------------------
 *
 * Since the array is already sorted, we can use two pointers starting at
 * opposite ends and converge them based on the current sum — no need for
 * a HashMap or nested loops.
 *
 * KEY INSIGHT:
 * ------------
 * - If sum > target → right pointer is too large, move it left (right--)
 * - If sum < target → left pointer is too small, move it right (left++)
 * - If sum == target → found the answer, return 1-indexed positions
 *
 * HOW IT WORKS:
 * -------------
 *   left  starts at index 0                  (smallest element)
 *   right starts at index numbers.length - 1 (largest element)
 *
 *   Each step, compute sum = numbers[left] + numbers[right]:
 *     - sum > target : the right value is too big  → right--
 *     - sum < target : the left value is too small → left++
 *     - sum == target: found it → store left+1, right+1 (1-indexed), return
 *
 * STEP-BY-STEP with numbers = [2,7,11,15], target = 9:
 * ------------------------------------------------------
 *   left=0, right=3 → sum = 2+15 = 17 > 9  → right=2
 *   left=0, right=2 → sum = 2+11 = 13 > 9  → right=1
 *   left=0, right=1 → sum = 2+7  = 9  == 9 → return [1, 2] ✓
 *
 * STEP-BY-STEP with numbers = [2,3,4], target = 6:
 * --------------------------------------------------
 *   left=0, right=2 → sum = 2+4 = 6 == 6 → return [1, 3] ✓
 *
 * STEP-BY-STEP with numbers = [-1,0], target = -1:
 * --------------------------------------------------
 *   left=0, right=1 → sum = -1+0 = -1 == -1 → return [1, 2] ✓
 *
 * NOTE on the sum variable:
 * --------------------------
 * The lines "sum = sum - numbers[right]" and "sum = sum - numbers[left]"
 * before moving the pointers are unnecessary — sum is immediately
 * recalculated at the top of the next iteration as
 * "sum = numbers[left] + numbers[right]". They don't cause bugs but
 * are redundant. The logic works correctly regardless.
 *
 * WHY TWO POINTERS WORKS HERE (not in regular Two Sum):
 * -------------------------------------------------------
 * Regular Two Sum (LC 1) uses an unsorted array → needs a HashMap for O(n).
 * Here the array is sorted → the two-pointer approach works because moving
 * left right increases the sum, and moving right left decreases it.
 * This directional property lets us converge to the answer in O(n).
 *
 * Time Complexity : O(n) — at most n steps before left meets right
 * Space Complexity: O(1) — only two pointers and a sum variable
 */
