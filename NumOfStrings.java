/**
 * LeetCode Daily Problem - June 24, 2026
 * ----------------------------------------
 * Problem #1967: Number of Strings That Appear as Substrings in Word
 * Difficulty : Easy
 * Link       : https://leetcode.com/problems/number-of-strings-that-appear-as-substrings-in-word/
 *
 * PROBLEM STATEMENT:
 * ------------------
 * Given an array of strings patterns and a string word, return the number of
 * strings in patterns that exist as a substring in word.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : patterns = ["a","abc","bc","d"], word = "abc"
 *   Output: 3
 *   Explanation:
 *     "a"   is a substring of "abc" ✓
 *     "abc" is a substring of "abc" ✓
 *     "bc"  is a substring of "abc" ✓
 *     "d"   is NOT a substring of "abc" ✗
 *
 * Example 2:
 *   Input : patterns = ["a","b","c"], word = "aaaaabbbbbb"
 *   Output: 3
 *   Explanation: All three single characters appear in word.
 *
 * Example 3:
 *   Input : patterns = ["a","a","a"], word = "ab"
 *   Output: 3
 *   Explanation: Each occurrence of "a" in patterns is counted individually.
 *
 * CONSTRAINTS:
 * ------------
 * - 1 <= patterns.length <= 100
 * - 1 <= patterns[i].length <= 100
 * - 1 <= word.length <= 100
 * - patterns[i] and word consist of lowercase English letters.
 */

import java.util.*;

class Solution {
    public int numOfStrings(String[] patterns, String word) {

        // ---------------------------------------------------------------
        // APPROACH 1 (Brute Force — commented out):
        // Manually generate all substrings of word into a list,
        // then check if each pattern exists in that list.
        //
        // int count = 0;
        // HashMap<String, Integer> map = new HashMap<>();
        // ArrayList<String> list = new ArrayList<>();
        // for (int i = 0; i < word.length(); i++) {
        //     char ch = word.charAt(i);
        //     String s = "" + ch;
        //     list.add(s);
        //     String temp = s;
        //     for (int j = i + 1; j < word.length(); j++) {
        //         char ch1 = word.charAt(j);
        //         String s1 = "" + ch1;
        //         temp = temp + s1;
        //         list.add(temp);
        //     }
        // }
        // for (int i = 0; i < patterns.length; i++) {
        //     if (list.contains(patterns[i])) {
        //         count = count + 1;
        //     }
        // }
        // ---------------------------------------------------------------

        // APPROACH 2 (Optimal — used):
        // Use Java's built-in String.contains() to directly check
        // if each pattern is a substring of word.
        int count = 0;
        for (String pattern : patterns) {
            if (word.contains(pattern)) {
                count++;
            }
        }
        return count;
    }
}

/*
 * APPROACH 1 (Brute Force — commented out):
 * -------------------------------------------
 * Generate every possible substring of word using a nested loop:
 *   - Outer loop: starting index i
 *   - Inner loop: extending the substring from i to j
 * Store all substrings in a list, then check if each pattern exists in it.
 *
 * Downside: O(n²) space and time to generate substrings, plus O(m*n) for
 * list.contains() — total O(n² * m). Also, list.contains() does a linear
 * scan each time, making it slower than necessary.
 *
 * APPROACH 2 (Optimal — active):
 * --------------------------------
 * Directly use Java's built-in word.contains(pattern) for each pattern.
 * Under the hood, contains() uses an optimized character-search algorithm
 * (indexOf internally), making this clean, readable, and fast.
 *
 * HOW IT WORKS:
 * -------------
 * Loop through each pattern string:
 *   - If word.contains(pattern) is true → increment count
 *   - Otherwise skip
 * Return count at the end.
 *
 * STEP-BY-STEP with patterns = ["a","abc","bc","d"], word = "abc":
 * -----------------------------------------------------------------
 *   "a"   → "abc".contains("a")   = true  → count = 1
 *   "abc" → "abc".contains("abc") = true  → count = 2
 *   "bc"  → "abc".contains("bc")  = true  → count = 3
 *   "d"   → "abc".contains("d")   = false → count = 3
 *   Return 3 ✓
 *
 * WHY APPROACH 2 IS BETTER:
 * --------------------------
 * Approach 1 builds O(n²) substrings in memory even if most patterns
 * are short. Approach 2 skips all that — word.contains() uses Java's
 * native implementation which runs in O(n * m) total across all patterns,
 * where n = word.length and m = pattern.length. No extra space needed.
 *
 * Time Complexity : O(p * n) — p = patterns.length, n = word.length
 * Space Complexity: O(1)     — no extra data structures
 */
