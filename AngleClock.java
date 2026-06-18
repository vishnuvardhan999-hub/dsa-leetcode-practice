/**
 * LeetCode Daily Problem - June 18, 2026
 * ----------------------------------------
 * Problem #1344: Angle Between Hands of a Clock
 * Difficulty : Medium
 * Link       : https://leetcode.com/problems/angle-between-hands-of-a-clock/
 *
 * PROBLEM STATEMENT:
 * ------------------
 * Given two numbers, hour and minutes, return the smaller angle (in degrees)
 * formed between the hour and the minute hand on a clock.
 *
 * Answers within 10^-5 of the actual value will be accepted as correct.
 *
 * EXAMPLES:
 * ---------
 * Example 1:
 *   Input : hour = 12, minutes = 30
 *   Output: 165.0
 *   Explanation:
 *     Minute hand at 30 min → 6 * 30 = 180°
 *     Hour hand at 12:30    → 30 * 12 + 0.5 * 30 = 360 + 15 = 375° → 15° (mod 360)
 *     Difference = |15 - 180| = 165°
 *     min(165, 360 - 165) = 165°
 *
 * Example 2:
 *   Input : hour = 3, minutes = 30
 *   Output: 75.0
 *   Explanation:
 *     Minute hand → 6 * 30 = 180°
 *     Hour hand   → 30 * 3 + 0.5 * 30 = 90 + 15 = 105°
 *     Difference  = |105 - 180| = 75°
 *     min(75, 285) = 75°
 *
 * Example 3:
 *   Input : hour = 3, minutes = 15
 *   Output: 7.5
 *   Explanation:
 *     Minute hand → 6 * 15 = 90°
 *     Hour hand   → 30 * 3 + 0.5 * 15 = 90 + 7.5 = 97.5°
 *     Difference  = |97.5 - 90| = 7.5°
 *     min(7.5, 352.5) = 7.5°
 *
 * CONSTRAINTS:
 * ------------
 * - 1 <= hour <= 12
 * - 0 <= minutes <= 59
 */

class Solution {
    public double angleClock(int hour, int minutes) {
        double result;
        result = (30 * hour + 0.5 * minutes) - (6 * minutes);
        double pre = Math.abs(result);
        return Math.min(pre, 360 - pre);
    }
}

/*
 * APPROACH: Math / Clock Geometry
 * --------------------------------
 *
 * A clock is a circle of 360°. We need the angle between the two hands.
 *
 * KEY FACTS:
 * ----------
 * 1. Minute hand:
 *    - Completes 360° in 60 minutes → moves 6° per minute
 *    - Angle = 6 * minutes
 *
 * 2. Hour hand:
 *    - Completes 360° in 12 hours → moves 30° per hour
 *    - BUT it also moves continuously as minutes pass:
 *      moves 0.5° per minute (30° / 60 min)
 *    - Angle = 30 * hour + 0.5 * minutes
 *
 * CALCULATION:
 * ------------
 * result = (hour hand angle) - (minute hand angle)
 *        = (30 * hour + 0.5 * minutes) - (6 * minutes)
 *
 * Take the absolute value to get the raw difference.
 * Since a clock is circular, the actual smaller angle is:
 *   min(|result|, 360 - |result|)
 *
 * STEP-BY-STEP with Example (hour=3, minutes=30):
 * ------------------------------------------------
 *   Hour hand   = 30 * 3 + 0.5 * 30 = 90 + 15 = 105°
 *   Minute hand = 6 * 30 = 180°
 *   result      = 105 - 180 = -75
 *   pre         = |-75| = 75
 *   answer      = min(75, 360 - 75) = min(75, 285) = 75° ✓
 *
 * EDGE CASE — hour = 12:
 *   30 * 12 = 360° which is the same as 0° on a clock.
 *   This works naturally because we take the difference and apply
 *   min(pre, 360 - pre), so 360° - 360° = 0° is handled correctly.
 *
 * Time Complexity : O(1) — pure arithmetic, no loops
 * Space Complexity: O(1) — no extra space used
 */
