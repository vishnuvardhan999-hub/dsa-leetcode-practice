/**
 * LeetCode Daily Problem - June 16, 2026
 * ----------------------------------------
 * Problem #3612: Process String with Special Operations I
 * Difficulty : Easy
 * Link       : https://leetcode.com/problems/process-string-with-special-operations-i/
 *
 * PROBLEM STATEMENT:
 * ------------------
 * You are given a string s consisting of lowercase English letters
 * and the special characters: '*', '#', and '%'.
 *
 * Build a new string result by processing s from left to right
 * using the following rules:
 *
 *   - Lowercase letter  → append it to result
 *   - '*'               → remove the last character from result (if result is non-empty)
 *   - '#'               → duplicate result and append it to itself  (result = result + result)
 *   - '%'               → reverse the current result
 *
 * Return the final string result after processing all characters in s.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : s = "abc#"
 *   Output: "abcabc"
 *   Explanation: 'a','b','c' are appended → result = "abc"
 *                '#' duplicates it        → result = "abcabc"
 *
 * Example 2:
 *   Input : s = "ab%"
 *   Output: "ba"
 *   Explanation: 'a','b' are appended → result = "ab"
 *                '%' reverses it      → result = "ba"
 *
 * Example 3:
 *   Input : s = "a*b"
 *   Output: "b"
 *   Explanation: 'a' is appended → result = "a"
 *                '*' removes last → result = ""
 *                'b' is appended  → result = "b"
 *
 * CONSTRAINTS:
 * ------------
 * - 1 <= s.length <= 100
 * - s consists of lowercase English letters and the characters '*', '#', '%'
 */

class Solution {
    public String processStr(String s) {
        String result = new String();
        int n = s.length();
        for(int i=0;i<n;i++){
            String res = "";
            char ch = s.charAt(i);
            if (ch >= 'a' && ch <= 'z'){
                result = result + ch;
            }
            if(ch == '#'){
                result = result + result;
            }
            if(ch == '%'){
                for (int j = result.length() - 1; j >= 0; j--) {
                    res += result.charAt(j);
                }
                result = res;
            }
            if(ch == '*'){
                if(!result.isEmpty()){
                    // int a = result.length() - 1;
                    // String b ="";
                    //  b = b + result.charAt(a);
                    // result = result.replace(b,"");
                    result = result.substring(0, result.length() - 1);
                }
            }
        }
        return result;
    }
}

/*
 * APPROACH: Linear Scan / Simulation
 * ------------------------------------
 *
 * The idea is simple — just simulate exactly what the problem says,
 * processing each character one at a time from left to right.
 *
 * HOW IT WORKS:
 * -------------
 * A single result string is maintained and updated based on each character:
 *
 *   1. Lowercase letter (a-z):
 *      → Directly concatenate the character to result.
 *         e.g. result = "ab", ch = 'c'  →  result = "abc"
 *
 *   2. '#' (Duplicate):
 *      → result = result + result  (doubles the string)
 *         e.g. result = "abc"  →  result = "abcabc"
 *
 *   3. '%' (Reverse):
 *      → Loop from the last index down to 0, building a new reversed string.
 *         e.g. result = "abc"  →  result = "cba"
 *
 *   4. '*' (Remove last):
 *      → If result is not empty, use substring(0, length-1) to chop the last char.
 *         e.g. result = "abc"  →  result = "ab"
 *      → The commented-out code tried result.replace(b,"") which is wrong because
 *        replace() removes ALL occurrences of that character, not just the last one.
 *        substring() is the correct fix here.
 *
 * STEP-BY-STEP with Example "abc#":
 * ----------------------------------
 *   ch='a' → result = "a"
 *   ch='b' → result = "ab"
 *   ch='c' → result = "abc"
 *   ch='#' → result = "abcabc"
 *   Output : "abcabc" ✓
 *
 * STEP-BY-STEP with Example "ab%":
 * ----------------------------------
 *   ch='a' → result = "a"
 *   ch='b' → result = "ab"
 *   ch='%' → result = "ba"
 *   Output : "ba" ✓
 *
 * Time Complexity : O(n * L) — n characters processed, each operation at most O(L)
 *                              where L is the current length of result
 * Space Complexity: O(L)     — space for the result string
 */
