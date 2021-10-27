package trees

import TreeNodes.BinaryTree.TreeNode

/**
 * 230. Kth Smallest Element in a BST
 *
 * Given the root of a binary search tree, and an integer k,
 * return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 */
fun main() {
    // Confirmed in Leetcode.
}

// Populate a Stack, interate inOrder, when n == k, return val.
fun kthSmallest(root: TreeNode?, k: Int): Int {
    // Iterative
    var n = 0
    var node = root
    val stack = ArrayDeque<TreeNode>()

    while (node != null || stack.isNotEmpty()) {
        while (node != null) {
            stack.add(node)
            node = node.left
        }
        node = stack.removeLast()
        n++
        if (n == k)
            return node.`val`
        node = node.right
    }
    return 0


    // Recursive
    /*fun inOrder(root: TreeNode?): List<Int> {
        return root?.run {
            inOrder(left)!! + listOf(`val`) + inOrder(right)!!
        } ?: emptyList()
    }
    return inOrder(root)[k - 1]*/
}