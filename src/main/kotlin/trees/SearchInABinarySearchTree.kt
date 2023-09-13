package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * 700. Search in a Binary Search Tree
 * Easy
 *
 * You are given the root of a binary search tree (BST) and an integer val.
 *
 * Find the node in the BST that the node's value equals val and return the
 * subtree rooted with that node. If such a node does not exist, return null.
 *
 * This implementation of contains is an O(log n) operation in a balanced binary search tree.
 */
fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
    var current: TreeNode? = root ?: return null

    while (current != null) {
        if (current.`val` == `val`) return current

        current = if (`val` < current.`val`) {
            current.left
        } else {
            current.right
        }
    }
    return null
}

fun searchBSTRec(root: TreeNode?, `val`: Int): TreeNode? {
    if (root == null || root.`val` == `val`) return root

    if (`val` < root.`val`) {
        return searchBSTRec(root.left, `val`)
    }
    return searchBSTRec(root.right, `val`)
}