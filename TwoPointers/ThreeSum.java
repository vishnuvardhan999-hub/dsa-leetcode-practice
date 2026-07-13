/**
 * LeetCode Problem #15: 3Sum
 * Difficulty : Medium
 * Link       : https://leetcode.com/problems/3sum/
 * Pattern    : Two Pointers
 *
 * PROBLEM STATEMENT:
 * ------------------
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : nums = [-1,0,1,2,-1,-4]
 *   Output: [[-1,-1,2],[-1,0,1]]
 *   Explanation:
 *     nums[0]+nums[1]+nums[2] = -1+0+1 = 0  → [-1,0,1]
 *     nums[0]+nums[3]+nums[4] = -1+2+(-1) = 0 → [-1,-1,2]
 *
 * Example 2:
 *   Input : nums = [0,1,1]
 *   Output: []
 *   Explanation: No triplet sums to 0.
 *
 * Example 3:
 *   Input : nums = [0,0,0]
 *   Output: [[0,0,0]]
 *
 * CONSTRAINTS:
 * ------------
 * - 3 <= nums.length <= 3000
 * - -10^5 <= nums[i] <= 10^5
 */

import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            // Skip duplicate values for the fixed element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            while (left < right) {
                List<Integer> list1 = new ArrayList<>();
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    list1.add(nums[i]);
                    list1.add(nums[left]);
                    list1.add(nums[right]);
                    list.add(list1);
                    left++;
                    right--;
                    // Skip duplicates for left pointer
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    // Skip duplicates for right pointer
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return list;
    }
}

/*
 * APPROACH: Sort + Two Pointers (Fix One, Search Two)
 * -----------------------------------------------------
 *
 * KEY IDEA:
 * ---------
 * Fix one element nums[i] using the outer loop.
 * Then use two pointers (left, right) on the remaining subarray [i+1..n-1]
 * to find all pairs that sum to -nums[i].
 * This reduces the 3-pointer brute force O(n³) to O(n²).
 *
 * Sorting first enables the two-pointer technique and also makes
 * duplicate skipping straightforward.
 *
 * HOW IT WORKS:
 * -------------
 * 1. Sort nums.
 * 2. Iterate i from 0 to n-1:
 *    - Skip if nums[i] == nums[i-1] (duplicate fixed element).
 *    - Set left = i+1, right = n-1.
 *    - While left < right:
 *        sum = nums[i] + nums[left] + nums[right]
 *        - sum == 0 → found a triplet, add it, move both pointers inward,
 *                     skip duplicates on both sides.
 *        - sum < 0  → need a larger sum → left++
 *        - sum > 0  → need a smaller sum → right--
 *
 * DUPLICATE SKIPPING (3 places):
 * --------------------------------
 *   1. Outer loop: if nums[i] == nums[i-1], skip (same fixed element again).
 *   2. After finding a triplet: advance left past duplicate nums[left] values.
 *   3. After finding a triplet: retreat right past duplicate nums[right] values.
 *
 * STEP-BY-STEP with nums = [-1,0,1,2,-1,-4]:
 * --------------------------------------------
 *   After sort: [-4,-1,-1,0,1,2]
 *
 *   i=0 (nums[i]=-4): no triplet possible (smallest 3 sum = -4+-1+-1 = -6 ≠ 0... skip)
 *   i=1 (nums[i]=-1):
 *     left=2(-1), right=5(2) → sum=0 → add [-1,-1,2], left=3, right=4
 *     left=3(0),  right=4(1) → sum=0 → add [-1,0,1],  left=4, right=3 → break
 *   i=2 (nums[i]=-1): nums[2]==nums[1] → skip
 *   i=3 (nums[i]=0):
 *     left=4(1), right=5(2) → sum=3 > 0 → right--
 *     left=4 >= right=4 → break
 *   Result: [[-1,-1,2],[-1,0,1]] ✓
 *
 * Time Complexity : O(n²) — outer loop O(n) × inner two-pointer O(n)
 * Space Complexity: O(1)  — output list aside, no extra data structures
 */
