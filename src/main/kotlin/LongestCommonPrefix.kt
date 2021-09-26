/**
 * 14. Longest Common Prefix
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 */
fun main() {
    val input1 = arrayOf("flower", "flow", "flight")
    val input2 = arrayOf("dog", "racecar", "car")
    listOf(input1, input2).forEach {
        println("Longest common prefix for: ${it.toList()}: ${longestCommonPrefix(it)}")
    }
}

fun longestCommonPrefix(strs: Array<String>): String {
    var prefix = strs.first()
    for (i in 1 until strs.size) {
        while (!strs[i].startsWith(prefix)) {
            prefix = prefix.substring(0, prefix.length - 1)
            if (prefix.isEmpty()) break
        }
    }
    return if (prefix.isNotEmpty()) prefix else ""
}
