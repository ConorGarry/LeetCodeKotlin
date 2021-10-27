package strings

/**
 * 647. Palindromic Substrings
 *
 * Given a string s, return the number of palindromic substrings in it.
 * A string is a palindrome when it reads the same backward as forward.
 * A substring is a contiguous sequence of characters within the string.
 *
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
fun main() {
    println("palindrom count for 'abc': ${countSubstrings("abc")}") // 3
    println("palindrom count for 'aaa': ${countSubstrings("aaa")}") // 6
}

fun countPals(str: String, left: Int, right: Int): Int {
    var count = 0
    var l = left
    var r = right
    while (l >= 0 && r <= str.length - 1 && str[l] == str[r]) {
        count++
        l--
        r++
    }
    return count
}

// Create countPals function for middle out pointer algorithm
// Run algo twice for each iteration of indices (even and odd (+1))
fun countSubstrings(s: String): Int {
    var total = 0
    for (i in s.indices) {
        total += countPals(s, i, i)
        total += countPals(s, i, i + 1)
    }
    return total
}