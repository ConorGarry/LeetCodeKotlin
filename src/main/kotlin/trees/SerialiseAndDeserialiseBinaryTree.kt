package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that
 * it can be stored in a file or memory buffer, or transmitted across a network connection link to be
 * reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can
 * be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree.
 * You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 *
 *
 * Your Codec object will be instantiated and called as such:
 * var ser = Codec()
 * var deser = Codec()
 * var data = ser.serialize(longUrl)
 * var ans = deser.deserialize(data)
 */
fun main() {
    val tree = TreeNode(
        4,
        TreeNode(2, TreeNode(1), TreeNode(3)),
        TreeNode(5, TreeNode(6, TreeNode(0)), TreeNode(7))
    )
    with(Codec()) {
        val serialTree = serialize(tree)
        println("serialTree: $serialTree")
        println("deserialTree: ${deserialize(serialTree)}")
    }

    // Confirmed in leetcode.
}

// Multiple ways of doing this.
// We'll use Pre-order solution.
class Codec() {
    // Encodes a URL to a shortened URL.
    fun serialize(root: TreeNode?): String {
        val res = mutableListOf<String>()

        fun dfs(node: TreeNode?) {
            if (node == null) {
                res.add("n")
                return
            }
            res.add(node.`val`.toString())
            dfs(node.left)
            dfs(node.right)
        }
        dfs(root)
        return res.joinToString(",")
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        val nodeVals = data.split(",")
        var i = 0
        fun dfs(): TreeNode? {
            if (nodeVals[i] == "n") {
                i++
                return null
            }
            val node = TreeNode(nodeVals[i].toInt())
            i++
            node.left = dfs()
            node.right = dfs()
            return node
        }
        return dfs()
    }
}




