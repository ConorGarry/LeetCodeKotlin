package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * 102. Binary Tree Level Order Traversal
 *
 * Given the root of a binary tree, return the level order traversal
 * of its nodes' values. (i.e., from left to right, level by level).
 */
fun main() {
    // Confirmed in Leetcode.
}

fun levelOrder(root: TreeNode?): List<List<Int>> {

    val queue = ArrayDeque(listOf(root ?: return emptyList()))
    val list = mutableListOf(listOf(root.`val`))

    while (queue.isNotEmpty()) {
        val level = mutableListOf<Int>()
        for (i in queue.indices) {
            val node = queue.removeFirst()
            node.left?.run {
                level.add(`val`)
                queue.add(this)
            }
            node.right?.run {
                level.add(`val`)
                queue.add(this)
            }
        }
        if (level.isNotEmpty())
            list.add(level)
    }
    return list
}