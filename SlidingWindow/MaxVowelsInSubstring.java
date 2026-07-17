/**
 * LeetCode Problem #1456: Maximum Number of Vowels in a Substring of Given Length
 * Difficulty : Medium
 * Link       : https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 * Pattern    : Sliding Window (Fixed Size)
 *
 * PROBLEM STATEMENT:
 * ------------------
 * Given a string s and an integer k, return the maximum number of vowel letters
 * in any substring of s with length k.
 *
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : s = "abciiidef", k = 3
 *   Output: 3
 *   Explanation: The substring "iii" contains 3 vowels.
 *
 * Example 2:
 *   Input : s = "aeiou", k = 2
 *   Output: 2
 *   Explanation: Any substring of length 2 contains 2 vowels.
 *
 * Example 3:
 *   Input : s = "leetcode", k = 3
 *   Output: 2
 *   Explanation: "lee", "eet", and "ode" each contain 2 vowels.
 *
 * CONSTRAINTS:
 * ------------
 * - 1 <= s.length <= 10^5
 * - s consists of lowercase English letters
 * - 1 <= k <= s.length
 */

class Solution {
    public int maxVowels(String s, int k) {
        int res = 0;
        int max = 0;
        if (s.length() == 0) {
            return 0;
        }
        // Build the first window [0 .. k-1]
        for (int i = 0; i < k; i++) {
            char ch = s.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                max = max + 1;
            }
        }
        int i = 0;
        int j = k - 1;
        int n = s.length();
        while (j < n) {
            res = Math.max(res, max);
            // Remove the leftmost character of the window
            char ch1 = s.charAt(i);
            if (ch1 == 'a' || ch1 == 'e' || ch1 == 'i' || ch1 == 'o' || ch1 == 'u') {
                max = max - 1;
            }
            i++;
            j++;
            // Add the new rightmost character
            if (j < n) {
                char ch = s.charAt(j);
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    max = max + 1;
                }
            }
        }
        return res;
    }
}

/*
 * APPROACH: Fixed-Size Sliding Window
 * -------------------------------------
 *
 * Since every window has exactly the same size k, we don't need to
 * recount vowels from scratch each time. Instead, slide the window one
 * step at a time: remove the leftmost character's contribution and add
 * the new rightmost character's contribution — O(1) per slide.
 *
 * HOW IT WORKS:
 * -------------
 * PHASE 1 — Build first window:
 *   Count vowels in s[0..k-1] and store in 'max'.
 *
 * PHASE 2 — Slide:
 *   i = left pointer (0), j = right pointer (k-1).
 *   While j < n:
 *     1. Update res = max(res, max)        — track global max
 *     2. If s[i] is vowel → max--          — remove outgoing char
 *     3. Advance i and j
 *     4. If j < n and s[j] is vowel → max++ — add incoming char
 *
 * STEP-BY-STEP with s = "abciiidef", k = 3:
 * -------------------------------------------
 *   First window "abc": a=vowel → max=1
 *
 *   i=0, j=2: res=1,  remove 'a'(vowel) → max=0, i=1, j=3, add 'i'(vowel) → max=1
 *   i=1, j=3: res=1,  remove 'b'(no)    → max=1, i=2, j=4, add 'i'(vowel) → max=2
 *   i=2, j=4: res=2,  remove 'c'(no)    → max=2, i=3, j=5, add 'i'(vowel) → max=3
 *   i=3, j=5: res=3,  remove 'i'(vowel) → max=2, i=4, j=6, add 'd'(no)   → max=2
 *   i=4, j=6: res=3,  remove 'i'(vowel) → max=1, i=5, j=7, add 'e'(vowel) → max=2
 *   i=5, j=7: res=3,  remove 'i'(vowel) → max=1, i=6, j=8, add 'f'(no)   → max=1
 *   i=6, j=8: res=3,  remove 'd'(no)    → max=1, i=7, j=9 → j>=n, skip
 *   Return 3 ✓
 *
 * NOTE on the commented-out lines:
 *   The original code had the window-size check (j - i + 1 == k) commented out.
 *   Since i and j are always exactly k-1 apart throughout the loop, the check
 *   is always true and was correctly removed — the logic works without it.
 *
 * Time Complexity : O(n) — initial build O(k) + sliding O(n), overall O(n)
 * Space Complexity: O(1) — only a few counters and pointers
 */
