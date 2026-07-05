/**
 * LeetCode Problem #125: Valid Palindrome
 * Difficulty : Easy
 * Link       : https://leetcode.com/problems/valid-palindrome/
 * Pattern    : Two Pointers
 *
 * PROBLEM STATEMENT:
 * ------------------
 * A phrase is a palindrome if, after converting all uppercase letters into
 * lowercase letters and removing all non-alphanumeric characters, it reads
 * the same forward and backward. Alphanumeric characters include letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : s = "A man, a plan, a canal: Panama"
 *   Output: true
 *   Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 * Example 2:
 *   Input : s = "race a car"
 *   Output: false
 *   Explanation: "raceacar" is not a palindrome.
 *
 * Example 3:
 *   Input : s = " "
 *   Output: true
 *   Explanation: After removing non-alphanumeric characters, s = "".
 *                An empty string reads the same forward and backward.
 *
 * CONSTRAINTS:
 * ------------
 * - 1 <= s.length <= 2 * 10^5
 * - s consists only of printable ASCII characters
 */

class Solution {
    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char ch1 = s.charAt(left);
            char ch2 = s.charAt(right);
            if (ch1 != ch2) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

/*
 * APPROACH: Clean + Two Pointers
 * --------------------------------
 *
 * STEP 1 — Preprocess:
 *   Remove all non-alphanumeric characters using regex [^a-zA-Z0-9]
 *   and convert the result to lowercase.
 *   This normalizes the string so we only compare letters and digits,
 *   case-insensitively.
 *
 * STEP 2 — Two Pointer Check:
 *   Place left at the start and right at the end.
 *   Compare characters at both pointers:
 *     - If they differ → not a palindrome, return false immediately.
 *     - If they match  → move both pointers inward (left++, right--).
 *   If all pairs match → return true.
 *
 * STEP-BY-STEP with s = "A man, a plan, a canal: Panama":
 * ---------------------------------------------------------
 *   After replaceAll + toLowerCase:
 *     s = "amanaplanacanalpanama"
 *
 *   left=0 ('a'), right=19 ('a') → match → left=1, right=18
 *   left=1 ('m'), right=18 ('m') → match → left=2, right=17
 *   left=2 ('a'), right=17 ('a') → match → ...
 *   ... all pairs match → return true ✓
 *
 * STEP-BY-STEP with s = "race a car":
 * --------------------------------------
 *   After replaceAll + toLowerCase:
 *     s = "raceacar"
 *
 *   left=0 ('r'), right=7 ('r') → match
 *   left=1 ('a'), right=6 ('a') → match
 *   left=2 ('c'), right=5 ('c') → match
 *   left=3 ('e'), right=4 ('a') → MISMATCH → return false ✓
 *
 * EDGE CASE — empty or single-char string:
 *   After cleaning, if s is "" or a single char,
 *   left >= right immediately → loop never runs → return true ✓
 *
 * WHY REGEX?
 * ----------
 * replaceAll("[^a-zA-Z0-9]", "") removes spaces, punctuation, colons,
 * and any other non-alphanumeric character in one clean line.
 * toLowerCase() handles case-insensitive comparison.
 * This keeps the two-pointer logic simple and focused.
 *
 * Time Complexity : O(n) — regex scan + two-pointer traversal, both O(n)
 * Space Complexity: O(n) — cleaned string stored as a new String object
 */
