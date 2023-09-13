package datastructures

import java.lang.StringBuilder

/**
 * 208. Implement datastructures.Trie (Prefix Tree)
 *
 * Not that in the Kotlin book a datastructures.TrieNode keeps key and parent references, though these are
 * only needed for advanced tasks requiring back-tracking. Likely not required in general usage.
 */
fun main() {
    val input1 = arrayOf("flower", "flow", "flight")
    val input2 = arrayOf("dog", "racecar", "car")
    val trie = Trie().apply {
        //input1.forEach { insert(it) } // expects `fl`
        input2.forEach { insert(it) } // expects `""`
    }
    println("Most common: ${trie.mostCommonPrefix()}")
}

class TrieNode(
    val children: MutableMap<Char, TrieNode> = mutableMapOf(),
    var isWord: Boolean = false
)

class Trie {
    private val root = TrieNode()

    fun insert(word: String) {
        var cur = root
        for (c in word) {
            cur.children.putIfAbsent(c, TrieNode())
            cur = cur.children[c]!!
        }
        cur.isWord = true
    }

    fun mostCommonPrefix(): String {
        var cur = root
        val sb = StringBuilder()
        while (!cur.isWord && cur.children.size == 1) {
            for (str in cur.children) {
                sb.append(str.key)
                cur = str.value
            }
        }
        return sb.toString()
    }
}