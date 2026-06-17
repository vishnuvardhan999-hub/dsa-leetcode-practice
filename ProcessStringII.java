/**
 * LeetCode Daily Problem - June 17, 2026
 * ----------------------------------------
 * Problem #3614: Process String with Special Operations II
 * Difficulty : Hard
 * Link       : https://leetcode.com/problems/process-string-with-special-operations-ii/
 *
 * PROBLEM STATEMENT:
 * ------------------
 * You are given a string s consisting of lowercase English letters and
 * the special characters: '*', '#', and '%'.
 * You are also given an integer k.
 *
 * Build a new string result by processing s from left to right using these rules:
 *   - Lowercase letter → append it to result
 *   - '*'              → remove the last character from result (if non-empty)
 *   - '#'              → duplicate result and append to itself (result = result + result)
 *   - '%'              → reverse the current result
 *
 * Return the k-th character (0-indexed) of the final result string.
 * If k is out of bounds, return '.'.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : s = "a#b%*", k = 1
 *   Output: 'a'
 *   Explanation:
 *     'a' → "a"
 *     '#' → "aa"
 *     'b' → "aab"
 *     '%' → "baa"
 *     '*' → "ba"
 *   Final result = "ba", character at index 1 = 'a'
 *
 * Example 2:
 *   Input : s = "cd%#*#", k = 3
 *   Output: 'd'
 *   Explanation:
 *     'c' → "c"
 *     'd' → "cd"
 *     '%' → "dc"
 *     '#' → "dcdc"
 *     '*' → "dcd"
 *     '#' → "dcddcd"
 *   Final result = "dcddcd", character at index 3 = 'd'
 *
 * Example 3:
 *   Input : s = "z*#", k = 0
 *   Output: '.'
 *   Explanation:
 *     'z' → "z"
 *     '*' → ""
 *     '#' → ""
 *   Final result = "", index 0 is out of bounds → return '.'
 *
 * CONSTRAINTS:
 * ------------
 * - 1 <= s.length <= 10^5
 * - s consists of lowercase English letters and '*', '#', '%'
 * - 0 <= k <= 10^15
 * - The length of result after processing will not exceed 10^15
 */

class Solution {
    public char processStr(String s, long k) {
        long len = 0;
        // Step 1: only calculate final length
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                len++;
            } else if (ch == '*') {
                if (len > 0) len--;
            } else if (ch == '#') {
                len *= 2;
            } else if (ch == '%') {
                // length stays the same
            }
        }
        if (k >= len) return '.';

        // Step 2: walk backwards
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                len--;
                if (k == len) {
                    return ch;
                }
            } else if (ch == '#') {
                len /= 2;
                k %= len;
            } else if (ch == '*') {
                len++;
            } else if (ch == '%') {
                k = len - 1 - k;
            }
        }
        return '.';
    }
}

/*
 * APPROACH: Two-Pass Reverse Simulation (No String Construction)
 * ---------------------------------------------------------------
 *
 * The key insight is that the final result string can be up to 10^15 characters long
 * (due to '#' doubling), so we CANNOT build it in memory. Instead, we use a
 * mathematical reverse-tracing technique to find what character sits at index k.
 *
 * STEP 1 — Forward pass (calculate final length only):
 * ----------------------------------------------------
 * Walk through s left to right and just track the length of result:
 *   - Lowercase letter → len++
 *   - '*'              → len-- (if len > 0)
 *   - '#'              → len *= 2
 *   - '%'              → no length change
 *
 * If k >= final len, the index is out of bounds → return '.'
 *
 * STEP 2 — Backward pass (trace k back to its origin):
 * -----------------------------------------------------
 * Walk through s right to left, undoing each operation:
 *
 *   - Lowercase letter:
 *       This letter was appended at position (len - 1) just before len was incremented.
 *       So undo: len--. If k == len now, this is the character we want → return ch.
 *
 *   - '#' (undo doubling):
 *       Before '#', len was half. After '#', the string was [first_half | second_half].
 *       So undo: len /= 2. Now k might be in either half — since both halves are identical,
 *       just map k back into the first half: k %= len.
 *
 *   - '*' (undo deletion):
 *       '*' removed the last character, so before '*' the length was len + 1.
 *       Undo: len++. (k is unchanged — it still points to the same logical position.)
 *
 *   - '%' (undo reversal):
 *       '%' mirrors the string. Undo: k = len - 1 - k (mirror k back).
 *
 * STEP-BY-STEP with Example "a#b%*", k=1:
 * -----------------------------------------
 * Forward pass:
 *   'a' → len=1
 *   '#' → len=2
 *   'b' → len=3
 *   '%' → len=3
 *   '*' → len=2
 * k=1 < 2, so proceed.
 *
 * Backward pass (len=2, k=1):
 *   '*' → len=3,  k=1
 *   '%' → k = 3-1-1 = 1
 *   'b' → len=2,  k=1 (k != len=2, skip)
 *   '#' → len=1,  k = 1 % 1 = 0
 *   'a' → len=0,  k==len → return 'a' ✓
 *
 * Time Complexity : O(n) — two passes over s
 * Space Complexity: O(1) — no string built, only length and k tracked
 */
