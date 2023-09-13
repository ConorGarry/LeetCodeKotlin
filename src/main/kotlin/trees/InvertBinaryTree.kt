package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * 226. Invert Binary Tree
 *
 * Given the root of a binary tree, invert the tree, and return its root.
 */
fun main() {
    // Confirmed in Leetcode.
}

fun invertTree(root: TreeNode?): TreeNode? {
    if (root == null) return null

    invertTree(root.left)
    invertTree(root.right)

    val tmp = root.left
    root.left = root.right
    root.right = tmp
    return root
}