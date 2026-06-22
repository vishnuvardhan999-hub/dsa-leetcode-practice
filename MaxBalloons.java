/**
 * LeetCode Daily Problem - June 22, 2026
 * ----------------------------------------
 * Problem #1189: Maximum Number of Balloons
 * Difficulty : Easy
 * Link       : https://leetcode.com/problems/maximum-number-of-balloons/
 *
 * PROBLEM STATEMENT:
 * ------------------
 * Given a string text, you want to use the characters of text to form as many
 * instances of the word "balloon" as possible.
 *
 * You can use each character in text at most once.
 * Return the maximum number of instances of "balloon" that can be formed.
 *
 * NOTE: The word "balloon" requires:
 *   b → 1 time
 *   a → 1 time
 *   l → 2 times
 *   o → 2 times
 *   n → 1 time
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : text = "nlaebolko"
 *   Output: 1
 *   Explanation: b=1, a=1, l=2, o=2, n=1 → can form 1 "balloon"
 *
 * Example 2:
 *   Input : text = "loonbalxballpoon"
 *   Output: 2
 *   Explanation: b=2, a=2, l=4, o=4, n=2 → can form 2 "balloon"
 *
 * Example 3:
 *   Input : text = "leetcode"
 *   Output: 0
 *   Explanation: Missing 'b', 'a', 'n' → can't form even 1 "balloon"
 *
 * CONSTRAINTS:
 * ------------
 * - 1 <= text.length <= 10^4
 * - text consists of lower case English letters only
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfBalloons(String text) {
        HashMap<Character, Integer> map = new HashMap<>();
        int arr[] = new int[5];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char ch = entry.getKey();
            int freq = entry.getValue();
            if (ch == 'b') { arr[0] = freq / 1; }
            if (ch == 'a') { arr[1] = freq / 1; }
            if (ch == 'n') { arr[2] = freq / 1; }
            if (ch == 'o') { arr[3] = freq / 2; }
            if (ch == 'l') { arr[4] = freq / 2; }
        }
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        return min;
    }
}

/*
 * APPROACH: Frequency Count + Bottleneck (Min) Finding
 * ------------------------------------------------------
 *
 * The word "balloon" = b(1) a(1) l(2) o(2) n(1)
 * The number of times we can form it is limited by the scarcest letter.
 *
 * HOW IT WORKS:
 * -------------
 * 1. Count the frequency of every character in text using a HashMap.
 *
 * 2. For each of the 5 required letters, calculate how many "balloon"s
 *    that letter alone can support:
 *      - b, a, n → appear once per balloon  → capacity = freq / 1
 *      - l, o    → appear twice per balloon → capacity = freq / 2
 *    Store these 5 capacities in arr[0..4].
 *
 * 3. The answer is the minimum of all 5 capacities — the bottleneck letter
 *    limits how many complete "balloon"s can be formed.
 *
 * STEP-BY-STEP with text = "loonbalxballpoon":
 * ----------------------------------------------
 *   Frequency map: b=2, a=2, l=4, o=4, n=2, x=1, p=1
 *
 *   arr[0] = b: 2/1 = 2
 *   arr[1] = a: 2/1 = 2
 *   arr[2] = n: 2/1 = 2
 *   arr[3] = o: 4/2 = 2
 *   arr[4] = l: 4/2 = 2
 *
 *   min = 2 → Output: 2 ✓
 *
 * STEP-BY-STEP with text = "nlaebolko":
 * ----------------------------------------
 *   Frequency map: n=1, l=2, a=1, e=1, b=1, o=2, k=1
 *
 *   arr[0] = b: 1/1 = 1
 *   arr[1] = a: 1/1 = 1
 *   arr[2] = n: 1/1 = 1
 *   arr[3] = o: 2/2 = 1
 *   arr[4] = l: 2/2 = 1
 *
 *   min = 1 → Output: 1 ✓
 *
 * EDGE CASE — missing letter:
 *   If a required letter never appears in text, its entry won't exist
 *   in the HashMap, so arr[i] stays 0 (default int value).
 *   min will then be 0, which is the correct answer.
 *
 * Time Complexity : O(n) — one pass to build frequency map + O(1) for 5 checks
 * Space Complexity: O(1) — HashMap holds at most 26 entries (fixed alphabet)
 */
