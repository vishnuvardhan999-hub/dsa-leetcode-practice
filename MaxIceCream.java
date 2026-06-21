/**
 * LeetCode Daily Problem - June 21, 2026
 * ----------------------------------------
 * Problem #1833: Maximum Ice Cream Bars
 * Difficulty : Medium
 * Link       : https://leetcode.com/problems/maximum-ice-cream-bars/
 *
 * PROBLEM STATEMENT:
 * ------------------
 * It is a sweltering summer day, and a boy wants to buy some ice cream bars.
 *
 * At the store, there are n ice cream bars. You are given an array costs of
 * length n where costs[i] is the price of the i-th ice cream bar in coins.
 * The boy initially has coins coins to spend, and he wants to buy as many
 * ice cream bars as possible.
 *
 * Note: The boy can buy the ice cream bars in any order.
 *
 * Return the maximum number of ice cream bars the boy can buy with coins coins.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : costs = [1, 3, 2, 4, 1], coins = 7
 *   Output: 4
 *   Explanation: Buy bars at index 0, 1, 2, 4 → total = 1 + 3 + 2 + 1 = 7
 *
 * Example 2:
 *   Input : costs = [10, 6, 8, 7, 7, 8], coins = 5
 *   Output: 0
 *   Explanation: Not enough coins to buy any bar (cheapest costs 6).
 *
 * Example 3:
 *   Input : costs = [1, 6, 3, 1, 2, 5], coins = 20
 *   Output: 6
 *   Explanation: Buy all 6 bars → total = 1 + 6 + 3 + 1 + 2 + 5 = 18 <= 20
 *
 * CONSTRAINTS:
 * ------------
 * - costs.length == n
 * - 1 <= n <= 10^5
 * - 1 <= costs[i] <= 10^5
 * - 1 <= coins <= 10^8
 */

import java.util.Arrays;

class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int count = 0;
        int max = 0;
        int n = costs.length;
        int value = 0;
        Arrays.sort(costs);
        for (int i = 0; i < n; i++) {
            value = value + costs[i];
            if (value <= coins) {
                count = count + 1;
            } else {
                value = value - costs[i];
            }
        }
        return count;
    }
}

/*
 * APPROACH: Greedy + Sorting
 * ---------------------------
 *
 * To maximize the NUMBER of ice cream bars bought (not value),
 * always buy the cheapest ones first. This is the classic greedy insight:
 * spending less per bar leaves more coins for additional bars.
 *
 * HOW IT WORKS:
 * -------------
 * 1. Sort costs in ascending order so cheapest bars come first.
 * 2. Greedily add each bar's cost to a running total (value).
 *    - If total <= coins, we can afford it → increment count.
 *    - If total > coins, we can't afford this bar → undo the addition
 *      and stop (since all remaining bars are even more expensive).
 *
 * STEP-BY-STEP with Example costs = [1,3,2,4,1], coins = 7:
 * -----------------------------------------------------------
 *   After sort: [1, 1, 2, 3, 4]
 *
 *   i=0: value = 0+1 = 1  <= 7 → count=1
 *   i=1: value = 1+1 = 2  <= 7 → count=2
 *   i=2: value = 2+2 = 4  <= 7 → count=3
 *   i=3: value = 4+3 = 7  <= 7 → count=4
 *   i=4: value = 7+4 = 11 >  7 → undo: value=7
 *
 *   Return 4 ✓
 *
 * STEP-BY-STEP with Example costs = [10,6,8,7,7,8], coins = 5:
 * -------------------------------------------------------------
 *   After sort: [6, 7, 7, 8, 8, 10]
 *
 *   i=0: value = 0+6 = 6 > 5 → undo: value=0
 *   (remaining bars are even costlier, but loop continues)
 *
 *   Return 0 ✓
 *
 * WHY GREEDY WORKS:
 * -----------------
 * Sorting ensures we always try the cheapest option first.
 * Each coin we spend on a cheaper bar maximizes the number of additional
 * bars we can still afford. Skipping an expensive bar and trying a cheaper
 * one later is never beneficial — since the array is sorted, if we can't
 * afford bar[i], we also can't afford bar[i+1], bar[i+2], etc.
 * So we can safely break (though your code continues — both are correct).
 *
 * Note: The variables 'max' is declared but unused — it can be removed.
 *
 * Time Complexity : O(n log n) — dominated by sorting
 * Space Complexity: O(1)       — no extra space (sort is in-place)
 */
