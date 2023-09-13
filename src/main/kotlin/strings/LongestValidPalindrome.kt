/**
 * 5. Longest Palindromic Substring
 *
 * Given a string s, return the longest palindromic substring in s.
 *
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Input: s = "cbbd"
 * Output: "bb"
 *
 * Input: s = "a"
 * Output: "a"
 *
 * Input: s = "ac"
 * Output: "a"
 */
fun main() {
    println("longesstPalindrom: ${longestPalindrome("ac")}")
    println("longesstPalindrom: ${longestPalindrome("bb")}")
    println("longesstPalindrom: ${longestPalindrome("babad")}") // "bab"
}

fun longestPalindrome(s: String): String {

    fun maxStr(s: String, sum: String, left: Int, right: Int = left): String {
        var res = sum
        var l = left
        var r = right
        while (l >= 0 && r < s.length && s[l] == s[r]) {
            if (r - l + 1 > res.length)
                res = s.substring(l, r + 1)
            l--
            r++
        }
        return res
    }

    var res = ""
    for (i in s.indices) {
        val odd = maxStr(s, res, i)
        if (odd.length > res.length)
            res = odd

        val even = maxStr(s, res, i, i + 1)
        if (even.length > res.length)
            res = even
    }

    return res
}