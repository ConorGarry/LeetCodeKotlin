package trees

import datastructures.TrieNode

/**
 *
 * 211. Design Add and Search Words Data Structure
 *
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Your WordDictionary object will be instantiated and called as such:
 * var obj = WordDictionary()
 * obj.addWord(word)
 * var param_2 = obj.search(word)
 */
fun main() {
    // Confirmed on leetcode.
}

class TrieNode(
    val children: MutableMap<Char, TrieNode> = mutableMapOf(),
    var isWord: Boolean = false,
)

class WordDictionary() {

    val root = TrieNode()

    fun addWord(word: String) {
        var current = root
        for (c in word) {
            current.children.putIfAbsent(c, TrieNode())
            if (current.children[c] == null) {
                current.children[c] = TrieNode()
            }
            current = current.children[c]!!
        }
        current.isWord = true
    }

    fun search(word: String): Boolean {

        fun dfs(j: Int, root: TrieNode): Boolean {
            var current = root

            for (i in j until word.length) {
                val c = word[i]
                if (c == '.') {
                    for (child in current.children.values) {
                        if (dfs(i + 1, child)) return true
                    }
                    return false
                } else {
                    if (c !in current.children) {
                        return false
                    }
                    current = current.children[c]!!
                }
            }
            return current.isWord
        }
        return dfs(0, root)
    }
}
