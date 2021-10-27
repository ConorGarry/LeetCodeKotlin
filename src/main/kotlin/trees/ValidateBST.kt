package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * 98. Validate Binary Search Tree
 *
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 */
fun main() {
    // Confirmed in Leetcode.
}

fun isValidBST(node: TreeNode): Boolean = isValid(node)

fun isValid(node: TreeNode?, min: Int? = null, max: Int? = null): Boolean {
    if (node == null) return true
    if (min != null && min >= node.`val`) return false
    else if (max != null && max < node.`val`) return false
    return isValid(node.left, min, node.`val`) &&
            isValid(node.right, node.`val`, max)
}

