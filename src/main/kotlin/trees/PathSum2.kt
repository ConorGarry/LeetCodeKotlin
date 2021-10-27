package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * 113. Path Sum II
 *
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf
 * paths where the sum of the node values in the path equals targetSum. Each path should
 * be returned as a list of the node values, not node references.
 * A root-to-leaf path is a path starting from the root and ending at any leaf node.
 *
 * A leaf is a node with no children.
 */
fun main() {
    // Validated in leetcode.
}

fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
    fun dfs(
        paths: MutableList<List<Int>>,
        current: MutableList<Int>,
        node: TreeNode?,
        target: IntArray,
    ) {
        if (node == null) return
        current.add(node.`val`)
        if (node.left == null && node.right == null && target[0] == node.`val`) {
            paths.add(current)
            return
        }
        dfs(paths, ArrayList(current), node.left, intArrayOf(target[0] - node.`val`))
        dfs(paths, ArrayList(current), node.right, intArrayOf(target[0] - node.`val`))
    }

    val paths = mutableListOf<List<Int>>()
    dfs(paths, mutableListOf(), root, intArrayOf(targetSum))
    return paths
}