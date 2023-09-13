/**
 * 1763. Longest Nice Substring
 *
 * A string s is nice if, for every letter of the alphabet that s contains,
 * it appears both in uppercase and lowercase. For example, "abABB" is nice
 * because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not
 * because 'b' appears, but 'B' does not.
 *
 * Given a string s, return the longest substring of s that is nice.
 * If there are multiple, return the substring of the earliest occurrence.
 * If there are none, return an empty string.
 *
 * Input: s = "YazaAay"
 * Output: "aAa
 *
 * Input: s = "dDzeE"
 * Output: "dD"
 *
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem
 */
fun main() {
    val input1 = "YazaAay"
    val input2 = "dDzeE"
    val input3 = "c"
    val input4 = ""
    val input5 = "ddzEE"
    val input6 = "Bb"
    val input7 = "cChH"
    listOf(input1, input2, input3, input4, input5, input6, input7).forEach {
        println("Longest nice substring for $it: ${longestNiceSubstring(it)}")
    }
}

fun longestNiceSubstring(s: String): String {
    var result = ""
    for (i in s.indices) {
        for (end in i..s.length) {
            with(s.substring(i, end)) {
                if (length > result.length && isNice()) {
                    result = this
                }
            }
        }
    }
    return result
}

// TODO:at time of writing this, LeetCode was still on Kotlin 1.4.x, meaning that
//  lowercase() and uppercase() will need to be toLowerCase() and toUpperCase() in the submissions.
//  (These were introduced in Kotlin 1.5.x).
fun String.isNice(): Boolean {
    if (length < 2) return false

    for (i in this) {
        if (i.isLowerCase() && !contains(i.uppercase()) ||
            i.isUpperCase() && !contains(i.lowercase())
        ) return false
    }
    return true
}
