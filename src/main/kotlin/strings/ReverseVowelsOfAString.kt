/**
 * 345. Reverse Vowels of a String
 * Given a string s, reverse only all the vowels in the string and return it.
 *
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both cases.
 *
 * Input: s = "hello"
 * Output: "holle"
 *
 * Input: s = "leetcode"
 * Output: "leotcede"
 *
 * Trick here is to recognise that the pointers won't move in a uniformed way.
 * One pointer may have to wait for the other to reach a vowel before a swap can be performed.
 */
fun main() {
    println(reverseVowels("hello"))
    println(reverseVowels("leetcode"))
}

fun reverseVowels(s: String): String {
    val arr = s.toCharArray()
    var l = 0
    var r = s.length - 1

    val vowels = setOf(
        'a', 'e', 'i', 'o', 'u',
        'A', 'E', 'I', 'O', 'U'
    )

    while (l < r) {
        when {
            vowels.contains(arr[r]) && vowels.contains(arr[l]) -> { // Both vowels.
                val tmp = arr[r]
                arr[r] = arr[l]
                arr[l] = tmp
                r--
                l++
            }
            !vowels.contains(arr[r]) -> r-- // r not a vowel
            !vowels.contains(arr[l]) -> l++ // l not a vowel
            else -> { // Neither are vowels.
                r--
                l++
            }
        }
    }
    return String(arr)
}
