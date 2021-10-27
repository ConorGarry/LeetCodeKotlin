package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * Same tree
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 */
fun main() {
    // Confirmed in Leetcode.
}

private fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null && q == null) return true
    if (p == null || q == null) return false
    return (p.`val` == q.`val`) &&
            isSameTree(p.left, q.left) &&
            isSameTree(p.right, q.right)
}