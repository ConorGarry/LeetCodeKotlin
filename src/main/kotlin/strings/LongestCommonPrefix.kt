import java.lang.StringBuilder

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

/**
 * Start by assuming that the first string in the array is the common prefix.
 *
 * This algorithm works by iteratively comparing the prefix with each string in the array,
 * progressively reducing the prefix until it no longer matches the beginning of a string or
 * becomes empty. This ensures that you find the longest common prefix among all the strings.
 *
 * It does this by using a while loop to compare prefix with current str character by character
 * While prefix does not match the beginning of str, keep removing the last character from prefix.
 * If at any point prefix becomes empty (meaning there's no common prefix), immediately return an empty string.
 */
fun longestCommonPrefix(strs: Array<String>): String {
    var prefix = strs.first()
    for (i in 1 until strs.size) {
        while (!strs[i].startsWith(prefix)) {
            prefix = prefix.substring(0, prefix.length - 1)
            if (prefix.isEmpty()) return prefix
        }
    }
    return prefix
}

/*
    f
    l
   o  i
   w  g
  e
 */
fun lcpWithTrie(strings: Array<String>): String {
    class TrieNode(
        val children: MutableMap<Char, TrieNode> = mutableMapOf(),
        var isWord: Boolean = false,
    )

    class Trie {
        val root = TrieNode()

        fun insert(word: String) {
            var cur = root
            for (c in word) {
                cur.children.putIfAbsent(c, TrieNode())
                cur = cur.children[c]!!
            }
            cur.isWord = true
        }

        fun mostCommon(): String {
            var cur = root
            val sb = StringBuilder()
            while (!cur.isWord && cur.children.size == 1) {
                for ((s, trieNode) in cur.children) {
                    sb.append(s)
                    cur = trieNode
                }
            }
            return sb.toString()
        }
    }

    val trie = Trie().apply {
        strings.forEach { insert(it) }
    }
    return trie.mostCommon()
}
