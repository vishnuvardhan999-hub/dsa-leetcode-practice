/**
 * LeetCode Problem #219: Contains Duplicate II
 * Difficulty : Easy
 * Link       : https://leetcode.com/problems/contains-duplicate-ii/
 * Pattern    : Sliding Window (Fixed Size) + HashSet
 *
 * PROBLEM STATEMENT:
 * ------------------
 * Given an integer array nums and an integer k, return true if there are two
 * distinct indices i and j in the array such that:
 *   - nums[i] == nums[j]  (same value)
 *   - abs(i - j) <= k     (indices are at most k apart)
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : nums = [1,2,3,1], k = 3
 *   Output: true
 *   Explanation: nums[0] == nums[3] and abs(0-3) = 3 <= 3.
 *
 * Example 2:
 *   Input : nums = [1,0,1,1], k = 1
 *   Output: true
 *   Explanation: nums[2] == nums[3] and abs(2-3) = 1 <= 1.
 *
 * Example 3:
 *   Input : nums = [1,2,3,1,2,3], k = 2
 *   Output: false
 *   Explanation: Duplicates exist but none are within distance 2.
 *
 * CONSTRAINTS:
 * ------------
 * - 1 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 * - 0 <= k <= 10^5
 */

import java.util.HashSet;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int i = 0;
        int j = 0;
        int n = nums.length;
        HashSet<Integer> set = new HashSet<>();
        while (j < n) {
            if (Math.abs(i - j) <= k) {
                if (set.contains(nums[j])) {
                    return true;
                } else {
                    set.add(nums[j]);
                    j++;
                }
            } else {
                set.remove(nums[i]);
                i++;
            }
        }
        return false;
    }
}

/*
 * APPROACH: Sliding Window of Size k + HashSet for O(1) Lookup
 * --------------------------------------------------------------
 *
 * KEY INSIGHT:
 * ------------
 * We need to check if any duplicate exists within a window of at most k indices.
 * Maintain a sliding window of size at most k+1 using a HashSet.
 * The HashSet gives O(1) contains() and remove() — much faster than scanning.
 *
 * HOW IT WORKS:
 * -------------
 * Two pointers i (left/start) and j (right/end) define the current window.
 *
 *   While j < n:
 *     Case 1 — window size is within limit (|i - j| <= k):
 *       - If nums[j] already in set → duplicate found within k distance → return true
 *       - Else → add nums[j] to set, advance j
 *
 *     Case 2 — window is too large (|i - j| > k):
 *       - Remove nums[i] from set (it's now out of range), advance i
 *
 *   If loop completes without finding a duplicate → return false.
 *
 * STEP-BY-STEP with nums = [1,2,3,1], k = 3:
 * --------------------------------------------
 *   i=0, j=0: |0-0|=0 <= 3, set={},    1 not in set → add 1, j=1  | set={1}
 *   i=0, j=1: |0-1|=1 <= 3, set={1},   2 not in set → add 2, j=2  | set={1,2}
 *   i=0, j=2: |0-2|=2 <= 3, set={1,2}, 3 not in set → add 3, j=3  | set={1,2,3}
 *   i=0, j=3: |0-3|=3 <= 3, set={1,2,3}, 1 IS in set → return true ✓
 *
 * STEP-BY-STEP with nums = [1,2,3,1,2,3], k = 2:
 * -------------------------------------------------
 *   i=0, j=0: add 1, j=1  | set={1}
 *   i=0, j=1: add 2, j=2  | set={1,2}
 *   i=0, j=2: add 3, j=3  | set={1,2,3}
 *   i=0, j=3: |0-3|=3 > 2 → remove nums[0]=1, i=1 | set={2,3}
 *   i=1, j=3: |1-3|=2 <= 2, 1 not in set → add 1, j=4 | set={2,3,1}
 *   i=1, j=4: |1-4|=3 > 2 → remove nums[1]=2, i=2 | set={3,1}
 *   i=2, j=4: |2-4|=2 <= 2, 2 not in set → add 2, j=5 | set={3,1,2}
 *   i=2, j=5: |2-5|=3 > 2 → remove nums[2]=3, i=3 | set={1,2}
 *   i=3, j=5: |3-5|=2 <= 2, 3 not in set → add 3, j=6 | set={1,2,3}
 *   j=6 >= n=6 → loop ends → return false ✓
 *
 * WHY HASHSET INSTEAD OF ARRAY?
 * ------------------------------
 * Values range from -10^9 to 10^9, so an array-based frequency map isn't
 * feasible. A HashSet gives O(1) add/remove/contains regardless of value range.
 *
 * Time Complexity : O(n) — each element added and removed from set at most once
 * Space Complexity: O(k) — set holds at most k+1 elements at any time
 */
