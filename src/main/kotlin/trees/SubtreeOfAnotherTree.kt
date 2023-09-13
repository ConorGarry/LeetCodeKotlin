package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * 572. Subtree of Another Tree
 *
 * Given the roots of two binary trees root and subRoot, return true if there is
 * a subtree of root with the same structure and node values of subRoot and false otherwise.
 *
 * A subtree of a binary tree tree is a tree that consists of a node in tree
 * and all of this node's descendants. The tree tree could also be considered as a subtree of itself.
 *
 */
fun main() {
    // Confirmed in Leetcode.
}

// Same as isSameTree problem.
private fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null && q == null) return true
    if (p == null || q == null) return false
    return p.`val` == q.`val` &&
            isSameTree(p.left, q.left) &&
            isSameTree(p.right, q.right)
}

fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
    if (subRoot == null) return true
    if (root == null) return false

    if (isSameTree(root, subRoot)) return true

    return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot)
}

