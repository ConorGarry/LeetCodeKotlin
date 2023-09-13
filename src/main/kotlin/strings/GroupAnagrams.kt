package strings

/**
 * 49. Group Anagrams
 *
 * Given an array of strings strs, group the anagrams together.
 * You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Input: strs = [""]
 * Output: [[""]]
 *
 * Input: strs = ["a"]
 * Output: [["a"]]
 */
fun main() {
    arrayOf(
        arrayOf("eat", "tea", "tan", "ate", "nat", "bat"),
        arrayOf(""),
        arrayOf("a")
    ).forEach {
        println("$it grouped: ${groupAnagrams(it)}")
    }
}

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val map = mutableMapOf<List<Int>, MutableList<String>>()
    for (s in strs) {
        // Create char count Map for String.
        val count = IntArray(26) { 0 }

        // Iterate String and populate count IntArray by char value - 'a'.
        for (c in s) {
            count[c - 'a']++
        }
        // Update Map, append (if required) and add new String.
        map.getOrPut(count.toList()) { mutableListOf() }
            .add(s)
    }
    return map.values.toList()
}