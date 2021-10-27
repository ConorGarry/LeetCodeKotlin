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

fun longestCommonPrefix(strs: Array<String>): String {
    var prefix = strs.first()
    for (i in 1 until strs.size) {
        while (!strs[i].startsWith(prefix)) {
            prefix = prefix.substring(0, prefix.length - 1)
            if (prefix.isEmpty()) break
        }
    }
    return prefix.ifEmpty { "" }
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
