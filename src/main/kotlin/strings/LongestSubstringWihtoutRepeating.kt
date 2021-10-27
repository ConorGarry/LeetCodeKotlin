/**
 * 3. Longest Substring Without Repeating Characters
 *
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Input: s = "abcabcbb"
 * Output: 3
 *
 * Input: s = "bbbbb"
 * Output: 1
 *
 * Input: s = "pwwkew"
 * Output: 3
 */
fun main() {
    listOf(
        "abcabcbb",
        "bbbbb",
        "pwwkew",
        "abcd",
        " ",
        "au"
    ).forEach {
        println("Length of longest substring $it: ${lengthOfLongestSubstring(it)}")
    }
}

fun lengthOfLongestSubstring(s: String): Int {

    // My own solution. 192 ms, faster that > 90%.
    var c = 0
    var l = 0
    for (i in 1..s.length) {
        val sub = s.substring(l, i)
        if (i - l > c && (sub.length == 1 || areCharsUnique(sub)))
            c = sub.length
        else
            l++ // Window is either too short or fails unique check. Move l pointer up one position.
    }
    return c
}

fun areCharsUnique(str: String): Boolean {
    for (i in str.indices) {
        for (j in i + 1 until str.length)
            if (str[i] == str[j])
                return false
    }
    return true
}

// Brute force O(n^2)
fun lengthOfLongestSubstringBF(s: String): Int {
    var string = ""
    for (i in 1 until s.length) {
        for (j in 0 until i) {
            val sub = s.substring(j, i)
            if (i - j > string.length && areCharsUnique(sub)) {
                string = sub
            }
        }
    }
    return string.length
}

// Map solution, seems to be the most popular in comments but
// at 208ms / faster than 75%, it's consistently slower than my solution.
fun lengthOfLongestSubstringMap(s: String): Int {
    val map = mutableMapOf<Char, Int>()
    var l = 0
    var r = 0
    var c = 0
    while (r < s.length) {
        if (map.containsKey(s[r])) {
            l = Math.max(l, map[s[r]]!! + 1)
        }
        map[s[r]] = r
        c = Math.max(c, r - l + 1)
        r++
    }
    return c
}
