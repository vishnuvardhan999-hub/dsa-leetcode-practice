/**
 * LeetCode Problem #977: Squares of a Sorted Array
 * Difficulty : Easy
 * Link       : https://leetcode.com/problems/squares-of-a-sorted-array/
 * Pattern    : Two Pointers
 *
 * PROBLEM STATEMENT:
 * ------------------
 * Given an integer array nums sorted in non-decreasing order, return an array
 * of the squares of each number sorted in non-decreasing order.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : nums = [-4,-1,0,3,10]
 *   Output: [0,1,9,16,100]
 *   Explanation: After squaring → [16,1,0,9,100]. After sorting → [0,1,9,16,100].
 *
 * Example 2:
 *   Input : nums = [-7,-3,2,3,11]
 *   Output: [4,9,9,49,121]
 *
 * CONSTRAINTS:
 * ------------
 * - 1 <= nums.length <= 10^4
 * - -10^4 <= nums[i] <= 10^4
 * - nums is sorted in non-decreasing order
 *
 * Follow up: Squaring each element and sorting the new array is very trivial.
 * Could you find an O(n) solution using a different approach?
 */

class Solution {
    public int[] sortedSquares(int[] nums) {
        int arr[] = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int val = right;
        int lq = nums[left] * nums[left];
        int rq = nums[right] * nums[right];
        while (left <= right) {
            lq = nums[left] * nums[left];
            rq = nums[right] * nums[right];
            if (lq < rq) {
                arr[val] = rq;
                val--;
                right--;
            } else {
                // handles both lq > rq AND lq == rq
                arr[val] = lq;
                left++;
                val--;
            }
        }
        return arr;
    }
}

/*
 * APPROACH: Two Pointers — Fill from the Back
 * ---------------------------------------------
 *
 * KEY INSIGHT:
 * ------------
 * The input array is sorted, but after squaring, large negatives produce
 * large squares. So the largest squares always come from either the
 * leftmost (most negative) or rightmost (most positive) end.
 *
 * We compare the squares from both ends and place the LARGER one at the
 * back of the result array, filling from right to left.
 *
 * THREE POINTERS:
 * ---------------
 *   left  → starts at index 0            (most negative / smallest)
 *   right → starts at index length - 1   (most positive / largest)
 *   val   → write position in result arr, starts at length - 1 (fills backward)
 *
 * HOW IT WORKS:
 * -------------
 * Each iteration:
 *   1. Compute lq = nums[left]²  and  rq = nums[right]²
 *   2. If lq < rq  → rq is larger → place rq at arr[val], move right inward
 *   3. Else (lq >= rq) → lq is larger or equal → place lq at arr[val], move left inward
 *   4. Decrement val to move the write position left
 * Repeat until left > right.
 *
 * STEP-BY-STEP with nums = [-4,-1,0,3,10]:
 * ------------------------------------------
 *   left=0(-4), right=4(10), val=4
 *   lq=16, rq=100 → 16 < 100 → arr[4]=100, right=3, val=3
 *
 *   left=0(-4), right=3(3), val=3
 *   lq=16, rq=9  → 16 > 9  → arr[3]=16,  left=1, val=2
 *
 *   left=1(-1), right=3(3), val=2
 *   lq=1,  rq=9  → 1 < 9   → arr[2]=9,   right=2, val=1
 *
 *   left=1(-1), right=2(0), val=1
 *   lq=1,  rq=0  → 1 > 0   → arr[1]=1,   left=2, val=0
 *
 *   left=2(0), right=2(0), val=0
 *   lq=0,  rq=0  → equal   → arr[0]=0,   left=3, val=-1
 *
 *   left=3 > right=2 → loop ends
 *   Result: [0,1,9,16,100] ✓
 *
 * WHY FILL FROM THE BACK?
 * ------------------------
 * The largest square always comes from one of the two ends.
 * Filling from the back lets us place the largest unsorted square at
 * its correct final position each step — no extra sorting needed.
 *
 * NOTE on the commented-out code:
 *   The equal case (lq == rq) was originally handled separately,
 *   but the else branch already covers it correctly — placing lq (== rq)
 *   at arr[val] and advancing left works fine for equal values too.
 *   The commented code was a correct but redundant special case.
 *
 * Time Complexity : O(n) — single pass with two pointers
 * Space Complexity: O(n) — output array of size n (required by the problem)
 */
