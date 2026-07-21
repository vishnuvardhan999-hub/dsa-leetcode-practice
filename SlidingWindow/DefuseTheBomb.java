/**
 * LeetCode Problem #1652: Defuse the Bomb
 * Difficulty : Easy
 * Link       : https://leetcode.com/problems/defuse-the-bomb/
 * Pattern    : Sliding Window (Fixed Size, Circular Array)
 *
 * PROBLEM STATEMENT:
 * ------------------
 * You have a circular array code of length n and a key k.
 * To decrypt the code, replace every number simultaneously:
 *
 *   - If k > 0: replace code[i] with the sum of the NEXT k numbers.
 *   - If k < 0: replace code[i] with the sum of the PREVIOUS |k| numbers.
 *   - If k == 0: replace code[i] with 0.
 *
 * Since the array is circular, the next element after code[n-1] is code[0],
 * and the previous element before code[0] is code[n-1].
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : code = [5,7,1,4], k = 3
 *   Output: [12,10,16,13]
 *   Explanation: Each number is replaced by the sum of the next 3:
 *     code[0] = 7+1+4 = 12
 *     code[1] = 1+4+5 = 10
 *     code[2] = 4+5+7 = 16
 *     code[3] = 5+7+1 = 13
 *
 * Example 2:
 *   Input : code = [1,2,3,4], k = 0
 *   Output: [0,0,0,0]
 *
 * Example 3:
 *   Input : code = [2,4,9,3], k = -2
 *   Output: [12,5,6,13]
 *   Explanation: Each number is replaced by the sum of the previous 2:
 *     code[0] = 9+3 = 12
 *     code[1] = 2+3 = 5  (wait: prev 2 of index 1 are index 0 and index 3)
 *              actually: code[3]+code[0] = 3+2 = 5
 *     code[2] = code[0]+code[1] = 2+4 = 6
 *     code[3] = code[1]+code[2] = 4+9 = 13
 *
 * CONSTRAINTS:
 * ------------
 * - n == code.length
 * - 1 <= n <= 100
 * - 1 <= code[i] <= 100
 * - -(n - 1) <= k <= n - 1
 */

import java.util.Arrays;

class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int arr[] = new int[n];
        int m = Math.abs(k);

        // k == 0: all zeros
        if (k == 0) {
            Arrays.fill(arr, 0);
            return arr;
        }

        if (k > 0) {
            // Build initial window: sum of code[1..k] (next k elements after index 0)
            int sum = 0;
            for (int i = 1; i <= k; i++) {
                sum = sum + code[i % n];
            }
            arr[0] = sum;
            // Slide: for index i, remove code[i % n] (was first in window),
            //        add code[(i + k) % n] (new last in window)
            for (int i = 1; i < n; i++) {
                sum = sum - code[i % n];
                sum = sum + code[(i + k) % n];
                arr[i] = sum;
            }
        }

        if (k < 0) {
            int sum = 0;
            k = -k;  // work with positive value
            // Build initial window: sum of code[n-1], code[n-2], ..., code[n-k]
            // (previous k elements before index 0, going backwards)
            for (int i = 1; i <= k; i++) {
                sum = sum + code[(n - i) % n];
            }
            arr[0] = sum;
            // Slide: for index i, remove code[(i - k - 1 + n) % n] (fell out of window),
            //        add code[(i - 1 + n) % n] (new element entering window)
            for (int i = 1; i < n; i++) {
                sum = sum - code[(i - k - 1 + n) % n];
                sum = sum + code[(i - 1 + n) % n];
                arr[i] = sum;
            }
        }

        return arr;
    }
}

/*
 * APPROACH: Circular Sliding Window (Fixed Size = |k|)
 * ------------------------------------------------------
 *
 * KEY INSIGHT:
 * ------------
 * The window size is always |k| — it just slides to the right (k>0) or
 * references elements to the left (k<0). All index arithmetic uses modulo
 * to wrap around the circular array.
 *
 * THREE CASES:
 * ------------
 *
 * CASE k == 0:
 *   Every element becomes 0. Fill and return immediately.
 *
 * CASE k > 0 (sum of NEXT k elements):
 *   For index 0, window = code[1], code[2], ..., code[k]  (mod n for wrap)
 *   To slide from index i to i+1:
 *     - Remove: code[i % n]          (was the first element of the window)
 *     - Add:    code[(i + k) % n]    (new last element of the window)
 *
 * CASE k < 0 (sum of PREVIOUS |k| elements):
 *   Work with k = |k| for clarity.
 *   For index 0, window = code[n-1], code[n-2], ..., code[n-k]
 *   To slide from index i to i+1:
 *     - Remove: code[(i - k - 1 + n) % n]   (element that fell out of range)
 *     - Add:    code[(i - 1 + n) % n]        (new element entering the window)
 *
 * STEP-BY-STEP with code = [5,7,1,4], k = 3 (n=4):
 * ---------------------------------------------------
 *   Initial sum = code[1]+code[2]+code[3] = 7+1+4 = 12 → arr[0]=12
 *   i=1: sum = 12 - code[1%4=1]=7 + code[(1+3)%4=0]=5 = 10 → arr[1]=10
 *   i=2: sum = 10 - code[2%4=2]=1 + code[(2+3)%4=1]=7 = 16 → arr[2]=16
 *   i=3: sum = 16 - code[3%4=3]=4 + code[(3+3)%4=2]=1 = 13 → arr[3]=13
 *   Result: [12,10,16,13] ✓
 *
 * STEP-BY-STEP with code = [2,4,9,3], k = -2 (n=4, |k|=2):
 * -----------------------------------------------------------
 *   Initial sum = code[(4-1)%4=3]+code[(4-2)%4=2] = 3+9 = 12 → arr[0]=12
 *   i=1: sum = 12 - code[(1-2-1+4)%4=2]=9 + code[(1-1+4)%4=0]=2 = 5  → arr[1]=5
 *   i=2: sum = 5  - code[(2-2-1+4)%4=3]=3 + code[(2-1+4)%4=1]=4 = 6  → arr[2]=6
 *   i=3: sum = 6  - code[(3-2-1+4)%4=0]=2 + code[(3-1+4)%4=2]=9 = 13 → arr[3]=13
 *   Result: [12,5,6,13] ✓
 *
 * NOTE: Variable 'm' is declared but unused — it can be removed.
 *
 * Time Complexity : O(n) — initial build O(|k|) + sliding O(n), overall O(n)
 * Space Complexity: O(1) — output array only, no extra data structures
 */
