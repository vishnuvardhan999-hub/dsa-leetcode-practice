/**
 * LeetCode Daily Problem - June 25, 2026
 * ----------------------------------------
 * Problem #1358: Number of Substrings Containing All Three Characters
 * Difficulty : Medium
 * Link       : https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
 *
 * PROBLEM STATEMENT:
 * ------------------
 * Given a string s consisting only of characters 'a', 'b', and 'c',
 * return the number of substrings containing at least one occurrence
 * of all these characters a, b, and c.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : s = "abcabc"
 *   Output: 10
 *   Explanation: The substrings containing at least one of each character are:
 *     "abc", "abca", "abcab", "abcabc",
 *     "bca", "bcab", "bcabc",
 *     "cab", "cabc",
 *     "abc" (again)
 *
 * Example 2:
 *   Input : s = "aaacb"
 *   Output: 3
 *   Explanation: "aaacb", "aacb", "acb"
 *
 * Example 3:
 *   Input : s = "abc"
 *   Output: 1
 *
 * CONSTRAINTS:
 * ------------
 * - 3 <= s.length <= 5 * 10^4
 * - s consists only of 'a', 'b', and 'c'
 */

class Solution {
    public int numberOfSubstrings(String s) {
        int count = 0;
        int n = s.length();
        int left = 0;
        int right = 0;
        int a = 0;
        int b = 0;
        int c = 0;
        while (right < n) {
            char ch1 = s.charAt(right);
            if (ch1 == 'a') { a = a + 1; }
            if (ch1 == 'b') { b = b + 1; }
            if (ch1 == 'c') { c = c + 1; }
            while (a >= 1 && b >= 1 && c >= 1) {
                count = count + (n - right);
                char ch = s.charAt(left);
                if (ch == 'a') { a = a - 1; }
                if (ch == 'b') { b = b - 1; }
                if (ch == 'c') { c = c - 1; }
                left++;
            }
            right++;
        }
        return count;
    }
}

/*
 * APPROACH: Sliding Window (Two Pointers)
 * ----------------------------------------
 *
 * KEY INSIGHT:
 * ------------
 * Once a window [left, right] contains all three characters (a, b, c),
 * every extension of that window to the right is ALSO valid.
 * Specifically, substrings starting at 'left' and ending at right, right+1,
 * right+2, ..., n-1 all contain all three characters.
 * That gives us (n - right) valid substrings for the current left position.
 *
 * HOW IT WORKS:
 * -------------
 * Use two pointers: left and right, and three counters a, b, c.
 *
 * 1. Expand right: add s[right] to the window, update the corresponding counter.
 *
 * 2. While the window has all three characters (a>=1 && b>=1 && c>=1):
 *    - All substrings from s[left..right] to s[left..n-1] are valid.
 *      Add (n - right) to count.
 *    - Shrink from the left: remove s[left], decrement its counter, left++.
 *    - Repeat — after shrinking, the window may still have all three,
 *      meaning the new left also contributes (n - right) valid substrings.
 *
 * 3. Move right forward.
 *
 * STEP-BY-STEP with s = "abcabc" (n=6):
 * ---------------------------------------
 *   right=0 ('a'): a=1           → no valid window yet
 *   right=1 ('b'): a=1,b=1       → no valid window yet
 *   right=2 ('c'): a=1,b=1,c=1   → valid!
 *     count += (6-2) = 4  → count=4  | remove 'a' (left=0→1), a=0
 *     window broken → exit inner while
 *   right=3 ('a'): a=1,b=1,c=1   → valid!
 *     count += (6-3) = 3  → count=7  | remove 'b' (left=1→2), b=0
 *     window broken → exit inner while
 *   right=4 ('b'): a=1,b=1,c=1   → valid!
 *     count += (6-4) = 2  → count=9  | remove 'c' (left=2→3), c=0
 *     window broken → exit inner while
 *   right=5 ('c'): a=1,b=1,c=1   → valid!
 *     count += (6-5) = 1  → count=10 | remove 'a' (left=3→4), a=0
 *     window broken → exit inner while
 *   Return 10 ✓
 *
 * WHY (n - right) WORKS:
 * ----------------------
 * When the current window [left..right] already has all three characters,
 * extending it to [left..right+1], [left..right+2], ..., [left..n-1]
 * will still have all three. So there are (n - right) valid substrings
 * starting at 'left' with ending index >= right.
 *
 * Time Complexity : O(n) — each character is added and removed at most once
 * Space Complexity: O(1) — only 3 counters and 2 pointers used
 */
