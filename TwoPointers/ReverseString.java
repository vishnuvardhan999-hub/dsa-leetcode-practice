/**
 * LeetCode Problem #344: Reverse String
 * Difficulty : Easy
 * Link       : https://leetcode.com/problems/reverse-string/
 * Pattern    : Two Pointers
 *
 * PROBLEM STATEMENT:
 * ------------------
 * Write a function that reverses a string.
 * The input string is given as an array of characters s.
 *
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : s = ['h','e','l','l','o']
 *   Output: ['o','l','l','e','h']
 *
 * Example 2:
 *   Input : s = ['H','a','n','n','a','h']
 *   Output: ['h','a','n','n','a','H']
 *
 * CONSTRAINTS:
 * ------------
 * - 1 <= s.length <= 10^5
 * - s[i] is a printable ASCII character
 */

class Solution {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}

/*
 * APPROACH: Two Pointers (Inward Swap)
 * --------------------------------------
 *
 * Place one pointer at the start (left) and one at the end (right).
 * Swap the characters at both pointers, then move them toward each other.
 * Repeat until they meet in the middle.
 *
 * HOW IT WORKS:
 * -------------
 *   left  starts at index 0          (first character)
 *   right starts at index length - 1 (last character)
 *
 *   Each iteration:
 *     1. Swap s[left] and s[right] using a temp variable
 *     2. left++  (move inward from left)
 *     3. right-- (move inward from right)
 *   Stop when left >= right (pointers have crossed or met)
 *
 * STEP-BY-STEP with s = ['h','e','l','l','o']:
 * ----------------------------------------------
 *   left=0, right=4 → swap 'h' and 'o' → ['o','e','l','l','h']
 *   left=1, right=3 → swap 'e' and 'l' → ['o','l','l','e','h']
 *   left=2, right=2 → left == right → loop ends
 *   Result: ['o','l','l','e','h'] ✓
 *
 * STEP-BY-STEP with s = ['H','a','n','n','a','h']:
 * --------------------------------------------------
 *   left=0, right=5 → swap 'H' and 'h' → ['h','a','n','n','a','H']
 *   left=1, right=4 → swap 'a' and 'a' → ['h','a','n','n','a','H'] (same)
 *   left=2, right=3 → swap 'n' and 'n' → ['h','a','n','n','a','H'] (same)
 *   left=3, right=2 → left > right → loop ends
 *   Result: ['h','a','n','n','a','H'] ✓
 *
 * WHY TWO POINTERS?
 * -----------------
 * Moving from both ends toward the center ensures each character is
 * swapped exactly once. Only half the array needs to be traversed.
 * A single temp variable handles the swap — no extra array needed.
 *
 * Time Complexity : O(n) — each character visited at most once
 * Space Complexity: O(1) — only one temp variable used, in-place swap
 */
