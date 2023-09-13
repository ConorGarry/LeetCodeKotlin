package strings

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 */
fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) return false // Impossible to be an anagram.
    val counts = IntArray(26)
    s.forEach { counts[it - 'a']++ }
    t.forEach { counts[it - 'a']-- }
    return counts.any { it != 0 }


    /*val charCount = mutableMapOf<Char, Int>()
    for (c in s) {
        charCount[c] = charCount.getOrDefault(c, 0) + 1
    }
    for (c in t) {
        charCount[c] = charCount.getOrDefault(c, 0) - 1
        if ((charCount[c] ?: -1) < 0) return false
    }
    return true*/

    /*val sortedS = s.toList().sorted()
    val sortedT = t.toList().sorted()
    for (i in sortedS.indices) {
        if (sortedS[i] != sortedT[i]) return false
    }
    return true*/
}

fun main() {
    ("anagram" to "nagaram").let { (s1, s2) ->
        check(isAnagram(s1, s2)) { "$s1, $s2 isAnagram should be true!" }
    }

    ("rat" to "car").let { (s1, s2) ->
        check(!isAnagram(s1, s2)) { "$s1, $s2 isAnagram should be false!" }
    }
}