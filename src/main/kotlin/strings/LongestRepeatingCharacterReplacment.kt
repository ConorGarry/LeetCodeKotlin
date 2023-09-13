package strings

/**
 * 424. Longest Repeating Character Replacement
 * Medium
 *
 * You are given a string s and an integer k. You can choose any character of the string and change
 * it to any other uppercase English character. You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter
 * you can get after performing the above operations.
 *
 * Neetcode notes:
 * PAY ATTENTION: limited to chars A-Z;
 *      for each capital char, check if it could create the longest repeating substr, use sliding window to optimize;
 *      check if windowlen=1 works,
 *      if yes, increment len,
 *      if not, shift window right;
 */
fun characterReplacement(s: String, k: Int): Int {
    val map = mutableMapOf<Char, Int>()
    var l = 0
    var max = 0
    for (r in s.indices) {
        map[s[r]] = map.getOrDefault(s[r], 0) + 1

        while ((r - l + 1) - map.values.maxByOrNull { it }!! > k) {
            map[s[l]] = map[s[l]]!! - 1
            l++
        }
        max = Math.max(max, r - l + 1)
    }
    return max
}