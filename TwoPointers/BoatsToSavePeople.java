/**
 * LeetCode Problem #881: Boats to Save People
 * Difficulty : Medium
 * Link       : https://leetcode.com/problems/boats-to-save-people/
 * Pattern    : Two Pointers
 *
 * PROBLEM STATEMENT:
 * ------------------
 * You are given an array people where people[i] is the weight of the i-th person,
 * and an infinite number of boats where each boat can carry a maximum weight of limit.
 *
 * Each boat carries at most TWO people at the same time, provided the sum of
 * their weights is at most limit.
 *
 * Return the minimum number of boats required to carry every given person.
 * (It is guaranteed each person can be carried by a boat.)
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : people = [1,2], limit = 3
 *   Output: 1
 *   Explanation: 1+2 = 3 <= 3, both fit in one boat.
 *
 * Example 2:
 *   Input : people = [3,2,2,1], limit = 3
 *   Output: 3
 *   Explanation: (1,2), (2), (3) → 3 boats.
 *
 * Example 3:
 *   Input : people = [3,5,3,4], limit = 5
 *   Output: 4
 *   Explanation: (3), (3), (4), (5) → 4 boats (no two can share).
 *
 * CONSTRAINTS:
 * ------------
 * - 1 <= people.length <= 5 * 10^4
 * - 1 <= people[i] <= limit <= 3 * 10^4
 */

import java.util.Arrays;

class Solution {
    public int numRescueBoats(int[] people, int limit) {
        int res = 0;
        int left = 0;
        int right = people.length - 1;
        int sum = 0;
        Arrays.sort(people);
        while (left < right) {
            sum = people[left] + people[right];
            if (sum > limit) {
                // Heaviest person goes alone
                res = res + 1;
                right--;
            } else {
                // Lightest and heaviest share a boat
                res = res + 1;
                left++;
                right--;
            }
        }
        // One person left in the middle (odd-length array)
        if (left == right) {
            res++;
        }
        return res;
    }
}

/*
 * APPROACH: Greedy + Two Pointers
 * ---------------------------------
 *
 * KEY INSIGHT:
 * ------------
 * To minimize the number of boats, always try to pair the HEAVIEST person
 * with the LIGHTEST person. If they fit together (sum <= limit), they share
 * a boat. If not, the heaviest must go alone.
 *
 * Sorting first lets us use two pointers to always access the lightest
 * (left) and heaviest (right) person in O(1).
 *
 * HOW IT WORKS:
 * -------------
 * 1. Sort people in ascending order.
 * 2. left = 0 (lightest), right = n-1 (heaviest).
 * 3. While left < right:
 *    - If people[left] + people[right] > limit:
 *        Heaviest can't pair → goes alone → res++, right--
 *    - Else (sum <= limit):
 *        They can share → res++, left++, right--
 * 4. If left == right after the loop, one person remains → res++
 *
 * STEP-BY-STEP with people = [3,2,2,1], limit = 3:
 * --------------------------------------------------
 *   After sort: [1, 2, 2, 3]
 *
 *   left=0(1), right=3(3): 1+3=4 > 3 → boat for 3 alone, res=1, right=2
 *   left=0(1), right=2(2): 1+2=3 <= 3 → boat for (1,2), res=2, left=1, right=1
 *   left==right=1 → one person left → res=3
 *   Return 3 ✓
 *
 * STEP-BY-STEP with people = [3,5,3,4], limit = 5:
 * --------------------------------------------------
 *   After sort: [3, 3, 4, 5]
 *
 *   left=0(3), right=3(5): 3+5=8 > 5 → boat for 5 alone, res=1, right=2
 *   left=0(3), right=2(4): 3+4=7 > 5 → boat for 4 alone, res=2, right=1
 *   left=0(3), right=1(3): 3+3=6 > 5 → boat for 3 alone, res=3, right=0
 *   left=0 >= right=0 → loop ends
 *   left==right=0 → one person left → res=4
 *   Return 4 ✓
 *
 * WHY GREEDY WORKS:
 * -----------------
 * The heaviest person is the hardest to pair. Give them the best possible
 * partner (the lightest). If even the lightest can't fit with them, no one
 * can — so the heavy person must go alone. This always leads to the minimum
 * number of boats.
 *
 * Time Complexity : O(n log n) — dominated by sorting
 * Space Complexity: O(1)       — two pointers, no extra space
 */
