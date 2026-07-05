/**
 * LeetCode Problem #88: Merge Sorted Array
 * Difficulty : Easy
 * Link       : https://leetcode.com/problems/merge-sorted-array/
 * Pattern    : Two Pointers
 *
 * PROBLEM STATEMENT:
 * ------------------
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order,
 * and two integers m and n, representing the number of elements in nums1 and nums2.
 *
 * Merge nums2 into nums1 as one sorted array in-place.
 *
 * The final sorted array should not be returned, but stored inside nums1.
 * nums1 has a length of m + n — the first m elements are the actual values,
 * the last n elements are set to 0 and should be ignored.
 * nums2 has a length of n.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 *   Output: [1,2,2,3,5,6]
 *   Explanation: Merging [1,2,3] and [2,5,6] gives [1,2,2,3,5,6].
 *
 * Example 2:
 *   Input : nums1 = [1], m = 1, nums2 = [], n = 0
 *   Output: [1]
 *   Explanation: nums2 is empty, nothing to merge.
 *
 * Example 3:
 *   Input : nums1 = [0], m = 0, nums2 = [1], n = 1
 *   Output: [1]
 *   Explanation: nums1 has no elements. Result is nums2.
 *
 * CONSTRAINTS:
 * ------------
 * - nums1.length == m + n
 * - nums2.length == n
 * - 0 <= m, n <= 200
 * - 1 <= m + n <= 200
 * - -10^9 <= nums1[i], nums2[j] <= 10^9
 *
 * Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 */

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m - 1;
        int right = n - 1;
        int len = nums1.length - 1;
        while (left >= 0 && right >= 0) {
            if (nums1[left] > nums2[right]) {
                nums1[len] = nums1[left];
                left--;
                len--;
            } else {
                nums1[len] = nums2[right];
                right--;
                len--;
            }
        }
        if (left < 0 && right >= 0) {
            for (int i = 0; i <= right; i++) {
                nums1[i] = nums2[i];
            }
        }
    }
}

/*
 * APPROACH: Two Pointers from the Back
 * --------------------------------------
 *
 * The naive approach would merge from the front, but that risks overwriting
 * elements in nums1 before they are used.
 *
 * The smart trick: fill nums1 from the BACK (largest position first).
 * Since the trailing zeros in nums1 are unused, we can safely overwrite them.
 *
 * THREE POINTERS:
 * ---------------
 *   left  → points to the last real element in nums1 (index m-1)
 *   right → points to the last element in nums2      (index n-1)
 *   len   → points to the last position in nums1     (index m+n-1)
 *
 * HOW IT WORKS:
 * -------------
 * Compare nums1[left] and nums2[right]:
 *   - Whichever is larger gets placed at nums1[len]
 *   - Decrement the corresponding pointer and len
 * Repeat until one of the arrays is exhausted.
 *
 * After the loop:
 *   - If nums2 still has remaining elements (right >= 0), copy them into
 *     nums1[0..right] directly.
 *   - If nums1 still has remaining elements (left >= 0), they are already
 *     in place — no action needed.
 *
 * STEP-BY-STEP with nums1=[1,2,3,0,0,0], m=3, nums2=[2,5,6], n=3:
 * -----------------------------------------------------------------
 *   left=2, right=2, len=5
 *
 *   nums1[2]=3 vs nums2[2]=6 → 6 > 3 → nums1[5]=6, right=1, len=4
 *   nums1[2]=3 vs nums2[1]=5 → 5 > 3 → nums1[4]=5, right=0, len=3
 *   nums1[2]=3 vs nums2[0]=2 → 3 > 2 → nums1[3]=3, left=1, len=2
 *   nums1[1]=2 vs nums2[0]=2 → equal → nums1[2]=2, right=-1, len=1
 *
 *   right < 0 → loop ends. left=1 still has elements but already in place.
 *   nums1 = [1, 2, 2, 3, 5, 6] ✓
 *
 * STEP-BY-STEP with nums1=[0], m=0, nums2=[1], n=1:
 * --------------------------------------------------
 *   left=-1 → while loop never enters (left < 0)
 *   right=0 >= 0 → copy nums2[0..0] into nums1 → nums1=[1] ✓
 *
 * WHY FILL FROM THE BACK?
 * ------------------------
 * Filling from the front would overwrite unprocessed elements of nums1.
 * Filling from the back uses the guaranteed-empty trailing zeros as a
 * safe buffer — no extra space needed.
 *
 * Time Complexity : O(m + n) — each element is placed exactly once
 * Space Complexity: O(1)     — merge done in-place, no extra array
 */
