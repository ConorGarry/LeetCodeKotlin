package trees

import TreeNodes.BinaryTree.TreeNode

/**
 *
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes
 * p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 */
fun main() {
    // Both confirmed in Leetcode.
}

fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root == null) return null

    // If both p and q are smaller than root, then LCA lies in left.
    if (p != null && p.`val` < root.`val` && q != null && q.`val` < root.`val`) {
        return lowestCommonAncestor(root.left, p, q)
    }

    // If both p and q are greater than root, then LCA lies in right.
    if (p != null && p.`val` > root.`val` && q != null && q.`val` > root.`val`) {
        return lowestCommonAncestor(root.right, p, q)
    }
    return root
}


// O(logn) (we could fail fast).
// LCA is where the split occurs between p and q.
// Iterate version is very simple!
fun lowestCommonAncestorIt(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    var node = root
    while (node != null) {
        if (p == null || q == null) return node
        node = when {
            p.`val` > node.`val` && q.`val` > node.`val` -> node.right
            p.`val` < node.`val` && q.`val` < node.`val` -> node.left
            else -> return node
        }
    }
    return null
}



