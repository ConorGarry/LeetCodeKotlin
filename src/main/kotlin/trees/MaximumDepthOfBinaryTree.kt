package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along
 * the longest path from the root node down to the farthest leaf node.
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 *
 * Input: root = [1,null,2]
 * Output: 2
 *
 * Input: root = []
 * Output: 0
 *
 * Input: root = [0]
 * Output: 1
 */
fun main() {
    // Confirmed in Leetcode.
}

fun maxDepth(root: TreeNode?): Int {
    return root?.run {
        1 + Math.max(
            maxDepth(left),
            maxDepth(right)
        )
    } ?: 0
}