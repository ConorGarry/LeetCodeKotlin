/**
 * 76. Minimum Window Substring
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 *
 * Input: s = "a", t = "aa"
 * Output: ""
 */
fun main() {
    listOf(
        "ADOBECODEBANC" to "ABC",
        "a" to "a",
        "a" to "aa"
    ).forEach { (input, pattern) ->
        println(minWindow(input, pattern))
    }
}

fun charCountsEqual(tMap: Map<Char, Int>, windowMap: Map<Char, Int>): Boolean {
    for (k in tMap.keys) {
        // If char is in window _and_ had a higher count.
        val charCount = tMap[k] ?: 0
        if (charCount > 0 && charCount > (windowMap[k] ?: 0)) {
            return false
        }
    }
    return true
}

fun minWindow(s: String, t: String): String {
    var minString: String? = null
    var l = 0
    var r = 0
    val patternCharMap = mutableMapOf<Char, Int>()
    val winCharMap = mutableMapOf<Char, Int>()
    for (char in t) { // Map of chars in pattern.
        patternCharMap[char] = patternCharMap.getOrDefault(char, 0) + 1
    }
    // Slide Window
    while (r < s.length) {
        val end = r++
        winCharMap[s[end]] = winCharMap.getOrDefault(s[end], 0) + 1
        while (charCountsEqual(patternCharMap, winCharMap)) {
            if (minString == null || minString.length > r - l) {
                minString = s.substring(l, r)
            }
            val start = l++
            winCharMap[s[start]] = winCharMap.getOrDefault(s[start], 0) - 1
        }
    }
    return minString ?: ""
}
