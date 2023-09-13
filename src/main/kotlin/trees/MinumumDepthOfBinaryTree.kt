package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * 111. Minimum Depth of Binary Tree
 * Easy
 *
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path
 * from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 */
fun minDepth(root: TreeNode?): Int {
    return root?.run {
        when {
            left == null && right == null -> 1
            left == null -> 1 + minDepth(right)
            right == null -> 1 + minDepth(left)
            else -> 1 + Math.min(
                minDepth(left), minDepth(right)
            )
        }
    } ?: 0
}